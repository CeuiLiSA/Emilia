package ceuilisa.mirai.utils;

import android.util.Log;

public class Common {

    public static <T> void showLog(T t) {
        Log.d("a line of my log", String.valueOf(t));
    }
}
