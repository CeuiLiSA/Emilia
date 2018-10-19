package ceuilisa.mirai.network;

import java.util.List;

import ceuilisa.mirai.response.ItemResponse;
import ceuilisa.mirai.response.PlayListDetailResponse;
import ceuilisa.mirai.response.PlayListTitleResponse;
import ceuilisa.mirai.response.SingleSongResponse;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AppApi {

    @GET("search/{username}?type=PLAYLIST")
    Observable<PlayListTitleResponse> getAllPlayList(@Path("username") String username);

    @GET("playlist/{id}")
    Observable<PlayListDetailResponse> getPlayListDetail(@Path("id") String id);

    @GET("url/{id}")
    Observable<SingleSongResponse> getSingleSong(@Path("id") String id);

    @GET("query_req")
    Observable<List<ItemResponse>> getAllItem();
}
