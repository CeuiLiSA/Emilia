package ceuilisa.mirai.network;

import ceuilisa.mirai.nodejs.ArtistAlbumResponse;
import ceuilisa.mirai.nodejs.DayRecommend;
import ceuilisa.mirai.nodejs.EventResponse;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.nodejs.MvPlayUrlResponse;
import ceuilisa.mirai.nodejs.MvRankResponse;
import ceuilisa.mirai.nodejs.PlayListResponse;
import ceuilisa.mirai.nodejs.RecmPlayListResponse;
import ceuilisa.mirai.nodejs.UserDetailResponse;
import ceuilisa.mirai.response.CommentResponse;
import ceuilisa.mirai.response.MvDetail;
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


    @GET("/recommend/songs")
    Observable<DayRecommend> getDayRecommend();



    @GET("/comment/music")
    Observable<CommentResponse> getComments(@Query("id") int id,
                                            @Query("limit") int limit,
                                            @Query("offset") int offset);


    @GET("/recommend/resource")
    Observable<RecmPlayListResponse> getRecmPlayList(@Query("uid") int uid,
                                                     @Query("limit") int limit,
                                                     @Query("offset") int offset);


    /**
     * 获取某一个歌手的专辑
     *
     * @param id
     * @param limit
     * @param offset
     * @return
     */
    @GET("/artist/album")
    Observable<ArtistAlbumResponse> getArtistAlbum(@Query("id") int id,
                                                   @Query("limit") int limit,
                                                   @Query("offset") int offset);


    @GET("/top/mv")
    Observable<MvRankResponse> getMvRank(@Query("limit") int limit,
                                         @Query("offset") int offset);


    @GET("/mv/detail")
    Observable<MvDetail> getMvDetail(@Query("mvid") int mvid);


    @GET("/mv/url")
    Observable<MvPlayUrlResponse> getMvPlayUrl(@Query("id") int id);
}
