package ceuilisa.mirai.activities;

import android.support.v4.app.Fragment;

import ceuilisa.mirai.fragments.FragmentMyPlayList;

public class MyPlayListActivity extends FragmentActivity {

    @Override
    protected Fragment createNewFragment() {
        return new FragmentMyPlayList();
    }
}
