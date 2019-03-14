package ceuilisa.mirai.network;

import ceuilisa.mirai.response.BackResponse;
import ceuilisa.mirai.response.PlayListDetailResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BackSupport {

    @GET("emilia/like")
    Observable<BackResponse> postLike(@Query("userid") String userid,
                                      @Query("song_gson") String song_gson,
                                      @Query("like") String like);


}
