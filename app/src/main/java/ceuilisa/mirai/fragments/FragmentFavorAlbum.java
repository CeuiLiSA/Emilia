package ceuilisa.mirai.fragments;

import ceuilisa.mirai.adapters.AlbumListAdapter;
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
    }
}
