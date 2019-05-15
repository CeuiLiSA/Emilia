package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.PlayListActivity;
import ceuilisa.mirai.activities.TemplateFragmentActivity;
import ceuilisa.mirai.adapters.PlayListTypeAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.nodejs.PlaylistTagResponse;
import ceuilisa.mirai.utils.ChatDetailItemDecoration;
import ceuilisa.mirai.utils.DensityUtil;

public class FragmentPlaylistTag extends BaseFragment {

    private PlaylistTagResponse mTagResponse;
    private RecyclerView mRecyclerView;
    private int index;

    public static FragmentPlaylistTag newInstance(PlaylistTagResponse response, int i){
        FragmentPlaylistTag fragmentPlaylistTag = new FragmentPlaylistTag();
        fragmentPlaylistTag.mTagResponse = response;
        fragmentPlaylistTag.index = i;
        return fragmentPlaylistTag;
    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_playlist_tag;
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
        mRecyclerView.addItemDecoration(new ChatDetailItemDecoration(DensityUtil.dip2px(mContext, 16.0f)));
        List<String> data = new ArrayList<>();
        for (int i = 0; i < mTagResponse.getSub().size(); i++) {
            if(mTagResponse.getSub().get(i).getCategory() == index){
                data.add(mTagResponse.getSub().get(i).getName());
            }
        }
        PlayListTypeAdapter adapter = new PlayListTypeAdapter(data, mContext);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int viewType) {
                Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
                intent.putExtra("tag", data.get(position));
                intent.putExtra("dataType", "热门歌单");
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapter);
    }
}
