package ceuilisa.mirai.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import ceuilisa.mirai.R;

public class FragmentExplore extends BaseFragment{

    private RecyclerView mRecyclerView;

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_explore;
    }

    @Override
    View initView(View v) {

        return v;
    }

    @Override
    void initData() {

    }
}
