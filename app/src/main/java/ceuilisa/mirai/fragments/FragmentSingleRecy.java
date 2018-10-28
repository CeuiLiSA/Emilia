package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.activities.PlayListActivity;
import ceuilisa.mirai.adapters.PlayAllHistoryAdapter;
import ceuilisa.mirai.adapters.PlayListAdapter;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.adapters.PlayListTypeAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.ArtistResponse;
import ceuilisa.mirai.response.PlayAllHistoryResponse;
import ceuilisa.mirai.response.PlayWeekHistoryResponse;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.ChatDetailItemDecoration;
import ceuilisa.mirai.utils.Constant;
import ceuilisa.mirai.utils.DensityUtil;
import ceuilisa.mirai.utils.Reference;
import ceuilisa.mirai.utils.Translate;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentSingleRecy extends BaseFragment {

    public static FragmentSingleRecy newInstance(int index, String dataType) {
        Bundle args = new Bundle();
        args.putSerializable("index", index);
        args.putSerializable("dataType", dataType);
        FragmentSingleRecy fragment = new FragmentSingleRecy();
        fragment.setArguments(args);
        return fragment;
    }

    private int index;
    private String dataType;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_single_recy;
    }

    @Override
    View initView(View v) {
        mRecyclerView = v.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mProgressBar = v.findViewById(R.id.progress);
        return v;
    }

    @Override
    void initData() {
        index = (int) getArguments().getSerializable("index");
        dataType = (String) getArguments().getSerializable("dataType");
        if (dataType.equals("听歌记录")) {
            if (index == 0) {
                getWeeklyHistory();
            } else if (index == 1) {
                getAllHistory();
            }
        } else if (dataType.equals("歌单分类")) {
            getPlaylistType();
        }
    }

    private void getPlaylistType() {
        final String[] data;
        if (index == 0) {
            data = Constant.TYPE_YUZHONG;
        } else if (index == 1) {
            data = Constant.TYPE_FENGGE;
        } else if (index == 2) {
            data = Constant.TYPE_CHANGJIN;
        } else if (index == 3) {
            data = Constant.TYPE_QINGAN;
        } else {
            data = Constant.TYPE_ZHUTI;
        }
        mRecyclerView.addItemDecoration(new ChatDetailItemDecoration(DensityUtil.dip2px(mContext, 16.0f)));
        PlayListTypeAdapter adapter = new PlayListTypeAdapter(data, mContext);
        adapter.setOnItemClickListener((view, position, viewType) -> {
            Intent intent = new Intent(mContext, PlayListActivity.class);
            intent.putExtra("dataType", "根据类型搜索歌单");
            intent.putExtra("key", data[position]);
            startActivity(intent);
        });
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setAdapter(adapter);
    }

    private void getWeeklyHistory() {
        RetrofitUtil.getImjadApi().getWeekPlayHistory(Constant.USER_ID, 1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayWeekHistoryResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(PlayWeekHistoryResponse playListTitleResponse) {
                        PlayAllHistoryAdapter adapter = new PlayAllHistoryAdapter(
                                playListTitleResponse.getWeekData(), mContext);
                        adapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position, int viewType) {
                                Translate.translateMusic(playListTitleResponse.getWeekData());
                                Intent intent = new Intent(mContext, MusicActivity.class);
                                intent.putExtra("index", position);
                                startActivity(intent);
                            }
                        });
                        mProgressBar.setVisibility(View.INVISIBLE);
                        mRecyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void getAllHistory() {
        RetrofitUtil.getImjadApi().getAllPlayHistory(Constant.USER_ID, 0)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayAllHistoryResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(PlayAllHistoryResponse playListTitleResponse) {
                        PlayAllHistoryAdapter adapter = new PlayAllHistoryAdapter(
                                playListTitleResponse.getAllData(), mContext);
                        adapter.setOnItemClickListener((view, position, viewType) -> {
                            Translate.translateMusic(playListTitleResponse.getAllData());
                            Intent intent = new Intent(mContext, MusicActivity.class);
                            intent.putExtra("index", position);
                            startActivity(intent);
                        });
                        mProgressBar.setVisibility(View.INVISIBLE);
                        mRecyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
