package ceuilisa.mirai.utils;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.network.MusicChannel;
import ceuilisa.mirai.response.HistorySongBean;
import ceuilisa.mirai.response.TracksBean;

public class Translate {
    public static void translateMusic(List<HistorySongBean> allDataBeans) {
        MusicChannel channel = MusicChannel.get();
        channel.getMusicList().clear();
        for (int i = 0; i < allDataBeans.size(); i++) {
            channel.getMusicList().add(allDataBeans.get(i).getSong());
        }
    }


    public static List<TracksBean> translateMusicToList(List<HistorySongBean> allDataBeans) {
        List<TracksBean> tempList = new ArrayList<>();

        for (int i = 0; i < allDataBeans.size(); i++) {
            tempList.add(allDataBeans.get(i).getSong());
        }
        return tempList;
    }
}
