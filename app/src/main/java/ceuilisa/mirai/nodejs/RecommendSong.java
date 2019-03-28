package ceuilisa.mirai.nodejs;

import java.util.List;

public class RecommendSong {
    /**
     * name : Dreaming Stars
     * id : 534539189
     * position : 8
     * alias : []
     * status : 0
     * fee : 0
     * copyrightId : 0
     * disc : 01
     * no : 8
     * artists : [{"name":"高田憂希","id":12074587,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0},{"name":"山口愛","id":12074588,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0}]
     * album : {"name":"TVアニメ「NEW GAME!!」キャラクターソングミニアルバム2「SINGin' SING UP♪♪♪♪」","id":37465070,"type":"专辑","size":8,"picId":109951163127818900,"blurPicUrl":"http://p1.music.126.net/vojUub6YEGDqQW1ZSALNIg==/109951163127818893.jpg","companyId":0,"pic":109951163127818900,"picUrl":"http://p1.music.126.net/vojUub6YEGDqQW1ZSALNIg==/109951163127818893.jpg","publishTime":1516723200007,"description":"","tags":"","company":"KADOKAWA","briefDesc":"","artist":{"name":"","id":0,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0},"songs":[],"alias":[],"status":0,"copyrightId":0,"commentThreadId":"R_AL_3_37465070","artists":[{"name":"V.A.","id":21138,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0}],"subType":"录音室版","transName":null,"picId_str":"109951163127818893"}
     * starred : false
     * popularity : 25
     * score : 25
     * starredNum : 0
     * duration : 238785
     * playedNum : 0
     * dayPlays : 0
     * hearTime : 0
     * ringtone : null
     * crbt : null
     * audition : null
     * copyFrom :
     * commentThreadId : R_SO_4_534539189
     * rtUrl : null
     * ftype : 0
     * rtUrls : []
     * copyright : 0
     * transName : null
     * sign : null
     * mvid : 0
     * bMusic : {"name":null,"id":1415066005,"size":3821026,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":128000,"playTime":238785,"volumeDelta":-27200}
     * mp3Url : null
     * rtype : 0
     * rurl : null
     * hMusic : {"name":null,"id":1415066003,"size":9552501,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":320000,"playTime":238785,"volumeDelta":-31000}
     * mMusic : {"name":null,"id":1415066004,"size":5731518,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":192000,"playTime":238785,"volumeDelta":-28599}
     * lMusic : {"name":null,"id":1415066005,"size":3821026,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":128000,"playTime":238785,"volumeDelta":-27200}
     * reason : 根据你可能喜欢的单曲 CONTINUE…？
     * privilege : {"id":534539189,"fee":0,"payed":0,"st":0,"pl":320000,"dl":320000,"sp":7,"cp":1,"subp":1,"cs":false,"maxbr":320000,"fl":320000,"toast":false,"flag":128,"preSell":false}
     * alg : nearlineBased_t
     */

    private String name;
    private int id;
    private int position;
    private int status;
    private int fee;
    private int copyrightId;
    private String disc;
    private int no;
    private AlbumBean album;
    private boolean starred;
    private int popularity;
    private int score;
    private int starredNum;
    private int duration;
    private int playedNum;
    private int dayPlays;
    private int hearTime;
    private Object ringtone;
    private Object crbt;
    private Object audition;
    private String copyFrom;
    private String commentThreadId;
    private Object rtUrl;
    private int ftype;
    private int copyright;
    private Object transName;
    private Object sign;
    private int mvid;
    private BMusicBean bMusic;
    private Object mp3Url;
    private int rtype;
    private Object rurl;
    private HMusicBean hMusic;
    private MMusicBean mMusic;
    private LMusicBean lMusic;
    private String reason;
    private PrivilegeBean privilege;
    private String alg;
    private List<String> alias;
    private List<ArtistBean> artists;
    private List<?> rtUrls;

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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getCopyrightId() {
        return copyrightId;
    }

