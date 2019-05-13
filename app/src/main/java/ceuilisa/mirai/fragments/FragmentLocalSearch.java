package ceuilisa.mirai.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Common;

public class FragmentLocalSearch extends BaseFragment {

    public static List<TracksBean> sTracksBeanList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private PlayListDetailAdapter mAdapter;
    private EditText mEditText;

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_local_search;
    }

    @Override
    View initView(View v) {
        mRecyclerView = v.findViewById(R.id.recy_list);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new PlayListDetailAdapter(sTracksBeanList, mContext);
        mRecyclerView.setAdapter(mAdapter);
        mEditText = v.findViewById(R.id.search_et);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return v;
    }

    @Override
    void initData() {

    }

    private void filter(CharSequence keyWord){
        if(sTracksBeanList == null){
            return;
        }
        List<TracksBean> beanList = new ArrayList<>();
        for (int i = 0; i < sTracksBeanList.size(); i++) {
            if(sTracksBeanList.get(i).getContent().contains(keyWord)){
                beanList.add(sTracksBeanList.get(i));
            }
            mAdapter = new PlayListDetailAdapter(beanList, mContext);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
