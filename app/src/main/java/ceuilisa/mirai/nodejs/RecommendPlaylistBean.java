package ceuilisa.mirai.nodejs;

/**
 * 推荐歌单
 */
public  class RecommendPlaylistBean {
    /**
     * id : 70741032
     * type : 1
     * name : 英文歌曲——适合学习英语
     * copywriter : 根据你喜欢的单曲《Trouble Is A Friend》推荐
     * picUrl : https://p2.music.126.net/Zuao0qGaRjKksGz-9WwE_w==/2923601419389486.jpg
     * playcount : 7780139
     * createTime : 1430731440023
     * creator : {"backgroundImgIdStr":"18600438208966560","avatarImgIdStr":"19128203788504834","mutual":false,"remarkName":null,"avatarImgId":19128203788504830,"backgroundImgId":18600438208966560,"detailDescription":"","defaultAvatar":false,"expertTags":null,"djStatus":10,"backgroundUrl":"http://p1.music.126.net/TTjs9ZI2-IGwz-hKWn-nOw==/18600438208966560.jpg","description":"","birthday":746294400000,"city":310101,"followed":false,"avatarUrl":"https://p2.music.126.net/HXrcxQpd6LSlQqfMJnp4XA==/19128203788504834.jpg","authStatus":0,"userType":0,"accountStatus":0,"userId":42855766,"vipType":0,"province":310000,"gender":1,"nickname":"冷小石","signature":"年轻的心有了白发~","authority":0}
     * trackCount : 82
     * userId : 42855766
     * alg : bysong_play_rt
     */

    private int id;
    private int type;
    private String name;
    private String copywriter;
    private String picUrl;
    private int playcount;
    private long createTime;
    private CreatorBean creator;
    private int trackCount;
    private int userId;
    private String alg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCopywriter() {
        return copywriter;
    }

    public void setCopywriter(String copywriter) {
        this.copywriter = copywriter;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public CreatorBean getCreator() {
        return creator;
    }

    public void setCreator(CreatorBean creator) {
        this.creator = creator;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }
}
