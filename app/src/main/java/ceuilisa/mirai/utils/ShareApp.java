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
public abstract class ShareApp implements IExecutor<Void> {

    private Context mContext;

    public ShareApp(Context context) {
        mContext = context;
    }

    @Override
    public void execute() {
        onPrepare();
        share();
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,
                mContext.getString(R.string.share_app,
                        "https://github.com/CeuiLiSA/Emilia"));
        mContext.startActivity(Intent.createChooser(intent, mContext.getString(R.string.share)));
    }
}
