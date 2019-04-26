package ceuilisa.mirai.network;

import org.greenrobot.eventbus.EventBus;

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
}
