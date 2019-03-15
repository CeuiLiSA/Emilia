package ceuilisa.mirai.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.response.UserBean;
import ceuilisa.mirai.utils.Local;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected Activity mActivity;
    protected int mLayoutID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
        setContentView(mLayoutID);

        mContext = this;
        mActivity = this;


        initView();
        initData();
    }



    abstract void initLayout();

    abstract void initView();

    abstract void initData();
}
