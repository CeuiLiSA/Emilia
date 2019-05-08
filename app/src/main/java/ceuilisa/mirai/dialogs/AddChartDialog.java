package ceuilisa.mirai.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.adapters.PlayListSimpleAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.ObjListen;
import ceuilisa.mirai.network.Retro;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.nodejs.PlayListResponse;
import ceuilisa.mirai.nodejs.PlaylistBean;
import ceuilisa.mirai.response.BaseResponse;
import ceuilisa.mirai.utils.Channel;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Local;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static ceuilisa.mirai.fragments.BaseListFragment.PAGE_SIZE;

/**
 * 收藏歌曲到歌单
 */
public class AddChartDialog extends DialogFragment {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private LoginResponse user;
    private long trackID;
    private List<PlaylistBean> allItems = new ArrayList<>();

    public static AddChartDialog newInctance(long trackID) {
        AddChartDialog dialog = new AddChartDialog();
        dialog.trackID = trackID;
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_chart, null);
        mRecyclerView = view.findViewById(R.id.recy_list);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        getPlaylist();
        builder.setView(view);
        return builder.create();
    }

    void getPlaylist() {
        user = Local.getUser();
        Retro.getNodeApi().getMyPlayList(user.getProfile().getUserId(),
                PAGE_SIZE, allItems.size(), System.currentTimeMillis())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PlayListResponse playListResponse) {
                        Common.showLog("PlayListResponse 000000");
                        if (playListResponse != null) {
                            if (playListResponse.getPlaylist() != null &&
                                    playListResponse.getPlaylist().size() != 0) {
                                allItems.clear();
                                allItems.addAll(playListResponse.getPlaylist());
                                PlayListSimpleAdapter adapter = new PlayListSimpleAdapter(allItems, mContext);
                                adapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position, int viewType) {
                                        addSongToPlaylist(allItems.get(position).getId(), trackID);
                                    }
                                });
                                mRecyclerView.setAdapter(adapter);
                                Common.showLog("PlayListResponse 111111");
                            } else {
                                Common.showToast("暂无歌单");
                                Common.showLog("PlayListResponse 222222");
                            }
                        } else {
                            Common.showLog("PlayListResponse 333333");
                            Common.showToast("加载失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Common.showLog("PlayListResponse 444444");
                        e.printStackTrace();
                        Common.showToast(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void addSongToPlaylist(long pid, long trackID) {
        Retro.getNodeApi().addChart(pid, trackID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObjListen<BaseResponse>() {
                    @Override
                    public void success(BaseResponse baseResponse) {
                        Channel channel = new Channel();
                        channel.setReceiver("FragmentMyPlayList");
                        EventBus.getDefault().post(channel);
                        dismiss();
                        Common.showToast("收藏成功");
                    }

                    @Override
                    public void error() {

                    }
                });
    }
}
