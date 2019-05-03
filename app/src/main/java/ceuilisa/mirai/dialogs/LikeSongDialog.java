package ceuilisa.mirai.dialogs;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.ArtistActivity;
import ceuilisa.mirai.activities.CommentActivity;
import ceuilisa.mirai.activities.PlayListDetailActivity;
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

    public static LikeSongDialog newInstance(TracksBean tracksBean, long pid, int index){
        LikeSongDialog dialog = new LikeSongDialog();
        dialog.mTracksBean = tracksBean;
        dialog.pid = pid;
        dialog.index = index;
        return dialog;
    }

    private Context mContext;
    private TracksBean mTracksBean;
    private long pid = 0L;
    private int index;
    private TextView songName, addToPlaylist, download,
            comment, artist, album, delete;

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_like_song;
    }

    @Override
    public void bindView(View v) {
        mContext = getContext();
        songName = v.findViewById(R.id.song_name);
        addToPlaylist = v.findViewById(R.id.like);
        download = v.findViewById(R.id.download);
        comment = v.findViewById(R.id.comments);
        artist = v.findViewById(R.id.artist_info);
        album = v.findViewById(R.id.album);
        delete = v.findViewById(R.id.delete);

        songName.setText("歌曲：" + mTracksBean.getFullSongName());


        addToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddChartDialog dialog = AddChartDialog.newInctance(mTracksBean.getId());
                dialog.show(getActivity().getSupportFragmentManager(), "AddChartDialog");
                dismiss();
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadDialog downloadDialog = DownloadDialog.newInstance(mTracksBean);
                downloadDialog.show(getActivity().getSupportFragmentManager(), "download");
                dismiss();
            }
        });
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PlayListDetailActivity.class);
                intent.putExtra("id", mTracksBean.getAl().getId());
                intent.putExtra("name", mTracksBean.getAl().getName());
                intent.putExtra("dataType", "专辑");
                intent.putExtra("coverImg", mTracksBean.getAl().getPicUrl());
                startActivity(intent);
                dismiss();
            }
        });
        artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTracksBean.getAr().size() == 1) {
                    Intent intent = new Intent(mContext, ArtistActivity.class);
                    intent.putExtra("id", mTracksBean.getAr().get(0).getId());
                    intent.putExtra("name", mTracksBean.getAr().get(0).getName());
                    mContext.startActivity(intent);
                } else {
                    SelectArtistDialog.newInstance(mTracksBean)
                            .show(getActivity().getSupportFragmentManager(), "select artist");
                }
                dismiss();
            }
        });
        comment.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, CommentActivity.class);
            intent.putExtra("id", mTracksBean.getId());
            startActivity(intent);
            dismiss();
        });



        if(pid != 0L){
            delete.setVisibility(View.VISIBLE);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DelChartDialog dialog = DelChartDialog.newInstance(pid, mTracksBean.getId(), index);
                    dialog.show(getActivity().getSupportFragmentManager(), "DelChartDialog");
                    dismiss();
                }
            });
        }else {
            delete.setVisibility(View.GONE);
        }
    }
}
