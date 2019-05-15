package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ceuilisa.mirai.activities.PlayListDetailActivity;
import ceuilisa.mirai.adapters.AlbumListAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.AlbumBean;
import ceuilisa.mirai.nodejs.ArtistAlbumResponse;
import io.reactivex.Observable;

public class FragmentArtistAlbum extends BaseListFragment<ArtistAlbumResponse, AlbumListAdapter, AlbumBean> {


    public static FragmentArtistAlbum newInstance(int artistID) {
        Bundle args = new Bundle();
        args.putSerializable("artist id", artistID);
        FragmentArtistAlbum fragment = new FragmentArtistAlbum();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    Observable<ArtistAlbumResponse> initApi() {
        int artistID = (int) getArguments().getSerializable("artist id");
        return Retro.getNodeApi().getArtistAlbum(artistID, PAGE_SIZE, allItems.size());
    }

    @Override
    boolean showToolbar() {
        return false;
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
                intent.putExtra("coverImg",allItems.get(position).getPicUrl());
                startActivity(intent);
            }
        });
    }
}
