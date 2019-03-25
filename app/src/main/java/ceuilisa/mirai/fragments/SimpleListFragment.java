package ceuilisa.mirai.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.adapters.PlayListAdapter;
import ceuilisa.mirai.fragments.BaseFragment;
import ceuilisa.mirai.response.BaseResponse;
import ceuilisa.mirai.response.CommentResponse;
import ceuilisa.mirai.response.PlayListTitleResponse;
import ceuilisa.mirai.utils.Common;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @param <Response> json解析累
 * @param <Adapter>  列表适配器
 * @param <ListItem>     列表数据元素
 */
public abstract class SimpleListFragment<Response extends BaseResponse<ListItem>,
        Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>,
        ListItem> extends BaseFragment {

    protected Observable<Response> mApi;
    protected Adapter mAdapter;
    protected RecyclerView mRecyclerView;
    protected RefreshLayout mRefreshLayout;
    protected List<ListItem> allItems = new ArrayList<>();
    protected int nowPage = 1;
    protected ProgressBar mProgressBar;

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_simple_list;
    }

    @Override
    View initView(View v) {
        mProgressBar = v.findViewById(R.id.progress);
        mRecyclerView = v.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRefreshLayout = v.findViewById(R.id.refreshLayout);
        mRefreshLayout.setRefreshHeader(new DeliveryHeader(mContext));
        mRefreshLayout.setOnRefreshListener(layout -> getFirstData());
        mRefreshLayout.setOnLoadMoreListener(layout -> getNextData());
        mRefreshLayout.setEnableLoadMore(hasNext());
        return v;
    }

    @Override
    void initData() {
        initApi();
        getFirstData();
    }

    boolean hasNext() {
        return true;
    }

    /**
     *
     */
    abstract void initApi();

    /**
     * the callback after getting the first page of data
     *
     */
    abstract void initAdapter();

    /**
     * 获取第一波数据
     */
    public void getFirstData() {
        if (mApi != null) {
            nowPage = 1;
            mApi.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Response response) {
                            if (response != null) {
                                allItems.addAll(response.getList());
                                initAdapter();
                                mRefreshLayout.finishRefresh(true);
                                nowPage = nowPage + 1;
                                if(mAdapter != null) {
                                    mRecyclerView.setAdapter(mAdapter);
                                }
                            } else {
                                mRefreshLayout.finishRefresh(false);
                            }
                            mProgressBar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onError(Throwable e) {
                            mProgressBar.setVisibility(View.INVISIBLE);
                            mRefreshLayout.finishRefresh(false);
                            Common.showToast(e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    /**
     * 获取后续数据
     */
    public void getNextData() {
        if (mApi != null) {
            mApi.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Response response) {
                            if (response != null) {
                                allItems.addAll(response.getList());
                                mRefreshLayout.finishLoadMore(true);
                                nowPage = nowPage + 1;
                                if(mAdapter != null) {
                                    mAdapter.notifyDataSetChanged();
                                }
                            } else {
                                mRefreshLayout.finishLoadMore(false);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            mRefreshLayout.finishLoadMore(false);
                            Common.showToast(e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}
