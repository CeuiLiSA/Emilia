package ceuilisa.mirai.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.adapters.CommentListAdapter;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.CommentResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Constant;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CommentActivity extends BaseActivity {

    private String id;
    private int nowIndex = 0;
    private Toolbar mToolbar;
    private CommentListAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private RefreshLayout mRefreshLayout;
    private List<CommentResponse.CommentsBean> allComment = new ArrayList<>();

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_comment;
    }

    @Override
    void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationOnClickListener(v -> finish());
        mProgressBar = findViewById(R.id.progress);
        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnLoadMoreListener(layout -> getNextData());
        mRefreshLayout.setOnRefreshListener(layout -> fetchComment());
    }

    @Override
    void initData() {
        id = getIntent().getStringExtra("id");
        fetchComment();
    }

    private void fetchComment() {
        allComment.clear();
        nowIndex = 0;
        RetrofitUtil.getImjadApi().getComment(Constant.TYPE_COMMENT, id, Constant.LIMIT, nowIndex)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(CommentResponse playListTitleResponse) {
                        allComment.addAll(playListTitleResponse.getComments());
                        mAdapter = new CommentListAdapter(
                                playListTitleResponse.getHotComments(), allComment, mContext);
                        mAdapter.setOnItemClickListener((view, position, viewType) -> {

                        });
                        nowIndex = nowIndex + playListTitleResponse.getComments().size();
                        mToolbar.setTitle("评论(" + playListTitleResponse.getTotal() + ")");
                        mProgressBar.setVisibility(View.INVISIBLE);
                        mRefreshLayout.finishRefresh();
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void getNextData() {
        RetrofitUtil.getImjadApi().getComment(Constant.TYPE_COMMENT, id, Constant.LIMIT, nowIndex)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(CommentResponse playListTitleResponse) {
                        if (mAdapter.getItemCount() == playListTitleResponse.getTotal()) {
                            Common.showToast(mContext, "没有更多数据啦");
                        } else {
                            allComment.addAll(playListTitleResponse.getComments());
                            nowIndex = nowIndex + playListTitleResponse.getComments().size();
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
}
