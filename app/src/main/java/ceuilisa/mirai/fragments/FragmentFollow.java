package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.view.View;

import ceuilisa.mirai.activities.UserDetailActivity;
import ceuilisa.mirai.adapters.FollowAdapter;
import ceuilisa.mirai.adapters.UserAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.FollowResponse;
import ceuilisa.mirai.nodejs.FollowedsBean;
import ceuilisa.mirai.nodejs.SearchUserResponse;
import ceuilisa.mirai.nodejs.UserprofilesBean;
import ceuilisa.mirai.utils.DensityUtil;
import ceuilisa.mirai.utils.LinearItemDecoration;
import io.reactivex.Observable;

/**
 * 获取关注列表，不是粉丝列表
 */
public class FragmentFollow extends BaseListFragment<FollowResponse, FollowAdapter, FollowedsBean> {

    private int uid;

    public static FragmentFollow newInstance(int key){
        FragmentFollow fragmentSearchArtist = new FragmentFollow();
        fragmentSearchArtist.uid = key;
        return fragmentSearchArtist;
    }

    @Override
    View initView(View v) {
        super.initView(v);
        mRecyclerView.addItemDecoration(new LinearItemDecoration(DensityUtil.dip2px(mContext, 12.0f)));
        return v;
    }

    @Override
    boolean showToolbar() {
        return false;
    }

    @Override
    Observable<FollowResponse> initApi() {
        return RetrofitUtil.getNodeApi().getFollow(uid, PAGE_SIZE, allItems.size());
    }

    @Override
    void initAdapter() {
        mAdapter = new FollowAdapter(allItems, mContext);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int viewType) {
                Intent intent = new Intent(mContext, UserDetailActivity.class);
                intent.putExtra("user id", allItems.get(position).getUserId());
                startActivity(intent);
            }
        });
    }
}
