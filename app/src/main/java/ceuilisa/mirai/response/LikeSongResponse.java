package ceuilisa.mirai.response;

import java.util.List;

import ceuilisa.mirai.interf.ListShow;

public class LikeSongResponse extends BaseResponse implements ListShow<Object> {

    private List<Object> songs;

    @Override
    public List<Object> getList() {
        return songs;
    }
}
