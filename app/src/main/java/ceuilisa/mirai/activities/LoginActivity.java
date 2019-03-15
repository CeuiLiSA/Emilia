package ceuilisa.mirai.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.CubeGrid;

import java.lang.reflect.Field;

import ceuilisa.mirai.R;
import ceuilisa.mirai.fragments.BaseFragment;
import ceuilisa.mirai.fragments.FragmentLogin;
import ceuilisa.mirai.fragments.FragmentSign;
import ceuilisa.mirai.interf.OnPrepared;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.BackResponse;
import ceuilisa.mirai.response.PlayListTitleResponse;
import ceuilisa.mirai.response.UserBean;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Constant;
import ceuilisa.mirai.utils.FixedSpeedScroller;
import ceuilisa.mirai.utils.Local;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    private ViewPager mViewPager;
    private ProgressBar mProgressBar;
    private BaseFragment[] mBaseFragments;

    @Override
    void initLayout() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        mLayoutID = R.layout.activity_login;
    }

    @Override
    void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        mProgressBar = findViewById(R.id.progress);
        CubeGrid cubeGrid = new CubeGrid();
        cubeGrid.setColor(getResources().getColor(R.color.colorPrimary));
        mProgressBar.setIndeterminateDrawable(cubeGrid);
        mViewPager = findViewById(R.id.frameLayout);
        mBaseFragments = new BaseFragment[]{new FragmentLogin(), new FragmentSign()};
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(mViewPager.getContext(),
                    new AccelerateInterpolator());
            field.set(mViewPager, scroller);
            scroller.setmDuration(400);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mBaseFragments[i];
            }

            @Override
            public int getCount() {
                return mBaseFragments.length;
            }
        });
    }

    @Override
    void initData() {

    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
    }

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        mProgressBar = progressBar;
    }

    public BaseFragment[] getBaseFragments() {
        return mBaseFragments;
    }

    public void setBaseFragments(BaseFragment[] baseFragments) {
        mBaseFragments = baseFragments;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(mContext, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
