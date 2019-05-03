package ceuilisa.mirai.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ceuilisa.mirai.R;
import ceuilisa.mirai.fragments.BaseFragment;
import ceuilisa.mirai.fragments.FragmentAboutUser;
import ceuilisa.mirai.fragments.FragmentEvents;
import ceuilisa.mirai.fragments.FragmentUserPlayList;
import ceuilisa.mirai.network.Operate;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.UserDetailResponse;
import ceuilisa.mirai.utils.AppBarStateChangeListener;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Local;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserDetailActivity extends BaseActivity {

    private int userID;
    private ImageView background;
    private TabLayout mTabLayout;
    private CircleImageView userHead;
    private TextView userName, follow, fans, nowFollow;
    private ViewPager mViewPager;
    private UserDetailResponse mUserDetailResponse;
    private static final String[] TITLES = new String[]{"音乐", "动态", "关于"};

    @Override

    void initLayout() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        mLayoutID = R.layout.activity_user_detail;
    }

    @Override
    void initView() {
        background = findViewById(R.id.user_background);
        userHead = findViewById(R.id.user_head);
        userName = findViewById(R.id.user_name);
        follow = findViewById(R.id.user_follow);
        Toolbar toolbar = findViewById(R.id.toolbar_help);
        toolbar.setNavigationOnClickListener(v -> finish());
        fans = findViewById(R.id.follow_user);
        nowFollow = findViewById(R.id.follow);
        nowFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUserDetailResponse.getProfile().isFollowed()){
                    Operate.starUser(mUserDetailResponse.getProfile().getUserId(), false);
                    nowFollow.setText("+ 关 注");
                }else {
                    Operate.starUser(mUserDetailResponse.getProfile().getUserId(), true);
                    nowFollow.setText("取消关注");
                }
            }
        });
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                } else if (state == State.COLLAPSED) {
                    if(mUserDetailResponse != null) {
                        toolbar.setTitle(mUserDetailResponse.getProfile().getNickname());
                    }
                } else {
                    toolbar.setTitle(" ");
                }
            }
        });
        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab);
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewPagerActivity.class);
                intent.putExtra("dataType", "关注与粉丝");
                intent.putExtra("uid", mUserDetailResponse.getProfile().getUserId());
                startActivity(intent);
            }
        });
        fans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewPagerActivity.class);
                intent.putExtra("dataType", "关注与粉丝");
                intent.putExtra("uid", mUserDetailResponse.getProfile().getUserId());
                intent.putExtra("currentPage", 1);
                startActivity(intent);
            }
        });
    }

    @Override
    void initData() {
        userID = getIntent().getIntExtra("user id", 0);
        getUserDetail();
    }

    private void getUserDetail() {
        RetrofitUtil.getNodeApi().getUserDetail(userID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserDetailResponse userDetailResponse) {
                        if (userDetailResponse != null && userDetailResponse.getProfile() != null) {
                            mUserDetailResponse = userDetailResponse;
                            setData(userDetailResponse);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Common.showToast(e.toString());
                        e.printStackTrace();
                        Common.showToast("加载失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setData(UserDetailResponse response) {
        Glide.with(mContext).load(response.getProfile().getAvatarUrl()).into(userHead);
        Glide.with(mContext).load(response.getProfile().getBackgroundUrl()).into(background);
        userName.setText(response.getProfile().getNickname());
        follow.setText("关注：" + String.valueOf(response.getProfile().getFollows()));
        fans.setText("粉丝：" + String.valueOf(response.getProfile().getFolloweds()));
        BaseFragment[] baseFragments = new BaseFragment[]{
                FragmentUserPlayList.newInstance(response.getProfile().getUserId()),
                FragmentEvents.newInstance(response.getProfile().getUserId()),
                FragmentAboutUser.newInstance(response)};

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return baseFragments[i];
            }

            @Override
            public int getCount() {
                return baseFragments.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return TITLES[position];
            }
        });
        nowFollow.setText(response.getProfile().isFollowed() ? "取消关注" : "+ 关 注");
        if (response.getProfile().getUserId() == Local.getUser().getProfile().getUserId()) {
            nowFollow.setVisibility(View.GONE);
        }else {
            nowFollow.setVisibility(View.VISIBLE);
        }
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
