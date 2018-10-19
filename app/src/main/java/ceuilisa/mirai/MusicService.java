package ceuilisa.mirai;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.app.ActivityOptionsCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.activities.PlayListDetailActivity;
import ceuilisa.mirai.adapters.PlayListAdapter;
import ceuilisa.mirai.interf.MusicOperate;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.PlayListTitleResponse;
import ceuilisa.mirai.response.SingleSongResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Constant;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class MusicService extends Service implements MusicOperate {

    private boolean isPlaying = false;
    private Context mContext;
    private static volatile MusicService instance = null;
    private MediaPlayer mPlayer;

    public MusicService(){
        mPlayer = new MediaPlayer();
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
    public void playMusic(int id) {
        mPlayer.stop();
        RetrofitUtil.getAppApi().getSingleSong(String.valueOf(id))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SingleSongResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(SingleSongResponse playListTitleResponse) {
                        try {
                            mPlayer.reset();
                            if (playListTitleResponse.getData() != null &&
                                    playListTitleResponse.getData().get(0).getUrl() != null) {
                                mPlayer.setDataSource(playListTitleResponse.getData().get(0).getUrl());
                                mPlayer.prepareAsync();
                                mPlayer.setOnPreparedListener(mp -> {
                                    isPlaying = true;
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
        if(isPlaying){
            Common.showLog("停止播放");
            isPlaying = false;
            mPlayer.pause();
        }else {
            Common.showLog("继续播放");
            isPlaying = true;
            mPlayer.start();
        }
    }

    @Override
    public boolean isPlayingMusic() {
        return isPlaying;
    }
}
