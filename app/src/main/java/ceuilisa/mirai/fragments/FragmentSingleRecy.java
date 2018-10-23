package ceuilisa.mirai.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ceuilisa.mirai.R;

public class FragmentSingleRecy extends BaseFragment{

    public static FragmentSingleRecy newInstance(int index) {
        Bundle args = new Bundle();
        args.putSerializable("index", index);
        FragmentSingleRecy fragment = new FragmentSingleRecy();
        fragment.setArguments(args);
        return fragment;
    }

    private int index;
    private RecyclerView mRecyclerView;

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_single_recy;
    }

    @Override
    View initView(View v) {
        mRecyclerView = v.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        return v;
    }

    @Override
    void initData() {
        index = (int) getArguments().getSerializable("index");
    }
}
