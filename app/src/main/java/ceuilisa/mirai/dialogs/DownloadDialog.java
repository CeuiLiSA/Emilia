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

import ceuilisa.mirai.R;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.response.SingleSongResponse;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.DownloadMusic;
import ceuilisa.mirai.utils.FileUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 歌曲播放页面，确认下载歌曲对话框
 */
public class DownloadDialog extends DialogFragment {

    public static final String FILE_PATH = "/storage/emulated/0/EmiliaSongs/Music";
    private AlertDialog mAlertDialog;
    private ProgressBar mProgressBar;
    private int index;
    private TracksBean mTracksBean;

    public static DownloadDialog newInstance(TracksBean tracksBean) {
        DownloadDialog downloadDialog = new DownloadDialog();
        downloadDialog.mTracksBean = tracksBean;
        return downloadDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_download, null);
        mProgressBar = view.findViewById(R.id.progress);
        TextView textView = view.findViewById(R.id.song_size);
        textView.setText(String.format("这将会占用%s的存储空间，是否继续？", "一定"));
        view.findViewById(R.id.download_now).setOnClickListener(v -> startDownload());
        view.findViewById(R.id.cancel).setOnClickListener(v -> mAlertDialog.dismiss());
        builder.setView(view);
        mAlertDialog = builder.create();
        return mAlertDialog;
    }

    public void startDownload() {
        File file = new File(FILE_PATH, mTracksBean.getName() + ".mp3");
        if (file.exists()) {
            mAlertDialog.dismiss();
            Common.showToast(getContext(), "该文件已存在");
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
            Retro.getImjadApi().getSingleSong(mTracksBean.getId())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<SingleSongResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(SingleSongResponse singleSongResponse) {
                            if (singleSongResponse != null) {
                                if (singleSongResponse.getData() != null) {
                                    Common.showToast("开始下载");
                                    DownloadMusic.downloadMusic(file, singleSongResponse.getData().get(0).getUrl(), mTracksBean);
                                    dismiss();
//                                    DownloadTask downloadTask = new DownloadTask.Builder(
//                                            singleSongResponse.getData().get(0).getUrl(),
//                                            new File(FILE_PATH))
//                                            .setFilename(file.getName())
//                                            .setMinIntervalMillisCallbackProcess(100)
//                                            .setPassIfAlreadyCompleted(false)
//                                            .build();
//                                    downloadTask.enqueue(new DownloadListener1() {
//                                        @Override
//                                        public void taskStart(@NonNull DownloadTask task, @NonNull Listener1Assist.Listener1Model model) {
//                                            mProgressBar.setMax(singleSongResponse.getData().get(0).getSize());
//                                            mProgressBar.setVisibility(View.VISIBLE);
//                                        }
//
//                                        @Override
//                                        public void retry(@NonNull DownloadTask task, @NonNull ResumeFailedCause cause) {
//
//                                        }
//
//                                        @Override
//                                        public void connected(@NonNull DownloadTask task, int blockCount, long currentOffset, long totalLength) {
//
//                                        }
//
//                                        @Override
//                                        public void progress(@NonNull DownloadTask task, long currentOffset, long totalLength) {
//                                            mProgressBar.setProgress((int) currentOffset);
//                                        }
//
//                                        @Override
//                                        public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause, @NonNull Listener1Assist.Listener1Model model) {
//                                            mProgressBar.setVisibility(View.INVISIBLE);
//                                            mAlertDialog.dismiss();
//                                            Common.showToast("下载完成");
//                                        }
//                                    });
                                } else {
                                    Common.showToast("暂无版权");
                                }
                            } else {
                                Common.showToast("请检查网络连接");
                            }
                            mProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            Common.showToast(e.toString());
                            mProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}
