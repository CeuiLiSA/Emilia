package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.view.View;

import ceuilisa.mirai.activities.MusicActivity;
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
                    startActivity(intent);
                } else if (viewType == 2) {
                    LikeSongDialog dialog = LikeSongDialog.newInstance(
                            allItems.get(position));
                    dialog.show(getChildFragmentManager());
                }
            }
        });
    }
}
