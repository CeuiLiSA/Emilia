package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.view.View;

import ceuilisa.mirai.activities.ArtistActivity;
import ceuilisa.mirai.activities.UserDetailActivity;
import ceuilisa.mirai.adapters.ArtistAdapter;
import ceuilisa.mirai.adapters.UserAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.ArtistBean;
import ceuilisa.mirai.nodejs.SearchUserResponse;
import ceuilisa.mirai.nodejs.UserprofilesBean;
import ceuilisa.mirai.response.SearchArtistResponse;
import ceuilisa.mirai.utils.DensityUtil;
import ceuilisa.mirai.utils.LinearItemDecoration;
import io.reactivex.Observable;

public class FragmentSearchUser extends BaseListFragment<SearchUserResponse, UserAdapter, UserprofilesBean> {

    private String keyword;

    public static FragmentSearchUser newInstance(String key){
        FragmentSearchUser fragmentSearchArtist = new FragmentSearchUser();
        fragmentSearchArtist.keyword = key;
        return fragmentSearchArtist;
    }

    @Override
    View initView(View v) {
        super.initView(v);
        mRecyclerView.addItemDecoration(new LinearItemDecoration(DensityUtil.dip2px(mContext, 12.0f)));
        return v;
    }

    @Override
    String getToolbarTitle() {
        return keyword + "的搜索结果";
    }

    @Override
    Observable<SearchUserResponse> initApi() {
        return RetrofitUtil.getNodeApi().searchUser(keyword, PAGE_SIZE, allItems.size());
    }

    @Override
    void initAdapter() {
        mAdapter = new UserAdapter(allItems, mContext);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int viewType) {
                Intent intent = new Intent(mContext, UserDetailActivity.class);
                intent.putExtra("user id", allItems.get(position).getUserId());
                startActivity(intent);
            }
        });
    }
}
