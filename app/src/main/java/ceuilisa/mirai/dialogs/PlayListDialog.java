package ceuilisa.mirai.dialogs;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.adapters.NowPlayListAdapter;
import me.shaohui.bottomdialog.BaseBottomDialog;

public class PlayListDialog extends BaseBottomDialog {

    private Context mContext;
    private RecyclerView nowPlayList;

    @Override
    public int getLayoutRes() {
        return R.layout.now_playing_list;
    }

    @Override
    public void bindView(View v) {
        mContext = getContext();
        nowPlayList = v.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        nowPlayList.setLayoutManager(layoutManager);
        nowPlayList.setHasFixedSize(true);
        NowPlayListAdapter adapter = new NowPlayListAdapter(MusicService.allSongs, mContext);
        nowPlayList.setAdapter(adapter);
        nowPlayList.scrollToPosition(MusicService.getInstance().getNowPlayIndex());
    }
}
