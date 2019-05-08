package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;

import ceuilisa.mirai.activities.PlayListDetailActivity;
import ceuilisa.mirai.adapters.RecmPlayListAdapter;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.nodejs.RecmPlayListResponse;
import ceuilisa.mirai.nodejs.RecommendPlaylistBean;
import ceuilisa.mirai.utils.Local;
import io.reactivex.Observable;

/**
 * 获取已登录用户的推荐歌单
 */
public class FragmentRecmPlaylist extends BaseListFragment<RecmPlayListResponse, RecmPlayListAdapter, RecommendPlaylistBean> {

    @Override
    Observable<RecmPlayListResponse> initApi() {
        LoginResponse user = Local.getUser();
        return Retro.getNodeApi().getRecmPlayList(user.getProfile().getUserId(), PAGE_SIZE, allItems.size());
    }

    @Override
    String getToolbarTitle() {
        return "推荐歌单";
    }

    @Override
    boolean hasNext() {
        return false;
    }

    @Override
    void initAdapter() {
        mAdapter = new RecmPlayListAdapter(allItems, mContext);
        mAdapter.setOnItemClickListener((view, position, viewType) -> {
            Intent intent = new Intent(mContext, PlayListDetailActivity.class);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(mActivity, view, "sharedView");
            intent.putExtra("id", allItems.get(position).getId());
            intent.putExtra("name", allItems.get(position).getName());
            intent.putExtra("author", allItems.get(position).getCreator().getNickname());
            intent.putExtra("dataType", "歌单");
            intent.putExtra("coverImg", allItems.get(position).getPicUrl());
            mContext.startActivity(intent, optionsCompat.toBundle());
        });
    }
}
