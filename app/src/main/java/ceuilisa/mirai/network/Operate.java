package ceuilisa.mirai.network;

import org.greenrobot.eventbus.EventBus;

import ceuilisa.mirai.fragments.FragmentMyPlayList;
import ceuilisa.mirai.interf.OnPrepare;
import ceuilisa.mirai.interf.OnPrepared;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.response.BaseResponse;
import ceuilisa.mirai.response.LikeSongResponse;
import ceuilisa.mirai.utils.Channel;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Local;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Operate {

    /**
     * 收藏一首歌，到我喜欢的音乐
     *
     * @param id
     * @param isLike
     */
    public static void likeSong(int id, boolean isLike){
        RetrofitUtil.getNodeApi().likeSong(id, isLike)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Listen<LikeSongResponse>() {
                    @Override
                    void success(LikeSongResponse baseResponse) {
                        if(baseResponse.getCode() == 200){
                            Common.showToast(isLike ? "收藏成功" : "取消收藏");
                            Channel channel = new Channel();
                            LoginResponse user = Local.getUser();
                            channel.setReceiver(user.getProfile().getNickname() + "喜欢的音乐");
                            EventBus.getDefault().post(channel);
                        }
                    }

                    @Override
                    void error() {

                    }
                });
    }


    /**
     * 收藏一个歌单
     * @param id
     * @param isLike
     */
    public static void starPlaylist(long id, boolean isLike){
        RetrofitUtil.getNodeApi().starPlaylist(isLike ? "1" : "2", id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse != null){
                            if(baseResponse.getCode() == 200){
                                Common.showToast(isLike ? "收藏成功" : "取消收藏");
                                Channel channel = new Channel();
                                channel.setReceiver("FragmentMyPlayList");
                                EventBus.getDefault().post(channel);
                            }
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
