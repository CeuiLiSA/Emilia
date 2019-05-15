package ceuilisa.mirai.nodejs;

import java.util.List;

import ceuilisa.mirai.interf.ListShow;
import ceuilisa.mirai.response.BaseResponse;
import ceuilisa.mirai.response.TracksBean;

public class BannerResponse extends BaseResponse implements ListShow<BannerResponse.BannersBean> {


    private List<BannersBean> banners;

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    @Override
    public List<BannersBean> getList() {
        return banners;
    }

    public static class BannersBean {
        /**
         * pic : http://p1.music.126.net/koH3-NWVtY71uSFtE-aAmw==/109951164074511849.jpg
         * targetId : 1365233944
         * adid : null
         * targetType : 1
         * titleColor : red
         * typeTitle : VIP专享
         * url : null
         * adurlV2 : null
         * exclusive : false
         * monitorImpress : null
         * monitorClick : null
         * monitorType : null
         * monitorImpressList : []
         * monitorClickList : []
         * monitorBlackList : null
         * extMonitor : null
         * extMonitorInfo : null
         * adSource : null
         * adLocation : null
         * encodeId : 1365233944
         * program : null
         * event : null
         * video : null
         * song : {"name":"MARIA","id":1365233944,"pst":0,"t":0,"ar":[{"id":13057497,"name":"黄明昊 (Justin)","tns":[],"alias":[]},{"id":893651,"name":"KOHH","tns":[],"alias":[]}],"alia":[],"pop":0,"st":0,"rt":"","fee":1,"v":3,"crbt":null,"cf":"","al":{"id":79107800,"name":"MARIA","picUrl":"http://p1.music.126.net/oH8it_bheo-odQObkaIIhw==/109951164073730138.jpg","tns":[],"pic_str":"109951164073730138","pic":109951164073730140},"dt":223573,"h":{"br":320000,"fid":0,"size":8945415,"vd":-47205},"m":{"br":192000,"fid":0,"size":5367266,"vd":-44618},"l":{"br":128000,"fid":0,"size":3578192,"vd":-43033},"a":null,"cd":"01","no":1,"rtUrl":null,"ftype":0,"rtUrls":[],"djId":0,"copyright":0,"s_id":0,"rtype":0,"rurl":null,"mst":9,"cp":1416115,"mv":0,"publishTime":0,"privilege":{"id":1365233944,"fee":1,"payed":0,"st":0,"pl":0,"dl":0,"sp":0,"cp":0,"subp":0,"cs":false,"maxbr":999000,"fl":0,"toast":false,"flag":1092,"preSell":false}}
         * bannerId : 1557845611638995
         * alg : null
         * scm : 1.music-homepage.homepage_banner_force.banner.446389.253633659.null
         * requestId :
         * showAdTag : true
         * pid : null
         */

        private String pic;
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public TracksBean getSong() {
            return song;
        }

        public void setSong(TracksBean song) {
            this.song = song;
        }

        private long targetId;
        private Object adid;
        private int targetType;
        private String titleColor;
        private String typeTitle;
        private String url;
        private Object adurlV2;
        private boolean exclusive;
        private Object monitorImpress;
        private Object monitorClick;
        private Object monitorType;
        private Object monitorBlackList;
        private Object extMonitor;
        private Object extMonitorInfo;
        private Object adSource;
        private Object adLocation;
        private String encodeId;
        private Object program;
        private Object event;
        private Object video;
        private TracksBean song;
        private String bannerId;
        private Object alg;
        private String scm;
        private String requestId;
        private boolean showAdTag;
        private Object pid;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public long getTargetId() {
            return targetId;
        }

        public void setTargetId(long targetId) {
            this.targetId = targetId;
        }

        public Object getAdid() {
            return adid;
        }

        public void setAdid(Object adid) {
            this.adid = adid;
        }

        public int getTargetType() {
            return targetType;
        }

        public void setTargetType(int targetType) {
            this.targetType = targetType;
        }

        public String getTitleColor() {
            return titleColor;
        }

        public void setTitleColor(String titleColor) {
            this.titleColor = titleColor;
        }

        public String getTypeTitle() {
            return typeTitle;
        }

        public void setTypeTitle(String typeTitle) {
            this.typeTitle = typeTitle;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Object getAdurlV2() {
            return adurlV2;
        }

        public void setAdurlV2(Object adurlV2) {
            this.adurlV2 = adurlV2;
        }

        public boolean isExclusive() {
            return exclusive;
        }

        public void setExclusive(boolean exclusive) {
            this.exclusive = exclusive;
        }

        public Object getMonitorImpress() {
            return monitorImpress;
        }

        public void setMonitorImpress(Object monitorImpress) {
            this.monitorImpress = monitorImpress;
        }

        public Object getMonitorClick() {
            return monitorClick;
        }

        public void setMonitorClick(Object monitorClick) {
            this.monitorClick = monitorClick;
        }

        public Object getMonitorType() {
            return monitorType;
        }

        public void setMonitorType(Object monitorType) {
            this.monitorType = monitorType;
        }

        public Object getMonitorBlackList() {
            return monitorBlackList;
        }

        public void setMonitorBlackList(Object monitorBlackList) {
            this.monitorBlackList = monitorBlackList;
        }

        public Object getExtMonitor() {
            return extMonitor;
        }

        public void setExtMonitor(Object extMonitor) {
            this.extMonitor = extMonitor;
        }

        public Object getExtMonitorInfo() {
            return extMonitorInfo;
        }

        public void setExtMonitorInfo(Object extMonitorInfo) {
            this.extMonitorInfo = extMonitorInfo;
        }

        public Object getAdSource() {
            return adSource;
        }

        public void setAdSource(Object adSource) {
            this.adSource = adSource;
        }

        public Object getAdLocation() {
            return adLocation;
        }

        public void setAdLocation(Object adLocation) {
            this.adLocation = adLocation;
        }

        public String getEncodeId() {
            return encodeId;
        }

        public void setEncodeId(String encodeId) {
            this.encodeId = encodeId;
        }

        public Object getProgram() {
            return program;
        }

        public void setProgram(Object program) {
            this.program = program;
        }

        public Object getEvent() {
            return event;
        }

        public void setEvent(Object event) {
            this.event = event;
        }

        public Object getVideo() {
            return video;
        }

        public void setVideo(Object video) {
            this.video = video;
        }

        public String getBannerId() {
            return bannerId;
        }

        public void setBannerId(String bannerId) {
            this.bannerId = bannerId;
        }

        public Object getAlg() {
            return alg;
        }

        public void setAlg(Object alg) {
            this.alg = alg;
        }

        public String getScm() {
            return scm;
        }

        public void setScm(String scm) {
            this.scm = scm;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public boolean isShowAdTag() {
            return showAdTag;
        }

        public void setShowAdTag(boolean showAdTag) {
            this.showAdTag = showAdTag;
        }

        public Object getPid() {
            return pid;
        }

        public void setPid(Object pid) {
            this.pid = pid;
        }
    }
}
