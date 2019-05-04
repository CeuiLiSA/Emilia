package ceuilisa.mirai.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gcssloop.widget.RCRelativeLayout;
import com.othershe.library.NiceImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.dialogs.LikeSongDialog;
import ceuilisa.mirai.network.Operate;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.AlbumResponse;
import ceuilisa.mirai.response.PlayListDetailResponse;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Channel;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Local;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;


public class PlayListDetailActivity extends WithPanelActivity {

    private String coverImg, name, author, dataType;
    private Toolbar mToolbar;
    private long id;
    private TextView mTextView, mTextView2, starPlaylistTv, markPlaylist;
    private RCRelativeLayout cornerRela;
    private RecyclerView mRecyclerView;
    private ImageView mImageView, starPlaylist;
    private NiceImageView mImageView2;
    private CircleImageView mCircleImageView;
    private boolean showProgress = true;
    private PlayListDetailAdapter mAdapter;
    private List<TracksBean> allDatas = new ArrayList<>();

    @Override
    boolean hasImage() {
        return false;
    }

    @Override
    boolean hasProgress() {
        return true;
    }

    @Override
    void initLayout() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        mLayoutID = R.layout.activity_play_list_detail_rela;
    }

    @Override
    void initView() {
        super.initView();
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        mImageView = findViewById(R.id.playlist_photo);
        mImageView2 = findViewById(R.id.imageView4);
        starPlaylistTv = findViewById(R.id.textView8);
        mCircleImageView = findViewById(R.id.circleImageView);
        mRecyclerView = findViewById(R.id.recyclerView);
        mTextView = findViewById(R.id.textView10);
        mTextView2 = findViewById(R.id.textView9);
        markPlaylist = findViewById(R.id.mark_playlist);
        cornerRela = findViewById(R.id.corner_rela);
        cornerRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allDatas.size() != 0) {
                    mChannel.setMusicList(allDatas);
                    MusicService.get().setPlaying(true);
                    MusicService.get().playMusic(0, () -> setData());
                }else {
                    Common.showToast("暂无播放列表");
                }
            }
        });
        loadProgress.setVisibility(View.VISIBLE);
        mTextView = findViewById(R.id.textView10);
        starPlaylist = findViewById(R.id.imageView10);
        starPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (starPlaylistTv.getText().toString().equals("取消收藏")) {
                    Operate.starPlaylist(id, false);
                    starPlaylistTv.setText("收藏");
                } else {
                    Operate.starPlaylist(id, true);
                    starPlaylistTv.setText("取消收藏");
                }
            }
        });
        markPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (markPlaylist.getText().toString().equals("取消收藏")) {
                    Operate.starPlaylist(id, false);
                    markPlaylist.setText("+ 添加收藏");
                } else {
                    Operate.starPlaylist(id, true);
                    markPlaylist.setText("取消收藏");
                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    void initData() {
        id = getIntent().getLongExtra("id", 0);
        coverImg = getIntent().getStringExtra("coverImg");
        name = getIntent().getStringExtra("name");
        author = getIntent().getStringExtra("author");
        dataType = getIntent().getStringExtra("dataType");

        if (coverImg != null && coverImg.length() != 0) {
            Glide.with(mContext).load(coverImg).bitmapTransform(
                    new BlurTransformation(mContext, 20, 2)).into(mImageView);
            Glide.with(mContext).load(coverImg).into(mImageView2);
        } else {
            Glide.with(mContext).load(R.mipmap.default_playlist_cover).into(mImageView2);
        }
        mTextView.setText(name);
        mTextView2.setText(author);
        mToolbar.setTitle(dataType);
        if (dataType.equals("歌单")) {
            getPlaylist();
        } else if (dataType.equals("我的收藏")) {
            //getMyFavor();
        } else if (dataType.equals("专辑")) {
            getAlbum();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (showProgress) {
            Common.showLog("必须出现");
            loadProgress.setVisibility(View.VISIBLE);
        } else {
            Common.showLog("必须消失");
            loadProgress.setVisibility(View.INVISIBLE);
        }
    }

    private void getPlaylist() {
        RetrofitUtil.getNodeApi().getPlayListDetail(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayListDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PlayListDetailResponse playListTitleResponse) {
                        if (playListTitleResponse != null &&
                                playListTitleResponse.getPlaylist() != null) {

                            if (playListTitleResponse.getPlaylist().getTracks() != null &&
                                    playListTitleResponse.getPlaylist().getTracks().size() > 0) {

                                allDatas.clear();
                                allDatas.addAll(playListTitleResponse.getPlaylist().getTracks());



                                if (playListTitleResponse.isMyPlaylist()) {
                                    markPlaylist.setVisibility(View.GONE);
                                }else {
                                    markPlaylist.setVisibility(View.VISIBLE);
                                }
                                if (playListTitleResponse.getPlaylist().isSubscribed()) {
                                    markPlaylist.setText("取消收藏");
                                } else {
                                    markPlaylist.setText("+ 添加收藏");
                                }
                                mAdapter = new PlayListDetailAdapter(allDatas, mContext);
                                mAdapter.setOnItemClickListener((view, position, viewType) -> {
                                    if (viewType == 0) {
                                        mChannel.setMusicList(allDatas);
                                        Intent intent = new Intent(mContext, MusicActivity.class);
                                        intent.putExtra("index", position);
                                        startActivity(intent);
                                    } else if (viewType == 1) {
                                        Intent intent = new Intent(mContext, VideoPlayActivity.class);
                                        intent.putExtra("mv id", allDatas.get(position).getMv());
                                        startActivity(intent);
                                    } else if (viewType == 2) {
                                        //如果歌单创建者是自己，则可以删除歌单中的歌曲
                                        if (playListTitleResponse.getPlaylist().getCreator().getUserId() ==
                                                Local.getUser().getProfile().getUserId()) {
                                            //支持删除歌曲的LikeSongDialog
                                            LikeSongDialog dialog = LikeSongDialog.newInstance(
                                                    allDatas.get(position),
                                                    playListTitleResponse.getPlaylist().getId(),
                                                    position);
                                            dialog.show(getSupportFragmentManager());
                                        } else {
                                            //不支持删除歌曲的LikeSongDialog
                                            LikeSongDialog dialog = LikeSongDialog.newInstance(
                                                    allDatas.get(position));
                                            dialog.show(getSupportFragmentManager());
                                        }
                                    }
                                });
                                mCircleImageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(mContext, UserDetailActivity.class);
                                        intent.putExtra("user id",
                                                playListTitleResponse.getPlaylist().getCreator().getUserId());
                                        startActivity(intent);
                                    }
                                });
                                mTextView2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(mContext, UserDetailActivity.class);
                                        intent.putExtra("user id",
                                                playListTitleResponse.getPlaylist().getCreator().getUserId());
                                        startActivity(intent);
                                    }
                                });
                                if (!isDestroyed()) {
                                    Glide.with(mContext).load(playListTitleResponse.getPlaylist().getCreator().
                                            getAvatarUrl()).into(mCircleImageView);
                                }
                                loadProgress.setVisibility(View.GONE);
                                mRecyclerView.setAdapter(new ScaleInAnimationAdapter(mAdapter));
                                showProgress = false;
                            } else {
                                Common.showToast("暂无歌曲");
                                loadProgress.setVisibility(View.GONE);
                                showProgress = false;
                            }
                        } else {
                            loadProgress.setVisibility(View.INVISIBLE);
                            Common.showToast("加载失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Common.showToast("加载失败");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void getAlbum() {
        RetrofitUtil.getImjadApi().getAlbum(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AlbumResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(AlbumResponse playListTitleResponse) {
                        allDatas.clear();
                        allDatas.addAll(playListTitleResponse.getSongs());
                        mAdapter = new PlayListDetailAdapter(allDatas, mContext);
                        mAdapter.setOnItemClickListener((view, position, viewType) -> {
                            if (viewType == 0) {
                                mChannel.setMusicList(allDatas);
                                Intent intent = new Intent(mContext, MusicActivity.class);
                                intent.putExtra("index", position);
                                startActivity(intent);
                            } else if (viewType == 1) {
                                Intent intent = new Intent(mContext, VideoPlayActivity.class);
                                intent.putExtra("mv id", allDatas.get(position).getMv());
                                startActivity(intent);
                            } else if (viewType == 2) {
                                LikeSongDialog dialog = LikeSongDialog.newInstance(
                                        allDatas.get(position));
                                dialog.show(getSupportFragmentManager());
                            }
                        });
                        mTextView2.setText(playListTitleResponse.getAlbum().getArtist().getName());
                        if (!isDestroyed()) {
                            Glide.with(mContext).load(playListTitleResponse.getAlbum()
                                    .getArtist().getPicUrl()).into(mCircleImageView);
                        }
                        loadProgress.setVisibility(View.GONE);
                        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(mAdapter));
                        showProgress = false;
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


//    private void getMyFavor(){
//        UserBean userBean = Local.getUser();
//        RetrofitUtil.getTempApi().getMyFavor(userBean.getUserID())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<PlayListDetailResponse>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(PlayListDetailResponse playListTitleResponse) {
//                        if(playListTitleResponse != null &&
//                                playListTitleResponse.getPlaylist() != null &&
//                                playListTitleResponse.getPlaylist().getTracks() != null &&
//                                playListTitleResponse.getPlaylist().getTracks().size() > 0) {
//                            PlayListDetailAdapter adapter = new PlayListDetailAdapter(
//                                    playListTitleResponse.getPlaylist().getTracks(), mContext);
//                            adapter.setOnItemClickListener((view, position, viewType) -> {
//                                MusicService.allSongs = playListTitleResponse.getPlaylist().getTracks();
//                                Intent intent = new Intent(mContext, MusicActivity.class);
//                                intent.putExtra("index", position);
//                                startActivity(intent);
//                            });
//                            if(!isDestroyed()) {
//                                if(playListTitleResponse.getPlaylist().getCreator() != null) {
//                                    Glide.with(mContext).load(playListTitleResponse.getPlaylist().getCreator().
//                                            getAvatarUrl()).into(mCircleImageView);
//                                }
//                            }
//                            loadProgress.setVisibility(View.GONE);
//                            mRecyclerView.setAdapter(new ScaleInAnimationAdapter(adapter));
//                            showProgress = false;
//                        }else {
//                            loadProgress.setVisibility(View.INVISIBLE);
//                            Common.showToast("加载失败");
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                        Common.showToast("加载失败");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Channel<Integer> channel) {


        String receiver = user.getProfile().getNickname() + "喜欢的音乐";

        Common.showLog("PlayListDetailActivity " + receiver);
        if (channel.getReceiver().equals(receiver)) {
            getPlaylist();
            Common.showLog("PlayListDetailActivity 里面的");
        }


        /**
         * 歌单详情页面，删除了一首歌，通知详情列表移除这首歌
         */
        if (channel.getReceiver().equals(getString(R.string.playlist_remove_item))) {
            if (mAdapter != null) {
                allDatas.remove((int) channel.getObject());
                mAdapter.notifyItemRemoved(channel.getObject());
                mAdapter.notifyItemRangeChanged(channel.getObject(), mAdapter.getItemCount());
                Common.showLog("PlayListDetailActivity 删除了一个item");
            }
        }
    }
}
