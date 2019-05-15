package ceuilisa.mirai.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import ceuilisa.mirai.R;
import ceuilisa.mirai.fragments.BaseFragment;
import ceuilisa.mirai.fragments.FragmentFavorAlbum;
import ceuilisa.mirai.fragments.FragmentFavorArtist;
import ceuilisa.mirai.fragments.FragmentFollow;
import ceuilisa.mirai.fragments.FragmentFollowers;
import ceuilisa.mirai.fragments.FragmentMvRank;
import ceuilisa.mirai.fragments.FragmentMvRecmd;
import ceuilisa.mirai.fragments.FragmentNewSong;
import ceuilisa.mirai.fragments.FragmentPlayAllHistory;
import ceuilisa.mirai.fragments.FragmentPlayWeekHistory;
import ceuilisa.mirai.fragments.FragmentPlaylistTag;
import ceuilisa.mirai.fragments.FragmentSingleRecy;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.BannerResponse;
import ceuilisa.mirai.nodejs.PlaylistTagResponse;
import ceuilisa.mirai.utils.Constant;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ViewPagerActivity extends WithPanelActivity {

    private String[] data;
    private Toolbar mToolbar;
    private ViewPager mViewPager;

    @Override
    boolean hasImage() {
        return false;
    }

    @Override
    boolean hasProgress() {
        return false;
    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_play_history;
    }

    @Override
    void initView() {
        super.initView();
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationOnClickListener(v -> finish());
        mViewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    void initData() {
        String dataType = getIntent().getStringExtra("dataType");
        if (dataType.equals("听歌记录")) {
            data = Constant.PLAY_HISTORY;
            mToolbar.setTitle("播放记录");
            BaseFragment[] baseFragments = new BaseFragment[]{new FragmentPlayWeekHistory(), new FragmentPlayAllHistory()};
            mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int i) {
                    return baseFragments[i];
                }

                @Override
                public int getCount() {
                    return data.length;
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    return data[position];
                }
            });
        } else if (dataType.equals("歌单分类")) {
            getPlaylistTag();
        } else if (dataType.equals("新歌速递")) {
            data = Constant.PLAYLIST_NEW_SONG;
            mToolbar.setTitle("新歌速递");
            mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int i) {
                    return FragmentNewSong.newInstance(i);
                }

                @Override
                public int getCount() {
                    return data.length;
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    return data[position];
                }
            });
        } else if (dataType.equals("关注与粉丝")) {
            data = Constant.FOLLOW_AND_FOLLOWERS;
            int uid = getIntent().getIntExtra("uid", 0);
            mToolbar.setTitle("TA的好友");
            mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int i) {
                    if (i == 0) {
                        return FragmentFollow.newInstance(uid);
                    } else {
                        return FragmentFollowers.newInstance(uid);
                    }
                }

                @Override
                public int getCount() {
                    return data.length;
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    return data[position];
                }
            });
            int currentPage = getIntent().getIntExtra("currentPage", 0);
            mViewPager.setCurrentItem(currentPage);
        } else if (dataType.equals("推荐mv")) {
            data = Constant.FOLLOW_HOT_MV;
            mToolbar.setTitle("MV");
            mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int i) {
                    if (i == 0) {
                        return new FragmentMvRecmd();
                    } else {
                        return new FragmentMvRank();
                    }
                }

                @Override
                public int getCount() {
                    return data.length;
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    return data[position];
                }
            });
            int currentPage = getIntent().getIntExtra("currentPage", 0);
            mViewPager.setCurrentItem(currentPage);
        }else if (dataType.equals("陈列室")) {
            data = Constant.FOLLOW_FAVOR;
            mToolbar.setTitle("我的收藏");
            mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int i) {
                    if (i == 0) {
                        return new FragmentFavorArtist();
                    } else {
                        return new FragmentFavorAlbum();
                    }
                }

                @Override
                public int getCount() {
                    return data.length;
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    return data[position];
                }
            });
        }

    }




    private void getPlaylistTag(){
        Retro.getNodeApi().getPlaylistTag()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlaylistTagResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PlaylistTagResponse bannerResponse) {
                        mToolbar.setTitle("歌单分类");
                        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                            @Override
                            public Fragment getItem(int i) {
                                return FragmentPlaylistTag.newInstance(bannerResponse, i);
                            }

                            @Override
                            public int getCount() {
                                return 5;
                            }

                            @Override
                            public CharSequence getPageTitle(int position) {
                                if(position == 0) {
                                    return bannerResponse.getCategories().get_$0();
                                }else if(position == 1) {
                                    return bannerResponse.getCategories().get_$1();
                                }else if(position == 2) {
                                    return bannerResponse.getCategories().get_$2();
                                }else if(position == 3) {
                                    return bannerResponse.getCategories().get_$3();
                                }else {
                                    return bannerResponse.getCategories().get_$4();
                                }
                            }
                        });
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
