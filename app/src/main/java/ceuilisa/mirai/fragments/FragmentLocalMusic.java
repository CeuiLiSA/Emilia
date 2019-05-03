package ceuilisa.mirai.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.ScannerMusic;

public class FragmentLocalMusic extends BaseFragment {

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_local_music;
    }

    @Override
    View initView(View v) {
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(view -> getActivity().finish());
        mProgressBar = v.findViewById(R.id.progress);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRecyclerView = v.findViewById(R.id.recy_list);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        return v;
    }

    @Override
    void initData() {
        List<TracksBean> tracksBeans = new ArrayList<>();
        ScannerMusic.scanMusic(mContext, tracksBeans);
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}
