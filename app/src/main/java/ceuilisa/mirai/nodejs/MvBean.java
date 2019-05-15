package ceuilisa.mirai.nodejs;

import java.io.Serializable;
import java.util.List;

public class MvBean implements Serializable {
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

    private long id;
    private String cover;
    private String picUrl;
    private String name;
    private int playCount;
    private String briefDesc;
    private Object desc;
    private String artistName;
    private int artistId;
    private int duration;
    private int mark;
    private int lastRank;
    private int score;
    private boolean subed;
    private List<ArtistBean> artists;
    private List<String> alias;
    private long coverId;
    private int subCount;
    private int shareCount;
    private int likeCount;
    private int commentCount;
    private int nType;
    private String publishTime;
    private boolean isReward;
    private String commentThreadId;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public long getCoverId() {
        return coverId;
    }

    public void setCoverId(long coverId) {
        this.coverId = coverId;
    }

    public int getSubCount() {
        return subCount;
    }

    public void setSubCount(int subCount) {
        this.subCount = subCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getnType() {
        return nType;
    }

    public void setnType(int nType) {
        this.nType = nType;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public boolean isReward() {
        return isReward;
    }

    public void setReward(boolean reward) {
        isReward = reward;
    }

    public String getCommentThreadId() {
        return commentThreadId;
    }

    public void setCommentThreadId(String commentThreadId) {
        this.commentThreadId = commentThreadId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCover() {
        if (cover != null && cover.length() != 0) {
            return cover;
        }
        if (picUrl != null && picUrl.length() != 0) {
            return picUrl;
        }
        return null;
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

    public String getBriefDesc() {
        return briefDesc;
    }

    public void setBriefDesc(String briefDesc) {
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

    public List<ArtistBean> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistBean> artists) {
        this.artists = artists;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }
}