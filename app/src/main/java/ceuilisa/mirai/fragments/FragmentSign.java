package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.LoginActivity;
import ceuilisa.mirai.activities.MainActivity;
import ceuilisa.mirai.interf.OnPrepared;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.BackResponse;
import ceuilisa.mirai.response.UserBean;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Local;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentSign extends BaseFragment{

    private ProgressBar mProgressBar;
    private EditText userName, password;
    private CardView sign;
    private TextView goLogin;
    private View rootView;


    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_sign;
    }

    @Override
    View initView(View v) {
        rootView = v.findViewById(R.id.root_view);
        mProgressBar = v.findViewById(R.id.progress);
        userName = v.findViewById(R.id.user_name);
        goLogin = v.findViewById(R.id.go_login);
        goLogin.setOnClickListener(view -> {
            ((LoginActivity) getActivity()).getViewPager().setCurrentItem(0);
            ((FragmentLogin) ((LoginActivity) getActivity()).getBaseFragments()[0]).showAnimate();
        });
        password = v.findViewById(R.id.password);
        sign = v.findViewById(R.id.sign);
        sign.setOnClickListener(view -> {
            Common.hideKeyboard(mActivity);
            if(userName.getText().toString().trim().length() != 0){
                if(password.getText().toString().trim().length() != 0){
                    //sign();
                }else {
                    Common.showToast("请输入密码");
                }
            }else {
                Common.showToast("请输入用户名");
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mProgressBar = ((LoginActivity) getActivity()).getProgressBar();
    }

//    private void sign(){
//        mProgressBar.setVisibility(View.VISIBLE);
//        RetrofitUtil.getTempApi().sign(userName.getText().toString().trim(),
//                password.getText().toString().trim())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<BackResponse<UserBean>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(BackResponse<UserBean> userBeanBackResponse) {
//                        if(userBeanBackResponse != null){
//                            if(userBeanBackResponse.getDatas() != null) {
//                                if(userBeanBackResponse.getDatas().size() != 0) {
//                                    Local.saveUser(userBeanBackResponse.getDatas().get(0), new OnPrepared<Object>() {
//                                        @Override
//                                        public void doSomething(Object o) {
//                                            mProgressBar.setVisibility(View.INVISIBLE);
//                                            Common.showToast(userBeanBackResponse.getMessage());
//                                            Intent intent = new Intent(mContext, MainActivity.class);
//                                            startActivity(intent);
//                                            getActivity().finish();
//                                        }
//                                    });
//                                }else{
//                                    if(userBeanBackResponse.getMessage() != null &&
//                                            userBeanBackResponse.getMessage().length() != 0){
//                                        Common.showToast(userBeanBackResponse.getMessage());
//                                    }else {
//                                        Common.showToast("登录失败");
//                                    }
//                                    mProgressBar.setVisibility(View.INVISIBLE);
//                                }
//                            }
//                        }else {
//                            Common.showToast("登录失败");
//                            mProgressBar.setVisibility(View.INVISIBLE);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Common.showToast("登录失败");
//                        mProgressBar.setVisibility(View.INVISIBLE);
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }

    @Override
    void initData() {
    }

    public void showAnimate(){
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = rootView.findViewById(R.id.root_view);
        relativeLayout.startAnimation(animation);
    }
}
