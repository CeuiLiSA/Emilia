package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.view.View;

import ceuilisa.mirai.activities.ArtistActivity;
import ceuilisa.mirai.adapters.ArtistAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.ArtistBean;
import ceuilisa.mirai.response.SearchArtistResponse;
import io.reactivex.Observable;

public class FragmentSearchArtist extends BaseListFragment<SearchArtistResponse, ArtistAdapter, ArtistBean> {

    private String keyword;

    public static FragmentSearchArtist newInstance(String key) {
        FragmentSearchArtist fragmentSearchArtist = new FragmentSearchArtist();
        fragmentSearchArtist.keyword = key;
        return fragmentSearchArtist;
    }

    @Override
    String getToolbarTitle() {
        return keyword + "的搜索结果";
    }

    @Override
    Observable<SearchArtistResponse> initApi() {
        return Retro.getNodeApi().searchArtist(keyword, PAGE_SIZE, allItems.size());
    }

    @Override
    void initAdapter() {
        mAdapter = new ArtistAdapter(allItems, mContext);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int viewType) {
                Intent intent = new Intent(mContext, ArtistActivity.class);
                intent.putExtra("id", allItems.get(position).getId());
                intent.putExtra("name", allItems.get(position).getName());
                mContext.startActivity(intent);
            }
        });
    }
}
