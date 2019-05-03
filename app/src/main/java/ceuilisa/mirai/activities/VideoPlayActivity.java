package ceuilisa.mirai.activities;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import ceuilisa.mirai.R;
import ceuilisa.mirai.fragments.FragmentMvDetail;
import ceuilisa.mirai.fragments.FragmentRelatedMv;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.MvBean;
import ceuilisa.mirai.nodejs.MvPlayUrlResponse;
import ceuilisa.mirai.nodejs.UserDetailResponse;
import ceuilisa.mirai.response.MvDetail;
import ceuilisa.mirai.utils.Common;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VideoPlayActivity extends BaseActivity{

    StandardGSYVideoPlayer videoPlayer;
    OrientationUtils orientationUtils;
    private Toolbar mToolbar;
    private ProgressBar mProgressBar;
    private ImageView cover;
    private int mvID;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private static final String[] TITLES = new String[]{"详情", "相关视频"};

    @Override
    void initLayout() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        mLayoutID = R.layout.activity_video;
    }

    @Override
    void initView() {
        mvID = getIntent().getIntExtra("mv id", 0);
        videoPlayer = findViewById(R.id.video_player);
        mProgressBar = findViewById(R.id.progress_bar);
        mToolbar = findViewById(R.id.toolbar_help);
        mToolbar.setNavigationOnClickListener(v -> finish());
        //增加封面
        cover = new ImageView(this);
        cover.setScaleType(ImageView.ScaleType.CENTER_CROP);
        cover.setImageResource(R.color.colorPrimary);
        videoPlayer.setThumbImageView(cover);
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.GONE);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.GONE);
        //设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);
        orientationUtils.setEnable(true);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
                videoPlayer.startWindowFullscreen(mContext, false, false);
                Common.showToast("WTF??");
            }
        });
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mTabLayout = findViewById(R.id.tab);
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                if(i == 0) {
                    return FragmentMvDetail.newInstance(mvID);
                }else {
                    return FragmentRelatedMv.newInstance(mvID);
                }
            }

            @Override
            public int getCount() {
                return TITLES.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return TITLES[position];
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    void initData() {
         getMvUrl(mvID);
    }

    private void getMvUrl(int id){
        RetrofitUtil.getNodeApi().getMvPlayUrl(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MvPlayUrlResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MvPlayUrlResponse mvPlayUrlResponse) {
                        if(mvPlayUrlResponse != null){
                            videoPlayer.setUp(mvPlayUrlResponse.getData().getUrl(),
                                    true, " ");
                            //Glide.with(mContext).load(mMvBean.getCover()).into(cover);
                            videoPlayer.startPlayLogic();
                            mProgressBar.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mProgressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        videoPlayer.setVideoAllCallBack(null);
        super.onBackPressed();
    }
}
