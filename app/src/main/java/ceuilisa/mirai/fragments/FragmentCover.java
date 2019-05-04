package ceuilisa.mirai.fragments;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.bumptech.glide.Glide;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.CoverDetailActivity;
import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.utils.Common;
import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentCover extends BaseFragment {

    private ObjectAnimator mAnimator;
    private CircleImageView mCircleImageView;

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_cover;
    }

    @Override
    View initView(View v) {
        mCircleImageView = v.findViewById(R.id.cover);
        mCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CoverDetailActivity.class);
                mTracksBean = mChannel.getMusicList().get(MusicService.get().getNowPlayIndex());
                intent.putExtra("cover", mTracksBean.getAl().getPicUrl());
                startActivity(intent);
            }
        });
        mAnimator = ObjectAnimator.ofFloat(mCircleImageView, "rotation", 0f, 360.0f);
        mAnimator.setDuration(50000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setRepeatCount(-1);
        return v;
    }

    public void refreshAnimation() {
        if(mAnimator == null){
            mAnimator = ObjectAnimator.ofFloat(mCircleImageView, "rotation", 0f, 360.0f);
            mAnimator.setDuration(50000);
            mAnimator.setInterpolator(new LinearInterpolator());
            mAnimator.setRepeatCount(-1);
            Common.showLog("FragmentCover refreshAnimation 上面的");
        }
        Common.showLog("FragmentCover refreshAnimation 下面的");
        mAnimator.start();
        mAnimator.pause();
    }

    public void resumeAnimation() {
        if(mAnimator == null){
            mAnimator = ObjectAnimator.ofFloat(mCircleImageView, "rotation", 0f, 360.0f);
            mAnimator.setDuration(50000);
            mAnimator.setInterpolator(new LinearInterpolator());
            mAnimator.setRepeatCount(-1);
            Common.showLog("FragmentCover resumeAnimation 上面的");
        }
        Common.showLog("FragmentCover resumeAnimation 下面的");
        mAnimator.resume();
    }

    public void pauseAnimation() {
        if(mAnimator == null){
            mAnimator = ObjectAnimator.ofFloat(mCircleImageView, "rotation", 0f, 360.0f);
            mAnimator.setDuration(50000);
            mAnimator.setInterpolator(new LinearInterpolator());
            mAnimator.setRepeatCount(-1);
            mAnimator.start();
            Common.showLog("FragmentCover pauseAnimation 上面的");
        }
        Common.showLog("FragmentCover pauseAnimation 下面的");
        mAnimator.pause();
    }

    @Override
    void initData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadCover();
    }

    public void loadCover() {
        if (getActivity() != null) {
            if (mChannel != null && mChannel.getMusicList().size() != 0) {
                refreshAnimation();
                Common.showLog(className + " loadcover");
                mTracksBean = mChannel.getMusicList().get(MusicService.get().getNowPlayIndex());
                Glide.with(getActivity())
                        .load(mTracksBean.getAl().getPicUrl())
                        //.placeholder(R.color.white)
                        .into(mCircleImageView);
                if(MusicService.get().isPlayingMusic()) {
                    mAnimator.start();
                }
            }
        }
    }
}
