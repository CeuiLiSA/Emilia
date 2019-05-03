package ceuilisa.mirai.nodejs;

import java.util.List;

import ceuilisa.mirai.interf.ListShow;
import ceuilisa.mirai.response.BaseResponse;

public class RelatedMvResponse extends BaseResponse implements ListShow<MvBean> {


    /**
     * mvs : [{"id":10847381,"cover":"http://p3.music.126.net/LMLEf-o6rsPXmAKwPSRSlA==/109951163794731644.jpg","name":"해야 (Sunrise) ","playCount":594605,"briefDesc":"GFRIEND正规二辑主打曲《Sunrise》MV发布！","desc":null,"artistName":"GFRIEND","artistId":1046043,"duration":234000,"mark":0,"artists":[{"id":1046043,"name":"GFRIEND","alias":["여자친구","Girl Friend","女朋友","女友"],"transNames":["여자친구"]}],"alg":"feature"},{"id":10851179,"cover":"http://p3.music.126.net/cYQajW6mlSCNDvW4sfOK7w==/109951163807729268.jpg","name":"起风了 歌词版","playCount":5826043,"briefDesc":"超级热单，特别重制！","desc":null,"artistName":"吴青峰","artistId":187229,"duration":308000,"mark":0,"artists":[{"id":187229,"name":"吴青峰","alias":["可人儿"],"transNames":null}],"alg":"feature"},{"id":10853117,"cover":"http://p4.music.126.net/3osHaze8KUH0pVHnL6rmSQ==/109951163862084771.jpg","name":"365","playCount":1087288,"briefDesc":"Zedd&Katy Perry合作新单《365》","desc":null,"artistName":"Zedd","artistId":46376,"duration":263000,"mark":0,"artists":[{"id":46376,"name":"Zedd","alias":null,"transNames":null},{"id":62888,"name":"Katy Perry","alias":["凯蒂.佩里","水果姐","Katy Hudson","果子狸","凯蒂·派瑞"],"transNames":["凯蒂.佩里"]}],"alg":"feature"},{"id":10857415,"cover":"http://p4.music.126.net/m24dAfUMTwgExvpMEdRUtg==/109951163909453160.jpg","name":"Good night","playCount":322491,"briefDesc":"郑世云献声韩剧《触及真心》OST","desc":null,"artistName":"郑世云","artistId":12487104,"duration":238000,"mark":0,"artists":[{"id":12487104,"name":"郑世云","alias":["정세운","JEONG SEWOON"],"transNames":["정세운"]}],"alg":"feature"},{"id":10859411,"cover":"http://p4.music.126.net/Wnh67l0hTSKnPjXS6EgsVQ==/109951163940525471.jpg","name":"蜂鸟","playCount":1111235,"briefDesc":"吴青峰热单《蜂鸟》MV公开！","desc":null,"artistName":"吴青峰","artistId":187229,"duration":231000,"mark":0,"artists":[{"id":187229,"name":"吴青峰","alias":["可人儿"],"transNames":null}],"alias":["电视剧《我在北京等你》主题曲"],"alg":"feature"}]
     * code : 200
     */

    private List<MvBean> mvs;

    public List<MvBean> getMvs() {
        return mvs;
    }

    public void setMvs(List<MvBean> mvs) {
        this.mvs = mvs;
    }

    @Override
    public List<MvBean> getList() {
        return mvs;
    }
}
