package ceuilisa.mirai.response;

import java.util.List;

public class ArtistResponse {


    /**
     * code : 200
     * artist : {"img1v1Id":1413971969299165,"topicPerson":0,"followed":false,"albumSize":51,"img1v1Url":"https://p4.music.126.net/G08LFZX6p95hlFJhX7ZMqQ==/1413971969299165.jpg","picUrl":"https://p3.music.126.net/dMh3-v4lIHTN15pgTIsbig==/1379887108659654.jpg","trans":"","briefDesc":"阿保刚(あぼ たけし)是一位出身日本的游戏音乐作曲家。\n原本在KID公司为该公司的恋爱冒险游戏制作各项音乐及效果配乐。在2006年12月该公司倒闭后, 加盟5pb.公司。音乐方面, 他跟多数的日本作曲家一样, 是麦金塔电脑的爱好者, 甚至在他的个人网页上还有\u201cIntel outside ! Microsoft outside ! Windows outside !\u201d之类的字句。 不过现在他使用PC机, 个人网站上也早没有这样的语句, 并且表示很喜欢这样的升级。","musicSize":1689,"picId":1379887108659654,"name":"阿保剛","id":160387,"alias":["あぼ たけし"],"publishTime":1538799850857,"mvSize":0}
     * more : true
     */

    private int code;
    private ArtistBean artist;
    private boolean more;
    public List<TracksBean> getHotSongs() {
        return hotSongs;
    }

    public void setHotSongs(List<TracksBean> hotSongs) {
        this.hotSongs = hotSongs;
    }

    private List<TracksBean> hotSongs;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public static class ArtistBean {
        /**
         * img1v1Id : 1413971969299165
         * topicPerson : 0
         * followed : false
         * albumSize : 51
         * img1v1Url : https://p4.music.126.net/G08LFZX6p95hlFJhX7ZMqQ==/1413971969299165.jpg
         * picUrl : https://p3.music.126.net/dMh3-v4lIHTN15pgTIsbig==/1379887108659654.jpg
         * trans :
         * briefDesc : 阿保刚(あぼ たけし)是一位出身日本的游戏音乐作曲家。
         原本在KID公司为该公司的恋爱冒险游戏制作各项音乐及效果配乐。在2006年12月该公司倒闭后, 加盟5pb.公司。音乐方面, 他跟多数的日本作曲家一样, 是麦金塔电脑的爱好者, 甚至在他的个人网页上还有“Intel outside ! Microsoft outside ! Windows outside !”之类的字句。 不过现在他使用PC机, 个人网站上也早没有这样的语句, 并且表示很喜欢这样的升级。
         * musicSize : 1689
         * picId : 1379887108659654
         * name : 阿保剛
         * id : 160387
         * alias : ["あぼ たけし"]
         * publishTime : 1538799850857
         * mvSize : 0
         */

        private long img1v1Id;
        private int topicPerson;
        private boolean followed;
        private int albumSize;
        private String img1v1Url;
        private String picUrl;
        private String trans;
        private String briefDesc;
        private int musicSize;
        private long picId;
        private String name;
        private int id;
        private long publishTime;
        private int mvSize;
        private List<String> alias;

        public long getImg1v1Id() {
            return img1v1Id;
        }

        public void setImg1v1Id(long img1v1Id) {
            this.img1v1Id = img1v1Id;
        }

        public int getTopicPerson() {
            return topicPerson;
        }

        public void setTopicPerson(int topicPerson) {
            this.topicPerson = topicPerson;
        }

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public int getAlbumSize() {
            return albumSize;
        }

        public void setAlbumSize(int albumSize) {
            this.albumSize = albumSize;
        }

        public String getImg1v1Url() {
            return img1v1Url;
        }

        public void setImg1v1Url(String img1v1Url) {
            this.img1v1Url = img1v1Url;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getTrans() {
            return trans;
        }

        public void setTrans(String trans) {
            this.trans = trans;
        }

        public String getBriefDesc() {
            return briefDesc;
        }

        public void setBriefDesc(String briefDesc) {
            this.briefDesc = briefDesc;
        }

        public int getMusicSize() {
            return musicSize;
        }

        public void setMusicSize(int musicSize) {
            this.musicSize = musicSize;
        }

        public long getPicId() {
            return picId;
        }

        public void setPicId(long picId) {
            this.picId = picId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getMvSize() {
            return mvSize;
        }

        public void setMvSize(int mvSize) {
            this.mvSize = mvSize;
        }

        public List<String> getAlias() {
            return alias;
        }

        public void setAlias(List<String> alias) {
            this.alias = alias;
        }
    }
}
