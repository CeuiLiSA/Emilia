package ceuilisa.mirai.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.othershe.library.NiceImageView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.PlayListDetailResponse;
import ceuilisa.mirai.utils.Reference;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;


public class PlayListDetailActivity extends BaseActivity {

    private String id, coverImg, name;
    private TextView mTextView, mTextView2;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private ImageView mImageView;
    private NiceImageView mImageView2;
    private CircleImageView mCircleImageView;

    @Override
    void initLayout() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        mLayoutID = R.layout.activity_play_list_detail_rela;
    }

    @Override
    void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        mImageView = findViewById(R.id.playlist_photo);
        mImageView2 = findViewById(R.id.imageView4);
        mCircleImageView = findViewById(R.id.circleImageView);
        mRecyclerView = findViewById(R.id.recyclerView);
        mTextView = findViewById(R.id.textView10);
        mTextView2 = findViewById(R.id.textView9);
        mProgressBar = findViewById(R.id.progress);
        mProgressBar.setVisibility(View.VISIBLE);
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
        Glide.with(mContext).load(coverImg).bitmapTransform(
                new BlurTransformation(mContext, 20, 2)).into(mImageView);
        Glide.with(mContext).load(coverImg).into(mImageView2);
        mTextView.setText(name);
        RetrofitUtil.getTengkoaApi().getPlayListDetail(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayListDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(PlayListDetailResponse playListTitleResponse) {
                        Reference.allSongs = playListTitleResponse.getPlaylist().getTracks();
                        PlayListDetailAdapter adapter = new PlayListDetailAdapter(
                                playListTitleResponse.getPlaylist().getTracks(), mContext);
                        adapter.setOnItemClickListener((view, position, viewType) -> {
                            Intent intent = new Intent(mContext, MusicActivity.class);
                            intent.putExtra("index", position);
                            startActivity(intent);
                        });
                        mTextView2.setText(playListTitleResponse.getPlaylist().getCreator().getNickname());
                        Glide.with(mContext).load(playListTitleResponse.getPlaylist().getCreator().
                                getAvatarUrl()).into(mCircleImageView);
                        mProgressBar.setVisibility(View.GONE);
                        mProgressBar = null;
                        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(adapter));
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
