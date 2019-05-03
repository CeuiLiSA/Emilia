package ceuilisa.mirai.nodejs;

import java.util.List;

public class AlbumBean {
    /**
     * name : TVアニメ「NEW GAME!!」キャラクターソングミニアルバム2「SINGin' SING UP♪♪♪♪」
     * id : 37465070
     * type : 专辑
     * size : 8
     * picId : 109951163127818900
     * blurPicUrl : http://p1.music.126.net/vojUub6YEGDqQW1ZSALNIg==/109951163127818893.jpg
     * companyId : 0
     * pic : 109951163127818900
     * picUrl : http://p1.music.126.net/vojUub6YEGDqQW1ZSALNIg==/109951163127818893.jpg
     * publishTime : 1516723200007
     * description :
     * tags :
     * company : KADOKAWA
     * briefDesc :
     * artist : {"name":"","id":0,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0}
     * songs : []
     * alias : []
     * status : 0
     * copyrightId : 0
     * commentThreadId : R_AL_3_37465070
     * artists : [{"name":"V.A.","id":21138,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0}]
     * subType : 录音室版
     * transName : null
     * picId_str : 109951163127818893
     */

    private String name;
    private long id;
    private String type;
    private int size;
    private long picId;
    private String blurPicUrl;
    private int companyId;
    private long pic;
    private String picUrl;
    private long publishTime;
    private String description;
    private String tags;
    private String company;
    private String briefDesc;
    private ArtistBean artist;
    private int status;
    private int copyrightId;
    private String commentThreadId;
    private String subType;
    private String transName;
    private String picId_str;
    private List<?> songs;
    private List<String> alias;
    private List<ArtistBean> artists;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getPicId() {
        return picId;
    }

    public void setPicId(long picId) {
        this.picId = picId;
    }

    public String getBlurPicUrl() {
        return blurPicUrl;
    }

    public void setBlurPicUrl(String blurPicUrl) {
        this.blurPicUrl = blurPicUrl;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public long getPic() {
        return pic;
    }

    public void setPic(long pic) {
        this.pic = pic;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBriefDesc() {
        return briefDesc;
    }

    public void setBriefDesc(String briefDesc) {
        this.briefDesc = briefDesc;
    }

    public ArtistBean getArtist() {
        return artist;
    }

    public void setArtist(ArtistBean artist) {
        this.artist = artist;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCopyrightId() {
        return copyrightId;
    }

    public void setCopyrightId(int copyrightId) {
        this.copyrightId = copyrightId;
    }

    public String getCommentThreadId() {
        return commentThreadId;
    }

    public void setCommentThreadId(String commentThreadId) {
        this.commentThreadId = commentThreadId;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public String getPicId_str() {
        return picId_str;
    }

    public void setPicId_str(String picId_str) {
        this.picId_str = picId_str;
    }

    public List<?> getSongs() {
        return songs;
    }

    public void setSongs(List<?> songs) {
        this.songs = songs;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public List<ArtistBean> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistBean> artists) {
        this.artists = artists;
    }
}
