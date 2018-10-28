package ceuilisa.mirai.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.steamcrafted.materialiconlib.MaterialIconView;

import java.text.SimpleDateFormat;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.dialogs.DownloadDialog;
import ceuilisa.mirai.fragments.BaseFragment;
import ceuilisa.mirai.fragments.FragmentCover;
import ceuilisa.mirai.fragments.FragmentLrc;
import ceuilisa.mirai.interf.OnMusicComplete;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.IndicatorLayout;
import ceuilisa.mirai.utils.Reference;
import jp.wasabeef.glide.transformations.BlurTransformation;


public class MusicActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    public Handler mHandler = new Handler();
    public int index;
    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;
    private TextView mTextView, mTextView2, mTextView3, mTextView4;
    private MaterialIconView lastSong, nextSong;
    private ImageView mImageView;
    private MyRunnable mMyRunnable = new MyRunnable();
    private ViewPager vpPlay;
    private IndicatorLayout mIndicatorLayout;
    private BaseFragment[] mViewPagerContent;
    private SeekBar mSeekBar;
    private FragmentCover mFragmentCover;
    private FragmentLrc mFragmentLrc;
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
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_about_card_show);
        ConstraintLayout constraintLayout = findViewById(R.id.top_parent);
        constraintLayout.startAnimation(animation);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationOnClickListener(v -> finish());
        lastSong = findViewById(R.id.previous);
        lastSong.setOnClickListener(v -> lastSong());
        nextSong = findViewById(R.id.next);
        nextSong.setOnClickListener(v -> nextSong());
        mFloatingActionButton = findViewById(R.id.playpausefloating);
        mFloatingActionButton.setOnClickListener(v -> stopOrPlay());
        mTextView = findViewById(R.id.song_title);
        mTextView2 = findViewById(R.id.song_artist);
        mTextView3 = findViewById(R.id.song_elapsed_time);
        mTextView4 = findViewById(R.id.song_duration);
        mImageView = findViewById(R.id.album_art);
        ImageView download = findViewById(R.id.download);
        download.setOnClickListener(v -> {
            DownloadDialog downloadDialog = new DownloadDialog();
            downloadDialog.setIndex(index);
            downloadDialog.show(getSupportFragmentManager(), "download");
        });
        ImageView comment = findViewById(R.id.comment);
        comment.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, CommentActivity.class);
            intent.putExtra("id", String.valueOf(Reference.allSongs.get(index).getId()));
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
        vpPlay = findViewById(R.id.view_pager);
        vpPlay.addOnPageChangeListener(this);
        mFragmentCover = new FragmentCover();
        mFragmentLrc = new FragmentLrc();
        mViewPagerContent = new BaseFragment[]{mFragmentCover, mFragmentLrc};
        vpPlay.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mViewPagerContent[i];
            }

            @Override
            public int getCount() {
                return mViewPagerContent.length;
            }
        });
        mIndicatorLayout = findViewById(R.id.il_indicator);
        mIndicatorLayout.create(mViewPagerContent.length);
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
                    mFragmentCover.pauseAnimation();
                    mHandler.removeCallbacksAndMessages(null);
                    mFloatingActionButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    MusicService.getInstance().setPlaying(false);
                }
            });
        }
        refreshLayout();
    }

    private void refreshLayout() {
        if (!isDestroyed()) {
            Glide.with(mContext).load(Reference.allSongs.get(index).getAl().getPicUrl())
                    .bitmapTransform(new BlurTransformation(mContext, 15, 10)).into(mImageView);
            mToolbar.setTitle(Reference.allSongs.get(index).getName());
            mTextView.setText(Reference.allSongs.get(index).getAl().getName());
            if (Reference.allSongs.get(index).getAr().size() == 1) {
                mTextView2.setText(Reference.allSongs.get(index).getAr().get(0).getName());
            } else {
                StringBuilder artist = new StringBuilder();
                for (int i = 0; i < Reference.allSongs.get(index).getAr().size(); i++) {
                    artist.append(Reference.allSongs.get(index).getAr().get(i).getName()).append(" / ");
                }
                mTextView2.setText(artist.substring(0, artist.length() - 3));
            }
            mTextView4.setText(mTime.format(Reference.allSongs.get(index).getDt()));
            mSeekBar.setMax(Reference.allSongs.get(index).getDt());
        }
        if (index != MusicService.getInstance().getNowPlayIndex()) {
            mHandler.removeCallbacksAndMessages(null);
            mSeekBar.setProgress(0);
            mTextView3.setText("00: 00");
            MusicService.getInstance().setPlaying(true);
            MusicService.getInstance().playMusic(Reference.allSongs.get(index).getId(), () -> {
                mFragmentCover.resumeAnimation();
                mHandler.post(mMyRunnable);
            });
            MusicService.getInstance().setNowPlayIndex(index);
        } else {
            if (MusicService.getInstance().isPlayingMusic()) {
                mHandler.post(mMyRunnable);
            } else {
                mSeekBar.setProgress(MusicService.getInstance().getPlayer().getCurrentPosition());
                mTextView3.setText(mTime.format(MusicService.getInstance().getPlayer().getCurrentPosition()));
            }
        }
        mFloatingActionButton.setImageResource(MusicService.getInstance().isPlayingMusic() ?
                R.drawable.ic_pause_black_24dp : R.drawable.ic_play_arrow_black_24dp);
    }

    private void nextSong(){
        if (index == Reference.allSongs.size() - 1) {
            Common.showToast(mContext, "这已经是最后一首歌了");
        } else {
            index = index + 1;
            mFragmentCover.loadCover();
            mFragmentCover.refreshAnimation();
            mFragmentLrc.loadLyric();
            refreshLayout();
        }
    }

    private void lastSong(){
        if (index == 0) {
            Common.showToast(mContext, "这已经是第一首歌了");
        } else {
            index = index - 1;
            mFragmentCover.loadCover();
            mFragmentLrc.loadLyric();
            refreshLayout();
        }
    }

    private void stopOrPlay(){
        if (MusicService.getInstance().isPlayingMusic()) {
            mFragmentCover.pauseAnimation();
            mHandler.removeCallbacksAndMessages(null);
            mFloatingActionButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        } else {
            mFragmentCover.resumeAnimation();
            mHandler.post(mMyRunnable);
            mFloatingActionButton.setImageResource(R.drawable.ic_pause_black_24dp);
        }
        MusicService.getInstance().stopOrPlay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        mIndicatorLayout.setCurrent(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            mSeekBar.setProgress(MusicService.getInstance().getPlayer().getCurrentPosition());
            mTextView3.setText(mTime.format(MusicService.getInstance().getPlayer().getCurrentPosition()));
            if (mFragmentLrc.isHasLyric()) {
                mFragmentLrc.mLrcView.updateTime(
                        MusicService.getInstance().getPlayer().getCurrentPosition());
            }
            mHandler.postDelayed(this, 1000);
            Log.d("&&&&****", "((()(()()()");
        }
    }
}
