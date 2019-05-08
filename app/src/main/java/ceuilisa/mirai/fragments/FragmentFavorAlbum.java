package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.view.View;

import ceuilisa.mirai.activities.PlayListDetailActivity;
import ceuilisa.mirai.adapters.AlbumListAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.AlbumBean;
import ceuilisa.mirai.nodejs.FavorAlbumResponse;
import io.reactivex.Observable;

public class FragmentFavorAlbum extends BaseListFragment<FavorAlbumResponse, AlbumListAdapter, AlbumBean>{


    @Override
    boolean showToolbar() {
        return false;
    }

    @Override
    Observable<FavorAlbumResponse> initApi() {
        return Retro.getNodeApi().getFavorAlbum(PAGE_SIZE, allItems.size(), System.currentTimeMillis());
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
                intent.putExtra("author", allItems.get(position).getArtists().get(0).getName());
                intent.putExtra("dataType", "专辑");
                intent.putExtra("coverImg", allItems.get(position).getPicUrl());
                startActivity(intent);
            }
        });
    }
}
