package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;

import ceuilisa.mirai.activities.PlayListDetailActivity;
import ceuilisa.mirai.adapters.PlayListNodeAdapter;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.nodejs.PlayListResponse;
import ceuilisa.mirai.nodejs.PlaylistBean;
import ceuilisa.mirai.utils.Local;
import io.reactivex.Observable;

public class FragmentUserPlayList extends BaseListFragment<PlayListResponse, PlayListNodeAdapter, PlaylistBean> {

    public static FragmentUserPlayList newInstance(int userID) {
        Bundle args = new Bundle();
        args.putSerializable("user id", userID);
        FragmentUserPlayList fragment = new FragmentUserPlayList();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    Observable<PlayListResponse> initApi() {
        int userID = (int) getArguments().getSerializable("user id");
        return RetrofitUtil.getNodeApi().getMyPlayList(userID, PAGE_SIZE, allItems.size());
    }

    @Override
    boolean showToolbar() {
        return false;
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
}
