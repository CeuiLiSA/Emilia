package ceuilisa.mirai.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener1;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;

import java.io.File;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.FileUtil;
import ceuilisa.mirai.utils.Reference;

public class DownloadDialog extends DialogFragment{

    private AlertDialog mAlertDialog;
    private ProgressBar mProgressBar;
    private int index;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.dialog_download, null);
        mProgressBar = view.findViewById(R.id.progress);
        TextView textView = view.findViewById(R.id.song_size);
        textView.setText(String.format("这将会占用%s的存储空间，是否继续？", FileUtil.convertFileSize(
                MusicService.getInstance().getSingleSong().getData().get(0).getSize())));
        view.findViewById(R.id.download_now).setOnClickListener(v -> startDownload());
        view.findViewById(R.id.cancel).setOnClickListener(v -> mAlertDialog.dismiss());
        builder.setView(view);
        mAlertDialog = builder.create();
        return mAlertDialog;
    }

    public void startDownload(){
        File file = new File("/storage/emulated/0/EmiliaSongs",
                Reference.allSongs.get(index).getName() + ".mp3");
        if(file.exists()){
            mAlertDialog.dismiss();
            Common.showToast(getContext(), "该文件已存在");
        }else {
            DownloadTask downloadTask = new DownloadTask.Builder(
                    MusicService.getInstance().getSingleSong().getData().get(0).getUrl(),
                    new File("/storage/emulated/0/EmiliaSongs"))
                    .setFilename(file.getName())
                    .setMinIntervalMillisCallbackProcess(100)
                    .setPassIfAlreadyCompleted(false)
                    .build();
            downloadTask.enqueue(new DownloadListener1() {
                @Override
                public void taskStart(@NonNull DownloadTask task, @NonNull Listener1Assist.Listener1Model model) {
                    mProgressBar.setMax(MusicService.getInstance().getSingleSong().getData().get(0).getSize());
                    mProgressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void retry(@NonNull DownloadTask task, @NonNull ResumeFailedCause cause) {

                }

                @Override
                public void connected(@NonNull DownloadTask task, int blockCount, long currentOffset, long totalLength) {

                }

                @Override
                public void progress(@NonNull DownloadTask task, long currentOffset, long totalLength) {
                    mProgressBar.setProgress((int) currentOffset);
                }

                @Override
                public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause, @NonNull Listener1Assist.Listener1Model model) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    mAlertDialog.dismiss();
                    Common.showToast(mProgressBar.getContext(), "下载完成");
                }
            });
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
