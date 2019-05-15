package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.view.View;

import ceuilisa.mirai.activities.VideoPlayActivity;
import ceuilisa.mirai.adapters.MvListAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.MvBean;
import ceuilisa.mirai.nodejs.MvListResponse;
import io.reactivex.Observable;

/**
 * mv排行榜
 */
public class FragmentMvRank extends BaseListFragment<MvListResponse, MvListAdapter, MvBean> {

    @Override
    Observable<MvListResponse> initApi() {
        return Retro.getNodeApi().getMvRank(PAGE_SIZE, allItems.size());
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
                intent.putExtra("dataType", "mv");
                startActivity(intent);
            }
        });
    }

    @Override
    boolean hasNext() {
        return false;
    }
}
