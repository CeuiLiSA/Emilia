package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.PlayListDetailActivity;
import ceuilisa.mirai.adapters.AlbumListAdapter;
import ceuilisa.mirai.adapters.PlayListAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.AlbumBean;
import ceuilisa.mirai.nodejs.PlaylistBean;
import ceuilisa.mirai.response.PlayListTitleResponse;
import ceuilisa.mirai.response.SearchAlbumResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Constant;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentSearch extends BaseFragment {

    private RecyclerView mRecyclerView;
    private int index, nowIndex = 0;
    private String keyWord;
    private ProgressBar mProgressBar;
    private List<AlbumBean> allAlbums = new ArrayList<>();
    private List<PlaylistBean> allPlaylist = new ArrayList<>();

    public static FragmentSearch newInstance(int index, String keyWord) {
        Bundle args = new Bundle();
        args.putSerializable("index", index);
        args.putSerializable("key", keyWord);
        FragmentSearch fragment = new FragmentSearch();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_single_recy;
    }

    @Override
    View initView(View v) {
        mProgressBar = v.findViewById(R.id.progress);
        mRecyclerView = v.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        index = (int) getArguments().getSerializable("index");
        keyWord = (String) getArguments().getSerializable("key");
        return v;
    }

    @Override
    void initData() {
        searchNow();
    }


    private void searchNow() {
        mProgressBar.setVisibility(View.VISIBLE);
        if (index == 0) {

        } else if (index == 1) {

        } else if (index == 2) {
            RetrofitUtil.getImjadApi().searchAlbum(keyWord, Constant.LIMIT, nowIndex)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<SearchAlbumResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(SearchAlbumResponse playListTitleResponse) {
                            if (playListTitleResponse.getResult().getAlbums() != null) {
                                allAlbums.clear();
                                allAlbums.addAll(playListTitleResponse.getResult().getAlbums());
                                AlbumListAdapter mAdapter = new AlbumListAdapter(allAlbums, mContext);
                                mAdapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position, int viewType) {
                                        Intent intent = new Intent(mContext, PlayListDetailActivity.class);
                                        intent.putExtra("id", allAlbums.get(position).getId());
                                        intent.putExtra("name", allAlbums.get(position).getName());
                                        intent.putExtra("author", allAlbums.get(position).getArtist().getName());
                                        intent.putExtra("dataType", "专辑");
                                        intent.putExtra("coverImg", allAlbums.get(position).getPicUrl());
                                        startActivity(intent);
                                    }
                                });
                                mRecyclerView.setAdapter(mAdapter);
                                mProgressBar.setVisibility(View.INVISIBLE);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            mProgressBar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        } else if (index == 3) {
            RetrofitUtil.getImjadApi().searchPlaylist(keyWord, Constant.LIMIT, nowIndex)
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
                                PlayListAdapter mAdapter = new PlayListAdapter(allPlaylist, mContext);
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
                                mProgressBar.setVisibility(View.INVISIBLE);
                                mRecyclerView.setAdapter(mAdapter);
                            } else {
                                mProgressBar.setVisibility(View.INVISIBLE);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            mProgressBar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        } else if (index == 4) {

        } else {
            Common.showToast(mContext, "搜索类型有误");
        }
    }
}
