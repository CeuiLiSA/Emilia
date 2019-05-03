package ceuilisa.mirai.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ceuilisa.mirai.network.MusicChannel;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Local;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected Activity mActivity;
    protected int mLayoutID;
    protected LoginResponse user;
    protected MusicChannel mChannel = MusicChannel.get();
    protected TracksBean mTracksBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
        setContentView(mLayoutID);

        mContext = this;
        mActivity = this;

        user = Local.getUser();

        initView();
        initData();
    }


    abstract void initLayout();

    abstract void initView();

    abstract void initData();
}
