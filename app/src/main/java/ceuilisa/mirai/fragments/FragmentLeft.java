package ceuilisa.mirai.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ceuilisa.mirai.MainActivity;
import ceuilisa.mirai.R;

public class FragmentLeft extends BaseFragment {


    @Override
    View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_left, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((MainActivity) getActivity()).mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    ((MainActivity) getActivity()).mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    ((MainActivity) getActivity()).mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        return view;
    }

}