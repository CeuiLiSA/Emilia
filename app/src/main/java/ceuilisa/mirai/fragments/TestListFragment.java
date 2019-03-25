package ceuilisa.mirai.fragments;

import android.view.View;

import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.fragments.SimpleListFragment;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.PlayListDetailResponse;
import ceuilisa.mirai.response.TracksBean;

public class TestListFragment extends
        SimpleListFragment<PlayListDetailResponse, PlayListDetailAdapter, TracksBean> {

    @Override
    boolean hasNext() {
        return false;
    }

    @Override
    void initApi() {
        mApi = RetrofitUtil.getTenkoaApi().getPlayListDetail("2238268434");
    }

    @Override
    void initAdapter() {
        mAdapter = new PlayListDetailAdapter(allItems, mContext);
    }
}
