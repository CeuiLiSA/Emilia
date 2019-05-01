package ceuilisa.mirai.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import ceuilisa.mirai.fragments.FragmentDayRecm;
import ceuilisa.mirai.fragments.FragmentMyPlayList;
import ceuilisa.mirai.fragments.FragmentRecmPlaylist;
import ceuilisa.mirai.fragments.FragmentSearchArtist;
import ceuilisa.mirai.fragments.FragmentSearchUser;

public class TemplateFragmentActivity extends FragmentActivity {

    @Override
    protected Fragment createNewFragment() {
        String dataType = getIntent().getStringExtra("dataType");

        if(dataType != null && dataType.length() != 0){
            if (dataType.equals("每日推荐")) {
                return new FragmentDayRecm();
            } else if (dataType.equals("我的歌单")) {
                return new FragmentMyPlayList();
            }else if (dataType.equals("推荐歌单")) {
                return new FragmentRecmPlaylist();
            }else if (dataType.equals("搜索歌手")) {
                String key = getIntent().getStringExtra("key");
                return FragmentSearchArtist.newInstance(key);
            }else if (dataType.equals("搜索用户")) {
                String key = getIntent().getStringExtra("key");
                return FragmentSearchUser.newInstance(key);
            }
        }

        return null;
    }
}
