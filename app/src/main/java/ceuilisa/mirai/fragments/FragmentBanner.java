package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.activities.PlayListDetailActivity;
import ceuilisa.mirai.activities.TemplateFragmentActivity;
import ceuilisa.mirai.activities.VideoPlayActivity;
import ceuilisa.mirai.nodejs.BannerResponse;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Common;

public class FragmentBanner extends BaseFragment {

    private BannerResponse.BannersBean mBannersBean;

    public static FragmentBanner newInstance(BannerResponse.BannersBean bean){
        FragmentBanner fragmentBanner = new FragmentBanner();
        fragmentBanner.mBannersBean = bean;
        return fragmentBanner;
    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_banner;
    }

    @Override
    View initView(View v) {
        ImageView banner = v.findViewById(R.id.banner_image);
        Glide.with(mContext).load(mBannersBean.getPic()).into(banner);
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBannersBean.getTargetType() == 1000){
                    Intent intent = new Intent(mContext, PlayListDetailActivity.class);
                    intent.putExtra("id", mBannersBean.getTargetId());
                    intent.putExtra("name", "歌单");
                    intent.putExtra("author", "创建者");
                    intent.putExtra("dataType", "歌单");
                    intent.putExtra("coverImg", "");
                    mContext.startActivity(intent);
                } else if(mBannersBean.getTargetType() == 1) {
                    List<TracksBean> allDatas = new ArrayList<>();
                    allDatas.add(mBannersBean.getSong());
                    mChannel.setMusicList(allDatas);
                    Intent intent = new Intent(mContext, MusicActivity.class);
                    intent.putExtra("index", 0);
                    startActivity(intent);
                } else if(mBannersBean.getTargetType() == 10) { //专辑
                    Intent intent = new Intent(mContext, PlayListDetailActivity.class);
                    intent.putExtra("id", mBannersBean.getTargetId());
                    intent.putExtra("name", "专辑");
                    intent.putExtra("author", "创建者");
                    intent.putExtra("dataType", "专辑");
                    intent.putExtra("coverImg", "");
                    startActivity(intent);
                } else if(mBannersBean.getTargetType() == 1004) { //视频
                    Intent intent = new Intent(mContext, VideoPlayActivity.class);
                    intent.putExtra("mv id", mBannersBean.getTargetId());
                    intent.putExtra("dataType", "mv");
                    startActivity(intent);
                } else if(!TextUtils.isEmpty(mBannersBean.getUrl())) {
                    Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
                    intent.putExtra("dataType", "网页链接");
                    intent.putExtra("title", "网页链接");
                    intent.putExtra("url", mBannersBean.getUrl());
                    startActivity(intent);
                } else {
                    Common.showToast("无链接");
                }
            }
        });
        return v;
    }

    @Override
    void initData() {

    }
}
