package ceuilisa.mirai.activities;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tbruyelle.rxpermissions2.RxPermissions;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.fragments.BaseFragment;
import ceuilisa.mirai.fragments.FragmentLeft;
import ceuilisa.mirai.fragments.FragmentMvNew;
import ceuilisa.mirai.interf.OnPrepared;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Local;

public class MainActivity extends WithPanelActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String[] TITLES = new String[]{"音乐", "视频"};
    public DrawerLayout mDrawerLayout;
    private TextView userName, email;
    private ViewPager mViewPager;
    private BaseFragment[] mBaseFragments;

    @Override
    boolean hasImage() {
        return false;
    }

    @Override
    boolean hasProgress() {
        return false;
    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_main;
    }

    @Override
    void initView() {
        super.initView();
        mDrawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> mDrawerLayout.openDrawer(Gravity.START));
        NavigationView navigationView = findViewById(R.id.nav_view);
        userName = navigationView.getHeaderView(0).findViewById(R.id.user_name);
        email = navigationView.getHeaderView(0).findViewById(R.id.email);
        ImageView userHead = navigationView.getHeaderView(0).findViewById(R.id.header);
        ImageView userBG = navigationView.getHeaderView(0).findViewById(R.id.user_background);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
        checkPermission(object -> {
            LoginResponse mUserBean = Local.getUser();
            if (mUserBean != null && mUserBean.isLogin()) {
                Glide.with(mContext).load(mUserBean.getProfile().getAvatarUrl()).into(userHead);
                Glide.with(mContext).load(mUserBean.getProfile().getBackgroundUrl()).into(userBG);
                userBG.setOnClickListener(v -> {
                    Intent intent = new Intent(mContext, UserDetailActivity.class);
                    intent.putExtra("user id", mUserBean.getProfile().getUserId());
                    startActivity(intent);
                });
                initFragments();
            } else {
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void checkPermission(OnPrepared<Object> onPrepared) {
        final RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .requestEachCombined(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE)
                .subscribe(permission -> { // will emit 1 Permission object
                    if (permission.granted) {
                        onPrepared.doSomething(null);
                    } else {
                        // At least one denied permission with ask never again
                        // Need to go to the settings
                        Common.showToast(mContext, "请给与足够的权限");
                        finish();
                    }
                });
    }

    @Override
    void initData() {
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);
    }

    private void initFragments() {
        mBaseFragments = new BaseFragment[]{new FragmentLeft(), new FragmentMvNew()};
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mBaseFragments[i];
            }

            @Override
            public int getCount() {
                return mBaseFragments.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return TITLES[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(mContext, ViewPagerActivity.class);
            intent.putExtra("dataType", "听歌记录");
            mContext.startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(mContext, ViewPagerActivity.class);
            intent.putExtra("dataType", "陈列室");
            mContext.startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(mContext, ViewPagerActivity.class);
            intent.putExtra("dataType", "推荐mv");
            mContext.startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(mContext, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
//            Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
//            intent.putExtra("dataType", "项目主页");
//            intent.putExtra("title", "项目主页");
//            intent.putExtra("url", "https://github.com/CeuiLiSA/Emilia");
//            mContext.startActivity(intent);
            Common.openUrl("https://github.com/CeuiLiSA/Emilia", mContext);
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        LoginResponse userBean = Local.getUser();
        if (userBean != null && userBean.getProfile() != null) {
            userName.setText(userBean.getProfile().getNickname());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Intent intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings:
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
