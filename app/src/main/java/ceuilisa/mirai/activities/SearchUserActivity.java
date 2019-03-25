package ceuilisa.mirai.activities;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import ceuilisa.mirai.R;
import ceuilisa.mirai.fragments.BaseFragment;
import ceuilisa.mirai.fragments.FragmentSearch;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Constant;

public class SearchUserActivity extends WithPanelActivity{

    private String dataType, key;
    private int nowIndex;
    private RecyclerView mRecyclerView;


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
        mLayoutID = R.layout.activity_search_user;
    }

    @Override
    void initView() {
        super.initView();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    void initData() {
        key = getIntent().getStringExtra("key");
        Common.showLog(key);
    }
}
