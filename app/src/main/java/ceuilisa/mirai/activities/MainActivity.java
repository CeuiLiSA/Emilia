package ceuilisa.mirai.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.fragments.FragmentCenter;
import ceuilisa.mirai.fragments.FragmentLeft;
import ceuilisa.mirai.fragments.FragmentRight;
import ceuilisa.mirai.interf.OnPrepared;
import ceuilisa.mirai.response.UserBean;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Constant;
import ceuilisa.mirai.utils.Local;

public class MainActivity extends WithPanelActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout mDrawerLayout;
    private TextView userName, email;

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
        NavigationView navigationView = findViewById(R.id.nav_view);
        userName = navigationView.getHeaderView(0).findViewById(R.id.user_name);
        email = navigationView.getHeaderView(0).findViewById(R.id.email);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
        checkPermission(object -> {
//            UserBean mUserBean = Local.getUser();
//            if(mUserBean != null && mUserBean.isLogin()){
//                initFragments();
//            }else {
//                Intent intent = new Intent(mContext, LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
            initFragments();
        });
    }


    private void checkPermission(OnPrepared<Object> onPrepared){
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
        FragmentLeft fragmentLeft = new FragmentLeft();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragmentLeft)
                .show(fragmentLeft)
                .commit();
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
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(mContext, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        UserBean userBean = Local.getUser();
        if(userBean != null) {
            if(userName != null) {
                userName.setText(userBean.getUserName());
            }
            if (userBean.getEmail() != null && userBean.getEmail().length() == 0) {
                if(email != null) {
                    email.setText(userBean.getEmail());
                }
            }
        }
    }
}
