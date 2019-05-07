package ceuilisa.mirai.nodejs;

import java.util.List;

import ceuilisa.mirai.interf.ListShow;
import ceuilisa.mirai.response.BaseResponse;
import ceuilisa.mirai.response.TracksBean;

public class SongDetailResponse extends BaseResponse implements ListShow<TracksBean> {

    public List<TracksBean> getSongs() {
        return songs;
    }

    public void setSongs(List<TracksBean> songs) {
        this.songs = songs;
    }

    private List<TracksBean> songs;


    @Override
    public List<TracksBean> getList() {
        return songs;
    }
}
