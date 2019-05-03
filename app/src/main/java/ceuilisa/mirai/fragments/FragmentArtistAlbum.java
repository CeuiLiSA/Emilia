package ceuilisa.mirai.fragments;

import android.os.Bundle;
import android.view.View;

import ceuilisa.mirai.adapters.AlbumListAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
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
        return RetrofitUtil.getNodeApi().getArtistAlbum(artistID, PAGE_SIZE, allItems.size());
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

            }
        });
    }
}
