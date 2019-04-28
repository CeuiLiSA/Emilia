package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.AddPlayListActivity;
import ceuilisa.mirai.activities.PlayListDetailActivity;
import ceuilisa.mirai.activities.SearchActivity;
import ceuilisa.mirai.activities.TemplateFragmentActivity;
import ceuilisa.mirai.adapters.PlayListNodeAdapter;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.nodejs.PlayListResponse;
import ceuilisa.mirai.nodejs.PlaylistBean;
import ceuilisa.mirai.utils.Channel;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Local;
import io.reactivex.Observable;

/**
 * 获取已登录用户自己的歌单（创建的+收藏的）
 */
public class FragmentMyPlayList extends BaseListFragment<PlayListResponse, PlayListNodeAdapter, PlaylistBean> {

    @Override
    Observable<PlayListResponse> initApi() {
        LoginResponse user = Local.getUser();
        return RetrofitUtil.getNodeApi().getMyPlayList(user.getProfile().getUserId(), PAGE_SIZE, allItems.size());
    }

    @Override
    String getToolbarTitle() {
        return "我的歌单";
    }

    @Override
    View initView(View v) {
        super.initView(v);
        ((TemplateFragmentActivity)getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(view -> getActivity().finish());
        return v;
    }

    @Override
    void initAdapter() {
        mAdapter = new PlayListNodeAdapter(allItems, mContext);
        mAdapter.setOnItemClickListener((view, position, viewType) -> {
            Intent intent = new Intent(mContext, PlayListDetailActivity.class);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(mActivity, view, "sharedView");
            intent.putExtra("id", allItems.get(position).getId());
            intent.putExtra("name", allItems.get(position).getName());
            intent.putExtra("author", allItems.get(position).getCreator().getNickname());
            intent.putExtra("dataType", "歌单");
            intent.putExtra("coverImg", allItems.get(position).getCoverImgUrl());
            mContext.startActivity(intent, optionsCompat.toBundle());
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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_playlist, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_playlist) {
            Intent intent = new Intent(mContext, AddPlayListActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
