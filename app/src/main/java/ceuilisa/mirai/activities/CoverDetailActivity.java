package ceuilisa.mirai.activities;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ceuilisa.mirai.R;

public class CoverDetailActivity extends BaseActivity {

    private ImageView mImageView;
    private String coverImage;

    @Override
    void initLayout() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        mLayoutID = R.layout.activity_cover_detail;
    }

    @Override
    void initView() {
        coverImage = getIntent().getStringExtra("cover");
        mImageView = findViewById(R.id.originalImage);
    }

    @Override
    void initData() {
        Glide.with(mContext).load(coverImage).into(mImageView);
    }
}
