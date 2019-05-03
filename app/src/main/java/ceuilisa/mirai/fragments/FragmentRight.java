package ceuilisa.mirai.fragments;

import android.os.Bundle;
import android.view.View;

import ceuilisa.mirai.R;

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