package ceuilisa.mirai.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.adapters.ItemListAdapter;
import ceuilisa.mirai.dialogs.DeleteImageDialog;
import ceuilisa.mirai.interf.FullClickListener;
import ceuilisa.mirai.interf.OnPrepare;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.ItemResponse;
import ceuilisa.mirai.utils.Common;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class TestActivity extends BaseActivity{

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private RefreshLayout mRefreshLayout;

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_test;
    }

    @Override
    void initView() {
        mProgressBar = findViewById(R.id.progress);
        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(refreshlayout -> initData());
        mRefreshLayout.setEnableLoadMore(false);
    }

    @Override
    void initData() {
//        RetrofitUtil.getTempApi().getAllItem()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<List<ItemResponse>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<ItemResponse> itemResponses) {
//                        ItemListAdapter mAdapter = new ItemListAdapter(itemResponses, mContext);
//                        mAdapter.setOnItemClickListener(new FullClickListener() {
//                            @Override
//                            public void onItemClick(View view, int position, int viewType) {
//
//                            }
//
//                            @Override
//                            public void onItemLongClick(View view, int position, int viewType) {
//                                DeleteImageDialog deleteImageDialog = new DeleteImageDialog();
//                                deleteImageDialog.setName(itemResponses.get(position).getName().substring(34));
//                                deleteImageDialog.setOnPrepare(new OnPrepare() {
//                                    @Override
//                                    public void updateUI() {
//                                        /*itemResponses.remove(position);
//                                        mAdapter.notifyDataSetChanged();*/
//                                        itemResponses.remove(position);
//                                        mAdapter.notifyItemRemoved(position);
//                                        if (position != itemResponses.size()) {
//                                            mAdapter.notifyItemRangeChanged(position, itemResponses.size() - position);
//                                        }
//                                    }
//                                });
//                                deleteImageDialog.show(getSupportFragmentManager(), "delete");
//                            }
//                        });
//                        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(mAdapter));
//                        mProgressBar.setVisibility(View.INVISIBLE);
//                        mRefreshLayout.finishRefresh();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mProgressBar.setVisibility(View.INVISIBLE);
//                        Toasty.error(mContext, "This is an error toast.",
//                                Toast.LENGTH_SHORT, true).show();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }
}
