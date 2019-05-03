package ceuilisa.mirai.fragments;

import android.os.Bundle;

import ceuilisa.mirai.adapters.EventAdapter;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.EventResponse;
import ceuilisa.mirai.nodejs.EventsBean;
import ceuilisa.mirai.utils.DensityUtil;
import ceuilisa.mirai.utils.LinearItemDecoration;
import io.reactivex.Observable;

public class FragmentEvents extends BaseListFragment<EventResponse, EventAdapter, EventsBean> {

    public static FragmentEvents newInstance(int userID) {
        Bundle args = new Bundle();
        args.putSerializable("user id", userID);
        FragmentEvents fragment = new FragmentEvents();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    Observable<EventResponse> initApi() {
        int userID = (int) getArguments().getSerializable("user id");
        return RetrofitUtil.getNodeApi().getUserEvents(userID, PAGE_SIZE, allItems.size());
    }

    @Override
    boolean showToolbar() {
        return false;
    }

    @Override
    void initAdapter() {
        mRecyclerView.addItemDecoration(new LinearItemDecoration(DensityUtil.dip2px(mContext, 0.0f)));
        mAdapter = new EventAdapter(allItems, mContext);
    }

}
