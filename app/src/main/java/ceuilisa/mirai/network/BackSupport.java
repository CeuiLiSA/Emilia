package ceuilisa.mirai.network;

import ceuilisa.mirai.response.BackResponse;
import ceuilisa.mirai.response.PlayListDetailResponse;
import ceuilisa.mirai.response.PlayListTitleResponse;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.response.UserBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BackSupport {

    @GET("/Emilia_war_exploded/emilia/like")
    Observable<BackResponse> postLike(@Query("userid") String userid,
                                      @Query("song_gson") String song_gson,
                                      @Query("like") String like);


    @GET("/Emilia_war_exploded/emilia/login")
    Observable<BackResponse<UserBean>> login(@Query("username") String username,
                                             @Query("password") String password);


    @GET("/Emilia_war_exploded/emilia/sign")
    Observable<BackResponse<UserBean>> sign(@Query("username") String username,
                                            @Query("password") String password);


    @GET("/Emilia_war_exploded/emilia/collections/song")
    Observable<PlayListDetailResponse> getMyFavor(@Query("userid") String userid);

    @GET("/Emilia_war_exploded/emilia/add_playlist")
    Observable<BackResponse> addPlaylist(@Query("userid") String userid,
                                         @Query("username") String userName,
                                         @Query("playlist_name") String playlistName,
                                         @Query("playlist_info") String playlist_info);


    @GET("/Emilia_war_exploded/emilia/collections/playlist")
    Observable<PlayListTitleResponse> getMyPlaylist(@Query("userid") String userid);

}
