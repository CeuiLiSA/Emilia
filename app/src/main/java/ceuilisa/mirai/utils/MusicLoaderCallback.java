package ceuilisa.mirai.utils;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.webkit.ValueCallback;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.nodejs.AlbumBean;
import ceuilisa.mirai.response.TracksBean;

public class MusicLoaderCallback implements LoaderManager.LoaderCallbacks {
    private final List<TracksBean> musicList;
    private final Context context;
    private final ValueCallback<List<TracksBean>> callback;

    public MusicLoaderCallback(Context context, ValueCallback<List<TracksBean>> callback) {
        this.context = context;
        this.callback = callback;
        this.musicList = new ArrayList<>();
    }

    public Loader onCreateLoader(int id, Bundle args) {
        return new MusicCursorLoader(context);
    }

    public void onLoadFinished(Loader var1, Object var2) {
        this.onLoadFinished(var1, (Cursor) var2);
    }

    public void onLoaderReset(Loader loader) {
    }

    public void onLoadFinished(Loader loader, Cursor data) {
        if (data == null) {
            return;
        }


        int counter = 0;
        musicList.clear();
        while (data.moveToNext()) {
            // 是否为音乐，魅族手机上始终为0
            int isMusic = data.getInt(data.getColumnIndex(MediaStore.Audio.AudioColumns.IS_MUSIC));
            long duration = data.getLong(data.getColumnIndex(MediaStore.Audio.Media.DURATION));
            long fileSize = data.getLong(data.getColumnIndex(MediaStore.Audio.Media.SIZE));
            long id = data.getLong(data.getColumnIndex(BaseColumns._ID));
            String title = data.getString(data.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE));
            String artist = data.getString(data.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST));
            String album = data.getString(data.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM));
            long albumId = data.getLong(data.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM_ID));
            String path = data.getString(data.getColumnIndex(MediaStore.Audio.AudioColumns.DATA));
            String fileName = data.getString(data.getColumnIndex(MediaStore.Audio.AudioColumns.DISPLAY_NAME));


            Common.showLog("MusicLoaderCallback _ID " + id);
            Common.showLog("MusicLoaderCallback artist " + artist);
            Common.showLog("MusicLoaderCallback album " + album);
            Common.showLog("MusicLoaderCallback albumId " + albumId);
            Common.showLog("MusicLoaderCallback path " + path);

            TracksBean music = new TracksBean();
            music.setId(id);
            music.setName(title);
            music.setArtistName(artist);
            music.setAlbumName(album);
            AlbumBean albumBean = new AlbumBean();
            albumBean.setId(albumId);
            music.setAlbum(albumBean);
            music.setDt((int) duration);
            music.setLocalPath(path);
            music.setFileName(fileName);
            music.setFileSize(fileSize);
            musicList.add(music);
        }

        callback.onReceiveValue(musicList);
    }
}
