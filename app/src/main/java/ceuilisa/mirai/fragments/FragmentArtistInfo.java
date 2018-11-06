package ceuilisa.mirai.fragments;

import android.view.View;
import android.widget.TextView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.response.ArtistResponse;

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

    public void showInfo(ArtistResponse.ArtistBean artistBean) {
        artistName.setText(String.format("%s简介", artistBean.getName()));
        if (artistBean.getBriefDesc() != null &&
                artistBean.getBriefDesc().length() != 0) {
            artistInfo.setText(artistBean.getBriefDesc());
        } else {
            artistInfo.setText("无相关信息");
        }
    }
}
