package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Local;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentLogin extends BaseFragment {

    private ProgressBar mProgressBar;
    private EditText userName, password;
    private CardView login;
    private TextView goSign;
    private View rootView;


    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_login;
    }

    @Override
    View initView(View v) {
        goSign = v.findViewById(R.id.go_sign);
        goSign.setOnClickListener(view -> {
            ((LoginActivity) getActivity()).getViewPager().setCurrentItem(1);
            ((FragmentSign) ((LoginActivity) getActivity()).getBaseFragments()[1]).showAnimate();
        });
        userName = v.findViewById(R.id.user_name);
        password = v.findViewById(R.id.password);
        login = v.findViewById(R.id.login);
        login.setOnClickListener(view -> {
            Common.hideKeyboard(mActivity);
            if (userName.getText().toString().trim().length() != 0) {
                if (password.getText().toString().trim().length() != 0) {
                    login();
                } else {
                    Common.showToast("请输入密码");
                }
            } else {
                Common.showToast("请输入用户名");
            }
        });
        rootView = v;
        userName.setText("13990845246");
        password.setText("merciskjl");
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mProgressBar = ((LoginActivity) getActivity()).getProgressBar();
    }

    private void login() {
        mProgressBar.setVisibility(View.VISIBLE);
        RetrofitUtil.getNodeApi().loginByPhone(userName.getText().toString().trim(),
                password.getText().toString().trim())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        if (loginResponse != null) {
                            if (loginResponse.getAccount() != null) {
                                loginResponse.setUserName(userName.getText().toString().trim());
                                loginResponse.setPassword(password.getText().toString().trim());
                                Local.saveUser(loginResponse, new OnPrepared<Object>() {
                                    @Override
                                    public void doSomething(Object o) {
                                        mProgressBar.setVisibility(View.INVISIBLE);
                                        Common.showToast("用户信息保存成功");
                                        Intent intent = new Intent(mContext, MainActivity.class);
                                        startActivity(intent);
                                        getActivity().finish();
                                    }
                                });
                            } else {
                                if (loginResponse.getMsg() != null && loginResponse.getMsg().length() != 0) {
                                    Common.showToast(loginResponse.getMsg());
                                } else {
                                    Common.showToast("登录失败");
                                }
                            }
                        } else {
                            Common.showToast("登录失败");
                            mProgressBar.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Common.showToast("登录失败");
                        mProgressBar.setVisibility(View.INVISIBLE);
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    void initData() {
//        LoginResponse userBean = Local.getUser();
//        if (userBean != null && userBean.getUserName() != null && userBean.getUserName().length() != 0) {
//            userName.setText(userBean.getUserName());
//        }
//        if (userBean != null && userBean.getPassword() != null && userBean.getPassword().length() != 0) {
//            password.setText(userBean.getPassword());
//        }
    }

    public void showAnimate() {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = rootView.findViewById(R.id.root_view);
        relativeLayout.startAnimation(animation);
    }
}
