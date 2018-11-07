package ceuilisa.mirai.fragments;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.bumptech.glide.Glide;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.MusicActivity;
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
        mAnimator = ObjectAnimator.ofFloat(mCircleImageView, "rotation", 0f, 360.0f);
        mAnimator.setDuration(50000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setRepeatCount(-1);
        return v;
    }

    public void refreshAnimation() {
        mAnimator.start();
        mAnimator.pause();
    }

    public void resumeAnimation() {
        mAnimator.resume();
    }

    public void pauseAnimation() {
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
            if (MusicService.allSongs != null) {
                refreshAnimation();
                int index = ((MusicActivity) getActivity()).index;
                Glide.with(getActivity()).load(MusicService.allSongs.get(index).getAl().getPicUrl()).into(mCircleImageView);
            }
        }
    }
}
