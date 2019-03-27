package ceuilisa.mirai.network;

import ceuilisa.mirai.nodejs.EventResponse;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.nodejs.PlayListResponse;
import ceuilisa.mirai.nodejs.UserDetailResponse;
import ceuilisa.mirai.response.PlayListTitleResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NodeApi {


    @GET("/login/cellphone")
    Observable<LoginResponse> loginByPhone(@Query("phone") String phone,
                                         @Query("password") String password);



    @GET("/user/playlist")
    Observable<PlayListResponse> getMyPlayList(@Query("uid") int uid,
                                               @Query("limit") int limit,
                                               @Query("offset") int offset);

    @GET("/user/detail")
    Observable<UserDetailResponse> getUserDetail(@Query("uid") int uid);


    @GET("/user/event")
    Observable<EventResponse> getUserEvents(@Query("uid") int uid,
                                            @Query("limit") int limit,
                                            @Query("offset") int offset);
}
