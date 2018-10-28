package ceuilisa.mirai.utils;

import java.util.List;

import ceuilisa.mirai.response.HistorySongBean;
import ceuilisa.mirai.response.TracksBean;

public class Translate {

    public static void translateMusic(List<HistorySongBean> allDataBeans) {
        Reference.allSongs.clear();
        for (int i = 0; i < allDataBeans.size(); i++) {
            Reference.allSongs.add(allDataBeans.get(i).getSong());
        }
        /*for (int i = 0; i < allDataBeans.size(); i++) {
            TracksBean tracksBean = new TracksBean(
                    allDataBeans.get(i).getSong().getName(),
                    allDataBeans.get(i).getSong().getId(),
                    allDataBeans.get(i).getSong().getAl(),
                    allDataBeans.get(i).getSong().getDt(),
                    allDataBeans.get(i).getSong().getPublishTime(),
                    allDataBeans.get(i).getSong().getAr(),
                    allDataBeans.get(i).getSong().getAlia());
            Reference.allSongs.add(tracksBean);
        }*/
    }
}
