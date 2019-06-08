package ceuilisa.mirai.interf;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ceuilisa.mirai.response.LocalMusic;
import ceuilisa.mirai.response.TracksBean;
import retrofit2.http.DELETE;

@Dao
public interface TrackDao {

    @Query("SELECT * FROM track_table")
    List<LocalMusic> getLocalTracks();


    @Delete
    void deleteTrack(LocalMusic localMusic);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertTrack(LocalMusic user);
}
