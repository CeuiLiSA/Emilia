package ceuilisa.mirai.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener1;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;

import java.io.File;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.interf.OnPrepare;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.DeleteImageResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.FileUtil;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DeleteImageDialog extends DialogFragment{

    private AlertDialog mAlertDialog;
    private OnPrepare mOnPrepare;
    private Context mContext;
    private String name;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mContext = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.dialog_delete_image, null);
        TextView textView = view.findViewById(R.id.song_size);
        textView.setText(String.format("这将会删除图片%s，是否继续？", name));
        view.findViewById(R.id.download_now).setOnClickListener(v -> deleteNow());
        view.findViewById(R.id.cancel).setOnClickListener(v -> mAlertDialog.dismiss());
        builder.setView(view);
        mAlertDialog = builder.create();
        return mAlertDialog;
    }




    public void deleteNow(){
        mAlertDialog.dismiss();
        RetrofitUtil.getTempApi().deleteImage(name)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeleteImageResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeleteImageResponse response) {
                        if(response.getMessage().equals("delete success")){
                            if(mOnPrepare != null){
                                Toasty.success(mContext, "删除成功",
                                        Toast.LENGTH_SHORT, true).show();
                                mOnPrepare.updateUI();
                            }else {
                                Toasty.error(mContext, "This is an error toast.",
                                        Toast.LENGTH_SHORT, true).show();
                            }
                        }else {
                            Toasty.error(mContext, "This is an error toast.",
                                    Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toasty.error(mContext, "This is an error toast.",
                                Toast.LENGTH_SHORT, true).show();
                        Common.showLog("onError");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OnPrepare getOnPrepare() {
        return mOnPrepare;
    }

    public void setOnPrepare(OnPrepare onPrepare) {
        mOnPrepare = onPrepare;
    }
}
