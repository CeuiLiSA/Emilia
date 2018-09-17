package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import ceuilisa.mirai.MainActivity;
import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.PlayListActivity;
import ceuilisa.mirai.network.AppApi;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.PlayListTitleResponse;
import ceuilisa.mirai.utils.Common;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class FragmentLeft extends BaseFragment {

    @Override
    View initView(View v) {
        Toolbar toolbar = v.findViewById(R.id.toolbar);
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
        TextView textView = v.findViewById(R.id.textView7);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PlayListActivity.class);
                mContext.startActivity(intent);
            }
        });
        return v;
    }

    @Override
    void initData() {
        RetrofitUtil.getAppApi().getAllPlayList("CeuiLiSA")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayListTitleResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(PlayListTitleResponse playListTitleResponse) {
                        Common.showLog(playListTitleResponse.result.playlists.get(1).name);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    void setLayoutID() {
        mLayoutID = R.layout.fragment_left;
    }
}