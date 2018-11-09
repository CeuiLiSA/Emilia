package ceuilisa.mirai.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import ceuilisa.mirai.R;

public abstract class NetWorkControlActivity extends BaseActivity {

    abstract boolean hasImage();

    abstract boolean hasProgress();

    protected ImageView mImageView;
    protected ProgressBar loadProgress;

    @Override
    void initLayout() {
    }

    @Override
    void initView() {
        if (hasImage()) {
            mImageView = findViewById(R.id.load_result);
        }
        if (hasProgress()) {
            loadProgress = findViewById(R.id.progress);
        }
    }

    @Override
    void initData() {

    }

    void loadError() {
        if (hasImage()) {
            mImageView.setVisibility(View.VISIBLE);
            mImageView.setImageResource(R.mipmap.load_error);
        }
        if (hasProgress()) {
            loadProgress.setVisibility(View.INVISIBLE);
        }
    }

    void loadNoData() {
        if (hasImage()) {
            mImageView.setVisibility(View.VISIBLE);
            mImageView.setImageResource(R.mipmap.no_data);
        }
        if (hasProgress()) {
            loadProgress.setVisibility(View.INVISIBLE);
        }
    }

    void hideImage() {
        if (hasImage()) {
            mImageView.setVisibility(View.INVISIBLE);
        }
    }
}
