package ceuilisa.mirai.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.fragments.FragmentSingleRecy;
import ceuilisa.mirai.utils.Constant;

public class SearchActivity extends WithPanelActivity {

    private ImageView back, search;
    private ViewPager mViewPager;
    private EditText mEditText;

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
        mLayoutID = R.layout.activity_search;
    }

    @Override
    void initView() {
        super.initView();
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());
        search = findViewById(R.id.search_now);
        mEditText = findViewById(R.id.edit_box);
        mViewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    void initData() {
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return FragmentSingleRecy.newInstance(i, "搜索结果");
            }

            @Override
            public int getCount() {
                return Constant.SEARCH_TYPE.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return Constant.SEARCH_TYPE[position];
            }
        });
    }
}
