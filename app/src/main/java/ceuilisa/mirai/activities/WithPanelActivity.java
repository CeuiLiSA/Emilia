package ceuilisa.mirai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.othershe.library.NiceImageView;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.dialogs.PlayListDialog;
import ceuilisa.mirai.utils.Common;

public abstract class WithPanelActivity extends NetWorkControlActivity{

    public Handler mHandler = new Handler();
    private MyRunnable mMyRunnable = new MyRunnable();
    private ProgressBar timeProgress;
    private NiceImageView mNiceImageView;
    private ImageView playPause, playList;
    private TextView songName, artistName;

    @Override
    void initLayout() {
    }

    @Override
    void initView() {
        super.initView();
        RelativeLayout relativeLayout = findViewById(R.id.root_view);
        timeProgress = findViewById(R.id.time_progress);
        mNiceImageView = findViewById(R.id.now_play_image);
        playPause = findViewById(R.id.play_pause);
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MusicService.allSongs != null) {
                    if (MusicService.getInstance().isPlayingMusic()) {
                        playPause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                        mHandler.removeCallbacksAndMessages(null);
                    }else {
                        playPause.setImageResource(R.drawable.ic_pause_black_24dp);
                        mHandler.post(mMyRunnable);
                    }
                    MusicService.getInstance().stopOrPlay();
                }
            }
        });
        playList = findViewById(R.id.play_list);
        playList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PlayListDialog().show(getSupportFragmentManager());
            }
        });
        songName = findViewById(R.id.song_name);
        artistName = findViewById(R.id.song_author);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MusicActivity.class);
                intent.putExtra("index", MusicService.getInstance().getNowPlayIndex());
                startActivity(intent);
            }
        });
    }

    @Override
    void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(MusicService.allSongs != null) {
            if (MusicService.getInstance().isPlayingMusic()) {
                mHandler.post(mMyRunnable);
                playPause.setImageResource(R.drawable.ic_pause_black_24dp);
            }else {
                playPause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            }
            Glide.with(mContext).load(MusicService.allSongs.get(
                    MusicService.getInstance().getNowPlayIndex()).getAl().getPicUrl()).into(mNiceImageView);
            songName.setText(MusicService.allSongs.get(MusicService.getInstance().getNowPlayIndex()).getName());
            artistName.setText(MusicService.allSongs.get(MusicService.getInstance().getNowPlayIndex()).getAr().get(0).getName());
            timeProgress.setMax(MusicService.allSongs.get(MusicService.getInstance().getNowPlayIndex()).getDt());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacksAndMessages(null);
    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            mHandler.postDelayed(this, 1000);
            timeProgress.setProgress(MusicService.getInstance().getPlayer().getCurrentPosition());
            Common.showLog("progress : " + MusicService.getInstance().getPlayer().getCurrentPosition());
            Log.d("+++++++", "_______");
        }
    }
}
