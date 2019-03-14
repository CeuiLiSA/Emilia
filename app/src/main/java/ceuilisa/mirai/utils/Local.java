package ceuilisa.mirai.utils;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ceuilisa.mirai.activities.GlobalApp;
import ceuilisa.mirai.response.UserBean;

import static android.content.Context.MODE_PRIVATE;

public class Local {

    private static SharedPreferences sp;
    private static final String USER = "user";
    private static final String DEFAULT_USER = "";

    private static void saveUser(UserBean userBean){
        if (sp == null) {
            sp = GlobalApp.getContext().getSharedPreferences("config", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userBean);
        editor.putString(USER, json);
        editor.commit();
    }

    public static UserBean getUser() {
        if (sp == null) {
            sp = GlobalApp.getContext().getSharedPreferences("config", MODE_PRIVATE);
        }
        Gson gson = new Gson();
        String json = sp.getString(USER, null);
        return gson.fromJson(json, UserBean.class);
    }
}
