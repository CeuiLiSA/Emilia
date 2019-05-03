package ceuilisa.mirai.nodejs;

import ceuilisa.mirai.response.TracksBean;

public class TempJson {
    private String msg;
    private TracksBean song;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TracksBean getSong() {
        return song;
    }

    public void setSong(TracksBean song) {
        this.song = song;
    }
}
