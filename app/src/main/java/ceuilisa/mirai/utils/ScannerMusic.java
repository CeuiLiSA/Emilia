package ceuilisa.mirai.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.nodejs.AlbumBean;
import ceuilisa.mirai.nodejs.ArtistBean;
import ceuilisa.mirai.response.TracksBean;

public class ScannerMusic {

    /**
     * 扫描歌曲
     */
    public static void scanMusic(Context context, List<TracksBean> musicList) {
        Common.showLog("ScannerMusic 开始扫描音乐");
        musicList.clear();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        BaseColumns._ID,
                        MediaStore.Audio.AudioColumns.IS_MUSIC,
                        MediaStore.Audio.AudioColumns.TITLE,
                        MediaStore.Audio.AudioColumns.ARTIST,
                        MediaStore.Audio.AudioColumns.ALBUM,
                        MediaStore.Audio.AudioColumns.ALBUM_ID,
                        MediaStore.Audio.AudioColumns.DATA,
                        MediaStore.Audio.AudioColumns.DISPLAY_NAME,
                        MediaStore.Audio.AudioColumns.SIZE,
                        MediaStore.Audio.AudioColumns.DURATION
                },
                null,
                null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (cursor == null) {
            return;
        }
        while (cursor.moveToNext()) {
            // 是否为音乐
            Common.showLog("ScannerMusic 找到了一首音乐");
            int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
            if (isMusic == 0) {
                continue;
            }
            long id = cursor.getLong(cursor.getColumnIndex(BaseColumns._ID));
            // 标题
            String title = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE)));
            // 艺术家
            String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST));
            // 专辑
            String album = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM)));
            // 专辑封面id，根据该id可以获得专辑封面图片
            long albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM_ID));
            // 持续时间
            int duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            // 音乐文件路径
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA));
            // 音乐文件名
            String fileName = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DISPLAY_NAME)));
            // 音乐文件大小
            long fileSize = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
            TracksBean music = new TracksBean();
            music.setId(id);
            music.setName(title);
            ArtistBean artistBean = new ArtistBean();
            artistBean.setName(artist);
            List<ArtistBean> beanList = new ArrayList<>();
            beanList.add(artistBean);

            AlbumBean albumBean = new AlbumBean();
            albumBean.setName(album);
            albumBean.setId(albumId);
            music.setAr(beanList);
            music.setAl(albumBean);
            music.setDt(duration);
            music.setLocalPath(path);
            TracksBean.H h = new TracksBean.H();
            h.setSize(fileSize);
            music.setH(h);
            musicList.add(music);
        }
        cursor.close();
        Common.showLog("ScannerMusic 扫描音乐结束");
    }

}
