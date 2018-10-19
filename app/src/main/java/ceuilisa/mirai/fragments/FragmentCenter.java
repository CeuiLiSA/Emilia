package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.PlayListDetailActivity;
import ceuilisa.mirai.adapters.ItemListAdapter;
import ceuilisa.mirai.adapters.PlayListAdapter;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.ItemResponse;
import ceuilisa.mirai.response.PlayListTitleResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Constant;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class FragmentCenter extends BaseFragment {

    private RecyclerView mRecyclerView;
    private RefreshLayout mRefreshLayout;

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_center;
    }

    @Override
    View initView(View v) {
        mRecyclerView = v.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRefreshLayout = v.findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(refreshlayout -> initData());
        /*refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000,false);//传入false表示加载失败
            }
        });*/
        return v;
    }

    @Override
    void initData() {
        RetrofitUtil.getTempApi().getAllItem()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ItemResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ItemResponse> itemResponses) {
                        List<ItemResponse> mPlayLists = itemResponses;
                        ItemListAdapter mAdapter = new ItemListAdapter(mPlayLists, mContext);
                        /*mAdapter.setOnItemClickListener((view, position, viewType) -> {
                            Intent intent = new Intent(mContext, PlayListDetailActivity.class);
                            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                                    .makeSceneTransitionAnimation(mActivity, view, "sharedView");
                            intent.putExtra("id", mPlayLists.get(position).id);
                            intent.putExtra("name", mPlayLists.get(position).name);
                            intent.putExtra("coverImg", mPlayLists.get(position).coverImgUrl);
                            mContext.startActivity(intent, optionsCompat.toBundle());
                        });*/
                        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(mAdapter));
                        mRefreshLayout.finishRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}