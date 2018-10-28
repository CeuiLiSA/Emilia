package ceuilisa.mirai.response;

public class LrcResponse {

    /**
     * sgc : true
     * sfy : true
     * qfy : false
     * lrc : {"version":10,"lyric":"[00:21.760]行くあてない街  ひとり先を歩く\n[00:31.140]夕日に照らされた  失いそうな心\n[00:41.470]変わっていくなんて  思いもしなかった\n[00:51.500]君と重ねた夢  いつの間に\n[00:58.780]違う方へと歩いてたんだね\n[01:05.170]そしていま  ふたりは\n[01:10.210]遠すぎる世界  見つめて\n[01:15.600]そしていま  ほら響くよ\n[01:20.470]君が教えてくれた歌   この空に\n[01:37.300]正座して並んで  みんなで撮った写真\n[01:46.490]心に呼びかける  懐かしい笑顔\n[01:56.830]遠く離れてても  同じ夢の未来\n[02:06.940]繋がってるよね  信じてる\n[02:14.800]誓い合った  約束の場所\n[02:20.290]そしていま  ふたりは\n[02:25.410]それぞれの道を  見つめて\n[02:30.460]そしていま  また歌うよ\n[02:36.100]ふたつの想いが  めぐり会う  その日まで\n[03:04.450]迷いながらも\n[03:08.870]夢の色重ねる\n[03:13.940]君を想うからこそ\n[03:16.660]強くなれるよ\n[03:19.210]きっと\n[03:20.670]終わらせない\n[03:24.320]そしていま私は\n[03:29.329]君に続く空  見上げて\n[03:34.190]そしていま  また歌うよ\n[03:39.730]どこにいたって  届くはずだね\n[03:44.640]そしていま  ふたりは\n[03:49.710]輝く明日を  見つめて\n[03:54.720]そしていま  ほら響くよ\n[04:00.240]君が教えてくれた歌  この空に\n"}
     * klyric : {"version":0,"lyric":null}
     * tlyric : {"version":2,"lyric":"[00:21.760]向着无名的街道  形单影只地前进着\n[00:31.140]被夕阳照得通红的  近乎遗失的心灵\n[00:41.470]却逐渐发生了变化  我万万也没有想到\n[00:51.500]与你在一起的梦境  不觉之间\n[00:58.780]我们却已走向了不同的道路呢\n[01:05.170]于是此刻你们两人\n[01:10.210]凝望着遥不可及的世界\n[01:15.600]于是此刻  你听  歌声响彻\n[01:20.470]你曾经教会我唱的歌谣  回响于这片天空\n[01:37.300]大家一起并肩端坐  拍下的照片\n[01:46.490]号召心中的  令人怀念的笑容\n[01:56.830]尽管彼此相距甚远  我依然相信\n[02:06.940]两人共同梦想的未来仍紧连着\n[02:14.800]在那个相互发誓的约定之地\n[02:20.290]于是此刻你们两人\n[02:25.410]凝望着各自不同的道路\n[02:30.460]于是此刻我又放声歌唱\n[02:36.100]直到两人的思念相邂逅的那天为止\n[03:04.450]在迷惘之中\n[03:08.870]也不放弃追逐梦想\n[03:13.940]正因为我思念你\n[03:16.660]才能变得坚强\n[03:19.210]一定\n[03:20.670]不会就这样结束\n[03:24.320]于是此刻我举头仰望\n[03:29.329]延伸到你身旁的天空\n[03:34.190]于是此刻我又放声歌唱\n[03:39.730]无论身处何方也应该传递得到\n[03:44.640]于是此刻你们两人\n[03:49.710]凝望着光辉闪烁的明天\n[03:54.720]于是此刻  你听  歌声响彻\n[04:00.240]你曾经教会我唱的歌谣  回响于这片天空"}
     * code : 200
     */

    private boolean sgc;

    public boolean isUncollected() {
        return uncollected;
    }

    public void setUncollected(boolean uncollected) {
        this.uncollected = uncollected;
    }

    private boolean uncollected;

    public boolean isNolyric() {
        return nolyric;
    }

    public void setNolyric(boolean nolyric) {
        this.nolyric = nolyric;
    }

    private boolean nolyric;
    private boolean sfy;
    private boolean qfy;
    private LrcBean lrc;
    private KlyricBean klyric;
    private TlyricBean tlyric;
    private int code;

    public boolean isSgc() {
        return sgc;
    }

    public void setSgc(boolean sgc) {
        this.sgc = sgc;
    }

    public boolean isSfy() {
        return sfy;
    }

    public void setSfy(boolean sfy) {
        this.sfy = sfy;
    }

    public boolean isQfy() {
        return qfy;
    }

