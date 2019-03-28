package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.view.View;

import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.adapters.PlayAllHistoryAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.response.HistorySongBean;
import ceuilisa.mirai.response.PlayWeekHistoryResponse;
import ceuilisa.mirai.utils.Local;
import ceuilisa.mirai.utils.Translate;
import io.reactivex.Observable;

public class FragmentPlayWeekHistory extends BaseListFragment<PlayWeekHistoryResponse, PlayAllHistoryAdapter, HistorySongBean> {

    @Override
    Observable<PlayWeekHistoryResponse> initApi() {
        LoginResponse user = Local.getUser();
        return RetrofitUtil.getImjadApi().getWeekPlayHistory(user.getProfile().getUserId(), 1);
    }

    @Override
    boolean showToolbar() {
        return false;
    }

    @Override
    void initAdapter() {
        mAdapter = new PlayAllHistoryAdapter(allItems, mContext);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int viewType) {
                Translate.translateMusic(allItems);
                Intent intent = new Intent(mContext, MusicActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });
    }
}
