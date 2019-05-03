package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import ceuilisa.mirai.activities.UserDetailActivity;
import ceuilisa.mirai.adapters.FollowAdapter;
import ceuilisa.mirai.adapters.UserAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.FollowedsBean;
import ceuilisa.mirai.nodejs.FollowerResponse;
import ceuilisa.mirai.nodejs.SearchUserResponse;
import ceuilisa.mirai.nodejs.UserprofilesBean;
import ceuilisa.mirai.utils.Channel;
import ceuilisa.mirai.utils.DensityUtil;
import ceuilisa.mirai.utils.LinearItemDecoration;
import io.reactivex.Observable;

/**
 * 获取粉丝列表
 */
public class FragmentFollowers extends BaseListFragment<FollowerResponse, FollowAdapter, FollowedsBean> {

    private long uid;

    public static FragmentFollowers newInstance(long key){
        FragmentFollowers fragmentSearchArtist = new FragmentFollowers();
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
    Observable<FollowerResponse> initApi() {
        return RetrofitUtil.getNodeApi().getFollowers(uid, PAGE_SIZE, allItems.size());
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setHasOptionsMenu(true);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Channel channel) {
        if(channel.getReceiver().equals(className)){
            getFirstData();
        }
    }
}
