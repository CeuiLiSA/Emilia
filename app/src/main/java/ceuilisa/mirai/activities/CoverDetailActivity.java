package ceuilisa.mirai.activities;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener1;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;

import java.io.File;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.network.MusicChannel;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Common;

public class CoverDetailActivity extends BaseActivity {

    public static final String PHOTO_FILE_PATH = "/storage/emulated/0/EmiliaPhotos";
    private ImageView mImageView;
    private String coverImage;
    private TextView download;

    @Override
    void initLayout() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        mLayoutID = R.layout.activity_cover_detail;
    }

    @Override
    void initView() {
        coverImage = getIntent().getStringExtra("cover");
        mImageView = findViewById(R.id.originalImage);
        download = findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TracksBean tracksBean = MusicChannel.get().getMusicList().get(MusicService.get().getNowPlayIndex());
                File file = new File(PHOTO_FILE_PATH, tracksBean.getName() + "_" + tracksBean.getDt() + ".png");
                if (file.exists()) {
                    Common.showToast("该文件已存在");
                } else {
                    DownloadTask downloadTask = new DownloadTask.Builder(
                            coverImage,
                            new File(PHOTO_FILE_PATH))
                            .setFilename(file.getName())
                            .setMinIntervalMillisCallbackProcess(100)
                            .setPassIfAlreadyCompleted(false)
                            .build();
                    downloadTask.enqueue(new DownloadListener1() {
                        @Override
                        public void taskStart(@NonNull DownloadTask task, @NonNull Listener1Assist.Listener1Model model) {
                        }

                        @Override
                        public void retry(@NonNull DownloadTask task, @NonNull ResumeFailedCause cause) {

                        }

                        @Override
                        public void connected(@NonNull DownloadTask task, int blockCount, long currentOffset, long totalLength) {

                        }

                        @Override
                        public void progress(@NonNull DownloadTask task, long currentOffset, long totalLength) {
                        }

                        @Override
                        public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause, @NonNull Listener1Assist.Listener1Model model) {
                            Common.showToast("下载完成");
                            Common.sendAlbumBroadcast(mContext, file);
                        }
                    });
                }
            }
        });
    }

    @Override
    void initData() {
        Glide.with(mContext).load(coverImage).into(mImageView);
    }
}
