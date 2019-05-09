package ceuilisa.mirai.network;

import org.greenrobot.eventbus.EventBus;

import ceuilisa.mirai.activities.Emilia;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.response.BaseResponse;
import ceuilisa.mirai.response.LikeSongResponse;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Channel;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Local;
import ceuilisa.mirai.utils.ShareOnlineMusic;
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
    public static void likeSong(long id, boolean isLike) {
        Retro.getNodeApi().likeSong(id, isLike)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Listen<LikeSongResponse>() {
                    @Override
                    void success(LikeSongResponse baseResponse) {
                        if (baseResponse.getCode() == 200) {
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

    public static void likeArtist(long id, boolean isLike) {
        Retro.getNodeApi().likeArtist(id, isLike ? "1" : "2")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObjListen<BaseResponse>(){

                    @Override
                    public void success(BaseResponse baseResponse) {
                        if(isLike){
                            Common.showToast("收藏成功");
                        }else {
                            Common.showToast("取消收藏");
                        }
                    }

                    @Override
                    public void error() {
                        Common.showToast("操作失败");
                    }
                });
    }

    public static void likeAlbum(long id, boolean isLike) {
        Retro.getNodeApi().likeAlbum(id, isLike ? "1" : "2")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObjListen<BaseResponse>(){

                    @Override
                    public void success(BaseResponse baseResponse) {
                        if(isLike){
                            Common.showToast("收藏成功");
                        }else {
                            Common.showToast("取消收藏");
                        }
                    }

                    @Override
                    public void error() {
                        Common.showToast("操作失败");
                    }
                });
    }


    /**
     * 收藏一个歌单
     *
     * @param id
     * @param isLike
     */
    public static void starPlaylist(long id, boolean isLike) {
        Retro.getNodeApi().starPlaylist(isLike ? "1" : "2", id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (baseResponse != null) {
                            if (baseResponse.getCode() == 200) {
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


    /**
     * 关注一个用户
     *
     * @param id
     * @param isLike
     */
    public static void starUser(long id, boolean isLike) {
        Retro.getNodeApi().starUser(isLike ? "1" : "2", id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (baseResponse != null) {
                            if (baseResponse.getCode() == 200) {
                                Common.showToast(isLike ? "关注成功" : "取消关注");

                                //通知关注列表页面刷新
                                Channel channel = new Channel();
                                channel.setReceiver("FragmentFollow");
                                EventBus.getDefault().post(channel);

                                //通知粉丝列表页面刷新
                                Channel channel2 = new Channel();
                                channel2.setReceiver("FragmentFollowers");
                                EventBus.getDefault().post(channel2);
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


    /**
     * 听歌打卡，如果在一个歌单里听了一首歌，调用这个接口之后，这个歌单的这首歌播放次数加1
     *
     * @param id
     */
    public static void scrobble(long id, long sourceID) {
        Retro.getNodeApi().scrobble(id, sourceID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (baseResponse != null) {
                            if (baseResponse.getCode() == 200) {
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

    public static void share(TracksBean tracksBean) {
        new ShareOnlineMusic(Emilia.getContext(), tracksBean.getName(), tracksBean.getId()) {
            @Override
            public void onPrepare() {

            }

            @Override
            public void onExecuteSuccess(Void aVoid) {

            }

            @Override
            public void onExecuteFail(Exception e) {

            }
        }.execute();
    }
}
