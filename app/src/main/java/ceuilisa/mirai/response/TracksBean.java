package ceuilisa.mirai.response;

import java.util.List;

import ceuilisa.mirai.nodejs.AlbumBean;
import ceuilisa.mirai.nodejs.ArtistBean;

public class TracksBean {

    /**
     * name : 願い~あの頃のキミへ~ Acoustic ver.
     * id : 543988578
     * pst : 0
     * t : 0
     * ar : [{"id":954456,"name":"當山みれい","tns":[],"alias":[]}]
     * alia : []
     * pop : 100
     * st : 0
     * rt : null
     * fee : 0
     * v : 2
     * crbt : null
     * cf :
     * al : {"id":37887094,"name":"Dear My Boo","picUrl":"https://p2.music.126.net/wRrEvenyms1DxFfTEiwE_Q==/109951163180867808.jpg","tns":[],"pic_str":"109951163180867808","pic":109951163180867808}
     * dt : 354708
     * h : {"br":320000,"fid":0,"size":14188713,"vd":0}
     * m : {"br":192000,"fid":0,"size":8513245,"vd":0}
     * l : {"br":128000,"fid":0,"size":5675511,"vd":0}
     * a : null
     * cd : 1/1
     * no : 2
     * rtUrl : null
     * ftype : 0
     * rtUrls : []
     * djId : 0
     * copyright : 0
     * s_id : 0
     * rtype : 0
     * rurl : null
     * mst : 9
     * cp : 663018
     * mv : 0
     * publishTime : 1520352000007
     * tns : ["再度和你"]
     */

    private String name;
    private int id;
    private AlbumBean al;
    private int dt;
    private long publishTime;

    public String getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(String isLiked) {
        this.isLiked = isLiked;
    }

    private String isLiked = "0";
    private List<ArtistBean> ar;
    private List<String> alia;

    public List<String> getTns() {
        return tns;
    }

    public void setTns(List<String> tns) {
        this.tns = tns;
    }

    private List<String> tns;

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

    public AlbumBean getAl() {
        return al;
    }

    public void setAl(AlbumBean al) {
        this.al = al;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public List<ArtistBean> getAr() {
        return ar;
    }

    public void setAr(List<ArtistBean> ar) {
        this.ar = ar;
    }

    public List<String> getAlia() {
        return alia;
    }

    public void setAlia(List<String> alia) {
        this.alia = alia;
    }
}
