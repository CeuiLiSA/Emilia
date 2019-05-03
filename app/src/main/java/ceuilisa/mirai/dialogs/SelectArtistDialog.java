package ceuilisa.mirai.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.ArtistActivity;
import ceuilisa.mirai.response.TracksBean;

/**
 * 歌曲播放页面若有多个演唱者，选其中一个歌手进去查看他的详情
 */
public class SelectArtistDialog extends DialogFragment {

    private AlertDialog mAlertDialog;
    private Context mContext;
    private TracksBean mTracksBean;

    public static SelectArtistDialog newInstance(TracksBean tracksBean) {
        SelectArtistDialog dialog = new SelectArtistDialog();
        dialog.mTracksBean = tracksBean;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mContext = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_select_artist, null);
        LinearLayout linearLayout = view.findViewById(R.id.root_view);
        for (int i = 0; i < mTracksBean.getAr().size(); i++) {
            final int position = i;
            View artist = LayoutInflater.from(mContext).inflate(R.layout.item_artist, null);
            TextView textView = artist.findViewById(R.id.song_name);
            textView.setText(mTracksBean.getAr().get(i).getName());
            artist.setOnClickListener(v -> {
                mAlertDialog.dismiss();
                Intent intent = new Intent(mContext, ArtistActivity.class);
                intent.putExtra("id", mTracksBean.getAr().get(position).getId());
                intent.putExtra("name", mTracksBean.getAr().get(position).getName());
                mContext.startActivity(intent);
            });
            linearLayout.addView(artist);
        }
        builder.setView(view);
        mAlertDialog = builder.create();
        return mAlertDialog;
    }

}
