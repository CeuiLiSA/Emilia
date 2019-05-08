package ceuilisa.mirai.fragments;

import ceuilisa.mirai.adapters.AlbumListAdapter;
import ceuilisa.mirai.adapters.ArtistAdapter;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.AlbumBean;
import ceuilisa.mirai.nodejs.ArtistBean;
import ceuilisa.mirai.nodejs.FavorAlbumResponse;
import ceuilisa.mirai.nodejs.FavorArtistResponse;
import io.reactivex.Observable;

public class FragmentFavorArtist extends BaseListFragment<FavorArtistResponse, ArtistAdapter, ArtistBean>{

    @Override
    boolean showToolbar() {
        return false;
    }

    @Override
    Observable<FavorArtistResponse> initApi() {
        return Retro.getNodeApi().getFavorArtist(PAGE_SIZE, allItems.size(), System.currentTimeMillis());
    }

    @Override
    void initAdapter() {
        mAdapter = new ArtistAdapter(allItems, mContext);
    }
}
