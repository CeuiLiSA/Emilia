package ceuilisa.mirai.utils;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ceuilisa.mirai.activities.GlobalApp;
import ceuilisa.mirai.interf.OnPrepare;
import ceuilisa.mirai.interf.OnPrepared;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.response.UserBean;

import static android.content.Context.MODE_PRIVATE;

public class Local {

    private static SharedPreferences sp;
    private static final String USER = "user";
    private static final String DEFAULT_USER = "";

    public Local() {

    }

    public static void saveUser(LoginResponse userBean, OnPrepared<Object> onPrepared) {
        if (sp == null) {
            sp = GlobalApp.getContext().getSharedPreferences("config", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        userBean.setLogin(true);
        String json = gson.toJson(userBean);
        editor.putString(USER, json);
        editor.apply();
        onPrepared.doSomething(null);
    }


    public static LoginResponse getUser() {
        if (sp == null) {
            sp = GlobalApp.getContext().getSharedPreferences("config", MODE_PRIVATE);
        }
        Gson gson = new Gson();
        String json = sp.getString(USER, null);
        return gson.fromJson(json, LoginResponse.class);
    }


    public static void loginOut(OnPrepared<Object> onPrepared) {
        LoginResponse userBean = getUser();
        userBean.setLogin(false);
        saveUser(userBean, onPrepared);
    }

    public static void clearLocal(OnPrepared<Object> onPrepared) {
        if (sp == null) {
            sp = GlobalApp.getContext().getSharedPreferences("config", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
        if(onPrepared != null) {
            onPrepared.doSomething(null);
        }
    }
}
