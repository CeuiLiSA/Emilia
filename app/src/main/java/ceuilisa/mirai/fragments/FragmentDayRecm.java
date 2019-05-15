package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.AddPlayListActivity;
import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.activities.TemplateFragmentActivity;
import ceuilisa.mirai.activities.VideoPlayActivity;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.adapters.RecmSongAdapter;
import ceuilisa.mirai.dialogs.LikeSongDialog;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.DayRecommend;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Local;
import io.reactivex.Observable;

public class FragmentDayRecm extends BaseListFragment<DayRecommend, PlayListDetailAdapter, TracksBean> {

    @Override
    boolean hasNext() {
        return false;
    }

    @Override
    String getToolbarTitle() {
        return "每日推荐";
    }

    @Override
    View initView(View v) {
        super.initView(v);
        ((TemplateFragmentActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(view -> getActivity().finish());
        return v;
    }

    @Override
    Observable<DayRecommend> initApi() {
        return Retro.getNodeApi().getDayRecommend();
    }

    @Override
    void initAdapter() {
        mAdapter = new PlayListDetailAdapter(allItems, mContext);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int viewType) {
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
