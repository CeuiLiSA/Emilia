package ceuilisa.mirai.response;

import java.util.List;

import ceuilisa.mirai.interf.ListShow;
import ceuilisa.mirai.nodejs.ArtistBean;

public class ArtistResponse extends BaseResponse implements ListShow<TracksBean> {


    /**
     * code : 200
     * artist : {"img1v1Id":1413971969299165,"topicPerson":0,"followed":false,"albumSize":51,"img1v1Url":"https://p4.music.126.net/G08LFZX6p95hlFJhX7ZMqQ==/1413971969299165.jpg","picUrl":"https://p3.music.126.net/dMh3-v4lIHTN15pgTIsbig==/1379887108659654.jpg","trans":"","briefDesc":"阿保刚(あぼ たけし)是一位出身日本的游戏音乐作曲家。\n原本在KID公司为该公司的恋爱冒险游戏制作各项音乐及效果配乐。在2006年12月该公司倒闭后, 加盟5pb.公司。音乐方面, 他跟多数的日本作曲家一样, 是麦金塔电脑的爱好者, 甚至在他的个人网页上还有\u201cIntel outside ! Microsoft outside ! Windows outside !\u201d之类的字句。 不过现在他使用PC机, 个人网站上也早没有这样的语句, 并且表示很喜欢这样的升级。","musicSize":1689,"picId":1379887108659654,"name":"阿保剛","id":160387,"alias":["あぼ たけし"],"publishTime":1538799850857,"mvSize":0}
     * more : true
     */

    private ArtistBean artist;
    private boolean more;
    private List<TracksBean> hotSongs;

    public List<TracksBean> getHotSongs() {
        return hotSongs;
    }

    public void setHotSongs(List<TracksBean> hotSongs) {
        this.hotSongs = hotSongs;
    }

    public ArtistBean getArtist() {
        return artist;
    }

    public void setArtist(ArtistBean artist) {
        this.artist = artist;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    @Override
    public List<TracksBean> getList() {
        return hotSongs;
    }
}
