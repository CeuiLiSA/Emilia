package ceuilisa.mirai;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.interf.MusicOperate;
import ceuilisa.mirai.interf.OnPlayComplete;
import ceuilisa.mirai.interf.OnPrepare;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.SingleSongResponse;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Common;
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
    public static List<TracksBean> allSongs = null;

    public MusicService() {
        mPlayer = new MediaPlayer();
        mPlayer.setOnErrorListener((mp, what, extra) -> true);
        mPlayer.setOnCompletionListener(mediaPlayer -> {
            if (mOnPlayComplete != null) {
                if (nowPlayIndex != allSongs.size() - 1) {
                    mOnPlayComplete.nextSong();
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

    public static MusicService getInstance() {
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
    public void playMusic(int id, OnPrepare onPrepare) {
        mPlayer.stop();
        RetrofitUtil.getImjadApi().getSingleSong(String.valueOf(id))
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
                                mPlayer.prepareAsync();
                                mPlayer.setOnPreparedListener(mp -> {
                                    isPlaying = true;
                                    if (onPrepare != null) {
                                        onPrepare.updateUI();
                                    }
                                    mPlayer.start();
                                });
                            } else {
                                isPlaying = false;
                                Common.showToast(mContext, "歌曲链接不存在");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
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

    public static void setAllSongs(List<TracksBean> allSongs) {
        MusicService.allSongs = allSongs;
    }
}
