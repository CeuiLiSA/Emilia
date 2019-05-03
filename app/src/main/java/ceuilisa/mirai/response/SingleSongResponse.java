package ceuilisa.mirai.response;

import java.util.List;

public class SingleSongResponse extends BaseResponse {


    /**
     * data : [{"id":597134,"url":"http://m10.music.126.net/20190502214851/22f2f1dcdf3b6ca7b2dfc4346b6cf083/ymusic/fd0a/74e0/6af6/010f5363d83472105f43ae660b8e461b.mp3","br":320000,"size":9848206,"md5":"010f5363d83472105f43ae660b8e461b","code":200,"expi":1200,"type":"mp3","gain":-2.8599,"fee":0,"uf":null,"payed":0,"flag":0,"canExtend":false,"freeTrialInfo":null,"level":"exhigh","encodeType":"mp3"}]
     * code : 200
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 597134
         * url : http://m10.music.126.net/20190502214851/22f2f1dcdf3b6ca7b2dfc4346b6cf083/ymusic/fd0a/74e0/6af6/010f5363d83472105f43ae660b8e461b.mp3
         * br : 320000
         * size : 9848206
         * md5 : 010f5363d83472105f43ae660b8e461b
         * code : 200
         * expi : 1200
         * type : mp3
         * gain : -2.8599
         * fee : 0
         * uf : null
         * payed : 0
         * flag : 0
         * canExtend : false
         * freeTrialInfo : null
         * level : exhigh
         * encodeType : mp3
         */

        private int id;
        private String url;
        private int br;
        private int size;
        private String md5;
        private int code;
        private int expi;
        private String type;
        private double gain;
        private int fee;
        private Object uf;
        private int payed;
        private int flag;
        private boolean canExtend;
        private Object freeTrialInfo;
        private String level;
        private String encodeType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getBr() {
            return br;
        }

        public void setBr(int br) {
            this.br = br;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getExpi() {
            return expi;
        }

        public void setExpi(int expi) {
            this.expi = expi;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getGain() {
            return gain;
        }

        public void setGain(double gain) {
            this.gain = gain;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public Object getUf() {
            return uf;
        }

        public void setUf(Object uf) {
            this.uf = uf;
        }

        public int getPayed() {
            return payed;
        }

        public void setPayed(int payed) {
            this.payed = payed;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public boolean isCanExtend() {
            return canExtend;
        }

        public void setCanExtend(boolean canExtend) {
            this.canExtend = canExtend;
        }

        public Object getFreeTrialInfo() {
            return freeTrialInfo;
        }

        public void setFreeTrialInfo(Object freeTrialInfo) {
            this.freeTrialInfo = freeTrialInfo;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getEncodeType() {
            return encodeType;
        }

        public void setEncodeType(String encodeType) {
            this.encodeType = encodeType;
        }
    }
}
