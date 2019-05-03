package ceuilisa.mirai.fragments;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;


import java.util.List;

import ceuilisa.mirai.R;

public class FragmentViewPager extends BaseFragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private String[] mTitles;
    private List<BaseFragment> mFragments;
    private String mToolbarTitle;

    @Override
    protected void initLayout() {
        mLayoutID = R.layout.fragment_view_pager;
    }

    @Override
    View initView(View v) {
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle(mToolbarTitle);
        toolbar.setNavigationOnClickListener(view -> getActivity().finish());
        mViewPager = v.findViewById(R.id.view_pager);
        mTabLayout = v.findViewById(R.id.tab);
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        return v;
    }

    public static FragmentViewPager newInstance(List<BaseFragment> fragments, String[] titles, String toolbarTitle){
        FragmentViewPager fragmentViewPager = new FragmentViewPager();
        fragmentViewPager.setFragments(fragments);
        fragmentViewPager.setTitles(titles);
        fragmentViewPager.setToolbarTitle(toolbarTitle);
        return fragmentViewPager;
    }


    @Override
    void initData() {

    }


    public String[] getTitles() {
        return mTitles;
    }

    public void setTitles(String[] titles) {
        mTitles = titles;
    }

    public List<BaseFragment> getFragments() {
        return mFragments;
    }

    public void setFragments(List<BaseFragment> fragments) {
        mFragments = fragments;
    }

    public String getToolbarTitle() {
        return mToolbarTitle;
    }

    public void setToolbarTitle(String toolbarTitle) {
        mToolbarTitle = toolbarTitle;
    }
}
