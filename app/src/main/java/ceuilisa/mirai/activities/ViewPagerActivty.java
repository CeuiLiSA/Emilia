package ceuilisa.mirai.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;


import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.fragments.BaseFragment;
import ceuilisa.mirai.fragments.FragmentNewSong;
import ceuilisa.mirai.fragments.FragmentViewPager;

public class ViewPagerActivty extends FragmentActivity {

    @Override
    protected Fragment createNewFragment() {
        Intent intent = getIntent();
        String dataType = intent.getStringExtra("dataType");
        List<BaseFragment> baseFragmentList = new ArrayList<>();
        if(dataType.equals("新歌速递")){
            baseFragmentList.add(FragmentNewSong.newInstance(7));
            baseFragmentList.add(FragmentNewSong.newInstance(96));
            baseFragmentList.add(FragmentNewSong.newInstance(8));
            baseFragmentList.add(FragmentNewSong.newInstance(16));
            String[] titles = new String[]{"华语", "欧美", "日本", "韩国"};
            return FragmentViewPager.newInstance(baseFragmentList, titles, dataType);
        }
        return null;
    }
}
