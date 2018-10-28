package ceuilisa.mirai.response;

import java.util.List;

public class PlayAllHistoryResponse {


    /**
     * allData : [{"playCount":0,"score":100,"song":{"song":{"authId":7001,"status":-1,"id":30854608,"name":"シルシ","transName":null,"aliaName":"TV动画《刀剑神域II》ED3 / TVアニメ「ソードアート・オンラインII」ED3テーマ","position":14,"artist":{"id":16995,"name":"LiSA","transName":"织部里沙","alias":"LiSA;リサ;Oribe Risa;織部里沙;LISA","aliaName":"リサ;Oribe Risa","realName":"リサ","areaId":8,"initial":76,"type":2,"picId":109951163014920830,"picture":null,"hotAlbumIds":"","musicSize":364,"albumSize":33,"score":1038241,"click":0,"hotSongs":[],"hotAlbums":[],"albums":[],"briefDesc":"","desc":"","json":null,"valid":99,"copyright":1},"audition":null,"sign":null,"genre":null,"json":null,"score":90,"click":0,"djId":0,"djProgramId":0,"mvId":0,"valid":99,"copyright":1,"pubTime":1425398400007,"songAddition":null,"copyFrom":"","ringtone":null,"disc":"1","no":14,"version":37,"fromType":0,"type":"0","subType":null,"dxResourceId":0,"songUrlType":0,"ydMiguId":null,"flacId":0,"fee":0,"user_id":0,"song_id":0,"create_time":0,"song_type":0,"music_status":9,"playTime":288232},"ar":[{"id":16995,"name":"LiSA"}],"al":{"id":3104348,"name":"Launcher","picUrl":"https://p3.music.126.net/uuzIrNRcbZTEJ2AUDydtYA==/17937432695799233.jpg","pic_str":"17937432695799233","pic":17937432695799232},"st":0,"rtype":0,"rurl":null,"pst":0,"alia":["TV动画《刀剑神域II》ED3 / TVアニメ「ソードアート・オンラインII」ED3テーマ"],"pop":90,"rt":null,"mst":9,"cp":7001,"crbt":null,"cf":"","dt":288232,"rtUrl":null,"ftype":0,"a":null,"cd":"1","mv":0,"t":0,"djId":0,"no":14,"name":"シルシ","id":30854608}}]
     * code : 200
     */

    private int code;
    private List<AllDataBean> allData;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<AllDataBean> getAllData() {
        return allData;
    }

    public void setAllData(List<AllDataBean> allData) {
        this.allData = allData;
    }

    public static class AllDataBean {
        /**
         * playCount : 0
         * score : 100
         * song : {"song":{"authId":7001,"status":-1,"id":30854608,"name":"シルシ","transName":null,"aliaName":"TV动画《刀剑神域II》ED3 / TVアニメ「ソードアート・オンラインII」ED3テーマ","position":14,"artist":{"id":16995,"name":"LiSA","transName":"织部里沙","alias":"LiSA;リサ;Oribe Risa;織部里沙;LISA","aliaName":"リサ;Oribe Risa","realName":"リサ","areaId":8,"initial":76,"type":2,"picId":109951163014920830,"picture":null,"hotAlbumIds":"","musicSize":364,"albumSize":33,"score":1038241,"click":0,"hotSongs":[],"hotAlbums":[],"albums":[],"briefDesc":"","desc":"","json":null,"valid":99,"copyright":1},"audition":null,"sign":null,"genre":null,"json":null,"score":90,"click":0,"djId":0,"djProgramId":0,"mvId":0,"valid":99,"copyright":1,"pubTime":1425398400007,"songAddition":null,"copyFrom":"","ringtone":null,"disc":"1","no":14,"version":37,"fromType":0,"type":"0","subType":null,"dxResourceId":0,"songUrlType":0,"ydMiguId":null,"flacId":0,"fee":0,"user_id":0,"song_id":0,"create_time":0,"song_type":0,"music_status":9,"playTime":288232},"ar":[{"id":16995,"name":"LiSA"}],"al":{"id":3104348,"name":"Launcher","picUrl":"https://p3.music.126.net/uuzIrNRcbZTEJ2AUDydtYA==/17937432695799233.jpg","pic_str":"17937432695799233","pic":17937432695799232},"st":0,"rtype":0,"rurl":null,"pst":0,"alia":["TV动画《刀剑神域II》ED3 / TVアニメ「ソードアート・オンラインII」ED3テーマ"],"pop":90,"rt":null,"mst":9,"cp":7001,"crbt":null,"cf":"","dt":288232,"rtUrl":null,"ftype":0,"a":null,"cd":"1","mv":0,"t":0,"djId":0,"no":14,"name":"シルシ","id":30854608}
         */

