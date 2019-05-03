package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.view.View;

import ceuilisa.mirai.activities.VideoPlayActivity;
import ceuilisa.mirai.adapters.MvListAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.MvBean;
import ceuilisa.mirai.nodejs.RecmdMvResponse;
import io.reactivex.Observable;

/**
 * 推荐mv
 */
public class FragmentMvRecmd extends BaseListFragment<RecmdMvResponse, MvListAdapter, MvBean> {

    @Override
    Observable<RecmdMvResponse> initApi() {
        return RetrofitUtil.getNodeApi().getMvRecmd(PAGE_SIZE, allItems.size());
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
