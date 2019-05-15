package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.activities.TemplateFragmentActivity;
import ceuilisa.mirai.activities.VideoPlayActivity;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.dialogs.LikeSongDialog;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.SearchSongResponse;
import ceuilisa.mirai.response.TracksBean;
import io.reactivex.Observable;

public class FragmentSearchSong extends BaseListFragment<SearchSongResponse, PlayListDetailAdapter, TracksBean> {

    private String keyword;

    public static FragmentSearchSong newInstance(String key) {
        FragmentSearchSong fragmentSearchArtist = new FragmentSearchSong();
        fragmentSearchArtist.keyword = key;
        return fragmentSearchArtist;
    }

    @Override
    View initView(View v) {
        super.initView(v);
        ((TemplateFragmentActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(view -> getActivity().finish());
        return v;
    }

    @Override
    String getToolbarTitle() {
        return keyword + "的搜索结果";
    }

    @Override
    Observable<SearchSongResponse> initApi() {
        return Retro.getNodeApi().searchSong(keyword, PAGE_SIZE, allItems.size());
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
                intent.putExtra("dataType", "mv");
                startActivity(intent);
            } else if (viewType == 2) {
                LikeSongDialog dialog = LikeSongDialog.newInstance(
                        allItems.get(position));
                dialog.show(getChildFragmentManager());
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_local_music, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            FragmentLocalSearch.sTracksBeanList = allItems;
            Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
            intent.putExtra("dataType", "列表搜索");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
