package ceuilisa.mirai.network;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    private static final String IMJAD_BASE_URL = "https://api.imjad.cn/";
    private static final String TENKOA_BASE_URL = "https://v1.hitokoto.cn/";

    public static AppApi getImjadApi(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                message -> Log.i("RetrofitLog","retrofitBack = "+message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(IMJAD_BASE_URL)
                .build();
        return retrofit.create(AppApi.class);
    }



    public static AppApi getTenkoaApi(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                message -> Log.i("RetrofitLog","retrofitBack = "+message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(TENKOA_BASE_URL)
                .build();
        return retrofit.create(AppApi.class);
    }

   


    public static BackSupport getTempApi(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("RetrofitLog","retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL_2)
                .build();
        return retrofit.create(BackSupport.class);
    }



    private static final String BASE_URL_2 = "http://192.168.0.111:8080/Emilia_war_exploded/";
}
