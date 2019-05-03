package ceuilisa.mirai.utils;

import java.util.List;

import ceuilisa.mirai.network.MusicChannel;
import ceuilisa.mirai.response.HistorySongBean;

public class Translate {
    public static void translateMusic(List<HistorySongBean> allDataBeans) {
        MusicChannel channel = MusicChannel.get();
        channel.getMusicList().clear();
        for (int i = 0; i < allDataBeans.size(); i++) {
            channel.getMusicList().add(allDataBeans.get(i).getSong());
        }
    }
}
