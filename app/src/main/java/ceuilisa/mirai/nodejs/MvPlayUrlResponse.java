package ceuilisa.mirai.nodejs;

public class MvPlayUrlResponse {

    /**
     * code : 200
     * data : {"id":10847581,"url":"http://vodkgeyttp8.vod.126.net/cloudmusic/d7a8/mv/ae02/fca8cc5b6c73fb7ed267150207a19d32.mp4?wsSecret=97f9fe1f7e3aee9476fe2680cde120e4&wsTime=1553926422","r":1080,"size":480591359,"md5":"","code":200,"expi":3600,"fee":0,"mvFee":0,"st":0,"msg":""}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 10847581
         * url : http://vodkgeyttp8.vod.126.net/cloudmusic/d7a8/mv/ae02/fca8cc5b6c73fb7ed267150207a19d32.mp4?wsSecret=97f9fe1f7e3aee9476fe2680cde120e4&wsTime=1553926422
         * r : 1080
         * size : 480591359
         * md5 :
         * code : 200
         * expi : 3600
         * fee : 0
         * mvFee : 0
         * st : 0
         * msg :
         */

        private int id;
        private String url;
        private int r;
        private int size;
        private String md5;
        private int code;
        private int expi;
        private int fee;
        private int mvFee;
        private int st;
        private String msg;

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

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
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

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public int getMvFee() {
            return mvFee;
        }

        public void setMvFee(int mvFee) {
            this.mvFee = mvFee;
        }

        public int getSt() {
            return st;
        }

        public void setSt(int st) {
            this.st = st;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
