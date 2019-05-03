package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.dialogs.LikeSongDialog;
import ceuilisa.mirai.response.TracksBean;

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

    public void showHotSongs(List<TracksBean> tracksBeans) {
        PlayListDetailAdapter adapter = new PlayListDetailAdapter(tracksBeans, mContext);
        adapter.setOnItemClickListener((view, position, viewType) -> {
            if (viewType == 0) {
                mChannel.setMusicList(tracksBeans);
                Intent intent = new Intent(mContext, MusicActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
            } else if (viewType == 2) {
                LikeSongDialog dialog = LikeSongDialog.newInstance(
                        tracksBeans.get(position));
                dialog.show(getChildFragmentManager());
            }
        });
        mRecyclerView.setAdapter(adapter);
    }
}
