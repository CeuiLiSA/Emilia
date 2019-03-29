package ceuilisa.mirai.nodejs;

import ceuilisa.mirai.response.TracksBean;

public class TempJson {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private TracksBean song;

    public TracksBean getSong() {
        return song;
    }

    public void setSong(TracksBean song) {
        this.song = song;
    }
}
