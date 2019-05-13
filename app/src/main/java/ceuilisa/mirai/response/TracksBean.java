package ceuilisa.mirai.response;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.nodejs.AlbumBean;
import ceuilisa.mirai.nodejs.ArtistBean;

public class TracksBean {
    /**
     * name : 彼女は美しい
     * id : 32320417
     * pst : 0
     * t : 0
     * ar : [{"id":15917,"name":"横山克","tns":[],"alias":[]}]
     * alia : []
     * pop : 55
     * st : 0
     * rt : null
     * fee : 0
     * v : 9
     * crbt : null
     * cf :
     * al : {"id":3154535,"name":"四月は君の嘘 第4巻 特典CD","picUrl":"http://p2.music.126.net/OCwtDZOBZqOQRVMaS0bjjw==/2919203373619208.jpg","tns":[],"pic":2919203373619208}
     * dt : 107000
     * h : {"br":320000,"fid":0,"size":4288536,"vd":6.4937}
     * m : {"br":160000,"fid":0,"size":2144406,"vd":6.98132}
     * l : {"br":96000,"fid":0,"size":1286754,"vd":6.90467}
     * a : null
     * cd : 1
     * no : 1
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
     * publishTime : 1432656000000
     */

    private String name;
    private boolean isLiked = false;
    private long id;
    private int pst;
    private int t;
    private int pop;
    private int st;
    private Object rt;
    private int fee;
    private int v;
    private Object crbt;
    private String cf;
    private AlbumBean al, album;
    private int dt;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    private int duration;
    private Object a;
    private String cd;
    private int no;
    private Object rtUrl;
    private int ftype;
    private int djId;
    private int copyright;
    private String localPath;
    private int s_id;
    private int rtype;
    private Object rurl;
    private int mst;
    private int cp;
    private int mv;
    private long publishTime;
    private List<ArtistBean> ar, artists;
    private List<String> alia, alias;

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    private List<?> rtUrls;
    private H h;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    private long fileSize;

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public AlbumBean getAlbum() {
        return album;
    }

    public void setAlbum(AlbumBean album) {
        this.album = album;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    private String albumName;

    public List<ArtistBean> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistBean> artists) {
        this.artists = artists;
    }

    public H getH() {
        return h;
    }

    public void setH(H h) {
        this.h = h;
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

    public int getPst() {
        return pst;
    }

    public void setPst(int pst) {
        this.pst = pst;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public int getSt() {
        return st;
    }

    public void setSt(int st) {
        this.st = st;
    }

    public Object getRt() {
        return rt;
    }

    public void setRt(Object rt) {
        this.rt = rt;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public Object getCrbt() {
        return crbt;
    }

    public void setCrbt(Object crbt) {
        this.crbt = crbt;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public AlbumBean getAl() {
        if (al != null) {
            return al;
        }
        if (album != null) {
            return album;
        }
        return null;
    }

    public void setAl(AlbumBean al) {
        this.al = al;
    }

    public int getDt() {
        if(dt != 0) {
            return dt;
        }
        if(duration != 0){
            return duration;
        }
        return 0;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Object getA() {
        return a;
    }

    public void setA(Object a) {
        this.a = a;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Object getRtUrl() {
        return rtUrl;
    }

    public void setRtUrl(Object rtUrl) {
        this.rtUrl = rtUrl;
    }

    public int getFtype() {
        return ftype;
    }

    public void setFtype(int ftype) {
        this.ftype = ftype;
    }

    public int getDjId() {
        return djId;
    }

    public void setDjId(int djId) {
        this.djId = djId;
    }

    public int getCopyright() {
        return copyright;
    }

    public void setCopyright(int copyright) {
        this.copyright = copyright;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public int getRtype() {
        return rtype;
    }

    public void setRtype(int rtype) {
        this.rtype = rtype;
    }

    public Object getRurl() {
        return rurl;
    }

    public void setRurl(Object rurl) {
        this.rurl = rurl;
    }

    public int getMst() {
        return mst;
    }

    public void setMst(int mst) {
        this.mst = mst;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public int getMv() {
        return mv;
    }

    public void setMv(int mv) {
        this.mv = mv;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public List<ArtistBean> getAr() {
        if (ar != null) {
            return ar;
        }
        if (artists != null) {
            return artists;
        }
        return null;
    }

    public void setAr(List<ArtistBean> ar) {
        this.ar = ar;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    private String artistName;

    public List<String> getAlia() {
        if(alia != null && alia.size() != 0){
            return alia;
        }
        if(alias != null && alias.size() != 0){
            return alias;
        }
        return null;
    }

    public String getFullAlia(){
        if(getAlia() == null){
            return "";
        }
        if(getAlia().size() == 0){
            return "";
        }
        String before = "";
        for (int i = 0; i < getAlia().size(); i++) {
            before = before + getAlia().get(i);
        }
        return before;
    }

    public void setAlia(List<String> alia) {
        this.alia = alia;
    }

    public List<?> getRtUrls() {
        return rtUrls;
    }

    public void setRtUrls(List<?> rtUrls) {
        this.rtUrls = rtUrls;
    }

    public String getFullSongName() {
        if (getAlia() != null && getAlia().size() != 0) {
            String spannableString = String.format("%s (%s)",
                    getName(), getAlia().get(0));
            return spannableString;
        } else {
            //若歌曲Alia为空，查找歌曲tns
            return getName();
        }
    }

    public String getFullArtistName() {
        List<ArtistBean> artistBeanList = getAr();
        if (artistBeanList != null) {
            String before = "";
            for (int i = 0; i < artistBeanList.size(); i++) {
                before = before + artistBeanList.get(i).getName() + "/";
            }
            return before.substring(0, before.length() - 1);
        } else {
            if(artistName != null && artistName.length() != 0){
                return artistName;
            }else {
                return "未知艺术家";
            }
        }
    }

    public static class H {
        private long size;

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }
    }

    public String getContent(){
        String before = "";
        before = before + name + " ";
        before = before + getFullArtistName() + " ";
        before = before + getAlbumName() + " ";
        before = before + getAlia() + " ";
        before = before + getFullAlia() + " ";
        return before;
    }
}
