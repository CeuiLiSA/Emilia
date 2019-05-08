package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.LoginActivity;
import ceuilisa.mirai.activities.MainActivity;
import ceuilisa.mirai.interf.OnPrepared;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.response.BaseResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Local;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentSign extends BaseFragment {

    private ProgressBar mProgressBar;
    private EditText userName, password, phoneNumber, signNumber;
    private CardView sign;
    private TextView goLogin;
    private Button getSignNumber;
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
        phoneNumber = v.findViewById(R.id.phone_number);
        signNumber = v.findViewById(R.id.sign_number);
        goLogin = v.findViewById(R.id.go_login);
        getSignNumber = v.findViewById(R.id.get_sign_number);
        getSignNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneNumber.getText().toString().length() != 11) {
                    Common.showToast("请输入11位手机号");
                } else {
                    getSignNumber();
                }
            }
        });
        goLogin.setOnClickListener(view -> {
            ((LoginActivity) getActivity()).getViewPager().setCurrentItem(0);
            ((FragmentLogin) ((LoginActivity) getActivity()).getBaseFragments()[0]).showAnimate();
        });
        password = v.findViewById(R.id.password);
        sign = v.findViewById(R.id.sign);
        sign.setOnClickListener(view -> {
            Common.hideKeyboard(mActivity);
            if (phoneNumber.getText().toString().trim().length() == 11) {
                if (userName.getText().toString().trim().length() != 0) {
                    if (password.getText().toString().trim().length() != 0) {
                        if(signNumber.getText().toString().trim().length() != 0){
                            nowSign();
                        }else {
                            Common.showToast("请输入验证码");
                        }
                    } else {
                        Common.showToast("请输入密码");
                    }
                } else {
                    Common.showToast("请输入用户名/昵称");
                }
            }else {
                Common.showToast("请输入11位手机号");
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mProgressBar = ((LoginActivity) getActivity()).getProgressBar();
    }


    private void getSignNumber() {
        Retro.getNodeApi().getSignNumber(phoneNumber.getText().toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (baseResponse != null) {
                            if (baseResponse.getCode() == 200) {
                                Common.showToast("验证码发送成功");
                            } else {
                                Common.showToast("验证码发送失败");
                            }
                        } else {
                            Common.showToast("操作失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Common.showToast(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void nowSign() {
        Retro.getNodeApi().signNetEasy(phoneNumber.getText().toString(),
                password.getText().toString(),
                signNumber.getText().toString(),
                userName.getText().toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        if (loginResponse != null) {
                            if (loginResponse.getCode() == 200) {
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
                                Common.showToast("注册失败");
                            }
                        } else {
                            Common.showToast("操作失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Common.showToast(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

//    private void sign(){
//        mProgressBar.setVisibility(View.VISIBLE);
//        Retro.getTempApi().sign(userName.getText().toString().trim(),
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

    public void showAnimate() {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = rootView.findViewById(R.id.root_view);
        relativeLayout.startAnimation(animation);
    }
}
