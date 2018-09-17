package ceuilisa.mirai.network;

import ceuilisa.mirai.response.PlayListTitleResponse;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AppApi {

    @GET("search/{username}?type=PLAYLIST")
    Observable<PlayListTitleResponse> getAllPlayList(@Path("username") String username);
}
