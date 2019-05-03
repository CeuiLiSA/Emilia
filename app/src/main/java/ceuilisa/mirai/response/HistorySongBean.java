package ceuilisa.mirai.response;

public class HistorySongBean {
    /**
     * playCount : 0
     * score : 100
     * song : {"song":{"authId":7001,"status":-1,"id":30854608,"name":"シルシ","transName":null,"aliaName":"TV动画《刀剑神域II》ED3 / TVアニメ「ソードアート・オンラインII」ED3テーマ","position":14,"artist":{"id":16995,"name":"LiSA","transName":"织部里沙","alias":"LiSA;リサ;Oribe Risa;織部里沙;LISA","aliaName":"リサ;Oribe Risa","realName":"リサ","areaId":8,"initial":76,"type":2,"picId":109951163014920830,"picture":null,"hotAlbumIds":"","musicSize":364,"albumSize":33,"score":1038241,"click":0,"hotSongs":[],"hotAlbums":[],"albums":[],"briefDesc":"","desc":"","json":null,"valid":99,"copyright":1},"audition":null,"sign":null,"genre":null,"json":null,"score":90,"click":0,"djId":0,"djProgramId":0,"mvId":0,"valid":99,"copyright":1,"pubTime":1425398400007,"songAddition":null,"copyFrom":"","ringtone":null,"disc":"1","no":14,"version":37,"fromType":0,"type":"0","subType":null,"dxResourceId":0,"songUrlType":0,"ydMiguId":null,"flacId":0,"fee":0,"user_id":0,"song_id":0,"create_time":0,"song_type":0,"music_status":9,"playTime":288232},"ar":[{"id":16995,"name":"LiSA"}],"al":{"id":3104348,"name":"Launcher","picUrl":"https://p3.music.126.net/uuzIrNRcbZTEJ2AUDydtYA==/17937432695799233.jpg","pic_str":"17937432695799233","pic":17937432695799232},"st":0,"rtype":0,"rurl":null,"pst":0,"alia":["TV动画《刀剑神域II》ED3 / TVアニメ「ソードアート・オンラインII」ED3テーマ"],"pop":90,"rt":null,"mst":9,"cp":7001,"crbt":null,"cf":"","dt":288232,"rtUrl":null,"ftype":0,"a":null,"cd":"1","mv":0,"t":0,"djId":0,"no":14,"name":"シルシ","id":30854608}
     */

    private int playCount;
    private int score;
    private TracksBean song;

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

    public TracksBean getSong() {
        return song;
    }

    public void setSong(TracksBean song) {
        this.song = song;
    }
}
