package ceuilisa.mirai.network;

import ceuilisa.mirai.response.LrcResponse;
import ceuilisa.mirai.response.PlayRecordResponse;
import ceuilisa.mirai.response.PlayListTitleResponse;
import ceuilisa.mirai.response.PlayWeekHistoryResponse;
import ceuilisa.mirai.response.SingleSongResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
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


}