    public void setCopyrightId(int copyrightId) {
        this.copyrightId = copyrightId;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public AlbumBean getAlbum() {
        return album;
    }

    public void setAlbum(AlbumBean album) {
        this.album = album;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStarredNum() {
        return starredNum;
    }

    public void setStarredNum(int starredNum) {
        this.starredNum = starredNum;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPlayedNum() {
        return playedNum;
    }

    public void setPlayedNum(int playedNum) {
        this.playedNum = playedNum;
    }

    public int getDayPlays() {
        return dayPlays;
    }

    public void setDayPlays(int dayPlays) {
        this.dayPlays = dayPlays;
    }

    public int getHearTime() {
        return hearTime;
    }

    public void setHearTime(int hearTime) {
        this.hearTime = hearTime;
    }

    public Object getRingtone() {
        return ringtone;
    }

    public void setRingtone(Object ringtone) {
        this.ringtone = ringtone;
    }

    public Object getCrbt() {
        return crbt;
    }

    public void setCrbt(Object crbt) {
        this.crbt = crbt;
    }

    public Object getAudition() {
        return audition;
    }

    public void setAudition(Object audition) {
        this.audition = audition;
    }

    public String getCopyFrom() {
        return copyFrom;
    }

    public void setCopyFrom(String copyFrom) {
        this.copyFrom = copyFrom;
    }

    public String getCommentThreadId() {
        return commentThreadId;
    }

    public void setCommentThreadId(String commentThreadId) {
        this.commentThreadId = commentThreadId;
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

    public int getCopyright() {
        return copyright;
    }

    public void setCopyright(int copyright) {
        this.copyright = copyright;
    }

    public Object getTransName() {
        return transName;
    }

    public void setTransName(Object transName) {
        this.transName = transName;
    }

    public Object getSign() {
        return sign;
    }

    public void setSign(Object sign) {
        this.sign = sign;
    }

    public int getMvid() {
        return mvid;
    }

    public void setMvid(int mvid) {
        this.mvid = mvid;
    }

    public BMusicBean getBMusic() {
        return bMusic;
    }

    public void setBMusic(BMusicBean bMusic) {
        this.bMusic = bMusic;
    }

    public Object getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(Object mp3Url) {
        this.mp3Url = mp3Url;
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

    public HMusicBean getHMusic() {
        return hMusic;
    }

    public void setHMusic(HMusicBean hMusic) {
        this.hMusic = hMusic;
    }

    public MMusicBean getMMusic() {
        return mMusic;
    }

    public void setMMusic(MMusicBean mMusic) {
        this.mMusic = mMusic;
    }

    public LMusicBean getLMusic() {
        return lMusic;
    }

    public void setLMusic(LMusicBean lMusic) {
        this.lMusic = lMusic;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public PrivilegeBean getPrivilege() {
        return privilege;
    }

    public void setPrivilege(PrivilegeBean privilege) {
        this.privilege = privilege;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
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

    public List<?> getRtUrls() {
        return rtUrls;
    }

    public void setRtUrls(List<?> rtUrls) {
        this.rtUrls = rtUrls;
    }



    public static class BMusicBean {
        /**
         * name : null
         * id : 1415066005
         * size : 3821026
         * extension : mp3
         * sr : 44100
         * dfsId : 0
         * bitrate : 128000
         * playTime : 238785
         * volumeDelta : -27200
         */

        private Object name;
        private long id;
        private int size;
        private String extension;
        private int sr;
        private int dfsId;
        private int bitrate;
        private int playTime;
        private float volumeDelta;

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public int getSr() {
            return sr;
        }

        public void setSr(int sr) {
            this.sr = sr;
        }

        public int getDfsId() {
            return dfsId;
        }

        public void setDfsId(int dfsId) {
            this.dfsId = dfsId;
        }

        public int getBitrate() {
            return bitrate;
        }

        public void setBitrate(int bitrate) {
            this.bitrate = bitrate;
        }

        public int getPlayTime() {
            return playTime;
        }

        public void setPlayTime(int playTime) {
            this.playTime = playTime;
        }

        public float getVolumeDelta() {
            return volumeDelta;
        }

        public void setVolumeDelta(float volumeDelta) {
            this.volumeDelta = volumeDelta;
        }
    }

    public static class HMusicBean {
        /**
         * name : null
         * id : 1415066003
         * size : 9552501
         * extension : mp3
         * sr : 44100
         * dfsId : 0
         * bitrate : 320000
         * playTime : 238785
         * volumeDelta : -31000
         */

        private Object name;
        private long id;
        private int size;
        private String extension;
        private int sr;
        private int dfsId;
        private int bitrate;
        private int playTime;
        private float volumeDelta;

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public int getSr() {
            return sr;
        }

        public void setSr(int sr) {
            this.sr = sr;
        }

        public int getDfsId() {
            return dfsId;
        }

        public void setDfsId(int dfsId) {
            this.dfsId = dfsId;
        }

        public int getBitrate() {
            return bitrate;
        }

        public void setBitrate(int bitrate) {
            this.bitrate = bitrate;
        }

        public int getPlayTime() {
            return playTime;
        }

        public void setPlayTime(int playTime) {
            this.playTime = playTime;
        }

        public float getVolumeDelta() {
            return volumeDelta;
        }

        public void setVolumeDelta(float volumeDelta) {
            this.volumeDelta = volumeDelta;
        }
    }

    public static class MMusicBean {
        /**
         * name : null
         * id : 1415066004
         * size : 5731518
         * extension : mp3
         * sr : 44100
         * dfsId : 0
         * bitrate : 192000
         * playTime : 238785
         * volumeDelta : -28599
         */

        private Object name;
        private long id;
        private int size;
        private String extension;
        private int sr;
        private int dfsId;
        private int bitrate;
        private int playTime;
        private float volumeDelta;

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public int getSr() {
            return sr;
        }

        public void setSr(int sr) {
            this.sr = sr;
        }

        public int getDfsId() {
            return dfsId;
        }

        public void setDfsId(int dfsId) {
            this.dfsId = dfsId;
        }

        public int getBitrate() {
            return bitrate;
        }

        public void setBitrate(int bitrate) {
            this.bitrate = bitrate;
        }

        public int getPlayTime() {
            return playTime;
        }

        public void setPlayTime(int playTime) {
            this.playTime = playTime;
        }

        public float getVolumeDelta() {
            return volumeDelta;
        }

        public void setVolumeDelta(float volumeDelta) {
            this.volumeDelta = volumeDelta;
        }
    }

    public static class LMusicBean {
        /**
         * name : null
         * id : 1415066005
         * size : 3821026
         * extension : mp3
         * sr : 44100
         * dfsId : 0
         * bitrate : 128000
         * playTime : 238785
         * volumeDelta : -27200
         */

        private Object name;
        private long id;
        private int size;
        private String extension;
        private int sr;
        private int dfsId;
        private int bitrate;
        private int playTime;
        private float volumeDelta;

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public int getSr() {
            return sr;
        }

        public void setSr(int sr) {
            this.sr = sr;
        }

        public int getDfsId() {
            return dfsId;
        }

        public void setDfsId(int dfsId) {
            this.dfsId = dfsId;
        }

        public int getBitrate() {
            return bitrate;
        }

        public void setBitrate(int bitrate) {
            this.bitrate = bitrate;
        }

        public int getPlayTime() {
            return playTime;
        }

        public void setPlayTime(int playTime) {
            this.playTime = playTime;
        }

        public float getVolumeDelta() {
            return volumeDelta;
        }

        public void setVolumeDelta(float volumeDelta) {
            this.volumeDelta = volumeDelta;
        }
    }

    public static class PrivilegeBean {
        /**
         * id : 534539189
         * fee : 0
         * payed : 0
         * st : 0
         * pl : 320000
         * dl : 320000
         * sp : 7
         * cp : 1
         * subp : 1
         * cs : false
         * maxbr : 320000
         * fl : 320000
         * toast : false
         * flag : 128
         * preSell : false
         */

        private int id;
        private int fee;
        private int payed;
        private int st;
        private int pl;
        private int dl;
        private int sp;
        private int cp;
        private int subp;
        private boolean cs;
        private int maxbr;
        private int fl;
        private boolean toast;
        private int flag;
        private boolean preSell;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public int getPayed() {
            return payed;
        }

        public void setPayed(int payed) {
            this.payed = payed;
        }

        public int getSt() {
            return st;
        }

        public void setSt(int st) {
            this.st = st;
        }

        public int getPl() {
            return pl;
        }

        public void setPl(int pl) {
            this.pl = pl;
        }

        public int getDl() {
            return dl;
        }

        public void setDl(int dl) {
            this.dl = dl;
        }

        public int getSp() {
            return sp;
        }

        public void setSp(int sp) {
            this.sp = sp;
        }

        public int getCp() {
            return cp;
        }

        public void setCp(int cp) {
            this.cp = cp;
        }

        public int getSubp() {
            return subp;
        }

        public void setSubp(int subp) {
            this.subp = subp;
        }

        public boolean isCs() {
            return cs;
        }

        public void setCs(boolean cs) {
            this.cs = cs;
        }

        public int getMaxbr() {
            return maxbr;
        }

        public void setMaxbr(int maxbr) {
            this.maxbr = maxbr;
        }

        public int getFl() {
            return fl;
        }

        public void setFl(int fl) {
            this.fl = fl;
        }

        public boolean isToast() {
            return toast;
        }

        public void setToast(boolean toast) {
            this.toast = toast;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public boolean isPreSell() {
            return preSell;
        }

        public void setPreSell(boolean preSell) {
            this.preSell = preSell;
        }
    }


}