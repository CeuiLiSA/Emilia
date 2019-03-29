package ceuilisa.mirai.fragments;

import ceuilisa.mirai.adapters.MvListAdapter;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.MvBean;
import ceuilisa.mirai.nodejs.MvRankResponse;
import io.reactivex.Observable;

public class FragmentMvRank extends BaseListFragment<MvRankResponse, MvListAdapter, MvBean> {

    @Override
    Observable<MvRankResponse> initApi() {
        return RetrofitUtil.getNodeApi().getMvRank(PAGE_SIZE, allItems.size());
    }

    @Override
    boolean showToolbar() {
        return false;
    }

    @Override
    void initAdapter() {
        mAdapter = new MvListAdapter(allItems, mContext);
    }
}
