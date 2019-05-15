package ceuilisa.mirai.activities;

import android.content.Intent;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
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

public abstract class WithPanelActivity extends NetWorkControlActivity {

    public Handler mHandler = new Handler();
    private MyRunnable mMyRunnable = new MyRunnable();
    private ProgressBar timeProgress;
    private NiceImageView mNiceImageView;
    private ImageView playPause, playList;
    private TextView songName, artistName;
    protected RelativeLayout rootView;

    @Override
    void initLayout() {
    }

    @Override
    void initView() {
        super.initView();
        rootView = findViewById(R.id.root_view);
        timeProgress = findViewById(R.id.time_progress);
        mNiceImageView = findViewById(R.id.now_play_image);
        playPause = findViewById(R.id.play_pause);
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mChannel != null && mChannel.getMusicList().size() != 0) {
                    if (MusicService.get().isPlayingMusic()) {
                        playPause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                        mHandler.removeCallbacksAndMessages(null);
                    } else {
                        playPause.setImageResource(R.drawable.ic_pause_black_24dp);
                        mHandler.post(mMyRunnable);
                    }
                    MusicService.get().stopOrPlay();
                }
            }
        });
        playList = findViewById(R.id.play_list);
        playList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mChannel != null && mChannel.getMusicList().size() != 0) {
                    new PlayListDialog().show(getSupportFragmentManager());
                } else {
                    Common.showToast(mContext, "无正在播放列表");
                }
            }
        });
        songName = findViewById(R.id.song_name);
        //songName.setMovementMethod(ScrollingMovementMethod.getInstance());
        artistName = findViewById(R.id.song_author);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MusicActivity.class);
                intent.putExtra("index", MusicService.get().getNowPlayIndex());
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
        setData();
    }

    public void setData(){
        if (mChannel != null && mChannel.getMusicList().size() != 0) {
            mTracksBean = mChannel.getMusicList().get(MusicService.get().getNowPlayIndex());
            if (MusicService.get().isPlayingMusic()) {
                mHandler.post(mMyRunnable);
                playPause.setImageResource(R.drawable.ic_pause_black_24dp);
            } else {
                playPause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            }
            Glide.with(mContext).load(mTracksBean.getAl().getPicUrl()).into(mNiceImageView);
            songName.setText(mTracksBean.getName());
            artistName.setText(mTracksBean.getAr().get(0).getName());
            timeProgress.setMax(mTracksBean.getDt());
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
            timeProgress.setProgress(MusicService.get().getPlayer().getCurrentPosition());
            Common.showLog("progress : " + MusicService.get().getPlayer().getCurrentPosition());
            Log.d("+++++++", "_______");
        }
    }
}