    public void setQfy(boolean qfy) {
        this.qfy = qfy;
    }

    public LrcBean getLrc() {
        return lrc;
    }

    public void setLrc(LrcBean lrc) {
        this.lrc = lrc;
    }

    public KlyricBean getKlyric() {
        return klyric;
    }

    public void setKlyric(KlyricBean klyric) {
        this.klyric = klyric;
    }

    public TlyricBean getTlyric() {
        return tlyric;
    }

    public void setTlyric(TlyricBean tlyric) {
        this.tlyric = tlyric;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class LrcBean {
        /**
         * version : 10
         * lyric : [00:21.760]行くあてない街  ひとり先を歩く
         [00:31.140]夕日に照らされた  失いそうな心
         [00:41.470]変わっていくなんて  思いもしなかった
         [00:51.500]君と重ねた夢  いつの間に
         [00:58.780]違う方へと歩いてたんだね
         [01:05.170]そしていま  ふたりは
         [01:10.210]遠すぎる世界  見つめて
         [01:15.600]そしていま  ほら響くよ
         [01:20.470]君が教えてくれた歌   この空に
         [01:37.300]正座して並んで  みんなで撮った写真
         [01:46.490]心に呼びかける  懐かしい笑顔
         [01:56.830]遠く離れてても  同じ夢の未来
         [02:06.940]繋がってるよね  信じてる
         [02:14.800]誓い合った  約束の場所
         [02:20.290]そしていま  ふたりは
         [02:25.410]それぞれの道を  見つめて
         [02:30.460]そしていま  また歌うよ
         [02:36.100]ふたつの想いが  めぐり会う  その日まで
         [03:04.450]迷いながらも
         [03:08.870]夢の色重ねる
         [03:13.940]君を想うからこそ
         [03:16.660]強くなれるよ
         [03:19.210]きっと
         [03:20.670]終わらせない
         [03:24.320]そしていま私は
         [03:29.329]君に続く空  見上げて
         [03:34.190]そしていま  また歌うよ
         [03:39.730]どこにいたって  届くはずだね
         [03:44.640]そしていま  ふたりは
         [03:49.710]輝く明日を  見つめて
         [03:54.720]そしていま  ほら響くよ
         [04:00.240]君が教えてくれた歌  この空に

         */

        private int version;
        private String lyric;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getLyric() {
            return lyric;
        }

        public void setLyric(String lyric) {
            this.lyric = lyric;
        }
    }

    public static class KlyricBean {
        /**
         * version : 0
         * lyric : null
         */

        private int version;
        private Object lyric;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public Object getLyric() {
            return lyric;
        }

        public void setLyric(Object lyric) {
            this.lyric = lyric;
        }
    }

    public static class TlyricBean {
        /**
         * version : 2
         * lyric : [00:21.760]向着无名的街道  形单影只地前进着
         [00:31.140]被夕阳照得通红的  近乎遗失的心灵
         [00:41.470]却逐渐发生了变化  我万万也没有想到
         [00:51.500]与你在一起的梦境  不觉之间
         [00:58.780]我们却已走向了不同的道路呢
         [01:05.170]于是此刻你们两人
         [01:10.210]凝望着遥不可及的世界
         [01:15.600]于是此刻  你听  歌声响彻
         [01:20.470]你曾经教会我唱的歌谣  回响于这片天空
         [01:37.300]大家一起并肩端坐  拍下的照片
         [01:46.490]号召心中的  令人怀念的笑容
         [01:56.830]尽管彼此相距甚远  我依然相信
         [02:06.940]两人共同梦想的未来仍紧连着
         [02:14.800]在那个相互发誓的约定之地
         [02:20.290]于是此刻你们两人
         [02:25.410]凝望着各自不同的道路
         [02:30.460]于是此刻我又放声歌唱
         [02:36.100]直到两人的思念相邂逅的那天为止
         [03:04.450]在迷惘之中
         [03:08.870]也不放弃追逐梦想
         [03:13.940]正因为我思念你
         [03:16.660]才能变得坚强
         [03:19.210]一定
         [03:20.670]不会就这样结束
         [03:24.320]于是此刻我举头仰望
         [03:29.329]延伸到你身旁的天空
         [03:34.190]于是此刻我又放声歌唱
         [03:39.730]无论身处何方也应该传递得到
         [03:44.640]于是此刻你们两人
         [03:49.710]凝望着光辉闪烁的明天
         [03:54.720]于是此刻  你听  歌声响彻
         [04:00.240]你曾经教会我唱的歌谣  回响于这片天空
         */

        private int version;
        private String lyric;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getLyric() {
            return lyric;
        }

        public void setLyric(String lyric) {
            this.lyric = lyric;
        }
    }
}
