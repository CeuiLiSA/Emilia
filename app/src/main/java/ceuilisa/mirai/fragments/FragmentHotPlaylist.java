package ceuilisa.mirai.fragments;

import ceuilisa.mirai.adapters.PlayListAdapter;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.HotPlaylistResponse;
import ceuilisa.mirai.nodejs.PlayListResponse;
import ceuilisa.mirai.nodejs.PlaylistBean;
import ceuilisa.mirai.nodejs.PlaylistTagResponse;
import io.reactivex.Observable;

public class FragmentHotPlaylist extends BaseListFragment<HotPlaylistResponse, PlayListAdapter, PlaylistBean> {

    private String mTag;

    public static FragmentHotPlaylist newInstance(String tag){
        FragmentHotPlaylist fragmentHotPlaylist = new FragmentHotPlaylist();
        fragmentHotPlaylist.mTag = tag;
        return fragmentHotPlaylist;
    }

    @Override
    Observable<HotPlaylistResponse> initApi() {
        return Retro.getNodeApi().getHotPlaylist("hot", PAGE_SIZE, allItems.size(), mTag);
    }

    @Override
    void initAdapter() {
        mAdapter = new PlayListAdapter(allItems, mContext);
    }
}
