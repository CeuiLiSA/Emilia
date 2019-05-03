package ceuilisa.mirai.response;

import ceuilisa.mirai.nodejs.MvBean;

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
    private MvBean data;
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

    public MvBean getData() {
        return data;
    }

    public void setData(MvBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
