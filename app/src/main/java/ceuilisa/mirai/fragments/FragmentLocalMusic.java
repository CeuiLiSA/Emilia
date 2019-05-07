package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.MusicActivity;
import ceuilisa.mirai.adapters.PlayListDetailAdapter;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.network.RetrofitUtil;
import ceuilisa.mirai.nodejs.SongDetailResponse;
import ceuilisa.mirai.response.LocalMusic;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.AppDatabase;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.MusicLoaderCallback;
import ceuilisa.mirai.utils.ScannerMusic;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentLocalMusic extends BaseFragment {

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private Loader<Cursor> loader;

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_local_music;
    }

    @Override
    View initView(View v) {
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(view -> getActivity().finish());
        mProgressBar = v.findViewById(R.id.progress);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRecyclerView = v.findViewById(R.id.recy_list);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        return v;
    }

    @Override
    void initData() {
        List<TracksBean> tracksBeans = new ArrayList<>();
        List<LocalMusic> localTracks = AppDatabase.getAppDatabase(mContext).trackDao().getLocalTracks();
        Gson gson = new Gson();
        for (int i = 0; i < localTracks.size(); i++) {
            tracksBeans.add(gson.fromJson(localTracks.get(i).getTrackJson(), TracksBean.class));
        }
        PlayListDetailAdapter adapter = new PlayListDetailAdapter(tracksBeans, mContext);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int viewType) {
                mChannel.setMusicList(tracksBeans);
                Intent intent = new Intent(mContext, MusicActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapter);
        mProgressBar.setVisibility(View.INVISIBLE);
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