        private int playCount;
        private int score;
        private SongBeanX song;

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public SongBeanX getSong() {
            return song;
        }

        public void setSong(SongBeanX song) {
            this.song = song;
        }

        public static class SongBeanX {
            /**
             * song : {"authId":7001,"status":-1,"id":30854608,"name":"シルシ","transName":null,"aliaName":"TV动画《刀剑神域II》ED3 / TVアニメ「ソードアート・オンラインII」ED3テーマ","position":14,"artist":{"id":16995,"name":"LiSA","transName":"织部里沙","alias":"LiSA;リサ;Oribe Risa;織部里沙;LISA","aliaName":"リサ;Oribe Risa","realName":"リサ","areaId":8,"initial":76,"type":2,"picId":109951163014920830,"picture":null,"hotAlbumIds":"","musicSize":364,"albumSize":33,"score":1038241,"click":0,"hotSongs":[],"hotAlbums":[],"albums":[],"briefDesc":"","desc":"","json":null,"valid":99,"copyright":1},"audition":null,"sign":null,"genre":null,"json":null,"score":90,"click":0,"djId":0,"djProgramId":0,"mvId":0,"valid":99,"copyright":1,"pubTime":1425398400007,"songAddition":null,"copyFrom":"","ringtone":null,"disc":"1","no":14,"version":37,"fromType":0,"type":"0","subType":null,"dxResourceId":0,"songUrlType":0,"ydMiguId":null,"flacId":0,"fee":0,"user_id":0,"song_id":0,"create_time":0,"song_type":0,"music_status":9,"playTime":288232}
             * ar : [{"id":16995,"name":"LiSA"}]
             * al : {"id":3104348,"name":"Launcher","picUrl":"https://p3.music.126.net/uuzIrNRcbZTEJ2AUDydtYA==/17937432695799233.jpg","pic_str":"17937432695799233","pic":17937432695799232}
             * st : 0
             * rtype : 0
             * rurl : null
             * pst : 0
             * alia : ["TV动画《刀剑神域II》ED3 / TVアニメ「ソードアート・オンラインII」ED3テーマ"]
             * pop : 90
             * rt : null
             * mst : 9
             * cp : 7001
             * crbt : null
             * cf :
             * dt : 288232
             * rtUrl : null
             * ftype : 0
             * a : null
             * cd : 1
             * mv : 0
             * t : 0
             * djId : 0
             * no : 14
             * name : シルシ
             * id : 30854608
             */

            private SongBean song;
            private AlBean al;
            private int st;
            private int rtype;
            private Object rurl;
            private int pst;
            private int pop;
            private Object rt;
            private int mst;
            private int cp;
            private Object crbt;
            private String cf;
            private int dt;
            private Object rtUrl;
            private int ftype;
            private Object a;
            private String cd;
            private int mv;
            private int t;
            private int djId;
            private int no;
            private String name;
            private int id;
            private List<ArBean> ar;
            private List<String> alia;

            public SongBean getSong() {
                return song;
            }

            public void setSong(SongBean song) {
                this.song = song;
            }

            public AlBean getAl() {
                return al;
            }

            public void setAl(AlBean al) {
                this.al = al;
            }

            public int getSt() {
                return st;
            }

