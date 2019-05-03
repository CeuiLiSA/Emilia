package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.view.View;

import ceuilisa.mirai.activities.ArtistActivity;
import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.activities.VideoPlayActivity;
import ceuilisa.mirai.adapters.ArtistAdapter;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.dialogs.LikeSongDialog;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.ArtistBean;
import ceuilisa.mirai.nodejs.SearchSongResponse;
import ceuilisa.mirai.nodejs.SongResult;
import ceuilisa.mirai.response.SearchArtistResponse;
import ceuilisa.mirai.response.TracksBean;
import io.reactivex.Observable;

public class FragmentSearchSong extends BaseListFragment<SearchSongResponse, PlayListDetailAdapter, TracksBean> {

    private String keyword;

    public static FragmentSearchSong newInstance(String key){
        FragmentSearchSong fragmentSearchArtist = new FragmentSearchSong();
        fragmentSearchArtist.keyword = key;
        return fragmentSearchArtist;
    }

    @Override
    String getToolbarTitle() {
        return keyword + "的搜索结果";
    }

    @Override
    Observable<SearchSongResponse> initApi() {
        return RetrofitUtil.getNodeApi().searchSong(keyword, PAGE_SIZE, allItems.size());
    }

    @Override
    void initAdapter() {
        mAdapter = new PlayListDetailAdapter(allItems, mContext);
        mAdapter.setOnItemClickListener((view, position, viewType) -> {
            if (viewType == 0) {
                mChannel.setMusicList(allItems);
                Intent intent = new Intent(mContext, MusicActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
            } else if (viewType == 1) {
                Intent intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra("mv id", allItems.get(position).getMv());
                startActivity(intent);
            } else if (viewType == 2) {
                LikeSongDialog dialog = LikeSongDialog.newInstance(
                        allItems.get(position));
                dialog.show(getChildFragmentManager());
            }
        });
    }
}
