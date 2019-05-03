package ceuilisa.mirai.fragments;

import ceuilisa.mirai.adapters.MvListAdapter;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.MvBean;
import ceuilisa.mirai.nodejs.MvRankResponse;
import ceuilisa.mirai.nodejs.RelatedMvResponse;
import io.reactivex.Observable;

public class FragmentRelatedMv extends BaseListFragment<RelatedMvResponse, MvListAdapter, MvBean> {

    private int mvID;

    public static FragmentRelatedMv newInstance(int id){
        FragmentRelatedMv fragmentRelatedMv = new FragmentRelatedMv();
        fragmentRelatedMv.mvID = id;
        return fragmentRelatedMv;
    }

    @Override
    boolean showToolbar() {
        return false;
    }

    @Override
    Observable<RelatedMvResponse> initApi() {
        return RetrofitUtil.getNodeApi().getRelatedMv(mvID);
    }

    @Override
    void initAdapter() {
        mAdapter = new MvListAdapter(allItems, mContext);
    }

    @Override
    boolean hasNext() {
        return false;
    }
}
