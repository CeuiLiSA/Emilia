package ceuilisa.mirai.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import ceuilisa.mirai.BuildConfig;
import ceuilisa.mirai.activities.GlobalApp;
import ceuilisa.mirai.network.MusicChannel;
import ceuilisa.mirai.nodejs.ArtistBean;
import ceuilisa.mirai.response.TracksBean;

public class Common {

    private static Toast toast = null;

    public static <T> void showLog(T t) {
        if (BuildConfig.DEBUG) {
            Log.d("a line of my log", String.valueOf(t));
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        if (imm.isActive() && activity.getCurrentFocus() != null) {
            if (activity.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    public static <T> void showToast(Context context, T t) {
        if (toast == null) {
            toast = Toast.makeText(context, String.valueOf(t), Toast.LENGTH_SHORT);
        } else {
            toast.setText(String.valueOf(t));
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static <T> void showToast(T t) {
        if (toast == null) {
            toast = Toast.makeText(GlobalApp.getContext(), String.valueOf(t), Toast.LENGTH_SHORT);
        } else {
            toast.setText(String.valueOf(t));
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static String timeStamp2Date(String time) {
        if (time == null) {
            return null;
        }
        if (time.length() != 13 && time.length() != 12) {
            return "没有日期数据";
        }
        Long timeLong = Long.parseLong(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");//要转换的时间格式
        Date date;
        try {
            date = sdf.parse(sdf.format(timeLong));
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getArtistList(List<ArtistBean> artistBeans) {
        String temp = "";
        if (artistBeans == null) {
            return temp;
        }
        if (artistBeans.size() == 0) {
            return temp;
        }

        for (int i = 0; i < artistBeans.size(); i++) {
            temp = temp + artistBeans.get(i).getName() + "/";
        }
        String after = temp.substring(0, temp.length() - 1);
        return after;
    }

    public static void openUrl(String url, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

    public static void sendAlbumBroadcast(Context context, File file) {
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(file)));
    }

    private void shareMusic(TracksBean music, Context context) {
        File file = new File(music.getLocalPath());
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("audio/*");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        context.startActivity(Intent.createChooser(intent, "分享歌曲"));
    }

    public static int getShuffleIndex(){
        if(MusicChannel.get().getMusicList().size() == 0){
            return 0;
        }else {
            return new Random().nextInt(MusicChannel.get().getMusicList().size());
        }
    }
}
