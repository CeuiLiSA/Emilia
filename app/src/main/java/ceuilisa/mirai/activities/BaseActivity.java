package ceuilisa.mirai.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected Activity mActivity;
    protected int mLayoutID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutID();
        setContentView(mLayoutID);

        initView();
        initData();
    }


    void initView() {
        mContext = this;
        mActivity = this;

    }

    abstract void initData();

    abstract void setLayoutID();
}
