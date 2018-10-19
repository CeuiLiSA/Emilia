package ceuilisa.mirai.activities;

import android.view.View;
import android.widget.TextView;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Reference;

public class MusicActivity extends BaseActivity{

    private int nowIndex;
    private TextView mTextView, mTextView2, mTextView3;

    @Override
    void initLayout() {
        mLayoutID = R.layout.activity_music;
    }

    @Override
    void initView() {
        mTextView = findViewById(R.id.last);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nowIndex == 0){
                    Common.showToast(mContext, "这已经是第一首歌了");
                }else {
                    nowIndex = nowIndex - 1;
                    MusicService.getInstance().playMusic(Reference.allSongs.get(nowIndex).getId());
                }
            }
        });
        mTextView2 = findViewById(R.id.stop);
        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicService.getInstance().stopOrPlay();
            }
        });
        mTextView3 = findViewById(R.id.next);
        mTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nowIndex == Reference.allSongs.size()-1){
                    Common.showToast(mContext, "这已经是最后一首歌了");
                }else {
                    nowIndex = nowIndex + 1;
                    MusicService.getInstance().playMusic(Reference.allSongs.get(nowIndex).getId());
                }
            }
        });
    }

    @Override
    void initData() {
        nowIndex = getIntent().getIntExtra("index", 0);
        MusicService.getInstance().playMusic(Reference.allSongs.get(nowIndex).getId());
    }
}
