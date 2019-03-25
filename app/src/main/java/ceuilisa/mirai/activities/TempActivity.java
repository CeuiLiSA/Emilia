package ceuilisa.mirai.activities;


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

    @Override
    protected Fragment createNewFragment() {
        return new TestListFragment();
    }
}

