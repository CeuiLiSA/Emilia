package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.MainActivity;

public class FragmentRight extends BaseFragment {

    public static FragmentRight newInstance(int index) {
        Bundle args = new Bundle();
        args.putSerializable("index", index);
        FragmentRight fragment = new FragmentRight();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_right;
    }

    @Override
    View initView(View v) {

        return v;
    }

    @Override
    void initData() {

    }
}