package ceuilisa.mirai.activities;

import android.support.v7.widget.Toolbar;

import ceuilisa.mirai.R;
import ceuilisa.mirai.fragments.BaseFragment;
import ceuilisa.mirai.fragments.FragmentArtistAlbum;
import ceuilisa.mirai.fragments.FragmentSearch;
import ceuilisa.mirai.fragments.FragmentSearchAlbum;

public class SingleFragmentActivity extends WithPanelActivity {

    private String keyWord;
    private String title;

    @Override
    boolean hasImage() {
        return false;
    }

    @Override
    boolean hasProgress() {
        return false;
    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_single_fragment;
    }



    @Override
    void initView() {
        super.initView();
        title = getIntent().getStringExtra("title");
        keyWord = getIntent().getStringExtra("key");
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        toolbar.setTitle(title);

        FragmentSearchAlbum artistAlbum = new FragmentSearchAlbum();
        artistAlbum.setName(keyWord);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, artistAlbum)
                .show(artistAlbum)
                .commit();
    }
}
