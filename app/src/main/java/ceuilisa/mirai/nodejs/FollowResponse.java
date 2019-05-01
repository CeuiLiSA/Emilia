package ceuilisa.mirai.nodejs;

import java.util.List;

import ceuilisa.mirai.interf.ListShow;
import ceuilisa.mirai.response.BaseResponse;

/**
 * 我关注的
 */
public class FollowResponse extends BaseResponse implements ListShow<FollowedsBean> {


    /**
     * follow : [{"py":"lhyy","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/P9ojKWMlwt1GxrQalT26Iw==/18646617697437679.jpg","authStatus":0,"userType":0,"gender":2,"expertTags":null,"vipType":0,"nickname":"令和一一","userId":347941821,"followed":false,"experts":null,"mutual":false,"follows":2,"followeds":6,"remarkName":null,"signature":"非正经社会人士","vipRights":null,"eventCount":16,"playlistCount":5},{"py":"hplcd","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/12N9hyGdRo9AUeyvEu0vhA==/19055636021532430.jpg","authStatus":0,"userType":0,"gender":2,"expertTags":null,"vipType":0,"nickname":"黑皮柳橙丁","userId":118828695,"followed":false,"experts":null,"mutual":false,"follows":24,"followeds":2,"remarkName":null,"signature":"","vipRights":null,"eventCount":0,"playlistCount":12},{"py":"kfdkytLing","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/EG18YNCAWOZkSCrzdI5lRA==/109951163639797369.jpg","authStatus":0,"userType":0,"gender":2,"expertTags":null,"vipType":0,"nickname":"咖啡的苦与甜Ling","userId":1335548938,"followed":false,"experts":null,"mutual":false,"follows":3,"followeds":2,"remarkName":null,"signature":"","vipRights":null,"eventCount":3,"playlistCount":3},{"py":"ysyy","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/MthJKyQrIio2j_1r7NcU2A==/18578447976566978.jpg","authStatus":0,"userType":0,"gender":2,"expertTags":null,"vipType":0,"nickname":"野生羊羊","userId":305749217,"followed":false,"experts":null,"mutual":false,"follows":17,"followeds":4,"remarkName":null,"signature":"Sometimes ever, sometimes never.","vipRights":null,"eventCount":13,"playlistCount":8},{"py":"mmydxyb","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/Ij_3PLxj04DZMddgMjs5Lw==/109951163805421898.jpg","authStatus":0,"userType":0,"gender":2,"expertTags":null,"vipType":0,"nickname":"摸摸鱼的小月豹","userId":84543639,"followed":false,"experts":null,"mutual":false,"follows":21,"followeds":4,"remarkName":null,"signature":"哦呼","vipRights":null,"eventCount":4,"playlistCount":30},{"py":"shsjzs","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/vnK6U3gzCck7Ca7qZ_RN4Q==/7875801790217953.jpg","authStatus":0,"userType":0,"gender":1,"expertTags":null,"vipType":0,"nickname":"守护世界之树","userId":56757277,"followed":false,"experts":null,"mutual":false,"follows":19,"followeds":15,"remarkName":null,"signature":"大好きって言うなら，大大好きって返すよ。 ","vipRights":null,"eventCount":36,"playlistCount":38},{"py":"mhsjxmy","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/6BSeaQYPGRwJ4y9Wvgzlrw==/109951163267408387.jpg","authStatus":0,"userType":0,"gender":2,"expertTags":null,"vipType":11,"nickname":"马猴烧酒徐梦圆","userId":382332472,"followed":false,"experts":null,"mutual":false,"follows":111,"followeds":16,"remarkName":null,"signature":"夜沉灯黄，弱草微霜。奉还君名，吹墨飞扬。千载相思抹不去，友人帐中诉衷肠。","vipRights":{"associator":{"vipCode":100,"rights":true},"musicPackage":null,"redVipAnnualCount":-1},"eventCount":3,"playlistCount":64},{"py":"yylxms","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/_aEPXmHuskM-g140GROZnQ==/109951163879490620.jpg","authStatus":1,"userType":10,"gender":0,"expertTags":null,"vipType":11,"nickname":"云音乐小秘书","userId":9003,"followed":false,"experts":null,"mutual":false,"follows":427,"followeds":99999,"remarkName":null,"signature":"网易云音乐是6亿人都在使用的音乐平台，致力于帮助用户发现音乐惊喜，帮助音乐人实现梦想。\n客服@云音乐客服 在线时间：9：00 - 24：00，如您在使用过程中遇到任何问题，欢迎私信咨询，我们会尽快回复。\n如果仍然不能解决您的问题，请邮件我们：\n用户：ncm5990@163.com\n音乐人：yyr599@163.com","vipRights":{"associator":{"vipCode":100,"rights":true},"musicPackage":null,"redVipAnnualCount":1},"eventCount":5866,"playlistCount":82},{"py":"yljdxma","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/3wFBnQGM4iNicignuB1OYw==/109951163320773804.jpg","authStatus":0,"userType":0,"gender":0,"expertTags":null,"vipType":0,"nickname":"祐里酱的小棉袄","userId":353346057,"followed":false,"experts":null,"mutual":false,"follows":11,"followeds":6,"remarkName":null,"signature":"","vipRights":null,"eventCount":0,"playlistCount":6},{"py":"wy-yy","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/xWlAAZez5Ji7d6XOU7X4Pg==/109951163924917983.jpg","authStatus":0,"userType":0,"gender":2,"expertTags":null,"vipType":0,"nickname":"王娅-丫丫","userId":249670783,"followed":false,"experts":null,"mutual":false,"follows":17,"followeds":21,"remarkName":null,"signature":"因为不了解，所以才完美","vipRights":null,"eventCount":4,"playlistCount":8},{"py":"qkwlqfxl","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/lhlyZv5vnuqZQ5lfRmNv6w==/109951163760882827.jpg","authStatus":0,"userType":0,"gender":2,"expertTags":null,"vipType":0,"nickname":"晴空万里清风徐来","userId":120976629,"followed":false,"experts":null,"mutual":false,"follows":7,"followeds":28,"remarkName":null,"signature":"Stay Alive ","vipRights":null,"eventCount":69,"playlistCount":7},{"py":"jhcxx","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/jDHEtBYKKd0jDjLfLrvrEg==/109951163077135247.jpg","authStatus":0,"userType":0,"gender":2,"expertTags":null,"vipType":0,"nickname":"江湖陈醒醒","userId":252081779,"followed":false,"experts":null,"mutual":false,"follows":11,"followeds":4,"remarkName":null,"signature":"今天我喜欢柠檬维他","vipRights":null,"eventCount":2,"playlistCount":13},{"py":"sde","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/HoqAC_oMeY5yu6PJ8KOEZA==/109951163789380488.jpg","authStatus":0,"userType":0,"gender":2,"expertTags":null,"vipType":0,"nickname":"三都诶","userId":253249472,"followed":false,"experts":null,"mutual":false,"follows":9,"followeds":16,"remarkName":null,"signature":"深爱自有归期，你又何必强求。","vipRights":null,"eventCount":3,"playlistCount":2},{"py":"yy","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/RugQfPv4U-lL9ZqKmd0S5Q==/7949469071123874.jpg","authStatus":0,"userType":0,"gender":2,"expertTags":null,"vipType":0,"nickname":"洋依","userId":55818250,"followed":false,"experts":null,"mutual":false,"follows":11,"followeds":9,"remarkName":null,"signature":"唔。。。","vipRights":null,"eventCount":5,"playlistCount":9},{"py":"systwyh","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/aOTH3JIvY_8e4GqKC1gbFA==/18903903416408127.jpg","authStatus":0,"userType":0,"gender":2,"expertTags":null,"vipType":0,"nickname":"岁月神偷wyh","userId":367085874,"followed":false,"experts":null,"mutual":false,"follows":11,"followeds":7,"remarkName":null,"signature":"以歌会友，虽然不会唱","vipRights":null,"eventCount":0,"playlistCount":5},{"py":"llj_","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/fyeIIWAkToRsFDE7giJYXQ==/109951163963316201.jpg","authStatus":0,"userType":0,"gender":2,"expertTags":null,"vipType":0,"nickname":"琉璃酱_","userId":310332937,"followed":false,"experts":null,"mutual":false,"follows":7,"followeds":5,"remarkName":null,"signature":null,"vipRights":null,"eventCount":0,"playlistCount":5},{"py":"Yin_Mou","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/ZeFzL-T8BEAOGGyBLqi1sA==/1386484174107801.jpg","authStatus":0,"userType":0,"gender":1,"expertTags":null,"vipType":0,"nickname":"Yin_Mou","userId":285198844,"followed":false,"experts":null,"mutual":false,"follows":13,"followeds":7,"remarkName":null,"signature":"想变优秀，因为我怕有一天我的亲人朋友需要我的时候我却无能为力","vipRights":null,"eventCount":2,"playlistCount":11},{"py":"WanderingzBunny","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/EBVzn170qqGqgsFWIdDvzw==/19186477904747220.jpg","authStatus":0,"userType":0,"gender":0,"expertTags":null,"vipType":0,"nickname":"Wandering丶Bunny","userId":258938406,"followed":false,"experts":null,"mutual":false,"follows":5,"followeds":7,"remarkName":null,"signature":"","vipRights":null,"eventCount":0,"playlistCount":3},{"py":"nghll","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/VE1_MUZPuNL39D207ZeQWg==/3442570909077209.jpg","authStatus":0,"userType":0,"gender":1,"expertTags":null,"vipType":0,"nickname":"你刚回来了","userId":256261565,"followed":false,"experts":null,"mutual":false,"follows":17,"followeds":10,"remarkName":null,"signature":"","vipRights":null,"eventCount":30,"playlistCount":13},{"py":"lcby","time":0,"accountStatus":0,"avatarUrl":"http://p2.music.126.net/z0YDLF4vQbw88MyUyylJPw==/109951163188198603.jpg","authStatus":0,"userType":0,"gender":2,"expertTags":null,"vipType":0,"nickname":"恋橙八雲","userId":248032120,"followed":false,"experts":null,"mutual":false,"follows":86,"followeds":53,"remarkName":null,"signature":"「梦里不觉秋已深 余情岂是为他人」","vipRights":null,"eventCount":3,"playlistCount":45}]
     * touchCount : 0
     * more : true
     * code : 200
     */

    private int touchCount;
    private boolean more;
    private List<FollowedsBean> follow;

    public int getTouchCount() {
        return touchCount;
    }

    public void setTouchCount(int touchCount) {
        this.touchCount = touchCount;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public List<FollowedsBean> getFollow() {
        return follow;
    }

    public void setFollow(List<FollowedsBean> follow) {
        this.follow = follow;
    }

    @Override
    public List<FollowedsBean> getList() {
        return follow;
    }
}