package ceuilisa.mirai.nodejs;

import java.util.List;

public class PlaylistBean {
    /**
     * subscribers : []
     * subscribed : false
     * creator : {"defaultAvatar":false,"province":1000000,"authStatus":0,"followed":false,"avatarUrl":"http://p1.music.126.net/9BSnF5WgYnnBjIqi2aLm-w==/19085322835203895.jpg","accountStatus":0,"gender":1,"city":1002400,"birthday":877276800000,"userId":113568254,"userType":0,"nickname":"CeuiLiSA","signature":"肥宅，腿控，御姐控，救老婆，真正的粉丝，只土不豪，约，爱过，不是很懂你们二次元 ( \u2022 ̀ω\u2022́ )✧","description":"","detailDescription":"","avatarImgId":19085322835203896,"backgroundImgId":109951163198268110,"backgroundUrl":"http://p1.music.126.net/id-110tprTCxF4BXNbZIOw==/109951163198268119.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":11,"remarkName":null,"backgroundImgIdStr":"109951163198268119","avatarImgIdStr":"19085322835203895","avatarImgId_str":"19085322835203895"}
     * artists : null
     * tracks : null
     * privacy : 0
     * trackUpdateTime : 1553658343003
     * trackCount : 377
     * updateTime : 1552186537768
     * coverImgId : 3397490930790007
     * specialType : 5
     * anonimous : false
     * newImported : false
     * totalDuration : 0
     * commentThreadId : A_PL_0_139786239
     * subscribedCount : 4
     * adType : 0
     * createTime : 1450139964170
     * highQuality : false
     * coverImgUrl : https://p1.music.126.net/GjCdp5ET4MpWW9yNKTtsXg==/3397490930790007.jpg
     * playCount : 5372
     * trackNumberUpdateTime : 1552186537768
     * cloudTrackCount : 0
     * tags : []
     * ordered : true
     * userId : 113568254
     * description : null
     * status : 0
     * name : CeuiLiSA喜欢的音乐
     * id : 139786239
     * coverImgId_str : 109951163041416248
     */

    private boolean subscribed;
    private CreatorBean creator;
    private Object artists;
    private Object tracks;
    private int privacy;
    private long trackUpdateTime;
    private int trackCount;
    private long updateTime;
    private long coverImgId;
    private int specialType;
    private boolean anonimous;
    private boolean newImported;
    private int totalDuration;
    private String commentThreadId;
    private int subscribedCount;
    private int adType;
    private long createTime;
    private boolean highQuality;
    private String coverImgUrl;
    private int playCount;
    private long trackNumberUpdateTime;
    private int cloudTrackCount;
    private boolean ordered;
    private int userId;
    private Object description;
    private int status;
    private String name;
    private long id;
    private String coverImgId_str;
    private List<?> subscribers;
    private List<?> tags;

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public CreatorBean getCreator() {
        return creator;
    }

    public void setCreator(CreatorBean creator) {
        this.creator = creator;
    }

    public Object getArtists() {
        return artists;
    }

    public void setArtists(Object artists) {
        this.artists = artists;
    }

    public Object getTracks() {
        return tracks;
    }

    public void setTracks(Object tracks) {
        this.tracks = tracks;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public long getTrackUpdateTime() {
        return trackUpdateTime;
    }

    public void setTrackUpdateTime(long trackUpdateTime) {
        this.trackUpdateTime = trackUpdateTime;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getCoverImgId() {
        return coverImgId;
    }

    public void setCoverImgId(long coverImgId) {
        this.coverImgId = coverImgId;
    }

    public int getSpecialType() {
        return specialType;
    }

    public void setSpecialType(int specialType) {
        this.specialType = specialType;
    }

    public boolean isAnonimous() {
        return anonimous;
    }

    public void setAnonimous(boolean anonimous) {
        this.anonimous = anonimous;
    }

    public boolean isNewImported() {
        return newImported;
    }

    public void setNewImported(boolean newImported) {
        this.newImported = newImported;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getCommentThreadId() {
        return commentThreadId;
    }

    public void setCommentThreadId(String commentThreadId) {
        this.commentThreadId = commentThreadId;
    }

    public int getSubscribedCount() {
        return subscribedCount;
    }

    public void setSubscribedCount(int subscribedCount) {
        this.subscribedCount = subscribedCount;
    }

    public int getAdType() {
        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean isHighQuality() {
        return highQuality;
    }

    public void setHighQuality(boolean highQuality) {
        this.highQuality = highQuality;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public long getTrackNumberUpdateTime() {
        return trackNumberUpdateTime;
    }

    public void setTrackNumberUpdateTime(long trackNumberUpdateTime) {
        this.trackNumberUpdateTime = trackNumberUpdateTime;
    }

    public int getCloudTrackCount() {
        return cloudTrackCount;
    }

    public void setCloudTrackCount(int cloudTrackCount) {
        this.cloudTrackCount = cloudTrackCount;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCoverImgId_str() {
        return coverImgId_str;
    }

    public void setCoverImgId_str(String coverImgId_str) {
        this.coverImgId_str = coverImgId_str;
    }

    public List<?> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<?> subscribers) {
        this.subscribers = subscribers;
    }

    public List<?> getTags() {
        return tags;
    }

    public void setTags(List<?> tags) {
        this.tags = tags;
    }
}
