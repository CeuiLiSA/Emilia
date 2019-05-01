package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.view.View;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.dialogs.LikeSongDialog;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.response.PlayListDetailResponse;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.response.UserBean;
import ceuilisa.mirai.utils.Local;
import io.reactivex.Observable;

public class SearchAlbumFragment extends
        BaseListFragment<PlayListDetailResponse, PlayListDetailAdapter, TracksBean> {

    @Override
    boolean hasNext() {
        return false;
    }

    @Override
    Observable<PlayListDetailResponse> initApi() {
        return null;
    }


    @Override
    void initAdapter() {
        mAdapter = new PlayListDetailAdapter(allItems, mContext);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int viewType) {
                if(viewType == 0) {
                    mChannel.setMusicList(allItems);
                    Intent intent = new Intent(mContext, MusicActivity.class);
                    intent.putExtra("index", position);
                    startActivity(intent);
                }else if (viewType == 2) {
                    LikeSongDialog dialog = LikeSongDialog.newInstance(
                            allItems.get(position));
                    dialog.show(getChildFragmentManager());
                }
            }
        });
    }
}
