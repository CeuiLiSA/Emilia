package ceuilisa.mirai.network;

import ceuilisa.mirai.nodejs.ArtistAlbumResponse;
import ceuilisa.mirai.nodejs.DayRecommend;
import ceuilisa.mirai.nodejs.EventResponse;
import ceuilisa.mirai.nodejs.FavorAlbumResponse;
import ceuilisa.mirai.nodejs.FavorArtistResponse;
import ceuilisa.mirai.nodejs.FollowResponse;
import ceuilisa.mirai.nodejs.FollowerResponse;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.nodejs.MvListResponse;
import ceuilisa.mirai.nodejs.MvPlayUrlResponse;
import ceuilisa.mirai.nodejs.PlayListResponse;
import ceuilisa.mirai.nodejs.RecmPlayListResponse;
import ceuilisa.mirai.nodejs.RecmdMvResponse;
import ceuilisa.mirai.nodejs.RelatedMvResponse;
import ceuilisa.mirai.nodejs.SearchSongResponse;
import ceuilisa.mirai.nodejs.SearchUserResponse;
import ceuilisa.mirai.nodejs.SongDetailResponse;
import ceuilisa.mirai.nodejs.UserDetailResponse;
import ceuilisa.mirai.response.AlbumResponse;
import ceuilisa.mirai.response.ArtistResponse;
import ceuilisa.mirai.response.BaseResponse;
import ceuilisa.mirai.response.CommentResponse;
import ceuilisa.mirai.response.LikeSongResponse;
import ceuilisa.mirai.response.MvDetail;
import ceuilisa.mirai.response.NewSongResponse;
import ceuilisa.mirai.response.PlayListDetailResponse;
import ceuilisa.mirai.response.SearchAlbumResponse;
import ceuilisa.mirai.response.SearchArtistResponse;
import ceuilisa.mirai.response.SingleSongResponse;
import ceuilisa.mirai.response.TracksBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NodeApi {


    /**
     *
     * @param phone
     * @param password
     * @return
     */
    @GET("/login/cellphone")
    Observable<LoginResponse> loginByPhone(@Query("phone") String phone,
                                           @Query("password") String password);

    @GET("/login")
    Observable<LoginResponse> loginByEmail(@Query("email") String email,
                                           @Query("password") String password);


    @GET("/user/playlist")
    Observable<PlayListResponse> getMyPlayList(@Query("uid") int uid,
                                               @Query("limit") int limit,
                                               @Query("offset") int offset,
                                               @Query("timestamp") long timestamp);

    @GET("/user/detail")
    Observable<UserDetailResponse> getUserDetail(@Query("uid") int uid);


    @GET("/user/event")
    Observable<EventResponse> getUserEvents(@Query("uid") int uid,
                                            @Query("limit") int limit,
                                            @Query("offset") int offset);


    @GET("/recommend/songs")
    Observable<DayRecommend> getDayRecommend();


    @GET("/comment/music")
    Observable<CommentResponse> getComments(@Query("id") long id,
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


    /**
     * 最热榜单mv
     *
     * @param limit
     * @param offset
     * @return
     */
    @GET("/top/mv")
    Observable<MvListResponse> getMvRank(@Query("limit") int limit,
                                         @Query("offset") int offset);


    /**
     * 推荐mv
     *
     * @param limit
     * @param offset
     * @return
     */
    @GET("/personalized/mv")
    Observable<RecmdMvResponse> getMvRecmd(@Query("limit") int limit,
                                           @Query("offset") int offset);


    /**
     * 最新mv
     *
     * @param limit
     * @param offset
     * @return
     */
    @GET("/mv/first")
    Observable<MvListResponse> getMvFirst(@Query("limit") int limit,
                                          @Query("offset") int offset);


    @GET("/mv/detail")
    Observable<MvDetail> getMvDetail(@Query("mvid") int mvid);


    @GET("/mv/url")
    Observable<MvPlayUrlResponse> getMvPlayUrl(@Query("id") int id);


    /**
     * 收藏音乐
     *
     * @param id
     * @param like
     * @return
     */
    @GET("/like")
    Observable<LikeSongResponse> likeSong(@Query("id") long id,
                                          @Query("like") boolean like);

    @GET("/artist/sub")
    Observable<LikeSongResponse> likeArtist(@Query("id") long id,
                                          @Query("t") String t);

    @GET("/album/sub")
    Observable<BaseResponse> likeAlbum(@Query("id") long id,
                                            @Query("t") String t);


    @GET("/playlist/detail")
    Observable<PlayListDetailResponse> getPlayListDetail(@Query("id") long id,
                                                         @Query("timestamp") long timestamp);


    @GET("/playlist/create")
    Observable<BaseResponse> createPlaylist(@Query("name") String name);


    /**
     * 收藏或取消收藏 歌单 t=1 关注，2取消关注
     *
     * @param t
     * @param id
     * @return
     */
    @GET("/playlist/subscribe")
    Observable<BaseResponse> starPlaylist(@Query("t") String t,
                                          @Query("id") long id);


    /**
     * 关注用户 t=1 关注，2取消关注
     *
     * @param t
     * @param id
     * @return
     */
    @GET("/follow")
    Observable<BaseResponse> starUser(@Query("t") String t,
                                      @Query("id") long id);


    /**
     * 将一首歌收藏至某歌单
     *
     * @param pid
     * @param tracks
     * @return
     */
    @GET("/playlist/tracks?op=add")
    Observable<BaseResponse> addChart(@Query("pid") long pid,
                                      @Query("tracks") long tracks);


    /**
     * 将一首歌从某歌单移除
     *
     * @param pid
     * @param tracks
     * @return
     */
    @GET("/playlist/tracks?op=del")
    Observable<BaseResponse> delFromChart(@Query("pid") long pid,
                                          @Query("tracks") long tracks);


    /**
     * 新歌速递
     *
     * @return
     */
    @GET("/top/song")
    Observable<NewSongResponse> newSong(@Query("type") int type);


    /**
     * 搜索歌手
     *
     * @return
     */
    @GET("/search?type=100")
    Observable<SearchArtistResponse> searchArtist(@Query("keywords") String keywords,
                                                  @Query("limit") int limit,
                                                  @Query("offset") int offset);


    /**
     * 搜索用户
     *
     * @return
     */
    @GET("/search?type=1002")
    Observable<SearchUserResponse> searchUser(@Query("keywords") String keywords,
                                              @Query("limit") int limit,
                                              @Query("offset") int offset);

    /**
     * 搜索单曲
     *
     * @return
     */
    @GET("/search?type=1")
    Observable<SearchSongResponse> searchSong(@Query("keywords") String keywords,
                                              @Query("limit") int limit,
                                              @Query("offset") int offset);


    /**
     * 搜索单曲
     *
     * @return
     */
    @GET("/search?type=10")
    Observable<SearchAlbumResponse> searchAlbum(@Query("keywords") String keywords,
                                                @Query("limit") int limit,
                                                @Query("offset") int offset);


    /**
     * 获取粉丝列表
     *
     * @param uid
     * @param limit
     * @param offset
     * @return
     */
    @GET("/user/followeds")
    Observable<FollowerResponse> getFollowers(@Query("uid") long uid,
                                              @Query("limit") int limit,
                                              @Query("offset") int offset);


    /**
     * 获取关注列表
     *
     * @param uid
     * @param limit
     * @param offset
     * @return
     */
    @GET("/user/follows")
    Observable<FollowResponse> getFollow(@Query("uid") int uid,
                                         @Query("limit") int limit,
                                         @Query("offset") int offset);


    @GET("/scrobble")
    Observable<BaseResponse> scrobble(@Query("id") long id,
                                      @Query("sourceid") long sourceid);


    @GET("/song/url?br=320000")
    Observable<SingleSongResponse> getSongUrl(@Query("id") long id);


    @GET("/simi/mv")
    Observable<RelatedMvResponse> getRelatedMv(@Query("mvid") int mvid);


    /**
     * 注册
     *
     * @param phone 电话号码
     * @param password 密码
     * @param captcha 验证码
     * @param nickname 昵称
     * @return
     */
    @GET("/captch/register")
    Observable<LoginResponse> signNetEasy(@Query("phone") String phone,
                                              @Query("password") String password,
                                              @Query("captcha") String captcha,
                                              @Query("nickname") String nickname);



    @GET("/captch/sent")
    Observable<BaseResponse> getSignNumber(@Query("phone") String phone);




    @GET("/song/detail")
    Observable<SongDetailResponse> getSongDetail(@Query("ids") String ids);


    @GET("/artist/sublist")
    Observable<FavorArtistResponse> getFavorArtist(@Query("limit") int limit,
                                                   @Query("offset") int offset,
                                                   @Query("timestamp") long timestamp);


    @GET("/album/sublist")
    Observable<FavorAlbumResponse> getFavorAlbum(@Query("limit") int limit,
                                                 @Query("offset") int offset,
                                                 @Query("timestamp") long timestamp);


    @GET("/album")
    Observable<AlbumResponse> getAlbumDetail(@Query("id") long id,
                                              @Query("timestamp") long timestamp);



    @GET("/artists")
    Observable<ArtistResponse> getArtistDetail(@Query("id") int id,
                                               @Query("timestamp") long timestamp);


}
