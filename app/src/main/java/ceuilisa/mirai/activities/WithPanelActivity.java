package ceuilisa.mirai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.othershe.library.NiceImageView;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.utils.Common;

public abstract class WithPanelActivity extends BaseActivity{

    public Handler mHandler = new Handler();
    private MyRunnable mMyRunnable = new MyRunnable();
    private ProgressBar mProgressBar;
    private RelativeLayout mRelativeLayout;
    private NiceImageView mNiceImageView;
    private TextView songName, artistName;
    abstract int getLayout();

    @Override
    void initLayout() {
        mLayoutID = getLayout();
    }

    @Override
    void initView() {
        mRelativeLayout = findViewById(R.id.root_view);
        mProgressBar = findViewById(R.id.time_progress);
        mNiceImageView = findViewById(R.id.now_play_image);
        songName = findViewById(R.id.song_name);
        artistName = findViewById(R.id.song_author);
        mRelativeLayout.setOnClickListener(new View.OnClickListener() {
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
        if(MusicService.getInstance().isPlayingMusic()) {
            Glide.with(mContext).load(MusicService.allSongs.get(
                    MusicService.getInstance().getNowPlayIndex()).getAl().getPicUrl()).into(mNiceImageView);
            songName.setText(MusicService.allSongs.get(MusicService.getInstance().getNowPlayIndex()).getName());
            artistName.setText(MusicService.allSongs.get(MusicService.getInstance().getNowPlayIndex()).getAr().get(0).getName());
            mProgressBar.setMax(MusicService.allSongs.get(MusicService.getInstance().getNowPlayIndex()).getDt());
            Common.showLog("all progress : " + MusicService.allSongs.get(MusicService.getInstance().getNowPlayIndex()).getDt());
            mHandler.post(mMyRunnable);
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
            mProgressBar.setProgress(MusicService.getInstance().getPlayer().getCurrentPosition());
            Common.showLog("progress : " + MusicService.getInstance().getPlayer().getCurrentPosition());
            Log.d("+++++++", "_______");
        }
    }
}
