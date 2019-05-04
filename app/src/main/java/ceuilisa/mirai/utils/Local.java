package ceuilisa.mirai.utils;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import ceuilisa.mirai.activities.GlobalApp;
import ceuilisa.mirai.interf.OnPrepared;
import ceuilisa.mirai.nodejs.LoginResponse;

import static android.content.Context.MODE_PRIVATE;

public class Local {

    private static final String USER = "user";
    private static final String DEFAULT_USER = "";
    private static SharedPreferences sp;

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
        if (onPrepared != null) {
            onPrepared.doSomething(null);
        }
    }


    /**
     * 播放音乐的循环模式
     */
    public static int getLoopMode(){
        if (sp == null) {
            sp = GlobalApp.getContext().getSharedPreferences("config", MODE_PRIVATE);
        }
        return sp.getInt("loop_mode", 0);
    }


    /**
     * 0列表一次，1单曲循环，2随机播放
     *
     * @param mode
     */
    public static void setLoopMode(int mode){
        if (sp == null) {
            sp = GlobalApp.getContext().getSharedPreferences("config", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("loop_mode", mode);
        editor.apply();
    }


    public static void changeLoopMode(OnPrepared<Integer> onPrepared){
        int mode = getLoopMode();
        if(mode == 0){
            setLoopMode(1);
            Common.showToast("单曲循环");
            if(onPrepared != null){
                onPrepared.doSomething(1);
            }
        }else if(mode == 1){
            setLoopMode(2);
            Common.showToast("随机播放");
            if(onPrepared != null){
                onPrepared.doSomething(2);
            }
        }else if(mode == 2){
            setLoopMode(0);
            Common.showToast("列表一次");
            if(onPrepared != null){
                onPrepared.doSomething(0);
            }
        }

    }
}
