package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import ceuilisa.mirai.activities.MainActivity;
import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.PlayListActivity;

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