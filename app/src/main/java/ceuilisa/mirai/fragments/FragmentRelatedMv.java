package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.view.View;

import ceuilisa.mirai.activities.VideoPlayActivity;
import ceuilisa.mirai.adapters.MvListAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.MvBean;
import ceuilisa.mirai.nodejs.RelatedMvResponse;
import io.reactivex.Observable;

public class FragmentRelatedMv extends BaseListFragment<RelatedMvResponse, MvListAdapter, MvBean> {

    private int mvID;

    public static FragmentRelatedMv newInstance(int id) {
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
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int viewType) {
                Intent intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra("mv id", allItems.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    boolean hasNext() {
        return false;
    }
}
