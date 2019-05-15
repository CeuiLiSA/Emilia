package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.activities.VideoPlayActivity;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.dialogs.LikeSongDialog;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Common;

public class FragmentLocalSearch extends BaseFragment {

    public static List<TracksBean> sTracksBeanList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private PlayListDetailAdapter mAdapter;
    private EditText mEditText;

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_local_search;
    }

    @Override
    View initView(View v) {
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(view -> getActivity().finish());
        mRecyclerView = v.findViewById(R.id.recy_list);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new PlayListDetailAdapter(sTracksBeanList, mContext);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int viewType) {
                if (viewType == 0) {
                    mChannel.setMusicList(sTracksBeanList);
                    Intent intent = new Intent(mContext, MusicActivity.class);
                    intent.putExtra("index", position);
                    startActivity(intent);
                } else if (viewType == 1) {
                    Intent intent = new Intent(mContext, VideoPlayActivity.class);
                    intent.putExtra("mv id", sTracksBeanList.get(position).getMv());
                    intent.putExtra("dataType", "mv");
                    startActivity(intent);
                } else if (viewType == 2) {
                    LikeSongDialog dialog = LikeSongDialog.newInstance(
                            sTracksBeanList.get(position));
                    dialog.show(getChildFragmentManager());
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mEditText = v.findViewById(R.id.search_et);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return v;
    }

    @Override
    void initData() {

    }

    private void filter(CharSequence keyWord){
        if(keyWord == null){
            return;
        }
        String convert = String.valueOf(keyWord).toLowerCase();
        if(sTracksBeanList == null){
            return;
        }
        List<TracksBean> beanList = new ArrayList<>();
        for (int i = 0; i < sTracksBeanList.size(); i++) {
            if(sTracksBeanList.get(i).getContent().contains(convert)){
                beanList.add(sTracksBeanList.get(i));
            }
        }
        mAdapter = new PlayListDetailAdapter(beanList, mContext);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int viewType) {
                if (viewType == 0) {
                    mChannel.setMusicList(beanList);
                    Intent intent = new Intent(mContext, MusicActivity.class);
                    intent.putExtra("index", position);
                    startActivity(intent);
                } else if (viewType == 1) {
                    Intent intent = new Intent(mContext, VideoPlayActivity.class);
                    intent.putExtra("mv id", beanList.get(position).getMv());
                    intent.putExtra("dataType", "mv");
                    startActivity(intent);
                } else if (viewType == 2) {
                    LikeSongDialog dialog = LikeSongDialog.newInstance(
                            beanList.get(position));
                    dialog.show(getChildFragmentManager());
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}
