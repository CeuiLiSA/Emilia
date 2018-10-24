package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import ceuilisa.mirai.activities.MainActivity;
import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.PlayHistoryActivity;
import ceuilisa.mirai.activities.PlayListActivity;
import ceuilisa.mirai.activities.PlayListDetailActivity;

public class FragmentLeft extends BaseFragment {

    @Override
    View initView(View v) {
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v1 -> {
            if (((MainActivity) getActivity()).mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                ((MainActivity) getActivity()).mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                ((MainActivity) getActivity()).mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        TextView textView3 = v.findViewById(R.id.textView3);
        textView3.setOnClickListener(v12 -> {
            Intent intent = new Intent(mContext, PlayHistoryActivity.class);
            mContext.startActivity(intent);
        });
        TextView textView1 = v.findViewById(R.id.textView6);
        textView1.setOnClickListener(v12 -> {
            Intent intent = new Intent(mContext, PlayListDetailActivity.class);
            intent.putExtra("id", "139786239");
            intent.putExtra("name", "CeuiLiSA喜欢的音乐");
            intent.putExtra("coverImg", "http://p1.music.126.net/id-110tprTCxF4BXNbZIOw==/109951163198268119.jpg");
            mContext.startActivity(intent);
        });
        TextView textView = v.findViewById(R.id.textView7);
        textView.setOnClickListener(v12 -> {
            Intent intent = new Intent(mContext, PlayListActivity.class);
            mContext.startActivity(intent);
        });
        return v;
    }

    @Override
    void initData() {

    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_left;
    }
}