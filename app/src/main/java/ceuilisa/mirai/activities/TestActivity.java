package ceuilisa.mirai.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.adapters.ItemListAdapter;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.ItemResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class TestActivity extends BaseActivity{

    private RecyclerView mRecyclerView;
    private RefreshLayout mRefreshLayout;

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_test;
    }

    @Override
    void initView() {
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
        RetrofitUtil.getTempApi().getAllItem()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ItemResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ItemResponse> itemResponses) {

                        ItemListAdapter mAdapter = new ItemListAdapter(itemResponses, mContext);
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
