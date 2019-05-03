package ceuilisa.mirai.nodejs;

import java.util.List;

import ceuilisa.mirai.interf.ListShow;
import ceuilisa.mirai.response.BaseResponse;

public class RecmdMvResponse extends BaseResponse implements ListShow<MvBean> {


    /**
     * category : 3
     * result : [{"id":10866606,"type":5,"name":"Paralyzed","copywriter":"编辑推荐：老棉Marshmello热单《Paralyzed》MV上线！","picUrl":"https://p1.music.126.net/83WHIj4E6x7mYdbEntcdOg==/109951164043616648.jpg","canDislike":false,"duration":189000,"playCount":66083,"subed":false,"artists":[{"id":1060019,"name":"Marshmello"}],"artistName":"Marshmello","artistId":1060019,"alg":"featured"},{"id":10865658,"type":5,"name":"Heaven Can Wait (歌词版)","copywriter":"编辑推荐：LSD新单《Heaven Can Wait》歌词版MV释出！","picUrl":"https://p1.music.126.net/iAfpTt5Ai-5G0M0wxHdxEA==/109951164043805663.jpg","canDislike":false,"duration":196000,"playCount":5822,"subed":false,"artists":[{"id":575566,"name":"LSD"},{"id":74625,"name":"Sia"},{"id":31645,"name":"Diplo"},{"id":38644,"name":"Labrinth"}],"artistName":"LSD","artistId":575566,"alg":"featured"},{"id":10866619,"type":5,"name":"Alligator","copywriter":"最新热门MV推荐","picUrl":"https://p2.music.126.net/bsnOVKZtVtmQvaS0fLCnyg==/109951164045732213.jpg","canDislike":true,"duration":185000,"playCount":1488,"subed":false,"artists":[{"id":98414,"name":"Of Monsters And Men"}],"artistName":"Of Monsters And Men","artistId":98414,"alg":"hot_server"},{"id":10865669,"type":5,"name":"La Boca","copywriter":"最新热门MV推荐","picUrl":"https://p2.music.126.net/XOFCcPgmJJKxZ8RmrclLxQ==/109951164045629450.jpg","canDislike":true,"duration":219000,"playCount":1384,"subed":false,"artists":[{"id":12035107,"name":"Mau y Ricky"},{"id":507846,"name":"Camilo"}],"artistName":"Mau y Ricky","artistId":12035107,"alg":"hot_server"}]
     */

    private int category;
    private List<MvBean> result;

    @Override
    public List<MvBean> getList() {
        return result;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public List<MvBean> getResult() {
        return result;
    }

    public void setResult(List<MvBean> result) {
        this.result = result;
    }

}
