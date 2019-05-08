package ceuilisa.mirai.activities;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.fragments.BaseFragment;
import ceuilisa.mirai.fragments.FragmentArtistAlbum;
import ceuilisa.mirai.fragments.FragmentArtistInfo;
import ceuilisa.mirai.fragments.FragmentHotSongs;
import ceuilisa.mirai.network.Operate;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.response.ArtistResponse;
import ceuilisa.mirai.utils.Common;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ArtistActivity extends WithPanelActivity {

    public String name;
    public int id;
    private String[] data = new String[]{"热门歌曲", "专辑", "艺人信息"};
    private Toolbar mToolbar;
    private ImageView mImageView;
    private ViewPager mViewPager;
    private BaseFragment[] mFragments;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private FragmentHotSongs fragmentHotSongs;
    private FragmentArtistInfo fragmentArtistInfo;
    private FragmentArtistAlbum mFragmentArtistAlbum;
    private ArtistResponse mArtistResponse;

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
        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        fragmentHotSongs = new FragmentHotSongs();
        mFragmentArtistAlbum = FragmentArtistAlbum.newInstance(id);
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
        Retro.getNodeApi().getArtistDetail(id, System.currentTimeMillis())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArtistResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ArtistResponse playListTitleResponse) {
                        if (playListTitleResponse != null) {
                            mArtistResponse = playListTitleResponse;
                            mToolbar.setTitle(playListTitleResponse.getArtist().getName());
                            mCollapsingToolbarLayout.setTitle(playListTitleResponse.getArtist().getName());
                            fragmentHotSongs.showHotSongs(playListTitleResponse.getHotSongs());
                            fragmentArtistInfo.showInfo(playListTitleResponse.getArtist());
                            invalidateOptionsMenu();
                            loadProgress.setVisibility(View.INVISIBLE);
                        } else {
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(mArtistResponse != null){
            if(mArtistResponse.getArtist().isFollowed()){
                getMenuInflater().inflate(R.menu.delete_artist_menu, menu);
            }else {
                getMenuInflater().inflate(R.menu.add_artist_menu, menu);
            }
        }else {
            getMenuInflater().inflate(R.menu.add_artist_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_artist) {
            if(mArtistResponse != null) {
                Operate.likeArtist(mArtistResponse.getArtist().getId(), true);
            }
            return true;
        }else if (id == R.id.action_delete_artist){
            if(mArtistResponse != null) {
                Operate.likeArtist(mArtistResponse.getArtist().getId(), false);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
