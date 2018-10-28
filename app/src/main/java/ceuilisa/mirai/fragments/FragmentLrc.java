package ceuilisa.mirai.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.Objects;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.LrcResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Reference;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.wcy.lrcview.LrcView;

public class FragmentLrc extends BaseFragment {

    private int index;
    private boolean hasLyric;
    public LrcView mLrcView;

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_lrc;
    }

    @Override
    View initView(View v) {
        mLrcView = v.findViewById(R.id.lrc_view_full);
        return v;
    }

    @Override
    void initData() {
        hasLyric = false;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadLyric();
    }

    public void loadLyric() {
        index = ((MusicActivity) Objects.requireNonNull(getActivity())).index;
        RetrofitUtil.getImjadApi().getLrc(String.valueOf(Reference.allSongs.get(index).getId()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LrcResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(LrcResponse playListTitleResponse) {
                        Common.showLog("onNext");
                        if (playListTitleResponse.isUncollected()) {
                            mLrcView.setLabel("歌词未收录");
                        } else if (playListTitleResponse.isNolyric()) {
                            mLrcView.setLabel("纯音乐，请欣赏");
                        } else {
                            if (playListTitleResponse.getLrc().getLyric() != null &&
                                    playListTitleResponse.getTlyric().getLyric() == null) {
                                mLrcView.loadLrc(playListTitleResponse.getLrc().getLyric());
                            } else if (playListTitleResponse.getLrc().getLyric() != null &&
                                    playListTitleResponse.getTlyric().getLyric() != null) {
                                mLrcView.loadLrc(playListTitleResponse.getLrc().getLyric() +
                                        playListTitleResponse.getTlyric().getLyric());
                            }else {
                            }
                        }
                        hasLyric = true;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Common.showLog("onError");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public boolean isHasLyric() {
        return hasLyric;
    }

    public void setHasLyric(boolean hasLyric) {
        this.hasLyric = hasLyric;
    }
}
