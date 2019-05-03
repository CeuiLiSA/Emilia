package ceuilisa.mirai.network;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Common;

/**
 * 正在播放列表
 */
public class MusicChannel {

    private volatile static MusicChannel instance = null;
    private List<TracksBean> musicList = new ArrayList<>();
    private MusicChannel() {
    }

    public static MusicChannel get() {
        if (instance == null) {
            synchronized (MusicChannel.class) {
                if (instance == null) {
                    instance = new MusicChannel();
                    Common.showToast("新建了MusicChannel实例");
                }
            }
        }

        return instance;
    }

    public List<TracksBean> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<TracksBean> illustList) {
        this.musicList = illustList;
    }
}
