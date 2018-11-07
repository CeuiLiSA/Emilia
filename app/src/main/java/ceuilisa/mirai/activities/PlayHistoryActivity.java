package ceuilisa.mirai.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import ceuilisa.mirai.R;
import ceuilisa.mirai.fragments.FragmentSingleRecy;
import ceuilisa.mirai.utils.Constant;

public class PlayHistoryActivity extends WithPanelActivity {

    private String[] data;
    private Toolbar mToolbar;
    private ViewPager mViewPager;

    @Override
    int getLayout() {
        return R.layout.activity_play_history;
    }

    @Override
    void initLayout() {
        super.initLayout();
        mLayoutID = getLayout();
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
        if(dataType.equals("听歌记录")){
            data = Constant.PLAY_HISTORY;
            mToolbar.setTitle("播放记录");
        }else if(dataType.equals("歌单分类")){
            data = Constant.PLAYLIST_TYPE;
            mToolbar.setTitle("歌单分类");
        }
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return FragmentSingleRecy.newInstance(i, dataType);
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
