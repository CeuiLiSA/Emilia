package ceuilisa.mirai.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import ceuilisa.mirai.R;
import ceuilisa.mirai.fragments.FragmentSingleRecy;

public class PlayHistoryActivity extends BaseActivity {

    private static final String[] TITLES = new String[]{"最近一周", "所有时间"};

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_play_history;
    }

    @Override
    void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return FragmentSingleRecy.newInstance(i);
            }

            @Override
            public int getCount() {
                return TITLES.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return TITLES[position];
            }
        });
        TabLayout tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    void initData() {

    }
}
