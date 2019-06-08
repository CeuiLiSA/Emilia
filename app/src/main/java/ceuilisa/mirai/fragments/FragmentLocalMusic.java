package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.activities.TemplateFragmentActivity;
import ceuilisa.mirai.activities.VideoPlayActivity;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.dialogs.LikeSongDialog;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.response.LocalMusic;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.AppDatabase;
import ceuilisa.mirai.utils.Channel;

public class FragmentLocalMusic extends BaseFragment {

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private List<TracksBean> allItems = new ArrayList<>();

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_local_music;
    }

    @Override
    View initView(View v) {
        mToolbar = v.findViewById(R.id.toolbar);
        ((TemplateFragmentActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(view -> getActivity().finish());
        mProgressBar = v.findViewById(R.id.progress);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRecyclerView = v.findViewById(R.id.recy_list);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        return v;
    }

    @Override
    void initData() {
        allItems = new ArrayList<>();
        List<LocalMusic> localTracks = AppDatabase.getAppDatabase(mContext).trackDao().getLocalTracks();
        Gson gson = new Gson();
        for (int i = 0; i < localTracks.size(); i++) {
            allItems.add(gson.fromJson(localTracks.get(i).getTrackJson(), TracksBean.class));
        }
        PlayListDetailAdapter adapter = new PlayListDetailAdapter(allItems, mContext);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int viewType) {
                mChannel.setMusicList(allItems);
                Intent intent = new Intent(mContext, MusicActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });
        adapter.setOnItemClickListener((view, position, viewType) -> {
            if (viewType == 0) {
                mChannel.setMusicList(allItems);
                Intent intent = new Intent(mContext, MusicActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
            } else if (viewType == 1) {
                Intent intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra("mv id", allItems.get(position).getMv());
                intent.putExtra("dataType", "mv");
                startActivity(intent);
            } else if (viewType == 2) {
                LikeSongDialog dialog = LikeSongDialog.newInstance(
                        allItems.get(position));
                dialog.show(getChildFragmentManager());
            }
        });
        mRecyclerView.setAdapter(adapter);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_local_music, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            FragmentLocalSearch.sTracksBeanList = allItems;
            Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
            intent.putExtra("dataType", "列表搜索");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Channel channel) {
        if (channel.getReceiver().equals(className)) {
            initData();
        }
    }

//    private void getLocalSongDetail(List<TracksBean> tracksBeans){
//        String ids = "";
//        for (int i = 0; i < tracksBeans.size(); i++) {
//            ids = ids + tracksBeans.get(i).getId() + ",";
//        }
//        String result = ids.substring(0, ids.length() - 1);
//        RetrofitUtil.getNodeApi().getSongDetail(result)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<SongDetailResponse>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(SongDetailResponse songDetailResponse) {
//                        if(songDetailResponse != null){
//                            if(songDetailResponse.getList() != null){
//                                PlayListDetailAdapter adapter = new PlayListDetailAdapter(songDetailResponse.getList(), mContext);
//                                adapter.setOnItemClickListener(new OnItemClickListener() {
//                                    @Override
//                                    public void onItemClick(View view, int position, int viewType) {
//                                        mChannel.setMusicList(songDetailResponse.getList());
//                                        Intent intent = new Intent(mContext, MusicActivity.class);
//                                        intent.putExtra("index", position);
//                                        startActivity(intent);
//                                    }
//                                });
//                                mRecyclerView.setAdapter(adapter);
//                            }
//                        }else {
//                            Common.showToast("加载失败");
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                        Common.showToast(e.toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
}
