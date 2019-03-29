package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.ArtistActivity;
import ceuilisa.mirai.activities.PlayListDetailActivity;
import ceuilisa.mirai.adapters.AlbumListAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.AlbumBean;
import ceuilisa.mirai.nodejs.ArtistAlbumResponse;
import ceuilisa.mirai.response.SearchAlbumResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Constant;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
