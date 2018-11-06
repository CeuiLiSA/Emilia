package ceuilisa.mirai.network;

import java.util.List;

import ceuilisa.mirai.response.AlbumResponse;
import ceuilisa.mirai.response.ArtistResponse;
import ceuilisa.mirai.response.BaseResponse;
import ceuilisa.mirai.response.CommentResponse;
import ceuilisa.mirai.response.DeleteImageResponse;
import ceuilisa.mirai.response.ItemResponse;
import ceuilisa.mirai.response.LrcResponse;
import ceuilisa.mirai.response.PlayAllHistoryResponse;
import ceuilisa.mirai.response.PlayListDetailResponse;
import ceuilisa.mirai.response.PlayListTitleResponse;
import ceuilisa.mirai.response.PlayWeekHistoryResponse;
import ceuilisa.mirai.response.SearchAlbumResponse;
import ceuilisa.mirai.response.SingleSongResponse;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AppApi {

    @GET("cloudmusic/?type=playlist")
    Observable<PlayListDetailResponse> getPlayListDetail(@Query("id") String id);

    @GET("cloudmusic/?type=song&br=320000")
    Observable<SingleSongResponse> getSingleSong(@Query("id") String id);

    @GET("cloudmusic/?type=lyric")
    Observable<LrcResponse> getLrc(@Query("id") String id);

    @GET("id_delete")
    Observable<DeleteImageResponse> deleteImage(@Query("id") String id);

    @GET("piclist")
    Observable<List<ItemResponse>> getAllItem();

    @Multipart
    @POST("uploadimg")
    Call<DeleteImageResponse> changeHeadImg(@Part MultipartBody.Part photo);


    // https://api.imjad.cn/cloudmusic/?type=comments&id=26196652&limit=10&offset=20
    @GET("cloudmusic/?type=comments")
    Observable<CommentResponse> getComment(@Query("id") String id,
                                           @Query("limit") int limit,
                                           @Query("offset") int offset);

    @GET("cloudmusic/?type=search&search_type=1000")
    Observable<PlayListTitleResponse> searchPlaylist(@Query("s") String s,
                                                     @Query("limit") int limit,
                                                     @Query("offset") int offset);

    @GET("cloudmusic/?type=search&search_type=10")
    Observable<SearchAlbumResponse> searchAlbum(@Query("s") String s,
                                                   @Query("limit") int limit,
                                                   @Query("offset") int offset);


    //https://v1.hitokoto.cn/nm/record/113568254?weekly=false

    @GET("cloudmusic/?type=record")
    Observable<PlayAllHistoryResponse> getAllPlayHistory(@Query("id") String id,
                                                         @Query("period") int period);

    @GET("cloudmusic/?type=record")
    Observable<PlayWeekHistoryResponse> getWeekPlayHistory(@Query("id") String id,
                                                           @Query("period") int period);

    @GET("cloudmusic/?type=album")
    Observable<AlbumResponse> getAlbum(@Query("id") String id);

    @GET("cloudmusic/?type=artist")
    Observable<ArtistResponse> getArtist(@Query("id") String id);
}
