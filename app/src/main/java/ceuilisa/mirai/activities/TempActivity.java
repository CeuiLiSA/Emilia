package ceuilisa.mirai.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import ceuilisa.mirai.fragments.TestListFragment;

/**
 * class name: TempActivity.class
 * <p>
 * description:
 * author: @CeuiLiSA
 * e-mail: fatemercis@qq.com
 * website: https://github.com/CeuiLiSA
 * created at: 2019/3/24 8:56 PM
 */
public class TempActivity extends FragmentActivity {

    private String key = "", title = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        title = intent.getStringExtra("title");
    }

    @Override
    protected Fragment createNewFragment() {
        if(title.equals("搜索专辑")){

        }
        return new TestListFragment();
    }
}

