package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.view.View;

import ceuilisa.mirai.activities.PlayListDetailActivity;
import ceuilisa.mirai.adapters.AlbumListAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.AlbumBean;
import ceuilisa.mirai.response.SearchAlbumResponse;
import io.reactivex.Observable;

public class FragmentSearchAlbum extends BaseListFragment<SearchAlbumResponse, AlbumListAdapter, AlbumBean> {

    private String keyword;

    public static FragmentSearchAlbum newInstance(String key) {
        FragmentSearchAlbum fragmentSearchArtist = new FragmentSearchAlbum();
        fragmentSearchArtist.keyword = key;
        return fragmentSearchArtist;
    }

    @Override
    String getToolbarTitle() {
        return keyword + "的搜索结果";
    }

    @Override
    Observable<SearchAlbumResponse> initApi() {
        return Retro.getNodeApi().searchAlbum(keyword, PAGE_SIZE, allItems.size());
    }

    @Override
    void initAdapter() {
        mAdapter = new AlbumListAdapter(allItems, mContext);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int viewType) {
                Intent intent = new Intent(mContext, PlayListDetailActivity.class);
                intent.putExtra("id", allItems.get(position).getId());
                intent.putExtra("name", allItems.get(position).getName());
                intent.putExtra("author", allItems.get(position).getArtist().getName());
                intent.putExtra("dataType", "专辑");
                intent.putExtra("coverImg", allItems.get(position).getPicUrl());
                startActivity(intent);
            }
        });
    }
}
