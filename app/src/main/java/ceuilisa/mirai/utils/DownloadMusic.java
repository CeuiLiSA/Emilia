package ceuilisa.mirai.utils;

import android.app.DownloadManager;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import com.google.gson.Gson;

import java.io.File;

import ceuilisa.mirai.activities.Emilia;
import ceuilisa.mirai.response.LocalMusic;
import ceuilisa.mirai.response.TracksBean;

public class DownloadMusic {


    public static void downloadMusic(File file, String url, TracksBean tracksBean) {
        try {
            String fileName = file.getName();
            Uri uri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setTitle(fileName);
            request.setDescription("正在下载…");
            request.setDestinationInExternalPublicDir(FileUtils.getRelativeMusicDir(), fileName);
            request.setMimeType(MimeTypeMap.getFileExtensionFromUrl(url));
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
            request.setAllowedOverRoaming(false); // 不允许漫游
            DownloadManager downloadManager = (DownloadManager) Emilia.getContext().getSystemService(Context.DOWNLOAD_SERVICE);
            long id = downloadManager.enqueue(request);
            String musicAbsPath = FileUtils.getMusicDir().concat(fileName);



            tracksBean.setLocalPath(file.getAbsolutePath());
            Common.showLog("getAbsolutePath " + file.getAbsolutePath());
            Common.showLog("getName " + file.getName());
            LocalMusic localMusic = new LocalMusic();
            localMusic.setId(tracksBean.getId());
            localMusic.setTrackJson(new Gson().toJson(tracksBean));
            AppDatabase.getAppDatabase(Emilia.getContext()).trackDao().insertTrack(localMusic);
            Common.showToast("下载完成");
            //DownloadMusicInfo downloadMusicInfo = new DownloadMusicInfo(title, musicAbsPath, coverPath);
            //AppCache.get().getDownloadList().put(id, downloadMusicInfo);
        } catch (Throwable th) {
            th.printStackTrace();
            //ToastUtils.show("下载失败");
        }
    }
}
