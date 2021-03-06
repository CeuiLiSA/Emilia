package ceuilisa.mirai;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.text.TextUtils;

import java.io.IOException;

import ceuilisa.mirai.interf.MusicOperate;
import ceuilisa.mirai.interf.OnPlayComplete;
import ceuilisa.mirai.interf.OnPrepare;
import ceuilisa.mirai.network.MusicChannel;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.receiver.AudioFocusManager;
import ceuilisa.mirai.receiver.NoisyAudioStreamReceiver;
import ceuilisa.mirai.response.SingleSongResponse;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Local;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MusicService extends Service implements MusicOperate {

    private Context mContext;
    private MediaPlayer mPlayer;
    private SingleSongResponse mSingleSong;
    private int nowPlayIndex = -1;
    private TracksBean currentTrack;
    private boolean isPlaying = false;
    private OnPlayComplete mOnPlayComplete;
    private static volatile MusicService instance = null;
    private MusicChannel mChannel = MusicChannel.get();
    private NoisyAudioStreamReceiver noisyReceiver;
    private AudioFocusManager audioFocusManager;
    private AudioManager mAudioManager;

    private int state = 0;

    public static final int STATE_EMPTY = 1;
    public static final int STATE_PREPARING = 2;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_PAUSE = 4;

    public MusicService() {
        state = STATE_EMPTY;
        mPlayer = new MediaPlayer();
        mPlayer.setOnErrorListener((mp, what, extra) -> true);
        mPlayer.setOnCompletionListener(mediaPlayer -> {
            if (mOnPlayComplete != null) {
                if (nowPlayIndex != mChannel.getMusicList().size() - 1) {
                    int loopMode = Local.getLoopMode();
                    if (loopMode == 0) {
                        mOnPlayComplete.playNextSong();
                    } else if (loopMode == 1) {
                        mOnPlayComplete.singleLoop();
                    } else if (loopMode == 2) {
                        mOnPlayComplete.shuffle();
                    }

                } else {
                    mOnPlayComplete.stop();
                    state = STATE_PAUSE;
                }
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        instance = this;
        noisyReceiver = new NoisyAudioStreamReceiver();
        audioFocusManager = new AudioFocusManager(this);
        Common.showLog("开启了这个服务");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Common.showLog("关闭了这个服务");
    }

    public static MusicService get() {
        if (instance == null) {
            synchronized (MusicService.class) {
                if (instance == null) {
                    instance = new MusicService();
                }
            }
        }

        return instance;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void playMusic(int index, OnPrepare onPrepare) {
        if(mChannel.getMusicList().size() == 0){
            state = STATE_EMPTY;
            return;
        }
        if (nowPlayIndex == -1){ //初始化状态，nowPlay = -1, 则直接播放index的歌曲
            playPlay(index, onPrepare);
        } else {
            if(index == nowPlayIndex){//如果进来的index 和nowplayIndex 相同， 则检查歌曲ID
                if(true){
                    if(currentTrack != null) {
                        if(currentTrack.getId() == mChannel.getMusicList().get(index).getId()){
                            if(isPlaying) {
                                state = STATE_PLAYING;
                            }else {
                                state = STATE_PAUSE;
                            }
                            return;
                        }else {
                            playPlay(index, onPrepare);
                        }
                    }
                } else {
                    playPlay(index, onPrepare);
                }
            }else {
                playPlay(index, onPrepare);
            }
        }
    }

    public void forceStop(){
        isPlaying = false;
        mPlayer.pause();
        state = STATE_PAUSE;
        audioFocusManager.abandonAudioFocus();
        Common.showLog("MusicService 这里设置为暂停");
    }

    public void forcePlay(){
        isPlaying = true;
        mpStart();
        state = STATE_PLAYING;
        Common.showLog("MusicService 这里设置为正在播放");
    }

    private void mpStart(){
        if (audioFocusManager.requestAudioFocus()) {
            mPlayer.start();
            state = STATE_PLAYING;
        }
    }

    private void playPlay(int index, OnPrepare onPrepare){
        nowPlayIndex = index;
        mPlayer.stop();
        isPlaying = false;
        state = STATE_PREPARING;
        //先检查本地有没有这首歌
        if (!TextUtils.isEmpty(mChannel.getMusicList().get(nowPlayIndex).getLocalPath())) {
            try {
                mPlayer.reset();
                Common.showLog("playPlay " + mChannel.getMusicList().get(nowPlayIndex).getLocalPath());
                mPlayer.setDataSource(mChannel.getMusicList().get(nowPlayIndex).getLocalPath());
                mPlayer.prepareAsync();

                mPlayer.setOnPreparedListener(mp -> {
                    Common.showLog("MusicService setOnPreparedListener");
                    Common.showToast("播放本地音乐");
                    if (onPrepare != null) {
                        onPrepare.updateUI();
                    }
                    mpStart();
                    isPlaying = true;
                    currentTrack = mChannel.getMusicList().get(nowPlayIndex);
                });
            } catch (IOException e) {
                Common.showToast(e.toString());
                e.printStackTrace();
            }
        } else {
            isPlaying = false;
            Retro.getNodeApi().getSongUrl(mChannel.getMusicList().get(nowPlayIndex).getId())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<SingleSongResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(SingleSongResponse playListTitleResponse) {
                            try {
                                mSingleSong = playListTitleResponse;
                                mPlayer.reset();
                                if (mSingleSong.getData() != null &&
                                        mSingleSong.getData().get(0).getUrl() != null) {
                                    mPlayer.setDataSource(mSingleSong.getData().get(0).getUrl());
                                    Common.showLog("MusicService " + mSingleSong.getData().get(0).getUrl());
                                    mPlayer.prepareAsync();

                                    mPlayer.setOnPreparedListener(mp -> {
                                        Common.showLog("MusicService setOnPreparedListener");

                                        if (onPrepare != null) {
                                            onPrepare.updateUI();
                                        }
                                        mpStart();
                                        isPlaying = true;
                                    });
                                } else {
                                    isPlaying = false;
                                    Common.showToast(mContext, "歌曲链接不存在");
                                }
                                currentTrack = mChannel.getMusicList().get(nowPlayIndex);
                            } catch (IOException e) {
                                isPlaying = false;
                                e.printStackTrace();
                                Common.showToast(e.toString());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            Common.showLog("MusicService onError");
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

    @Override
    public void stopOrPlay() {
        if (isPlaying) {
            isPlaying = false;
            mPlayer.pause();
            audioFocusManager.abandonAudioFocus();
            state = STATE_PAUSE;
            Common.showLog("MusicService 这里设置为暂停");
        } else {
            isPlaying = true;
            mpStart();
            Common.showLog("MusicService 这里设置为正在播放");
        }
    }

    @Override
    public boolean isPlayingMusic() {
        return isPlaying;
    }

    public int getNowPlayIndex() {
        return nowPlayIndex;
    }

    public void setNowPlayIndex(int nowPlayIndex) {
        this.nowPlayIndex = nowPlayIndex;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public MediaPlayer getPlayer() {
        return mPlayer;
    }

    public OnPlayComplete getOnPlayComplete() {
        return mOnPlayComplete;
    }

    public void setOnPlayComplete(OnPlayComplete onPlayComplete) {
        mOnPlayComplete = onPlayComplete;
    }

    public SingleSongResponse getSingleSong() {
        return mSingleSong;
    }

    public void setSingleSong(SingleSongResponse singleSong) {
        mSingleSong = singleSong;
    }


    public void nextSongPlay(OnPrepare onPrepare) {
        if (nowPlayIndex == mChannel.getMusicList().size() - 1) {
            Common.showToast("这已经是最后一首歌啦");
        } else {
            nowPlayIndex = nowPlayIndex + 1;
            playMusic(nowPlayIndex, onPrepare);
        }
    }

    public void lastSongPlay(OnPrepare onPrepare) {
        if (nowPlayIndex == 0) {
            Common.showToast("这已经是第一首歌啦");
        } else {
            nowPlayIndex = nowPlayIndex - 1;
            playMusic(nowPlayIndex, onPrepare);
        }
    }


    public void shufflePlay(OnPrepare onPrepare) {
        nowPlayIndex = Common.getShuffleIndex();
        playMusic(nowPlayIndex, onPrepare);
    }

    public void singleLoopPlay(OnPrepare onPrepare) {
        playMusic(nowPlayIndex, onPrepare);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
