package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.nodejs.UserDetailResponse;
import ceuilisa.mirai.utils.Common;

public class FragmentAboutUser extends BaseFragment {

    private UserDetailResponse mUserDetail;
    private TextView description, level, sex;


    public static FragmentAboutUser newInstance(UserDetailResponse response) {
        FragmentAboutUser fragment = new FragmentAboutUser();
        fragment.setUserDetailResponse(response);
        return fragment;
    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_about_user;
    }

    @Override
    View initView(View v) {
        description = v.findViewById(R.id.description);
        level = v.findViewById(R.id.level);
        sex = v.findViewById(R.id.sex);
        return v;
    }

    @Override
    void initData() {
        if(mUserDetail != null) {
            level.setText("等级：" + mUserDetail.getLevel());
            sex.setText("性别：" + (mUserDetail.getProfile().getGender() == 1 ? "男" : "女"));
            if(mUserDetail.getProfile().getSignature() != null && mUserDetail.getProfile().getSignature().length() != 0) {
                description.setText(mUserDetail.getProfile().getSignature());
            }else {
                description.setText("暂无介绍");
            }
        }
    }

    public UserDetailResponse getUserDetailResponse() {
        return mUserDetail;
    }

    public void setUserDetailResponse(UserDetailResponse userDetailResponse) {
        mUserDetail = userDetailResponse;
    }
}
