package ceuilisa.mirai.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MvDetail {

    /**
     * loadingPic :
     * bufferPic :
     * loadingPicFS :
     * bufferPicFS :
     * subed : false
     * data : {"id":10847581,"name":"toyumdur bugun + + +","artistId":12195983,"artistName":"nurmuhammat yimit نۇرمۇھەممەت ھىمىت","briefDesc":"","desc":"","cover":"http://p1.music.126.net/clqFqhgBayZTvpTHzSOLGA==/109951163808042750.jpg","coverId":109951163808042750,"playCount":53651,"subCount":1343,"shareCount":844,"likeCount":424,"commentCount":31,"duration":609000,"nType":0,"publishTime":"2019-01-21","brs":{"240":"http://vodkgeyttp8.vod.126.net/cloudmusic/d7a8/mv/ae02/5c0cf735fe00d50e1130260f89439b4f.mp4?wsSecret=934d6212466f46993ee4439e318b435c&wsTime=1553926047","480":"http://vodkgeyttp8.vod.126.net/cloudmusic/d7a8/mv/ae02/f8a3129890a91bb1aa0c60693e0aef62.mp4?wsSecret=8452a7c5689856911b68b915aabd161f&wsTime=1553926047","720":"http://vodkgeyttp8.vod.126.net/cloudmusic/d7a8/mv/ae02/4a0f5d896282a2fcc6fd81abaa648b30.mp4?wsSecret=99b35968c04a8d3049a8ef9f6979abc2&wsTime=1553926047","1080":"http://vodkgeyttp8.vod.126.net/cloudmusic/d7a8/mv/ae02/fca8cc5b6c73fb7ed267150207a19d32.mp4?wsSecret=fd5285ac569e6092f0c2a956048857f5&wsTime=1553926047"},"artists":[{"id":12195983,"name":"nurmuhammat yimit نۇرمۇھەممەت ھىمىت"}],"isReward":false,"commentThreadId":"R_MV_5_10847581"}
     * code : 200
     */

    private String loadingPic;
    private String bufferPic;
    private String loadingPicFS;
    private String bufferPicFS;
    private boolean subed;
    private DataBean data;
    private int code;

    public String getLoadingPic() {
        return loadingPic;
    }

    public void setLoadingPic(String loadingPic) {
        this.loadingPic = loadingPic;
    }

    public String getBufferPic() {
        return bufferPic;
    }

    public void setBufferPic(String bufferPic) {
        this.bufferPic = bufferPic;
    }

    public String getLoadingPicFS() {
        return loadingPicFS;
    }

    public void setLoadingPicFS(String loadingPicFS) {
        this.loadingPicFS = loadingPicFS;
    }

    public String getBufferPicFS() {
        return bufferPicFS;
    }

    public void setBufferPicFS(String bufferPicFS) {
        this.bufferPicFS = bufferPicFS;
    }

    public boolean isSubed() {
        return subed;
    }

    public void setSubed(boolean subed) {
        this.subed = subed;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * id : 10847581
         * name : toyumdur bugun + + +
         * artistId : 12195983
         * artistName : nurmuhammat yimit نۇرمۇھەممەت ھىمىت
         * briefDesc :
         * desc :
         * cover : http://p1.music.126.net/clqFqhgBayZTvpTHzSOLGA==/109951163808042750.jpg
         * coverId : 109951163808042750
         * playCount : 53651
         * subCount : 1343
         * shareCount : 844
         * likeCount : 424
         * commentCount : 31
         * duration : 609000
         * nType : 0
         * publishTime : 2019-01-21
         * brs : {"240":"http://vodkgeyttp8.vod.126.net/cloudmusic/d7a8/mv/ae02/5c0cf735fe00d50e1130260f89439b4f.mp4?wsSecret=934d6212466f46993ee4439e318b435c&wsTime=1553926047","480":"http://vodkgeyttp8.vod.126.net/cloudmusic/d7a8/mv/ae02/f8a3129890a91bb1aa0c60693e0aef62.mp4?wsSecret=8452a7c5689856911b68b915aabd161f&wsTime=1553926047","720":"http://vodkgeyttp8.vod.126.net/cloudmusic/d7a8/mv/ae02/4a0f5d896282a2fcc6fd81abaa648b30.mp4?wsSecret=99b35968c04a8d3049a8ef9f6979abc2&wsTime=1553926047","1080":"http://vodkgeyttp8.vod.126.net/cloudmusic/d7a8/mv/ae02/fca8cc5b6c73fb7ed267150207a19d32.mp4?wsSecret=fd5285ac569e6092f0c2a956048857f5&wsTime=1553926047"}
         * artists : [{"id":12195983,"name":"nurmuhammat yimit نۇرمۇھەممەت ھىمىت"}]
         * isReward : false
         * commentThreadId : R_MV_5_10847581
         */

        private int id;
        private String name;
        private int artistId;
        private String artistName;
        private String briefDesc;
        private String desc;
        private String cover;
        private long coverId;
        private int playCount;
        private int subCount;
        private int shareCount;
        private int likeCount;
        private int commentCount;
        private int duration;
        private int nType;
        private String publishTime;
        private BrsBean brs;
        private boolean isReward;
        private String commentThreadId;
        private List<ArtistsBean> artists;

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

        public int getArtistId() {
            return artistId;
        }

        public void setArtistId(int artistId) {
            this.artistId = artistId;
        }

        public String getArtistName() {
            return artistName;
        }

        public void setArtistName(String artistName) {
            this.artistName = artistName;
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

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public long getCoverId() {
            return coverId;
        }

        public void setCoverId(long coverId) {
            this.coverId = coverId;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
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

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getNType() {
            return nType;
        }

        public void setNType(int nType) {
            this.nType = nType;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public BrsBean getBrs() {
            return brs;
        }

        public void setBrs(BrsBean brs) {
            this.brs = brs;
        }

        public boolean isIsReward() {
            return isReward;
        }

        public void setIsReward(boolean isReward) {
            this.isReward = isReward;
        }

        public String getCommentThreadId() {
            return commentThreadId;
        }

        public void setCommentThreadId(String commentThreadId) {
            this.commentThreadId = commentThreadId;
        }

        public List<ArtistsBean> getArtists() {
            return artists;
        }

        public void setArtists(List<ArtistsBean> artists) {
            this.artists = artists;
        }

        public static class BrsBean {
            /**
             * 240 : http://vodkgeyttp8.vod.126.net/cloudmusic/d7a8/mv/ae02/5c0cf735fe00d50e1130260f89439b4f.mp4?wsSecret=934d6212466f46993ee4439e318b435c&wsTime=1553926047
             * 480 : http://vodkgeyttp8.vod.126.net/cloudmusic/d7a8/mv/ae02/f8a3129890a91bb1aa0c60693e0aef62.mp4?wsSecret=8452a7c5689856911b68b915aabd161f&wsTime=1553926047
             * 720 : http://vodkgeyttp8.vod.126.net/cloudmusic/d7a8/mv/ae02/4a0f5d896282a2fcc6fd81abaa648b30.mp4?wsSecret=99b35968c04a8d3049a8ef9f6979abc2&wsTime=1553926047
             * 1080 : http://vodkgeyttp8.vod.126.net/cloudmusic/d7a8/mv/ae02/fca8cc5b6c73fb7ed267150207a19d32.mp4?wsSecret=fd5285ac569e6092f0c2a956048857f5&wsTime=1553926047
             */

            @SerializedName("240")
            private String _$240;
            @SerializedName("480")
            private String _$480;
            @SerializedName("720")
            private String _$720;
            @SerializedName("1080")
            private String _$1080;

            public String get_$240() {
                return _$240;
            }

            public void set_$240(String _$240) {
                this._$240 = _$240;
            }

            public String get_$480() {
                return _$480;
            }

            public void set_$480(String _$480) {
                this._$480 = _$480;
            }

            public String get_$720() {
                return _$720;
            }

            public void set_$720(String _$720) {
                this._$720 = _$720;
            }

            public String get_$1080() {
                return _$1080;
            }

            public void set_$1080(String _$1080) {
                this._$1080 = _$1080;
            }
        }

        public static class ArtistsBean {
            /**
             * id : 12195983
             * name : nurmuhammat yimit نۇرمۇھەممەت ھىمىت
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
