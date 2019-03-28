package ceuilisa.mirai.activities;

import android.support.v4.app.Fragment;

import ceuilisa.mirai.fragments.FragmentRecmPlaylist;

public class RecmPlaylistActivity extends FragmentActivity{

    @Override
    protected Fragment createNewFragment() {
        return new FragmentRecmPlaylist();
    }
}
