package ceuilisa.mirai.fragments;

import android.content.Intent;

import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.dialogs.LikeSongDialog;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.response.NewSongResponse;
import ceuilisa.mirai.response.TracksBean;
import io.reactivex.Observable;

public class FragmentNewSong extends BaseListFragment<NewSongResponse, PlayListDetailAdapter, TracksBean> {

    private int area; //全部:0  华语:7  欧美:96  日本:8  韩国:16

    public static FragmentNewSong newInstance(int area) {
        FragmentNewSong fragmentNewSong = new FragmentNewSong();
        if (area == 0) {
            fragmentNewSong.area = 7;
        } else if (area == 1) {
            fragmentNewSong.area = 96;
        } else if (area == 2) {
            fragmentNewSong.area = 8;
        } else if (area == 3) {
            fragmentNewSong.area = 16;
        }
        return fragmentNewSong;
    }

    @Override
    boolean showToolbar() {
        return false;
    }

    @Override
    Observable<NewSongResponse> initApi() {
        return Retro.getNodeApi().newSong(area);
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
            } else if (viewType == 2) {
                LikeSongDialog dialog = LikeSongDialog.newInstance(
                        allItems.get(position));
                dialog.show(getChildFragmentManager());
            }
        });
    }

    @Override
    boolean hasNext() {
        return false;
    }
}
