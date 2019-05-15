package ceuilisa.mirai.fragments;

import android.content.Intent;

import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.activities.VideoPlayActivity;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.dialogs.LikeSongDialog;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.response.PlayRecordResponse;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Local;
import io.reactivex.Observable;

public class FragmentPlayAllHistory extends BaseListFragment<PlayRecordResponse, PlayListDetailAdapter, TracksBean> {

    @Override
    Observable<PlayRecordResponse> initApi() {
        LoginResponse user = Local.getUser();
        return Retro.getNodeApi().getPlayRecord(user.getProfile().getUserId(), 0);
    }

    @Override
    boolean showToolbar() {
        return false;
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
    boolean hasNext() {
        return false;
    }
}
