package ceuilisa.mirai.nodejs;

import java.util.List;

public class MvBean {
    /**
     * id : 10848831
     * cover : http://p1.music.126.net/xYgPXyHWHmTQdrBjgh3VBA==/109951163828442446.jpg
     * name : 下坠Falling
     * playCount : 2098712
     * briefDesc : null
     * desc : null
     * artistName : Corki
     * artistId : 14608268
     * duration : 0
     * mark : 0
     * lastRank : 1
     * score : 24966
     * subed : false
     * artists : [{"id":14608268,"name":"Corki"}]
     * alias : ["电影《绿皮书》推广曲"]
     */

    private int id;
    private String cover;
    private String name;
    private int playCount;
    private Object briefDesc;
    private Object desc;
    private String artistName;
    private int artistId;
    private int duration;
    private int mark;
    private int lastRank;
    private int score;
    private boolean subed;
    private List<ArtistsBean> artists;
    private List<String> alias;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public Object getBriefDesc() {
        return briefDesc;
    }

    public void setBriefDesc(Object briefDesc) {
        this.briefDesc = briefDesc;
    }

    public Object getDesc() {
        return desc;
    }

    public void setDesc(Object desc) {
        this.desc = desc;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getLastRank() {
        return lastRank;
    }

    public void setLastRank(int lastRank) {
        this.lastRank = lastRank;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isSubed() {
        return subed;
    }

    public void setSubed(boolean subed) {
        this.subed = subed;
    }

    public List<ArtistsBean> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistsBean> artists) {
        this.artists = artists;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public static class ArtistsBean {
        /**
         * id : 14608268
         * name : Corki
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}