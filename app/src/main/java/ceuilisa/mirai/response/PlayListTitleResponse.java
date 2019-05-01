package ceuilisa.mirai.response;

import java.util.List;

import ceuilisa.mirai.nodejs.PlaylistBean;

public class PlayListTitleResponse {


    /**
     * result : {"playlists":[{"id":369790156,"name":"『日系』清新女声","coverImgUrl":"http://p1.music.126.net/DlotD-DtCq_OgLF7KluqIg==/3412884094557524.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":203,"userId":113568254,"playCount":1149,"bookCount":4,"description":"Ho-kago Tea Time 放课後的下午茶时间","highQuality":false,"alg":"alg_playlist_basic"},{"id":314446309,"name":"『ACG』","coverImgUrl":"http://p1.music.126.net/zLD6C6gJx6dIqqkVvPOYfA==/3419481179937542.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":235,"userId":113568254,"playCount":1267,"bookCount":7,"description":"TV动画OP ED，剧中歌，角色歌，插入曲","highQuality":false,"alg":"alg_playlist_basic"},{"id":309390784,"name":"『Pure』纯纯纯音乐","coverImgUrl":"http://p1.music.126.net/EIcbllDDhD9SMKnYk-nDHQ==/3402988489378446.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":267,"userId":113568254,"playCount":3417,"bookCount":1,"description":"less is more","highQuality":false,"alg":"alg_playlist_basic"},{"id":375037521,"name":"『战斗音乐』","coverImgUrl":"http://p1.music.126.net/TeHhzV4xnX_s1tQWVVc4Qg==/2946691164520800.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":104,"userId":113568254,"playCount":889,"bookCount":2,"description":"没有人能在我的bgm里被我打败","highQuality":false,"alg":"alg_playlist_basic"},{"id":954125655,"name":"『日系』男","coverImgUrl":"http://p1.music.126.net/eYs9Gk5HvV0NL43ngNdBYw==/109951163041416248.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":83,"userId":113568254,"playCount":132,"bookCount":0,"description":"我才不要矫情的歌曲介绍(￣▽￣)","highQuality":false,"alg":"alg_playlist_basic"},{"id":394630702,"name":"『欧美』","coverImgUrl":"http://p1.music.126.net/YbL5FRuryNSDfd-v9C24aQ==/3393092888204717.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":105,"userId":113568254,"playCount":250,"bookCount":0,"description":"","highQuality":false,"alg":"alg_playlist_basic"},{"id":2238268434,"name":"『日常』","coverImgUrl":"http://p1.music.126.net/l2dmkPbN_zJtDoOmWUNrrA==/2504687488110680.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":45,"userId":113568254,"playCount":29,"bookCount":0,"description":null,"highQuality":false,"alg":"alg_playlist_basic"},{"id":401594702,"name":"『国语』","coverImgUrl":"http://p1.music.126.net/avFX2mtDUunAxFBwhZgS0A==/18638921115521635.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":63,"userId":113568254,"playCount":119,"bookCount":0,"description":"","highQuality":false,"alg":"alg_playlist_basic"},{"id":522456961,"name":"『R&B』","coverImgUrl":"http://p1.music.126.net/46eUqtSi1GArszH3VKrfvg==/18640020627149376.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":131,"userId":113568254,"playCount":128,"bookCount":2,"description":null,"highQuality":false,"alg":"alg_playlist_basic"}],"playlistCount":9}
     * code : 200
     */

    private ResultBean result;
    private int code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ResultBean {
        /**
         * playlists : [{"id":369790156,"name":"『日系』清新女声","coverImgUrl":"http://p1.music.126.net/DlotD-DtCq_OgLF7KluqIg==/3412884094557524.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":203,"userId":113568254,"playCount":1149,"bookCount":4,"description":"Ho-kago Tea Time 放课後的下午茶时间","highQuality":false,"alg":"alg_playlist_basic"},{"id":314446309,"name":"『ACG』","coverImgUrl":"http://p1.music.126.net/zLD6C6gJx6dIqqkVvPOYfA==/3419481179937542.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":235,"userId":113568254,"playCount":1267,"bookCount":7,"description":"TV动画OP ED，剧中歌，角色歌，插入曲","highQuality":false,"alg":"alg_playlist_basic"},{"id":309390784,"name":"『Pure』纯纯纯音乐","coverImgUrl":"http://p1.music.126.net/EIcbllDDhD9SMKnYk-nDHQ==/3402988489378446.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":267,"userId":113568254,"playCount":3417,"bookCount":1,"description":"less is more","highQuality":false,"alg":"alg_playlist_basic"},{"id":375037521,"name":"『战斗音乐』","coverImgUrl":"http://p1.music.126.net/TeHhzV4xnX_s1tQWVVc4Qg==/2946691164520800.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":104,"userId":113568254,"playCount":889,"bookCount":2,"description":"没有人能在我的bgm里被我打败","highQuality":false,"alg":"alg_playlist_basic"},{"id":954125655,"name":"『日系』男","coverImgUrl":"http://p1.music.126.net/eYs9Gk5HvV0NL43ngNdBYw==/109951163041416248.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":83,"userId":113568254,"playCount":132,"bookCount":0,"description":"我才不要矫情的歌曲介绍(￣▽￣)","highQuality":false,"alg":"alg_playlist_basic"},{"id":394630702,"name":"『欧美』","coverImgUrl":"http://p1.music.126.net/YbL5FRuryNSDfd-v9C24aQ==/3393092888204717.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":105,"userId":113568254,"playCount":250,"bookCount":0,"description":"","highQuality":false,"alg":"alg_playlist_basic"},{"id":2238268434,"name":"『日常』","coverImgUrl":"http://p1.music.126.net/l2dmkPbN_zJtDoOmWUNrrA==/2504687488110680.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":45,"userId":113568254,"playCount":29,"bookCount":0,"description":null,"highQuality":false,"alg":"alg_playlist_basic"},{"id":401594702,"name":"『国语』","coverImgUrl":"http://p1.music.126.net/avFX2mtDUunAxFBwhZgS0A==/18638921115521635.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":63,"userId":113568254,"playCount":119,"bookCount":0,"description":"","highQuality":false,"alg":"alg_playlist_basic"},{"id":522456961,"name":"『R&B』","coverImgUrl":"http://p1.music.126.net/46eUqtSi1GArszH3VKrfvg==/18640020627149376.jpg","creator":{"nickname":"CeuiLiSA","userId":113568254,"userType":0,"authStatus":0,"expertTags":null,"experts":null},"subscribed":false,"trackCount":131,"userId":113568254,"playCount":128,"bookCount":2,"description":null,"highQuality":false,"alg":"alg_playlist_basic"}]
         * playlistCount : 9
         */

        private int playlistCount;
        private List<PlaylistBean> playlists;

        public int getPlaylistCount() {
            return playlistCount;
        }

        public void setPlaylistCount(int playlistCount) {
            this.playlistCount = playlistCount;
        }

        public List<PlaylistBean> getPlaylists() {
            return playlists;
        }

        public void setPlaylists(List<PlaylistBean> playlists) {
            this.playlists = playlists;
        }

    }
}
