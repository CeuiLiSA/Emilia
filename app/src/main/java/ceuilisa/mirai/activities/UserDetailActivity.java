package ceuilisa.mirai.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ceuilisa.mirai.R;
import ceuilisa.mirai.adapters.PlayListAdapter;
import ceuilisa.mirai.fragments.FragmentEvents;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.UserDetailResponse;
import ceuilisa.mirai.response.PlayListTitleResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Constant;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserDetailActivity extends BaseActivity{

    private int userID;
    private ImageView background;
    private CircleImageView userHead;
    private TextView userName, follow, fans;
    private ViewPager mViewPager;

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
        fans = findViewById(R.id.follow_user);
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return new FragmentEvents();
            }

            @Override
            public int getCount() {
                return 1;
            }
        });
    }

    @Override
    void initData() {
        userID = getIntent().getIntExtra("user id", 0);
        getUserDetail();
    }

    private void getUserDetail(){
        RetrofitUtil.getNodeApi().getUserDetail(userID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserDetailResponse userDetailResponse) {
                        if(userDetailResponse != null && userDetailResponse.getProfile() != null){
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

    private void setData(UserDetailResponse response){
        Glide.with(mContext).load(response.getProfile().getAvatarUrl()).into(userHead);
        Glide.with(mContext).load(response.getProfile().getBackgroundUrl()).into(background);
        userName.setText(response.getProfile().getNickname());
        follow.setText("关注：" + String.valueOf(response.getProfile().getFollows()));
        fans.setText("粉丝：" + String.valueOf(response.getProfile().getFolloweds()));
    }
}
