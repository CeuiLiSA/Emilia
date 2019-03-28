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
public abstract class BaseListFragment<Response extends BaseResponse<ListItem>,
        Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>,
        ListItem> extends BaseFragment {

    public static final int PAGE_SIZE = 20;
    protected Observable<Response> mApi;
    protected Adapter mAdapter;
    protected RecyclerView mRecyclerView;
    protected RefreshLayout mRefreshLayout;
    protected List<ListItem> allItems = new ArrayList<>();
    protected ProgressBar mProgressBar;

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_simple_list;
    }

    @Override
    View initView(View v) {
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        if(showToolbar()){
            toolbar.setNavigationOnClickListener(view -> getActivity().finish());
            toolbar.setTitle(getToolbarTitle());
        }else {
            toolbar.setVisibility(View.GONE);
        }
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

    String getToolbarTitle(){
        return " ";
    }

    @Override
    void initData() {
        getFirstData();
    }

    boolean hasNext() {
        return true;
    }

    boolean showToolbar() {
        return true;
    }

    /**
     *
     */
    abstract Observable<Response> initApi();

    /**
     * the callback after getting the first page of data
     *
     */
    abstract void initAdapter();

    /**
     * 获取第一波数据
     */
    public void getFirstData() {
        mApi = initApi();
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
                                allItems.clear();
                                allItems.addAll(response.getList());
                                initAdapter();
                                mRefreshLayout.finishRefresh(true);
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
                            Common.showLog(e.toString());
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
        mApi = initApi();
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
