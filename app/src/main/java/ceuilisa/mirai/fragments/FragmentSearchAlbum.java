package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.ArtistActivity;
import ceuilisa.mirai.activities.PlayListDetailActivity;
import ceuilisa.mirai.adapters.AlbumListAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.AlbumBean;
import ceuilisa.mirai.response.SearchAlbumResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Constant;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentSearchAlbum extends BaseFragment {

    private AlbumListAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RefreshLayout mRefreshLayout;
    private ProgressBar mProgressBar;
    private List<AlbumBean> allData = new ArrayList<>();
    private String name;
    private int nowIndex = 0;

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_artist_album;
    }

    @Override
    View initView(View v) {
        mRecyclerView = v.findViewById(R.id.recyclerView);
        mProgressBar = v.findViewById(R.id.progress);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRefreshLayout = v.findViewById(R.id.refreshLayout);
        mRefreshLayout.setRefreshHeader(new DeliveryHeader(mContext));
        mRefreshLayout.setOnLoadMoreListener(layout -> getNextData());
        mRefreshLayout.setEnableRefresh(false);
        return v;
    }

    @Override
    void initData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getArtistAlbum();
    }

    private void getArtistAlbum() {
        allData.clear();
        nowIndex = 0;
        mProgressBar.setVisibility(View.VISIBLE);
        RetrofitUtil.getImjadApi().searchAlbum(name, Constant.LIMIT, nowIndex)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchAlbumResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(SearchAlbumResponse playListTitleResponse) {
                        if (playListTitleResponse.getResult().getAlbums() != null) {
                            allData.addAll(playListTitleResponse.getResult().getAlbums());
                            mAdapter = new AlbumListAdapter(allData, mContext);
                            mAdapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position, int viewType) {
                                    Intent intent = new Intent(mContext, PlayListDetailActivity.class);
                                    intent.putExtra("id", String.valueOf(allData.get(position).getId()));
                                    intent.putExtra("name", allData.get(position).getName());
                                    intent.putExtra("author", allData.get(position).getArtist().getName());
                                    intent.putExtra("dataType", "专辑");
                                    intent.putExtra("coverImg", allData.get(position).getPicUrl());
                                    startActivity(intent);
                                }
                            });
                            mRecyclerView.setAdapter(mAdapter);
                        }
                        mProgressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void getNextData() {
        RetrofitUtil.getImjadApi().searchAlbum(name, Constant.LIMIT, nowIndex)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchAlbumResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(SearchAlbumResponse playListTitleResponse) {
                        if (mAdapter.getItemCount() == playListTitleResponse.getResult().getAlbumCount()) {
                            Common.showToast(mContext, "没有更多数据啦");
                        } else {
                            allData.addAll(playListTitleResponse.getResult().getAlbums());
                            nowIndex = nowIndex + playListTitleResponse.getResult().getAlbums().size();
                            mAdapter.notifyDataSetChanged();
                        }
                        mRefreshLayout.finishLoadMore();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
