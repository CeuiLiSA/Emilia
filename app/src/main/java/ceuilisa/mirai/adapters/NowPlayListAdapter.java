package ceuilisa.mirai.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.othershe.library.NiceImageView;

import java.util.List;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.response.SearchAlbumResponse;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Common;


public class NowPlayListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private List<TracksBean> allIllust;
    private int nowPlayIndex;

    public NowPlayListAdapter(List<TracksBean> list, Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        allIllust = list;
        nowPlayIndex = MusicService.getInstance().getNowPlayIndex();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recy_now_play, parent, false);
        return new TagHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TagHolder) holder).mTextView.setText(allIllust.get(position).getName());
        if(position == nowPlayIndex){
            ((TagHolder) holder).mTextView.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            ((TagHolder) holder).mImageView.setVisibility(View.VISIBLE);
        }else {
            ((TagHolder) holder).mImageView.setVisibility(View.GONE);
        }
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v ->
                    mOnItemClickListener.onItemClick(((TagHolder) holder).itemView, position, 0));
        }
    }

    @Override
    public int getItemCount() {
        return allIllust.size();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public class TagHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private ImageView mImageView;

        TagHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.song_name);
            mImageView = itemView.findViewById(R.id.is_playing);
        }
    }
}
