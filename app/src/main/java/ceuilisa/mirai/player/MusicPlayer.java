package ceuilisa.mirai.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.interf.OnPrepared;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.response.SingleSongResponse;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Common;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MusicPlayer {

    private static final long TIME_UPDATE = 500L;
    private List<TracksBean> trackList = new ArrayList<>();
    private Context mContext;



    private int nowPlayIndex = -1;



    private boolean isPlaying = false;



    private MediaPlayer mPlayer;
    private Handler mHandler;
    private TracksBean nowPlaysong;

    public void init(Context context){
        mContext = context;
        mPlayer = new MediaPlayer();
        mHandler = new Handler(Looper.getMainLooper());
    }


    public void preparePlay(int index){
        if(index < 0){
            return;
        }

        if(index >= trackList.size()){
            return;
        }

        if(nowPlayIndex == -1){
            nowPlayIndex = index;
            playTrack();
            return;
        }

        if(nowPlayIndex != index){
            nowPlayIndex = index;
            playTrack();
            return;
        }else {
            Common.showToast("什么也不干");
        }
    }


//    public void setOnPlayCompleteListener(OnPrepared<TracksBean> onPrepared){
//        if(mPlayer == null){
//            return;
//        }
//
//        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                onPrepared.doSomething(trackList.get(nowPlayIndex));
//            }
//        });
//    }





    public void playTrack(){
        isPlaying = false;
        nowPlaysong = trackList.get(nowPlayIndex);
        if(!TextUtils.isEmpty(nowPlaysong.getLocalPath())){
            //从本地获取播放链接
            try {
                mPlayer.reset();
                mPlayer.setDataSource(nowPlaysong.getLocalPath());
                mPlayer.prepareAsync();
                mPlayer.setOnPreparedListener(mp -> {
                    Common.showToast("播放本地音乐");
                    mp.start();
                    isPlaying = true;
                });
            } catch (IOException e) {
                Common.showToast(e.toString());
                e.printStackTrace();
                isPlaying = false;
            }
        }else{
            //从网络获取播放链接
            Retro.getNodeApi().getSongUrl(nowPlaysong.getId())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<SingleSongResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(SingleSongResponse songResponse) {
                            try {
                                mPlayer.reset();
                                if (songResponse.getData() != null &&
                                        songResponse.getData().get(0).getUrl() != null) {
                                    mPlayer.setDataSource(songResponse.getData().get(0).getUrl());
                                    Common.showLog("MusicService " + songResponse.getData().get(0).getUrl());
                                    mPlayer.prepareAsync();
                                    mPlayer.setOnPreparedListener(mp -> {
                                        isPlaying = true;
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
                            Common.showToast(e.toString());
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }


















    private MusicPlayer() {
    }

    private static class SingletonHolder {
        private static MusicPlayer instance = new MusicPlayer();
    }

    public static MusicPlayer get() {
        return SingletonHolder.instance;
    }

    public List<TracksBean> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<TracksBean> trackList) {
        if(trackList != null) {
            this.trackList = trackList;
        }
    }

    public TracksBean getNowPlaysong() {
        return nowPlaysong;
    }

    public void setNowPlaysong(TracksBean nowPlaysong) {
        this.nowPlaysong = nowPlaysong;
    }

    public int getNowPlayIndex() {
        return nowPlayIndex;
    }

    public void setNowPlayIndex(int nowPlayIndex) {
        this.nowPlayIndex = nowPlayIndex;
    }

    public MediaPlayer getPlayer() {
        return mPlayer;
    }

    public void setPlayer(MediaPlayer player) {
        mPlayer = player;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
