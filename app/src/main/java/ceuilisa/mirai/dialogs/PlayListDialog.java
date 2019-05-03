package ceuilisa.mirai.dialogs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.adapters.NowPlayListAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.MusicChannel;
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
        NowPlayListAdapter adapter = new NowPlayListAdapter(
                MusicChannel.get().getMusicList(), mContext);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int viewType) {
                Intent intent = new Intent(mContext, MusicActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
                dismiss();
            }
        });
        nowPlayList.setAdapter(adapter);
        int tempPosition = MusicService.get().getNowPlayIndex() - 2;
        if (tempPosition >= 0) {
            nowPlayList.scrollToPosition(tempPosition);
        } else {
            nowPlayList.scrollToPosition(MusicService.get().getNowPlayIndex());
        }
    }
}
