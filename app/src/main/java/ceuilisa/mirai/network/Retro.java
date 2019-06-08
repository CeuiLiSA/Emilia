package ceuilisa.mirai.network;

import android.util.Log;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import ceuilisa.mirai.activities.Emilia;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {

    private static final String IMJAD_BASE_URL = "https://api.imjad.cn/";
    private static final String TENKOA_BASE_URL = "https://v1.hitokoto.cn/";
    //private static final String BASE_URL_2 = "http://192.168.0.112:3000/";





    private static final String BASE_URL_2 = "http://wangyl.agolddata.com:3000/";
    //private static final String BASE_URL_2 = "http://www.ceuilisa.com:3000/";
    //private static final String BASE_URL_2 = "http://192.168.0.101:3000/";
    private static final String BASE_URL_3 = "http://65.49.235.124/";
    private static Retrofit nodeApi = null;

    public static AppApi getImjadApi() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                message -> Log.i("RetrofitLog", "retrofitBack = " + message));
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

    public static NodeApi getNodeApi() {
        if (nodeApi == null) {
            HttpLoggingInterceptor log = new HttpLoggingInterceptor(
                    message -> Log.i("RetrofitLog", "retrofitBack = " + message));
            log.setLevel(HttpLoggingInterceptor.Level.BODY);
            ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(),
                    new SharedPrefsCookiePersistor(Emilia.getContext()));
            OkHttpClient okHttpClient = new OkHttpClient
                    .Builder()
                    .addInterceptor(log)
                    .cookieJar(cookieJar)
                    .build();
            nodeApi = new Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL_2)
                    .build();
        }
        return nodeApi.create(NodeApi.class);
    }
}
