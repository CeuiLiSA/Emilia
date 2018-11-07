package ceuilisa.mirai.utils;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.response.HistorySongBean;
import ceuilisa.mirai.response.TracksBean;

public class Translate {
    public static void translateMusic(List<HistorySongBean> allDataBeans) {
        MusicService.allSongs = new ArrayList<>();
        for (int i = 0; i < allDataBeans.size(); i++) {
            MusicService.allSongs.add(allDataBeans.get(i).getSong());
        }
    }
}
