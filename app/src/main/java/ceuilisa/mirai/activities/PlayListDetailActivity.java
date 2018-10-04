package ceuilisa.mirai.activities;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.othershe.library.NiceImageView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.response.PlayListDetailResponse;
import ceuilisa.mirai.utils.Common;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class PlayListDetailActivity extends BaseActivity {

    private int scrollDy;
    private String id, coverImg, name;
    private LinearLayout mLinearLayout;
    //private TextView mTextView, mTextView2;
    private RecyclerView mRecyclerView;
   // private ImageView mImageView;
    //private NiceImageView mImageView2;
    //private CircleImageView mCircleImageView;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        /*mImageView = findViewById(R.id.playlist_photo);
        mImageView2 = findViewById(R.id.imageView4);
        mCircleImageView = findViewById(R.id.circleImageView);*/
        mRecyclerView = findViewById(R.id.recyclerView);
        mLinearLayout = findViewById(R.id.header);
        /*mTextView = findViewById(R.id.textView10);
        mTextView2 = findViewById(R.id.textView9);*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollDy += dy;
                //mLinearLayout.setPadding(0, scrollDy, 0, 0);
                mLinearLayout.setPadding(scrollDy, -scrollDy, 0, 0);
                Common.showLog(scrollDy);
            }
        });
    }

    @Override
    void initData() {
        id = getIntent().getStringExtra("id");
        coverImg = getIntent().getStringExtra("coverImg");
        name = getIntent().getStringExtra("name");
        /*Glide.with(mContext).load(coverImg).apply(bitmapTransform(
                new BlurTransformation(25, 5))).into(mImageView);
        Glide.with(mContext).load(coverImg).into(mImageView2);
        mTextView.setText(name);*/
        RetrofitUtil.getAppApi().getPlayListDetail(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayListDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(PlayListDetailResponse playListTitleResponse) {
                        PlayListDetailAdapter adapter = new PlayListDetailAdapter(
                                playListTitleResponse.getPlaylist().getTracks(), mContext);
                        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(adapter));
                       /* mTextView2.setText(playListTitleResponse.getPlaylist().getCreator().getNickname());
                        Glide.with(mContext).load(playListTitleResponse.getPlaylist().getCreator().
                                getAvatarUrl()).into(mCircleImageView);*/
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
