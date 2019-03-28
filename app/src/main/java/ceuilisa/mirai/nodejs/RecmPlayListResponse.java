package ceuilisa.mirai.nodejs;

import java.util.List;

import ceuilisa.mirai.response.BaseResponse;

/**
 * 推荐歌单列表适配器
 */
public class RecmPlayListResponse extends BaseResponse<RecommendPlaylistBean> {


    /**
     * code : 200
     * featureFirst : true
     * haveRcmdSongs : false
     * recommend : [{"id":70741032,"type":1,"name":"英文歌曲\u2014\u2014适合学习英语","copywriter":"根据你喜欢的单曲《Trouble Is A Friend》推荐","picUrl":"https://p2.music.126.net/Zuao0qGaRjKksGz-9WwE_w==/2923601419389486.jpg","playcount":7780139,"createTime":1430731440023,"creator":{"backgroundImgIdStr":"18600438208966560","avatarImgIdStr":"19128203788504834","mutual":false,"remarkName":null,"avatarImgId":19128203788504830,"backgroundImgId":18600438208966560,"detailDescription":"","defaultAvatar":false,"expertTags":null,"djStatus":10,"backgroundUrl":"http://p1.music.126.net/TTjs9ZI2-IGwz-hKWn-nOw==/18600438208966560.jpg","description":"","birthday":746294400000,"city":310101,"followed":false,"avatarUrl":"https://p2.music.126.net/HXrcxQpd6LSlQqfMJnp4XA==/19128203788504834.jpg","authStatus":0,"userType":0,"accountStatus":0,"userId":42855766,"vipType":0,"province":310000,"gender":1,"nickname":"冷小石","signature":"年轻的心有了白发~","authority":0},"trackCount":82,"userId":42855766,"alg":"bysong_play_rt"},{"id":104418068,"type":1,"name":"白金ディスコ（白金DISCO合集）","copywriter":"根据你喜欢的歌单《『Pure』纯纯纯音乐》推荐","picUrl":"https://p2.music.126.net/Jg2hb579yqJLxQq07b6bwQ==/528865130222244.jpg","playcount":55925,"createTime":1441446467471,"creator":{"backgroundImgIdStr":"19139198904825474","avatarImgIdStr":"6645448278434895","mutual":false,"remarkName":null,"avatarImgId":6645448278434895,"backgroundImgId":19139198904825470,"detailDescription":"","defaultAvatar":false,"expertTags":null,"djStatus":0,"backgroundUrl":"http://p1.music.126.net/vmttdDWIrpHQK0ZOoyBItA==/19139198904825474.jpg","description":"","birthday":1478361600000,"city":320200,"followed":false,"avatarUrl":"https://p2.music.126.net/a8hdhcDglYFV_vQm1FIODQ==/6645448278434895.jpg","authStatus":0,"userType":0,"accountStatus":0,"userId":7564949,"vipType":0,"province":320000,"gender":1,"nickname":"顾氏医馆","signature":"十年饮冰，难凉热血。","authority":0},"trackCount":18,"userId":7564949,"alg":"complete_list_rt"},{"id":3142892,"type":1,"name":"ACG是一种态度 *大杂烩 *随机精选","copywriter":"根据你喜欢的单曲《サムライハート(Some Like It Hot!!)》推荐","picUrl":"https://p2.music.126.net/3Lc2NIN1c2EJ8ZTq-8XmZA==/5688873162179836.jpg","playcount":45509,"createTime":1375942565320,"creator":{"backgroundImgIdStr":"109951163571193899","avatarImgIdStr":"109951163578028576","mutual":false,"remarkName":null,"avatarImgId":109951163578028580,"backgroundImgId":109951163571193900,"detailDescription":"","defaultAvatar":false,"expertTags":["日语","ACG","电子"],"djStatus":0,"backgroundUrl":"http://p1.music.126.net/bwUH-NWgzHhK8_jxwT7JBA==/109951163571193899.jpg","description":"","birthday":-2209017600000,"city":1005500,"followed":false,"avatarUrl":"https://p2.music.126.net/JBjL3X9RQZfg5EYRsxcWcw==/109951163578028576.jpg","authStatus":0,"userType":200,"accountStatus":0,"userId":2498210,"vipType":11,"province":1000000,"gender":2,"nickname":"Mi飯","signature":"\u2022靜靜聆聽\u2022細細品味\u2022追求音樂的純粹性\u2022帶給你音樂正能量\u2022","authority":0},"trackCount":195,"userId":2498210,"alg":"bysong_play_rt"},{"id":110328076,"type":1,"name":"刷题用BGM.改","copywriter":"根据你喜欢的单曲《Trouble Is A Friend》推荐","picUrl":"https://p2.music.126.net/bg4UyA-nbaNA2vdV4lit1Q==/6664139977694275.jpg","playcount":7424102,"createTime":1443226241167,"creator":{"backgroundImgIdStr":"109951163745807352","avatarImgIdStr":"109951162943262569","mutual":false,"remarkName":null,"avatarImgId":109951162943262580,"backgroundImgId":109951163745807360,"detailDescription":"","defaultAvatar":false,"expertTags":null,"djStatus":10,"backgroundUrl":"http://p1.music.126.net/2VlLOrcVY-JnzbQ0Z0Bl6w==/109951163745807352.jpg","description":"","birthday":936186662281,"city":310101,"followed":false,"avatarUrl":"https://p2.music.126.net/9_DDYO17aqQzxFGC61GCdA==/109951162943262569.jpg","authStatus":0,"userType":0,"accountStatus":0,"userId":6994462,"vipType":0,"province":310000,"gender":2,"nickname":"声控灯","signature":"亮也没用 没用也亮","authority":0},"trackCount":239,"userId":6994462,"alg":"contextbased"},{"id":57876993,"type":1,"name":"四月是你的谎言 音乐合集","copywriter":"根据你喜欢的单曲《My Truth～ロンド・カプリチオーソ》推荐","picUrl":"https://p2.music.126.net/MVQDrUOKUlzmA8Ev4ZtSEQ==/7849413511167370.jpg","playcount":479630,"createTime":1426226027652,"creator":{"backgroundImgIdStr":"7766950139216548","avatarImgIdStr":"7700979442179893","mutual":false,"remarkName":null,"avatarImgId":7700979442179893,"backgroundImgId":7766950139216548,"detailDescription":"","defaultAvatar":false,"expertTags":["日语","ACG"],"djStatus":0,"backgroundUrl":"http://p1.music.126.net/PuZUNp7JoB3e6qNrG-q1pw==/7766950139216548.jpg","description":"","birthday":1420041600000,"city":1005500,"followed":false,"avatarUrl":"https://p2.music.126.net/fzi3IRIVgKSunLyryTPldA==/7700979442179893.jpg","authStatus":0,"userType":200,"accountStatus":0,"userId":52248121,"vipType":0,"province":1000000,"gender":0,"nickname":"ACG娘","signature":"ACG（动漫、同人、游戏、niconico相关）专攻。入库请邮件至：upload#acgmusic.org","authority":0},"trackCount":114,"userId":52248121,"alg":"itembased"},{"id":430657150,"type":1,"name":"日系&ACG｜百首经典奏响动人乐章","copywriter":"根据你喜欢的单曲《again》推荐","picUrl":"https://p2.music.126.net/f8WTuC-C4_vTknljOt3-0w==/109951163323320639.jpg","playcount":4371397,"createTime":1469533784031,"creator":{"backgroundImgIdStr":"109951162938005797","avatarImgIdStr":"109951163423711727","mutual":false,"remarkName":null,"avatarImgId":109951163423711730,"backgroundImgId":109951162938005800,"detailDescription":"","defaultAvatar":false,"expertTags":["日语","ACG","轻音乐"],"djStatus":0,"backgroundUrl":"http://p1.music.126.net/IXesZpcFF3UYRszzxUJL5g==/109951162938005797.jpg","description":"","birthday":-2209017600000,"city":110101,"followed":false,"avatarUrl":"https://p2.music.126.net/4bdWUdSciWe3pW7np5z9JQ==/109951163423711727.jpg","authStatus":0,"userType":200,"accountStatus":0,"userId":45441555,"vipType":0,"province":110000,"gender":0,"nickname":"阿卡琳","signature":"想成为一个温柔的人｜\r永远怀抱希望｜\r合縁奇縁 一期一会｜\r祝你和重要的人 有一天 能够再次相遇｜\r(´,,\u2022ω\u2022,,｀)♡","authority":0},"trackCount":1008,"userId":45441555,"alg":"bysong_play_rt"},{"id":2109518781,"type":1,"name":"抄（写）作业超燃英文【刷题BGM】","copywriter":"根据你喜欢的单曲《Trouble Is A Friend (Album Version)》推荐","picUrl":"https://p2.music.126.net/5Zs51JS6cQzvQclW5u_J1g==/18832435162240436.jpg","playcount":348762,"createTime":1519095420087,"creator":{"backgroundImgIdStr":"109951162868126486","avatarImgIdStr":"18686200114669622","mutual":false,"remarkName":null,"avatarImgId":18686200114669624,"backgroundImgId":109951162868126480,"detailDescription":"","defaultAvatar":true,"expertTags":null,"djStatus":0,"backgroundUrl":"http://p1.music.126.net/_f8R60U9mZ42sSNvdPn2sQ==/109951162868126486.jpg","description":"","birthday":-2209017600000,"city":650100,"followed":false,"avatarUrl":"https://p2.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","authStatus":0,"userType":0,"accountStatus":0,"userId":528459688,"vipType":0,"province":650000,"gender":0,"nickname":"玩儿来街舞学员","signature":"","authority":0},"trackCount":68,"userId":528459688,"alg":"bysong_play_rt"},{"id":79514807,"type":1,"name":"№.09百首♫日系纯音集♫","copywriter":"根据你喜欢的单曲《天空を駆ける風の都》推荐","picUrl":"https://p2.music.126.net/EqyZDhTXavs6JISIgRGEXA==/3338117303084932.jpg","playcount":81478,"createTime":1433664974303,"creator":{"backgroundImgIdStr":"3444769930707838","avatarImgIdStr":"1426066589165071","mutual":false,"remarkName":null,"avatarImgId":1426066589165071,"backgroundImgId":3444769930707838,"detailDescription":"","defaultAvatar":false,"expertTags":["轻音乐","流行","世界音乐"],"djStatus":0,"backgroundUrl":"http://p1.music.126.net/qLk-ujot-6ZnbUkMgkoYAg==/3444769930707838.jpg","description":"","birthday":750873600000,"city":350500,"followed":false,"avatarUrl":"https://p2.music.126.net/uuCPTxtk29TH7EkT9QBjGg==/1426066589165071.jpg","authStatus":0,"userType":200,"accountStatus":0,"userId":16941415,"vipType":0,"province":350000,"gender":1,"nickname":"JoyTunes音悦","signature":"村里没有花姑娘，离村出走，告辞！","authority":0},"trackCount":109,"userId":16941415,"alg":"itembased"},{"id":39326661,"type":1,"name":"轻音轻语，虐心的galgame歌曲。","copywriter":"根据你喜欢的单曲《時の魔法》推荐","picUrl":"https://p2.music.126.net/ra9DnC4ehadfA4_Bykh52g==/1378787582862324.jpg","playcount":34787,"createTime":1417439622741,"creator":{"backgroundImgIdStr":"109951163380819493","avatarImgIdStr":"18973172649273392","mutual":false,"remarkName":null,"avatarImgId":18973172649273390,"backgroundImgId":109951163380819490,"detailDescription":"ACG资讯号","defaultAvatar":false,"expertTags":["日语","轻音乐","流行"],"djStatus":10,"backgroundUrl":"http://p1.music.126.net/ugNvrAZJppDJrjmZMCTyNg==/109951163380819493.jpg","description":"ACG资讯号","birthday":661968000000,"city":1010000,"followed":false,"avatarUrl":"https://p2.music.126.net/IHZsbVuhn_zA0jLGLOskSQ==/18973172649273392.jpg","authStatus":1,"userType":10,"accountStatus":0,"userId":33431226,"vipType":0,"province":1000000,"gender":1,"nickname":"野良兔","signature":"歌单已分类，请根据分界线来挑选。","authority":0},"trackCount":20,"userId":33431226,"alg":"itembased"},{"id":629302105,"type":1,"name":"『日系|电音』那些燃到心态爆炸的番曲","copywriter":"根据你喜欢的单曲《The Asterisk War》推荐","picUrl":"https://p2.music.126.net/CDmYUj-b0N3LwmyRXiT8uA==/18570751395155001.jpg","playcount":55783,"createTime":1489449801334,"creator":{"backgroundImgIdStr":"109951163345913326","avatarImgIdStr":"109951163282002000","mutual":false,"remarkName":null,"avatarImgId":109951163282002000,"backgroundImgId":109951163345913330,"detailDescription":"","defaultAvatar":false,"expertTags":null,"djStatus":0,"backgroundUrl":"http://p1.music.126.net/z2Tkq8v2syJFTBESQNWTmQ==/109951163345913326.jpg","description":"","birthday":982649131935,"city":441900,"followed":false,"avatarUrl":"https://p2.music.126.net/nSNLY_WOyG6AMDbvvbt4ag==/109951163282002000.jpg","authStatus":0,"userType":0,"accountStatus":0,"userId":436976841,"vipType":0,"province":440000,"gender":1,"nickname":"Snow_Sakura彡","signature":"✿͜҉樱花掉落的速度是5cm/s","authority":0},"trackCount":64,"userId":436976841,"alg":"itembased"}]
     */

    private int code;
    private boolean featureFirst;
    private boolean haveRcmdSongs;
    private List<RecommendPlaylistBean> recommend;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isFeatureFirst() {
        return featureFirst;
    }

    public void setFeatureFirst(boolean featureFirst) {
        this.featureFirst = featureFirst;
    }

    public boolean isHaveRcmdSongs() {
        return haveRcmdSongs;
    }

    public void setHaveRcmdSongs(boolean haveRcmdSongs) {
        this.haveRcmdSongs = haveRcmdSongs;
    }

    public List<RecommendPlaylistBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendPlaylistBean> recommend) {
        this.recommend = recommend;
    }


    @Override
    public List<RecommendPlaylistBean> getList() {
        return recommend;
    }
}