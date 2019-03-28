package ceuilisa.mirai.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.othershe.library.NiceImageView;

import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.nodejs.PlaylistBean;
import ceuilisa.mirai.nodejs.RecommendPlaylistBean;

/**
 * 推荐歌单
 */
public class RecmPlayListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private List<RecommendPlaylistBean> allIllust;

    public RecmPlayListAdapter(List<RecommendPlaylistBean> list, Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        allIllust = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recy_play_list, parent, false);
        return new TagHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TagHolder) holder).mTextView.setText(allIllust.get(position).getName());
        ((TagHolder) holder).mTextView2.setText(String.format("共%s首歌曲，播放%s次",
                allIllust.get(position).getTrackCount(), allIllust.get(position).getPlaycount()));
        if(allIllust.get(position).getPicUrl() == null || allIllust.get(position).getPicUrl().length() == 0){
            Glide.with(mContext).load(R.mipmap.default_playlist_cover).into(((TagHolder) holder).mNiceImageView);
        }else {
            Glide.with(mContext).load(allIllust.get(position).getPicUrl()).into(((TagHolder) holder).mNiceImageView);
        }
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v ->
                    mOnItemClickListener.onItemClick(((TagHolder) holder).mNiceImageView, position, 0));
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
        private NiceImageView mNiceImageView;

        TagHolder(View itemView) {
            super(itemView);

            mNiceImageView = itemView.findViewById(R.id.playlist_photo);
            mTextView = itemView.findViewById(R.id.song_name);
            mTextView2 = itemView.findViewById(R.id.song_author);
        }
    }
}
