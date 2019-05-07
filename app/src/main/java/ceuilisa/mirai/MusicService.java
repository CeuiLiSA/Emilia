package ceuilisa.mirai;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.text.TextUtils;

import java.io.IOException;

import ceuilisa.mirai.interf.MusicOperate;
import ceuilisa.mirai.interf.OnPlayComplete;
import ceuilisa.mirai.interf.OnPrepare;
import ceuilisa.mirai.network.MusicChannel;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.SingleSongResponse;
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
    private boolean isPlaying = false;
    private OnPlayComplete mOnPlayComplete;
    private static volatile MusicService instance = null;
    private MusicChannel mChannel = MusicChannel.get();

    public MusicService() {
        mPlayer = new MediaPlayer();
        mPlayer.setOnErrorListener((mp, what, extra) -> true);
        mPlayer.setOnCompletionListener(mediaPlayer -> {
            if (mOnPlayComplete != null) {
                if (nowPlayIndex != mChannel.getMusicList().size() - 1) {
                    int loopMode = Local.getLoopMode();
                    if(loopMode == 0){
                        mOnPlayComplete.playNextSong();
                    }else if(loopMode == 1){
                        mOnPlayComplete.singleLoop();
                    }else if(loopMode == 2){
                        mOnPlayComplete.shuffle();
                    }

                } else {
                    mOnPlayComplete.stop();
                }
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        instance = this;
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
        if(index == nowPlayIndex){
            if(mSingleSong != null){
                if(mSingleSong.getData().get(0).getId() == mChannel.getMusicList().get(nowPlayIndex).getId()){
                    return;
                }
            }else {
                    return;
                }
            }else {
                nowPlayIndex = index;
                mPlayer.stop();


                if (!TextUtils.isEmpty(mChannel.getMusicList().get(index).getLocalPath())) {
                    try {
                        mPlayer.setDataSource(mChannel.getMusicList().get(index).getLocalPath());
                        mPlayer.prepareAsync();
                        mPlayer.setOnPreparedListener(mp -> {
                            Common.showLog("MusicService setOnPreparedListener");
                            Common.showToast("播放本地音乐");
                            isPlaying = true;
                            if (onPrepare != null) {
                                onPrepare.updateUI();
                            }
                            mp.start();
                        });
                    } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                RetrofitUtil.getNodeApi().getSongUrl(mChannel.getMusicList().get(nowPlayIndex).getId())
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
                                            isPlaying = true;
                                            if (onPrepare != null) {
                                                onPrepare.updateUI();
                                            }
                                            mp.start();
                                        });
                                    } else {
                                        isPlaying = false;
                                        Common.showToast(mContext, "歌曲链接不存在");
                                    }
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
    }

    @Override
    public void stopOrPlay() {
        if (isPlaying) {
            isPlaying = false;
            mPlayer.pause();
            Common.showLog("MusicService 这里设置为暂停");
        } else {
            isPlaying = true;
            mPlayer.start();
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


    public void nextSongPlay(OnPrepare onPrepare){
        if(nowPlayIndex == mChannel.getMusicList().size() - 1){
            Common.showToast("这已经是最后一首歌啦");
        }else {
            nowPlayIndex = nowPlayIndex + 1;
            playMusic(nowPlayIndex, onPrepare);
        }
    }

    public void lastSongPlay(OnPrepare onPrepare){
        if(nowPlayIndex == 0){
            Common.showToast("这已经是第一首歌啦");
        }else {
            nowPlayIndex = nowPlayIndex - 1;
            playMusic(nowPlayIndex, onPrepare);
        }
    }


    public void shufflePlay(OnPrepare onPrepare){
        nowPlayIndex = Common.getShuffleIndex();
        playMusic(nowPlayIndex, onPrepare);
    }

    public void singleLoopPlay(OnPrepare onPrepare){
        playMusic(nowPlayIndex, onPrepare);
    }
}
