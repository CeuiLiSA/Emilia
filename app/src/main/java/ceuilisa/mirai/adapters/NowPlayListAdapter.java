package ceuilisa.mirai.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.response.TracksBean;


public class NowPlayListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private List<TracksBean> allIllust;

    public NowPlayListAdapter(List<TracksBean> list, Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        allIllust = list;
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
        ((TagHolder) holder).mTextView2.setText(" - " + allIllust.get(position).getFullArtistName());
        if(position == MusicService.get().getNowPlayIndex()){
            ((TagHolder) holder).mTextView.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            ((TagHolder) holder).mTextView2.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            ((TagHolder) holder).mImageView.setVisibility(View.VISIBLE);
        }else {
            ((TagHolder) holder).mTextView.setTextColor(mContext.getResources().getColor(R.color.dark_text));
            ((TagHolder) holder).mTextView2.setTextColor(mContext.getResources().getColor(R.color.defaut_text));
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
        private TextView mTextView, mTextView2;
        private ImageView mImageView;

        TagHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.song_name);
            mTextView2 = itemView.findViewById(R.id.artist_info);
            mImageView = itemView.findViewById(R.id.is_playing);
        }
    }
}
