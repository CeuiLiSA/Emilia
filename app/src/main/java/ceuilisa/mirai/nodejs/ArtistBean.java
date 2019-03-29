package ceuilisa.mirai.nodejs;

import java.util.List;

public class ArtistBean {
    /**
     * name : 高田憂希
     * id : 12074587
     * picId : 0
     * img1v1Id : 0
     * briefDesc :
     * picUrl : http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
     * img1v1Url : http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
     * albumSize : 0
     * alias : []
     * trans :
     * musicSize : 0
     */

    private String name;
    private int id;
    private String briefDesc;
    private String picUrl;
    private String img1v1Url;
    private int albumSize;
    private String trans;
    private int musicSize;
    private List<String> alias;

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

    public String getBriefDesc() {
        return briefDesc;
    }

    public void setBriefDesc(String briefDesc) {
        this.briefDesc = briefDesc;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getImg1v1Url() {
        return img1v1Url;
    }

    public void setImg1v1Url(String img1v1Url) {
        this.img1v1Url = img1v1Url;
    }

    public int getAlbumSize() {
        return albumSize;
    }

    public void setAlbumSize(int albumSize) {
        this.albumSize = albumSize;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public int getMusicSize() {
        return musicSize;
    }

    public void setMusicSize(int musicSize) {
        this.musicSize = musicSize;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }
}