            public void setSt(int st) {
                this.st = st;
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

            public int getPst() {
                return pst;
            }

            public void setPst(int pst) {
                this.pst = pst;
            }

            public int getPop() {
                return pop;
            }

            public void setPop(int pop) {
                this.pop = pop;
            }

            public Object getRt() {
                return rt;
            }

            public void setRt(Object rt) {
                this.rt = rt;
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

            public int getDt() {
                return dt;
            }

            public void setDt(int dt) {
                this.dt = dt;
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

            public int getMv() {
                return mv;
            }

            public void setMv(int mv) {
                this.mv = mv;
            }

            public int getT() {
                return t;
            }

            public void setT(int t) {
                this.t = t;
            }

            public int getDjId() {
                return djId;
            }

            public void setDjId(int djId) {
                this.djId = djId;
            }

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
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

            public List<ArBean> getAr() {
                return ar;
            }

            public void setAr(List<ArBean> ar) {
                this.ar = ar;
            }

            public List<String> getAlia() {
                return alia;
            }

            public void setAlia(List<String> alia) {
                this.alia = alia;
            }

            public static class SongBean {
                /**
                 * authId : 7001
                 * status : -1
                 * id : 30854608
                 * name : シルシ
                 * transName : null
                 * aliaName : TV动画《刀剑神域II》ED3 / TVアニメ「ソードアート・オンラインII」ED3テーマ
                 * position : 14
                 * artist : {"id":16995,"name":"LiSA","transName":"织部里沙","alias":"LiSA;リサ;Oribe Risa;織部里沙;LISA","aliaName":"リサ;Oribe Risa","realName":"リサ","areaId":8,"initial":76,"type":2,"picId":109951163014920830,"picture":null,"hotAlbumIds":"","musicSize":364,"albumSize":33,"score":1038241,"click":0,"hotSongs":[],"hotAlbums":[],"albums":[],"briefDesc":"","desc":"","json":null,"valid":99,"copyright":1}
                 * audition : null
                 * sign : null
                 * genre : null
                 * json : null
                 * score : 90
                 * click : 0
                 * djId : 0
                 * djProgramId : 0
                 * mvId : 0
                 * valid : 99
                 * copyright : 1
                 * pubTime : 1425398400007
                 * songAddition : null
                 * copyFrom :
                 * ringtone : null
                 * disc : 1
                 * no : 14
                 * version : 37
                 * fromType : 0
                 * type : 0
                 * subType : null
                 * dxResourceId : 0
                 * songUrlType : 0
                 * ydMiguId : null
                 * flacId : 0
                 * fee : 0
                 * user_id : 0
                 * song_id : 0
                 * create_time : 0
                 * song_type : 0
                 * music_status : 9
                 * playTime : 288232
                 */

                private int authId;
                private int status;
                private int id;
                private String name;
                private Object transName;
                private String aliaName;
                private int position;
                private ArtistBean artist;
                private Object audition;
                private Object sign;
                private Object genre;
                private Object json;
                private int score;
                private int click;
                private int djId;
                private int djProgramId;
                private int mvId;
                private int valid;
                private int copyright;
                private long pubTime;
                private Object songAddition;
                private String copyFrom;
                private Object ringtone;
                private String disc;
                private int no;
                private int version;
                private int fromType;
                private String type;
                private Object subType;
                private int dxResourceId;
                private int songUrlType;
                private Object ydMiguId;
                private int flacId;
                private int fee;
                private int user_id;
                private int song_id;
                private int create_time;
                private int song_type;
                private int music_status;
                private int playTime;

                public int getAuthId() {
                    return authId;
                }

                public void setAuthId(int authId) {
                    this.authId = authId;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

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

                public Object getTransName() {
                    return transName;
                }

                public void setTransName(Object transName) {
                    this.transName = transName;
                }

                public String getAliaName() {
                    return aliaName;
                }

                public void setAliaName(String aliaName) {
                    this.aliaName = aliaName;
                }

                public int getPosition() {
                    return position;
                }

                public void setPosition(int position) {
                    this.position = position;
                }

                public ArtistBean getArtist() {
                    return artist;
                }

                public void setArtist(ArtistBean artist) {
                    this.artist = artist;
                }

                public Object getAudition() {
                    return audition;
                }

                public void setAudition(Object audition) {
                    this.audition = audition;
                }

                public Object getSign() {
                    return sign;
                }

                public void setSign(Object sign) {
                    this.sign = sign;
                }

                public Object getGenre() {
                    return genre;
                }

                public void setGenre(Object genre) {
                    this.genre = genre;
                }

                public Object getJson() {
                    return json;
                }

                public void setJson(Object json) {
                    this.json = json;
                }

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
                }

                public int getClick() {
                    return click;
                }

                public void setClick(int click) {
                    this.click = click;
                }

                public int getDjId() {
                    return djId;
                }

                public void setDjId(int djId) {
                    this.djId = djId;
                }

                public int getDjProgramId() {
                    return djProgramId;
                }

                public void setDjProgramId(int djProgramId) {
                    this.djProgramId = djProgramId;
                }

                public int getMvId() {
                    return mvId;
                }

                public void setMvId(int mvId) {
                    this.mvId = mvId;
                }

                public int getValid() {
                    return valid;
                }

                public void setValid(int valid) {
                    this.valid = valid;
                }

                public int getCopyright() {
                    return copyright;
                }

                public void setCopyright(int copyright) {
                    this.copyright = copyright;
                }

                public long getPubTime() {
                    return pubTime;
                }

                public void setPubTime(long pubTime) {
                    this.pubTime = pubTime;
                }

                public Object getSongAddition() {
                    return songAddition;
                }

                public void setSongAddition(Object songAddition) {
                    this.songAddition = songAddition;
                }

                public String getCopyFrom() {
                    return copyFrom;
                }

                public void setCopyFrom(String copyFrom) {
                    this.copyFrom = copyFrom;
                }

                public Object getRingtone() {
                    return ringtone;
                }

                public void setRingtone(Object ringtone) {
                    this.ringtone = ringtone;
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

                public int getVersion() {
                    return version;
                }

                public void setVersion(int version) {
                    this.version = version;
                }

                public int getFromType() {
                    return fromType;
                }

                public void setFromType(int fromType) {
                    this.fromType = fromType;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public Object getSubType() {
                    return subType;
                }

                public void setSubType(Object subType) {
                    this.subType = subType;
                }

                public int getDxResourceId() {
                    return dxResourceId;
                }

                public void setDxResourceId(int dxResourceId) {
                    this.dxResourceId = dxResourceId;
                }

                public int getSongUrlType() {
                    return songUrlType;
                }

                public void setSongUrlType(int songUrlType) {
                    this.songUrlType = songUrlType;
                }

                public Object getYdMiguId() {
                    return ydMiguId;
                }

                public void setYdMiguId(Object ydMiguId) {
                    this.ydMiguId = ydMiguId;
                }

                public int getFlacId() {
                    return flacId;
                }

                public void setFlacId(int flacId) {
                    this.flacId = flacId;
                }

                public int getFee() {
                    return fee;
                }

                public void setFee(int fee) {
                    this.fee = fee;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public int getSong_id() {
                    return song_id;
                }

                public void setSong_id(int song_id) {
                    this.song_id = song_id;
                }

                public int getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(int create_time) {
                    this.create_time = create_time;
                }

                public int getSong_type() {
                    return song_type;
                }

                public void setSong_type(int song_type) {
                    this.song_type = song_type;
                }

                public int getMusic_status() {
                    return music_status;
                }

                public void setMusic_status(int music_status) {
                    this.music_status = music_status;
                }

                public int getPlayTime() {
                    return playTime;
                }

                public void setPlayTime(int playTime) {
                    this.playTime = playTime;
                }

                public static class ArtistBean {
                    /**
                     * id : 16995
                     * name : LiSA
                     * transName : 织部里沙
                     * alias : LiSA;リサ;Oribe Risa;織部里沙;LISA
                     * aliaName : リサ;Oribe Risa
                     * realName : リサ
                     * areaId : 8
                     * initial : 76
                     * type : 2
                     * picId : 109951163014920830
                     * picture : null
                     * hotAlbumIds :
                     * musicSize : 364
                     * albumSize : 33
                     * score : 1038241
                     * click : 0
                     * hotSongs : []
                     * hotAlbums : []
                     * albums : []
                     * briefDesc :
                     * desc :
                     * json : null
                     * valid : 99
                     * copyright : 1
                     */

                    private int id;
                    private String name;
                    private String transName;
                    private String alias;
                    private String aliaName;
                    private String realName;
                    private int areaId;
                    private int initial;
                    private int type;
                    private long picId;
                    private Object picture;
                    private String hotAlbumIds;
                    private int musicSize;
                    private int albumSize;
                    private int score;
                    private int click;
                    private String briefDesc;
                    private String desc;
                    private Object json;
                    private int valid;
                    private int copyright;
                    private List<?> hotSongs;
                    private List<?> hotAlbums;
                    private List<?> albums;

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

                    public String getTransName() {
                        return transName;
                    }

                    public void setTransName(String transName) {
                        this.transName = transName;
                    }

                    public String getAlias() {
                        return alias;
                    }

                    public void setAlias(String alias) {
                        this.alias = alias;
                    }

                    public String getAliaName() {
                        return aliaName;
                    }

                    public void setAliaName(String aliaName) {
                        this.aliaName = aliaName;
                    }

                    public String getRealName() {
                        return realName;
                    }

                    public void setRealName(String realName) {
                        this.realName = realName;
                    }

                    public int getAreaId() {
                        return areaId;
                    }

                    public void setAreaId(int areaId) {
                        this.areaId = areaId;
                    }

                    public int getInitial() {
                        return initial;
                    }

                    public void setInitial(int initial) {
                        this.initial = initial;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public long getPicId() {
                        return picId;
                    }

                    public void setPicId(long picId) {
                        this.picId = picId;
                    }

                    public Object getPicture() {
                        return picture;
                    }

                    public void setPicture(Object picture) {
                        this.picture = picture;
                    }

                    public String getHotAlbumIds() {
                        return hotAlbumIds;
                    }

                    public void setHotAlbumIds(String hotAlbumIds) {
                        this.hotAlbumIds = hotAlbumIds;
                    }

                    public int getMusicSize() {
                        return musicSize;
                    }

                    public void setMusicSize(int musicSize) {
                        this.musicSize = musicSize;
                    }

                    public int getAlbumSize() {
                        return albumSize;
                    }

                    public void setAlbumSize(int albumSize) {
                        this.albumSize = albumSize;
                    }

                    public int getScore() {
                        return score;
                    }

                    public void setScore(int score) {
                        this.score = score;
                    }

                    public int getClick() {
                        return click;
                    }

                    public void setClick(int click) {
                        this.click = click;
                    }

                    public String getBriefDesc() {
                        return briefDesc;
                    }

                    public void setBriefDesc(String briefDesc) {
                        this.briefDesc = briefDesc;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public Object getJson() {
                        return json;
                    }

                    public void setJson(Object json) {
                        this.json = json;
                    }

                    public int getValid() {
                        return valid;
                    }

                    public void setValid(int valid) {
                        this.valid = valid;
                    }

                    public int getCopyright() {
                        return copyright;
                    }

                    public void setCopyright(int copyright) {
                        this.copyright = copyright;
                    }

                    public List<?> getHotSongs() {
                        return hotSongs;
                    }

                    public void setHotSongs(List<?> hotSongs) {
                        this.hotSongs = hotSongs;
                    }

                    public List<?> getHotAlbums() {
                        return hotAlbums;
                    }

                    public void setHotAlbums(List<?> hotAlbums) {
                        this.hotAlbums = hotAlbums;
                    }

                    public List<?> getAlbums() {
                        return albums;
                    }

                    public void setAlbums(List<?> albums) {
                        this.albums = albums;
                    }
                }
            }

            public static class AlBean {
                /**
                 * id : 3104348
                 * name : Launcher
                 * picUrl : https://p3.music.126.net/uuzIrNRcbZTEJ2AUDydtYA==/17937432695799233.jpg
                 * pic_str : 17937432695799233
                 * pic : 17937432695799232
                 */

                private int id;
                private String name;
                private String picUrl;
                private String pic_str;
                private long pic;

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

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public String getPic_str() {
                    return pic_str;
                }

                public void setPic_str(String pic_str) {
                    this.pic_str = pic_str;
                }

                public long getPic() {
                    return pic;
                }

                public void setPic(long pic) {
                    this.pic = pic;
                }
            }

            public static class ArBean {
                /**
                 * id : 16995
                 * name : LiSA
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
    }
}