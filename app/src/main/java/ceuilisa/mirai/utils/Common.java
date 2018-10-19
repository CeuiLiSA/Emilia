package ceuilisa.mirai.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Common {

    public static <T> void showLog(T t) {
        Log.d("a line of my log", String.valueOf(t));
    }

    private static Toast toast = null;

    public static <T> void showToast(Context context, T t) {
        if (toast == null) {
            toast = Toast.makeText(context, String.valueOf(t), Toast.LENGTH_SHORT);
        } else {
            toast.setText(String.valueOf(t));
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
