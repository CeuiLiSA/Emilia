package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.response.ArtistResponse;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Reference;

public class FragmentArtistInfo extends BaseFragment {

    private TextView artistName, artistInfo;

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_artist_info;
    }

    @Override
    View initView(View v) {
        artistName = v.findViewById(R.id.artist_name);
        artistInfo = v.findViewById(R.id.artist_info);
        return v;
    }

    @Override
    void initData() {
    }

    public void showInfo(ArtistResponse.ArtistBean artistBean){
        artistName.setText(String.format("%s简介", artistBean.getName()));
        artistInfo.setText(artistBean.getBriefDesc());
    }
}
