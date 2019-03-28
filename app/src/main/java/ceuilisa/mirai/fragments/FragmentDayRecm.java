package ceuilisa.mirai.fragments;

import android.view.View;

import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.adapters.RecmSongAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.DayRecommend;
import ceuilisa.mirai.nodejs.RecommendSong;
import ceuilisa.mirai.response.TracksBean;
import io.reactivex.Observable;

public class FragmentDayRecm extends BaseListFragment<DayRecommend, RecmSongAdapter, RecommendSong>{

    @Override
    boolean hasNext() {
        return false;
    }

    @Override
    String getToolbarTitle() {
        return "每日推荐";
    }

    @Override
    Observable<DayRecommend> initApi() {
        return RetrofitUtil.getNodeApi().getDayRecommend();
    }

    @Override
    void initAdapter() {
        mAdapter = new RecmSongAdapter(allItems, mContext);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int viewType) {

            }
        });
    }
}
