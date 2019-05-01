package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.activities.PlayListActivity;
import ceuilisa.mirai.adapters.PlayAllHistoryAdapter;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.adapters.PlayListTypeAdapter;
import ceuilisa.mirai.dialogs.LikeSongDialog;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.PlayAllHistoryResponse;
import ceuilisa.mirai.response.PlayWeekHistoryResponse;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.ChatDetailItemDecoration;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Constant;
import ceuilisa.mirai.utils.DensityUtil;
import ceuilisa.mirai.utils.Translate;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentHotSongs extends BaseFragment {

    private RecyclerView mRecyclerView;

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_hot_songs;
    }

    @Override
    View initView(View v) {
        mRecyclerView = v.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        return v;
    }

    @Override
    void initData() {
    }

    public void showHotSongs(List<TracksBean> tracksBeans){
        PlayListDetailAdapter adapter = new PlayListDetailAdapter(tracksBeans, mContext);
        adapter.setOnItemClickListener((view, position, viewType) -> {
            if(viewType == 0) {
                mChannel.setMusicList(tracksBeans);
                Intent intent = new Intent(mContext, MusicActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
            }else if (viewType == 2) {
                LikeSongDialog dialog = LikeSongDialog.newInstance(
                        tracksBeans.get(position));
                dialog.show(getChildFragmentManager());
            }
        });
        mRecyclerView.setAdapter(adapter);
    }
}
