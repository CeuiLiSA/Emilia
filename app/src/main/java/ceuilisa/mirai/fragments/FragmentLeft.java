package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.TemplateFragmentActivity;
import ceuilisa.mirai.activities.ViewPagerActivity;
import ceuilisa.mirai.network.Listen;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.BannerResponse;
import ceuilisa.mirai.utils.DensityUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FragmentLeft extends BaseFragment {

    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_left;
    }

    @Override
    View initView(View v) {
        TextView textView = v.findViewById(R.id.textView7);
        textView.setOnClickListener(v12 -> {
            Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
            intent.putExtra("dataType", "推荐歌单");
            mContext.startActivity(intent);
        });

        TextView textView6 = v.findViewById(R.id.textView6);
        textView6.setOnClickListener(v12 -> {
            Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
            intent.putExtra("dataType", "我的歌单");
            mContext.startActivity(intent);
        });
        TextView textView2 = v.findViewById(R.id.textView2);
        textView2.setOnClickListener(v12 -> {
            Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
            intent.putExtra("dataType", "每日推荐");
            mContext.startActivity(intent);
        });
        TextView textView3 = v.findViewById(R.id.textView3);
        textView3.setOnClickListener(v12 -> {

            Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
            intent.putExtra("dataType", "本地音乐");
            mContext.startActivity(intent);
        });
        TextView textView4 = v.findViewById(R.id.textView4);
        textView4.setOnClickListener(v12 -> {
            Intent intent = new Intent(mContext, ViewPagerActivity.class);
            intent.putExtra("dataType", "新歌速递");
            mContext.startActivity(intent);
        });
        TextView textView5 = v.findViewById(R.id.textView5);
        textView5.setOnClickListener(v12 -> {
            Intent intent = new Intent(mContext, ViewPagerActivity.class);
            intent.putExtra("dataType", "歌单分类");
            mContext.startActivity(intent);
        });
        mViewPager = v.findViewById(R.id.view_pager);
        int width = mContext.getResources().getDisplayMetrics().widthPixels;
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(width, 842 * width / 2162);
        mViewPager.setLayoutParams(layoutParams);
        return v;
    }

    @Override
    void initData() {
        getBanner();
    }

    private void getBanner(){
        Retro.getNodeApi().getBanner()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Listen<BannerResponse>() {
                    @Override
                    public void success(BannerResponse bannerResponse) {
                        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
                            @Override
                            public Fragment getItem(int i) {
                                return FragmentBanner.newInstance(bannerResponse.getBanners().get(i));
                            }

                            @Override
                            public int getCount() {
                                return bannerResponse.getBanners().size();
                            }
                        });
                    }

                    @Override
                    public void error() {

                    }
                });
    }
}