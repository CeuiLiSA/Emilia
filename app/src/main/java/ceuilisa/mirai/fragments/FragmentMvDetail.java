package ceuilisa.mirai.fragments;

import android.view.View;
import android.widget.TextView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.VideoPlayActivity;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.MvBean;
import ceuilisa.mirai.response.MvDetail;
import ceuilisa.mirai.utils.Common;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentMvDetail extends BaseFragment {

    private long mvID;
    private TextView name, playCount, duration, desc, artistInfo;

    public static FragmentMvDetail newInstance(long mvID) {
        FragmentMvDetail fragmentMvDetail = new FragmentMvDetail();
        fragmentMvDetail.mvID = mvID;
        return fragmentMvDetail;
    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_mv_detail;
    }

    @Override
    View initView(View v) {
        name = v.findViewById(R.id.name);
        playCount = v.findViewById(R.id.play_count);
        duration = v.findViewById(R.id.time);
        desc = v.findViewById(R.id.brief_desc);
        artistInfo = v.findViewById(R.id.artist_info);
        return v;
    }

    @Override
    void initData() {
        if(((VideoPlayActivity)getActivity()).dataType.equals("mv")){
            getMvDetail();
        }else if(((VideoPlayActivity)getActivity()).dataType.equals("video")){
            getVideoDetail();
        }
    }

    private void setData(MvBean mvBean) {
        name.setText(mvBean.getName());
        playCount.setText("播放：" + mvBean.getPlayCount());
        duration.setText("发布：" + mvBean.getPublishTime());
        desc.setText(mvBean.getBriefDesc() + "  " + mvBean.getDesc());
        artistInfo.setText(mvBean.getArtistName());
    }

    private void getMvDetail() {
        Retro.getNodeApi().getMvDetail(mvID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MvDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MvDetail mvDetail) {
                        if (mvDetail != null) {
                            setData(mvDetail.getData());
                        } else {
                            Common.showToast("加载失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Common.showToast(e.toString());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    private void getVideoDetail() {
        Retro.getNodeApi().getVideoDetail(mvID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MvDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MvDetail mvDetail) {
                        if (mvDetail != null) {
                            setData(mvDetail.getData());
                        } else {
                            Common.showToast("加载失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Common.showToast(e.toString());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
