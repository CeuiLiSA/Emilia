package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.view.View;

import ceuilisa.mirai.activities.VideoPlayActivity;
import ceuilisa.mirai.adapters.MvListAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
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
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int viewType) {
                Intent intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra("mv item", allItems.get(position));
                startActivity(intent);
            }
        });
    }
}
