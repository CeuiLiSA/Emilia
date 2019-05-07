package ceuilisa.mirai.response;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "track_table")
public class LocalMusic {
    //设置主键，并且定义自增增
    @PrimaryKey()
    @ColumnInfo(name = "track_id")
    private long id;
    //字段映射具体的数据表字段名
    @ColumnInfo(name = "track_json")
    private String trackJson;
    //字段映射具体的数据表字段名

    public LocalMusic() {
    }

    //其他构造方法要添加@Ignore注解

    //Setter、Getter方法是需要添加的，为了支持room工作

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTrackJson() {
        return trackJson;
    }

    public void setTrackJson(String trackJson) {
        this.trackJson = trackJson;
    }
}
