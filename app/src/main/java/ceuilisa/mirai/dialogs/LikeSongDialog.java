package ceuilisa.mirai.dialogs;

import android.view.View;
import android.widget.TextView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.response.TracksBean;
import me.shaohui.bottomdialog.BaseBottomDialog;

/**
 * 收藏一首歌Dialog
 */
public class LikeSongDialog extends BaseBottomDialog {

    public static LikeSongDialog newInstance(TracksBean tracksBean){
        LikeSongDialog dialog = new LikeSongDialog();
        dialog.mTracksBean = tracksBean;
        return dialog;
    }

    private TracksBean mTracksBean;
    private TextView songName, addToPlaylist, download,
            comment, artist, album, delete;

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_like_song;
    }

    @Override
    public void bindView(View v) {
        songName = v.findViewById(R.id.song_name);
        addToPlaylist = v.findViewById(R.id.like);
        download = v.findViewById(R.id.download);
        comment = v.findViewById(R.id.comments);
        artist = v.findViewById(R.id.artist_info);
        album = v.findViewById(R.id.album);
        delete = v.findViewById(R.id.delete);

        songName.setText("歌曲：" + mTracksBean.getFullSongName());
    }
}
