package ceuilisa.mirai;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

import ceuilisa.mirai.interf.MusicOperate;
import ceuilisa.mirai.interf.OnPlayComplete;
import ceuilisa.mirai.interf.OnPrepare;
import ceuilisa.mirai.network.MusicChannel;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.SingleSongResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Notifier;
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
                    mOnPlayComplete.nextSong();
                } else {
                    mOnPlayComplete.stop();
                }
            }
        });
    }


    public static void main(){

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
    public void playMusic(long id, OnPrepare onPrepare) {
        mPlayer.stop();
        Common.showLog("MusicService playMusic");
//        RetrofitUtil.getImjadApi().getSingleSong(id)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<SingleSongResponse>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Common.showLog("MusicService onSubscribe");
//                    }
//
//                    @Override
//                    public void onNext(SingleSongResponse playListTitleResponse) {
//                        Common.showLog("MusicService try");
//                        try {
//                            Common.showLog("MusicService onNext");
//                            mSingleSong = playListTitleResponse;
//                            mPlayer.reset();
//                            if (mSingleSong.getData() != null &&
//                                    mSingleSong.getData().get(0).getUrl() != null) {
//                                mPlayer.setDataSource(mSingleSong.getData().get(0).getUrl());
//                                mPlayer.prepareAsync();
//                                mPlayer.setOnPreparedListener(mp -> {
//                                    isPlaying = true;
//                                    if (onPrepare != null) {
//                                        onPrepare.updateUI();
//                                    }
//                                    mPlayer.start();
//                                    Common.showLog("MusicService mPlayer.start");
//                                    //Notifier.get().showPlay(mChannel.getMusicList().get(nowPlayIndex));
//                                });
//                            } else {
//                                isPlaying = false;
//                                Common.showToast(mContext, "歌曲链接不存在");
//                                Common.showLog("MusicService mPlayer.not start");
//                            }
//                        } catch (IOException e) {
//                            Common.showLog("MusicService 歌曲加载失败");
//                            e.printStackTrace();
//                            Common.showToast("歌曲加载失败");
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                        Common.showLog("MusicService onError");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });
        RetrofitUtil.getNodeApi().getSongUrl(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SingleSongResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Common.showLog("MusicService onSubscribe");
                    }

                    @Override
                    public void onNext(SingleSongResponse playListTitleResponse) {
                        Common.showLog("MusicService try");
                        try {

                            Notifier.get().init(get());
                            Notifier.get().showPlay(mChannel.getMusicList().get(nowPlayIndex));
                            Common.showLog("MusicService onNext");
                            mSingleSong = playListTitleResponse;
                            mPlayer.reset();
                            if (mSingleSong.getData() != null &&
                                    mSingleSong.getData().get(0).getUrl() != null) {
                                mPlayer.setDataSource(mSingleSong.getData().get(0).getUrl());
                                Common.showLog("MusicService " + mSingleSong.getData().get(0).getUrl());
                                //mPlayer.setDisplay();
                                mPlayer.prepareAsync();
                                mPlayer.setOnPreparedListener(mp -> {
                                    Common.showLog("MusicService setOnPreparedListener");
                                    isPlaying = true;
                                    if (onPrepare != null) {
                                        onPrepare.updateUI();
                                    }
                                    mp.start();
                                    Common.showLog("MusicService mPlayer.start");
                                    //
                                });
                            } else {
                                isPlaying = false;
                                Common.showToast(mContext, "歌曲链接不存在");
                                Common.showLog("MusicService mPlayer.not start");
                            }
                        } catch (IOException e) {
                            Common.showLog("MusicService 歌曲加载失败");
                            e.printStackTrace();
                            Common.showToast("歌曲加载失败");
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

    @Override
    public void stopOrPlay() {
        if (isPlaying) {
            isPlaying = false;
            mPlayer.pause();
        } else {
            isPlaying = true;
            mPlayer.start();
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
}
