package ceuilisa.mirai.fragments;

import ceuilisa.mirai.adapters.EventAdapter;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.EventResponse;
import ceuilisa.mirai.nodejs.EventsBean;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.utils.DensityUtil;
import ceuilisa.mirai.utils.LinearItemDecoration;
import ceuilisa.mirai.utils.Local;
import io.reactivex.Observable;

public class FragmentEvents extends BaseListFragment<EventResponse, EventAdapter, EventsBean>{

    @Override
    Observable<EventResponse> initApi() {
        LoginResponse user = Local.getUser();
        return RetrofitUtil.getNodeApi().getUserEvents(user.getProfile().getUserId(), PAGE_SIZE, allItems.size());
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
