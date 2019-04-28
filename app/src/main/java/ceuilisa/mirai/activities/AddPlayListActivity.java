package ceuilisa.mirai.activities;

import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.rengwuxian.materialedittext.MaterialEditText;

import org.greenrobot.eventbus.EventBus;

import ceuilisa.mirai.R;
import ceuilisa.mirai.network.Listen;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.response.BackResponse;
import ceuilisa.mirai.response.BaseResponse;
import ceuilisa.mirai.response.UserBean;
import ceuilisa.mirai.utils.Channel;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Constant;
import ceuilisa.mirai.utils.Local;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddPlayListActivity extends BaseActivity{

    private ProgressBar mProgressBar;
    private MaterialEditText playlistName, playlistInfo;
    private CardView submit;

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_add_playlist;
    }

    @Override
    void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        mProgressBar = findViewById(R.id.progress);
        playlistName = findViewById(R.id.user_name);
        playlistInfo = findViewById(R.id.password);
        submit = findViewById(R.id.login);
        submit.setOnClickListener(v -> {
            if(playlistName.getText().toString().trim().length() == 0){
                Common.showToast("请输入歌单名称");
            }else {
                addPlaylist();
            }
        });
    }

    @Override
    void initData() {

    }

    private void addPlaylist(){
        mProgressBar.setVisibility(View.VISIBLE);
        RetrofitUtil.getNodeApi().createPlaylist(playlistName.getText().toString().trim())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse != null){

                        }
                        mProgressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Common.showToast(e.toString());
                        mProgressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onComplete() {
                        Channel channel = new Channel();
                        channel.setReceiver("FragmentMyPlayList");
                        EventBus.getDefault().post(channel);
                    }
                });
    }
}
