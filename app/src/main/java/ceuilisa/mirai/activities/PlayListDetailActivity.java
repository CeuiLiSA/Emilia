package ceuilisa.mirai.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.othershe.library.NiceImageView;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.AlbumResponse;
import ceuilisa.mirai.response.PlayListDetailResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.DensityUtil;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;


public class PlayListDetailActivity extends WithPanelActivity {

    private String id, coverImg, name, author, dataType;
    private Toolbar mToolbar;
    private TextView mTextView, mTextView2;
    private RecyclerView mRecyclerView;
    private ImageView mImageView;
    private NiceImageView mImageView2;
    private CircleImageView mCircleImageView;

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
        mCircleImageView = findViewById(R.id.circleImageView);
        mRecyclerView = findViewById(R.id.recyclerView);
        mTextView = findViewById(R.id.textView10);
        mTextView2 = findViewById(R.id.textView9);
        loadProgress.setVisibility(View.VISIBLE);
        mTextView = findViewById(R.id.textView10);
        mTextView2 = findViewById(R.id.textView9);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    void initData() {
        id = getIntent().getStringExtra("id");
        coverImg = getIntent().getStringExtra("coverImg");
        name = getIntent().getStringExtra("name");
        author = getIntent().getStringExtra("author");
        dataType = getIntent().getStringExtra("dataType");
        Glide.with(mContext).load(coverImg).bitmapTransform(
                new BlurTransformation(mContext, 20, 2)).into(mImageView);
        Glide.with(mContext).load(coverImg).into(mImageView2);
        mTextView.setText(name);
        mTextView2.setText(author);
        mToolbar.setTitle(dataType);
        if(dataType.equals("歌单")){
            getPlaylist();
        }else if(dataType.equals("专辑")){
            getAlbum();
        }
    }

    private void getPlaylist(){
        RetrofitUtil.getImjadApi().getPlayListDetail(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayListDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(PlayListDetailResponse playListTitleResponse) {
                        if(playListTitleResponse.getPlaylist().getTracks() != null &&
                                playListTitleResponse.getPlaylist().getTracks().size() > 0) {
                            PlayListDetailAdapter adapter = new PlayListDetailAdapter(
                                    playListTitleResponse.getPlaylist().getTracks(), mContext);
                            adapter.setOnItemClickListener((view, position, viewType) -> {
                                MusicService.allSongs = playListTitleResponse.getPlaylist().getTracks();
                                Intent intent = new Intent(mContext, MusicActivity.class);
                                intent.putExtra("index", position);
                                startActivity(intent);
                            });
                            Glide.with(mContext).load(playListTitleResponse.getPlaylist().getCreator().
                                    getAvatarUrl()).into(mCircleImageView);
                            loadProgress.setVisibility(View.GONE);
                            mRecyclerView.setAdapter(new ScaleInAnimationAdapter(adapter));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void getAlbum(){
        RetrofitUtil.getImjadApi().getAlbum(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AlbumResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(AlbumResponse playListTitleResponse) {
                        PlayListDetailAdapter adapter = new PlayListDetailAdapter(
                                playListTitleResponse.getSongs(), mContext);
                        adapter.setOnItemClickListener((view, position, viewType) -> {
                            MusicService.allSongs = playListTitleResponse.getSongs();
                            Intent intent = new Intent(mContext, MusicActivity.class);
                            intent.putExtra("index", position);
                            startActivity(intent);
                        });
                        loadProgress.setVisibility(View.GONE);
                        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(adapter));
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
}
