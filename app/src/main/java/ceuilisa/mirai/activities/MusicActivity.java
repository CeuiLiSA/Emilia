package ceuilisa.mirai.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.steamcrafted.materialiconlib.MaterialIconView;

import java.text.SimpleDateFormat;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.dialogs.DownloadDialog;
import ceuilisa.mirai.interf.OnMusicComplete;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Reference;
import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;


public class MusicActivity extends BaseActivity {

    private int index;
    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;
    private TextView mTextView, mTextView2, mTextView3, mTextView4;
    private MaterialIconView lastSong, nextSong;
    private ImageView mImageView;
    private ObjectAnimator mAnimator;
    public Handler mHandler = new Handler();
    private MyRunnable mMyRunnable = new MyRunnable();
    private CircleImageView mCircleImageView;
    private SeekBar mSeekBar;
    private SimpleDateFormat mTime = new SimpleDateFormat("mm: ss");


    @Override
    void initLayout() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        mLayoutID = R.layout.activity_music;
    }

    @Override
    void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationOnClickListener(v -> finish());
        lastSong = findViewById(R.id.previous);
        lastSong.setOnClickListener(v -> {
            if (index == 0) {
                Common.showToast(mContext, "这已经是第一首歌了");
            } else {
                index = index - 1;
                refreshLayout();
            }
        });
        mFloatingActionButton = findViewById(R.id.playpausefloating);
        mFloatingActionButton.setOnClickListener(v -> {
            if (MusicService.getInstance().isPlayingMusic()) {
                mAnimator.pause();
                mHandler.removeCallbacksAndMessages(null);
                mFloatingActionButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            } else {
                mAnimator.resume();
                mHandler.post(mMyRunnable);
                mFloatingActionButton.setImageResource(R.drawable.ic_pause_black_24dp);
            }
            MusicService.getInstance().stopOrPlay();
        });
        nextSong = findViewById(R.id.next);
        nextSong.setOnClickListener(v -> {
            if (index == Reference.allSongs.size() - 1) {
                Common.showToast(mContext, "这已经是最后一首歌了");
            } else {
                index = index + 1;
                refreshLayout();
            }
        });
        mTextView = findViewById(R.id.song_title);
        mTextView2 = findViewById(R.id.song_artist);
        mTextView3 = findViewById(R.id.song_elapsed_time);
        mTextView4 = findViewById(R.id.song_duration);
        mImageView = findViewById(R.id.album_art);
        ImageView imageView = findViewById(R.id.download);
        imageView.setOnClickListener(v -> {
            DownloadDialog downloadDialog = new DownloadDialog();
            downloadDialog.setIndex(index);
            downloadDialog.show(getSupportFragmentManager(), "download");
        });
        mCircleImageView = findViewById(R.id.cover);
        mCircleImageView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, CoverDetailActivity.class);
            intent.putExtra("cover", Reference.allSongs.get(index).getAl().getPicUrl());
            startActivity(intent);
        });
        mSeekBar = findViewById(R.id.song_progress);
        mSeekBar.setProgress(0);
        SeekBar.OnSeekBarChangeListener sbLis = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                mTextView3.setText(mTime.format(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mHandler.removeCallbacksAndMessages(null);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (MusicService.getInstance().isPlayingMusic()) {
                    mHandler.post(mMyRunnable);
                }
                MusicService.getInstance().getPlayer().seekTo(mSeekBar.getProgress());
            }
        };
        mSeekBar.setOnSeekBarChangeListener(sbLis);
        mAnimator = ObjectAnimator.ofFloat(mCircleImageView, "rotation", 0f, 360.0f);
        mAnimator.setDuration(50000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setRepeatCount(-1);
    }

    @Override
    void initData() {
        index = getIntent().getIntExtra("index", 0);
        if (MusicService.getInstance().getOnMusicComplete() == null) {
            MusicService.getInstance().setOnMusicComplete(new OnMusicComplete() {
                @Override
                public void nextSong() {
                    nextSong.performClick();
                }

                @Override
                public void stop() {
                    mAnimator.pause();
                    mHandler.removeCallbacksAndMessages(null);
                    mFloatingActionButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    MusicService.getInstance().setPlaying(false);
                }
            });
        }
        refreshLayout();
    }

    private void refreshLayout() {
        Glide.with(mContext).load(Reference.allSongs.get(index).getAl().getPicUrl()).bitmapTransform(
                new BlurTransformation(mContext, 20, 2)).into(mImageView);
        Glide.with(mContext).load(Reference.allSongs.get(index).getAl().getPicUrl()).into(mCircleImageView);
        mToolbar.setTitle(Reference.allSongs.get(index).getName());
        mTextView.setText(Reference.allSongs.get(index).getName());
        mTextView2.setText(Reference.allSongs.get(index).getAr().get(0).getName());
        mTextView4.setText(mTime.format(Reference.allSongs.get(index).getDt()));
        mAnimator.start();
        mAnimator.pause();
        mSeekBar.setMax(Reference.allSongs.get(index).getDt());
        if (index != MusicService.getInstance().getNowPlayIndex()) {
            mHandler.removeCallbacksAndMessages(null);
            mSeekBar.setProgress(0);
            mTextView3.setText("00: 00");
            MusicService.getInstance().setPlaying(true);
            MusicService.getInstance().playMusic(Reference.allSongs.get(index).getId(), () -> {
                mHandler.post(mMyRunnable);
                mAnimator.resume();
            });
            MusicService.getInstance().setNowPlayIndex(index);
        } else {
            if (MusicService.getInstance().isPlayingMusic()) {
                mHandler.post(mMyRunnable);
                mAnimator.resume();
            } else {
                mSeekBar.setProgress(MusicService.getInstance().getPlayer().getCurrentPosition());
                mTextView3.setText(mTime.format(MusicService.getInstance().getPlayer().getCurrentPosition()));
            }
        }
        mFloatingActionButton.setImageResource(MusicService.getInstance().isPlayingMusic() ?
                R.drawable.ic_pause_black_24dp : R.drawable.ic_play_arrow_black_24dp);
    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            mSeekBar.setProgress(MusicService.getInstance().getPlayer().getCurrentPosition());
            mTextView3.setText(mTime.format(MusicService.getInstance().getPlayer().getCurrentPosition()));
            mHandler.postDelayed(this, 1000);
            Log.d("&&&&****", "((()(()()()");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        mAnimator.pause();
    }
}
