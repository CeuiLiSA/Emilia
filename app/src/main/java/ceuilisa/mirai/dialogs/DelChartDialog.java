package ceuilisa.mirai.dialogs;

import android.view.View;

import org.greenrobot.eventbus.EventBus;

import ceuilisa.mirai.network.ObjListen;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.BaseResponse;
import ceuilisa.mirai.utils.Channel;
import ceuilisa.mirai.utils.Common;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DelChartDialog extends BaseDialog{

    private View.OnClickListener positiveClick;
    private long pid, trackId;
    private int index;

    @Override
    void initLayout() {

    }

    public static DelChartDialog newInstance(long pid, long trackId, int index){
        DelChartDialog dialog = new DelChartDialog();
        dialog.pid = pid;
        dialog.trackId = trackId;
        dialog.index = index;
        return dialog;
    }

    @Override
    void initView(View v) {
        title.setText("提示：");
        content.setText("确定从歌单中删除这首歌？");
        if(positiveClick != null) {
            sure.setOnClickListener(positiveClick);
        }
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delSongFromPlaylist(pid, trackId);
            }
        });
    }

    public void setPositiveClick(View.OnClickListener onClickListener){
        positiveClick = onClickListener;
    }


    private void delSongFromPlaylist(long pid, long trackID){
        RetrofitUtil.getNodeApi().delFromChart(pid, trackID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObjListen<BaseResponse>(){
                    @Override
                    public void success(BaseResponse baseResponse) {
                        Channel<Integer> integerChannel = new Channel<>();
                        integerChannel.setObject(index);
                        integerChannel.setReceiver("PlayListDetailActivity remove item");
                        EventBus.getDefault().post(integerChannel);
                        dismiss();
                        Common.showToast("删除成功");
                    }

                    @Override
                    public void error() {
                        Common.showToast("操作失败");
                    }
                });
    }
}
