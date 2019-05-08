package ceuilisa.mirai.activities;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.adapters.PlayListAdapter;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.PlaylistBean;
import ceuilisa.mirai.response.PlayListTitleResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Constant;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PlayListActivity extends WithPanelActivity {

    public static boolean isNeedFresh = false;
    private String dataType, key;
    private int nowIndex;
    private RecyclerView mRecyclerView;
    private RefreshLayout mRefreshLayout;
    private PlayListAdapter mAdapter;
    private Toolbar mToolbar;
    private List<PlaylistBean> allPlaylist = new ArrayList<>();

    @Override
    boolean hasImage() {
        return true;
    }

    @Override
    boolean hasProgress() {
        return true;
    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_play_list;
    }

    @Override
    void initView() {
        super.initView();
        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRefreshLayout = findViewById(R.id.refreshLayout);
    }

    @Override
    void initData() {
        dataType = getIntent().getStringExtra("dataType");
        if (dataType.equals("本人歌单")) {
            //getMyPlaylist();
            setSupportActionBar(mToolbar);
            mToolbar.setTitle("我的歌单");
            mRefreshLayout.setEnableLoadMore(false);
            mRefreshLayout.setEnableRefresh(false);
        } else if (dataType.equals("根据类型搜索歌单")) {
            mRefreshLayout.setRefreshHeader(new DeliveryHeader(this));
            mRefreshLayout.setOnLoadMoreListener(layout -> getNextData(key));
            mRefreshLayout.setOnRefreshListener(layout -> getPlaylistByKey(key));
            key = getIntent().getStringExtra("key");
            getPlaylistByKey(key);
            mToolbar.setTitle(key);
        }
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    private void getPlaylistByKey(String playlistName) {
        nowIndex = 0;
        mImageView.setVisibility(View.INVISIBLE);
        loadProgress.setVisibility(View.VISIBLE);
        Retro.getImjadApi().searchPlaylist(playlistName, Constant.LIMIT, nowIndex)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayListTitleResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(PlayListTitleResponse playListTitleResponse) {
                        if (playListTitleResponse.getResult().getPlaylists() != null &&
                                playListTitleResponse.getResult().getPlaylists().size() > 0) {
                            allPlaylist.clear();
                            allPlaylist.addAll(playListTitleResponse.getResult().getPlaylists());
                            mAdapter = new PlayListAdapter(allPlaylist, mContext);
                            mAdapter.setOnItemClickListener((view, position, viewType) -> {
                                Intent intent = new Intent(mContext, PlayListDetailActivity.class);
                                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                                        .makeSceneTransitionAnimation(mActivity, view, "sharedView");
                                intent.putExtra("id", allPlaylist.get(position).getId());
                                intent.putExtra("name", allPlaylist.get(position).getName());
                                intent.putExtra("author", allPlaylist.get(position).getCreator().getNickname());
                                intent.putExtra("dataType", "歌单");
                                intent.putExtra("coverImg", allPlaylist.get(position).getCoverImgUrl());
                                mContext.startActivity(intent, optionsCompat.toBundle());
                            });
                            nowIndex = nowIndex + playListTitleResponse.getResult().getPlaylists().size();
                            loadProgress.setVisibility(View.INVISIBLE);
                            mRefreshLayout.finishRefresh();
                            mRecyclerView.setAdapter(mAdapter);
                        } else {
                            mRefreshLayout.finishRefresh();
                            loadNoData();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRefreshLayout.finishRefresh();
                        loadError();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void getNextData(String playlistName) {
        Retro.getImjadApi().searchPlaylist(playlistName, Constant.LIMIT, nowIndex)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayListTitleResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(PlayListTitleResponse playListTitleResponse) {
                        if (mAdapter.getItemCount() == playListTitleResponse.getResult().getPlaylistCount()) {
                            Common.showToast(mContext, "没有更多数据啦");
                        } else {
                            allPlaylist.addAll(playListTitleResponse.getResult().getPlaylists());
                            nowIndex = nowIndex + playListTitleResponse.getResult().getPlaylists().size();
                            mAdapter.notifyDataSetChanged();
                        }
                        mRefreshLayout.finishLoadMore();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRefreshLayout.finishLoadMore();
                        loadError();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

//    private void getMyPlaylist() {
//        UserBean userBean = Local.getUser();
//        Retro.getTempApi().getMyPlaylist(userBean.getUserID())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<PlayListTitleResponse>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//
//                    @Override
//                    public void onNext(PlayListTitleResponse playListTitleResponse) {
//                        if (playListTitleResponse.getResult().getPlaylists() != null &&
//                                playListTitleResponse.getResult().getPlaylists().size() > 0) {
//                            List<PlayListTitleResponse.ResultBean.PlaylistsBean> mPlayLists =
//                                    new ArrayList<>(playListTitleResponse.getResult().getPlaylists());
//                            PlayListAdapter mAdapter = new PlayListAdapter(mPlayLists, mContext);
//                            mAdapter.setOnItemClickListener((view, position, viewType) -> {
//                                Intent intent = new Intent(mContext, PlayListDetailActivity.class);
//                                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
//                                        .makeSceneTransitionAnimation(mActivity, view, "sharedView");
//                                intent.putExtra("id", String.valueOf(mPlayLists.get(position).getId()));
//                                intent.putExtra("name", mPlayLists.get(position).getName());
//                                intent.putExtra("author", mPlayLists.get(position).getCreator().getNickname());
//                                intent.putExtra("dataType", "歌单");
//                                intent.putExtra("coverImg", mPlayLists.get(position).getCoverImgUrl());
//                                mContext.startActivity(intent, optionsCompat.toBundle());
//                            });
//                            loadProgress.setVisibility(View.INVISIBLE);
//                            mRecyclerView.setAdapter(mAdapter);
//                        } else {
//                            loadNoData();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        loadError();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.playlist_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_playlist) {
            Intent intent = new Intent(mContext, AddPlayListActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNeedFresh) {
            //getMyPlaylist();
            isNeedFresh = false;
        }
    }
}
