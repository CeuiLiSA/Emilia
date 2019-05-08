package ceuilisa.mirai.network;

import java.util.List;

import ceuilisa.mirai.response.AlbumResponse;
import ceuilisa.mirai.response.ArtistResponse;
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
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AppApi {

    /*@GET("cloudmusic/?type=playlist")
    Observable<PlayListDetailResponse> getPlayListDetail(@Query("id") String id);*/

    @GET("cloudmusic/?type=song&br=320000")
    Observable<SingleSongResponse> getSingleSong(@Query("id") long id);

    @GET("cloudmusic/?type=lyric")
    Observable<LrcResponse> getLrc(@Query("id") String id);






    /**
     * 搜索歌单
     *
     * @param s      关键字
     * @param limit  每页结果数量
     * @param offset 偏移量
     * @return Observable<PlayListTitleResponse>
     */
    @GET("cloudmusic/?type=search&search_type=1000")
    Observable<PlayListTitleResponse> searchPlaylist(@Query("s") String s,
                                                     @Query("limit") int limit,
                                                     @Query("offset") int offset);



    //https://v1.hitokoto.cn/nm/record/113568254?weekly=false

    @GET("cloudmusic/?type=record")
    Observable<PlayAllHistoryResponse> getAllPlayHistory(@Query("id") int id,
                                                         @Query("period") int period);

    @GET("cloudmusic/?type=record")
    Observable<PlayWeekHistoryResponse> getWeekPlayHistory(@Query("id") int id,
                                                           @Query("period") int period);


}
