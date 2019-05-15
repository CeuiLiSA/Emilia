package ceuilisa.mirai.activities;

import android.support.v4.app.Fragment;
import android.view.View;

import ceuilisa.mirai.fragments.FragmentDayRecm;
import ceuilisa.mirai.fragments.FragmentHotPlaylist;
import ceuilisa.mirai.fragments.FragmentLocalMusic;
import ceuilisa.mirai.fragments.FragmentLocalSearch;
import ceuilisa.mirai.fragments.FragmentMyPlayList;
import ceuilisa.mirai.fragments.FragmentRecmPlaylist;
import ceuilisa.mirai.fragments.FragmentSearchAlbum;
import ceuilisa.mirai.fragments.FragmentSearchArtist;
import ceuilisa.mirai.fragments.FragmentSearchSong;
import ceuilisa.mirai.fragments.FragmentSearchUser;
import ceuilisa.mirai.fragments.FragmentWebView;

public class TemplateFragmentActivity extends FragmentActivity {

    @Override
    protected Fragment createNewFragment() {
        String dataType = getIntent().getStringExtra("dataType");

        if (dataType != null && dataType.length() != 0) {
            if (dataType.equals("每日推荐")) {
                return new FragmentDayRecm();
            } else if (dataType.equals("我的歌单")) {
                return new FragmentMyPlayList();
            } else if (dataType.equals("推荐歌单")) {
                return new FragmentRecmPlaylist();
            } else if (dataType.equals("搜索歌手")) {
                String key = getIntent().getStringExtra("key");
                return FragmentSearchArtist.newInstance(key);
            } else if (dataType.equals("搜索用户")) {
                String key = getIntent().getStringExtra("key");
                return FragmentSearchUser.newInstance(key);
            } else if (dataType.equals("搜索歌曲")) {
                String key = getIntent().getStringExtra("key");
                return FragmentSearchSong.newInstance(key);
            } else if (dataType.equals("搜索专辑")) {
                String key = getIntent().getStringExtra("key");
                return FragmentSearchAlbum.newInstance(key);
            } else if (dataType.equals("本地音乐")) {
                return new FragmentLocalMusic();
            } else if (dataType.equals("网页链接")) {
                String title = getIntent().getStringExtra("title");
                String url = getIntent().getStringExtra("url");
                rootView.setVisibility(View.GONE);
                return FragmentWebView.newInstance(title, url);
            }else if (dataType.equals("列表搜索")) {
                return new FragmentLocalSearch();
            }else if (dataType.equals("热门歌单")) {
                String tag = getIntent().getStringExtra("tag");
                return FragmentHotPlaylist.newInstance(tag);
            }
        }

        return null;
    }
}
