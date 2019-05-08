package ceuilisa.mirai.nodejs;

import java.util.List;

import ceuilisa.mirai.interf.ListShow;
import ceuilisa.mirai.response.BaseResponse;

public class FavorArtistResponse extends BaseResponse implements ListShow<ArtistBean> {


    /**
     * data : [{"info":"","id":1120106,"name":"高橋李依","trans":"高桥李依","alias":["たかはし りえ"],"albumSize":9,"mvSize":13,"picId":1375489060995697,"picUrl":"https://p1.music.126.net/lMdIHny_X-iYW6ON1iU1dQ==/1375489060995697.jpg","img1v1Url":"https://p2.music.126.net/lMdIHny_X-iYW6ON1iU1dQ==/1375489060995697.jpg"},{"info":"","id":19353,"name":"EGOIST","trans":null,"alias":["エゴイスト"],"albumSize":16,"mvSize":13,"picId":109951163955718200,"picUrl":"https://p1.music.126.net/aN-DehZP5tnXnPejwK8GHg==/109951163955718201.jpg","img1v1Url":"https://p2.music.126.net/aN-DehZP5tnXnPejwK8GHg==/109951163955718201.jpg"},{"info":"","id":16152,"name":"Aimer","trans":null,"alias":["エメ"],"albumSize":38,"mvSize":69,"picId":109951163992209300,"picUrl":"https://p1.music.126.net/LJoFlsgnmADsBq-9KpLEEg==/109951163992209298.jpg","img1v1Url":"https://p2.music.126.net/LJoFlsgnmADsBq-9KpLEEg==/109951163992209298.jpg"},{"info":"","id":768306,"name":"网易云音乐","trans":null,"alias":[],"albumSize":10,"mvSize":39,"picId":2274889557915240,"picUrl":"https://p1.music.126.net/0R_1imPfGwoeT199iPa5dA==/2274889557915240.jpg","img1v1Url":"https://p2.music.126.net/0R_1imPfGwoeT199iPa5dA==/2274889557915240.jpg"}]
     * hasMore : false
     * count : 4
     */

    private boolean hasMore;
    private int count;
    private List<ArtistBean> data;

    @Override
    public List<ArtistBean> getList() {
        return data;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ArtistBean> getData() {
        return data;
    }

    public void setData(List<ArtistBean> data) {
        this.data = data;
    }
}
