package ceuilisa.mirai.utils;

import android.content.Context;
import android.content.Intent;

import ceuilisa.mirai.R;
import ceuilisa.mirai.interf.IExecutor;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.response.SingleSongResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 分享在线歌曲
 */
public abstract class ShareOnlineMusic implements IExecutor<Void> {

    private Context mContext;
    private String mTitle;
    private long mSongId;

    public ShareOnlineMusic(Context context, String title, long songId) {
        mContext = context;
        mTitle = title;
        mSongId = songId;
    }

    @Override
    public void execute() {
        onPrepare();
        share();
    }

    private void share() {
        Retro.getImjadApi().getSingleSong(mSongId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SingleSongResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SingleSongResponse singleSongResponse) {
                        if(singleSongResponse != null){
                            if(singleSongResponse.getData() != null){
                                if(singleSongResponse.getData().get(0).getUrl().length() != 0){
                                    onExecuteSuccess(null);
                                    Intent intent = new Intent(Intent.ACTION_SEND);
                                    intent.setType("text/plain");
                                    intent.putExtra(Intent.EXTRA_TEXT,
                                            mContext.getString(R.string.share_music,
                                            mContext.getString(R.string.app_name),
                                            mTitle, singleSongResponse.getData().get(0).getUrl()));
                                    mContext.startActivity(Intent.createChooser(intent, mContext.getString(R.string.share)));
                                }else {
                                    Common.showToast("暂无歌曲链接");
                                }
                            }else {
                                Common.showToast("暂无版权");
                            }
                        }else {
                            Common.showToast("网络出错");
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
