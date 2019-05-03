package ceuilisa.mirai.activities;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ceuilisa.mirai.R;

/**
 * class name: FragmentActivity.class
 * <p>
 * description:
 * author: @CeuiLiSA
 * e-mail: fatemercis@qq.com
 * website: https://github.com/CeuiLiSA
 * created at: 2019/3/24 8:54 PM
 */
public abstract class FragmentActivity extends BaseActivity {

    protected abstract Fragment createNewFragment();

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_fragment;
    }

    @Override
    void initView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createNewFragment();
            if (fragment != null) {
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container, fragment)
                        .commit();
            }
        }
    }

    @Override
    void initData() {

    }
}

