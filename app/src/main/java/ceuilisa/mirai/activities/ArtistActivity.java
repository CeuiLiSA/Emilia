package ceuilisa.mirai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.fragments.BaseFragment;
import ceuilisa.mirai.fragments.FragmentArtistAlbum;
import ceuilisa.mirai.fragments.FragmentArtistInfo;
import ceuilisa.mirai.fragments.FragmentHotSongs;
import ceuilisa.mirai.fragments.FragmentRight;
import ceuilisa.mirai.fragments.FragmentSingleRecy;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.ArtistResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Constant;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ArtistActivity extends WithPanelActivity {

    private String[] data = new String[]{"热门歌曲", "专辑", "艺人信息"};
    public String id, name;
    private Toolbar mToolbar;
    private ImageView mImageView;
    private ViewPager mViewPager;
    private BaseFragment[] mFragments;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private FragmentHotSongs fragmentHotSongs;
    private FragmentArtistInfo fragmentArtistInfo;
    private FragmentArtistAlbum mFragmentArtistAlbum;

    @Override
    boolean hasImage() {
        return false;
    }

    @Override
    boolean hasProgress() {
        return true;
    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_artist;
    }

    @Override
    void initView() {
        super.initView();
        mToolbar = findViewById(R.id.toolbar);
        mCollapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(v -> finish());
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(data.length);
        mImageView = findViewById(R.id.image);
        loadProgress = findViewById(R.id.progress);
        TabLayout tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    void initData() {
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        fragmentHotSongs = new FragmentHotSongs();
        mFragmentArtistAlbum = FragmentArtistAlbum.newInstance(Integer.valueOf(id));
        fragmentArtistInfo = new FragmentArtistInfo();
        mFragments = new BaseFragment[]{fragmentHotSongs, mFragmentArtistAlbum, fragmentArtistInfo};
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments[i];
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return data[position];
            }
        });
        getArtist();
    }

    private void getArtist() {
        RetrofitUtil.getImjadApi().getArtist(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArtistResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ArtistResponse playListTitleResponse) {
                        if(playListTitleResponse != null) {
                            mToolbar.setTitle(playListTitleResponse.getArtist().getName());
                            mCollapsingToolbarLayout.setTitle(playListTitleResponse.getArtist().getName());
                            fragmentHotSongs.showHotSongs(playListTitleResponse.getHotSongs());
                            fragmentArtistInfo.showInfo(playListTitleResponse.getArtist());
                            loadProgress.setVisibility(View.INVISIBLE);
                        }else {
                            Common.showToast("加载失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
