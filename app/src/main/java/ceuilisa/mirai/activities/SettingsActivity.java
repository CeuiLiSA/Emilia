package ceuilisa.mirai.activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.dialogs.BaseDialog;
import ceuilisa.mirai.dialogs.ClearDataDialog;
import ceuilisa.mirai.interf.OnPrepared;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.response.UserBean;
import ceuilisa.mirai.utils.Local;

public class SettingsActivity extends BaseActivity{

    private Button loginOut, loginOutClear;
    private TextView userName, netEasyAccount;

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_settings;
    }

    @Override
    void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        loginOut = findViewById(R.id.login_out);
        loginOutClear = findViewById(R.id.login_out_and_clear);
        userName = findViewById(R.id.user_name);
        netEasyAccount = findViewById(R.id.wangyiyun_id);
        loginOut.setOnClickListener(v -> {
            Local.loginOut(new OnPrepared<Object>() {
                @Override
                public void doSomething(Object o) {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        });
        loginOutClear.setOnClickListener(v -> {
            ClearDataDialog clearDataDialog = new ClearDataDialog();
            clearDataDialog.setPositiveClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Local.clearLocal(o -> {
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    });
                    clearDataDialog.dismiss();
                }
            });
            clearDataDialog.show(getSupportFragmentManager(), "clear data");
        });
    }

    private void fillData(){
        LoginResponse userBean = Local.getUser();
        if(userBean != null){
            userName.setText(userBean.getUserName());
            netEasyAccount.setText(userBean.getPassword());
        }
    }

    @Override
    void initData() {
        fillData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillData();
    }
}
