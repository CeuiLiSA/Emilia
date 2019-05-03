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
import ceuilisa.mirai.nodejs.ArtistBean;

/**
 * 搜索歌手列表
 */
public class ArtistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private List<ArtistBean> allIllust;

    public ArtistAdapter(List<ArtistBean> list, Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        allIllust = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recy_search_artist, parent, false);
        return new TagHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String artistName = allIllust.get(position).getName();
        if (allIllust.get(position).getAlias() != null && allIllust.get(position).getAlias().size() != 0) {
            artistName = artistName + " (" + allIllust.get(position).getAlias().get(0) + ")";
        }
        ((TagHolder) holder).mTextView.setText(artistName);
        if (allIllust.get(position).getImg1v1Url() == null || allIllust.get(position).getImg1v1Url().length() == 0) {
            Glide.with(mContext).load(R.mipmap.default_playlist_cover).into(((TagHolder) holder).mNiceImageView);
        } else {
            Glide.with(mContext).load(allIllust.get(position).getImg1v1Url()).into(((TagHolder) holder).mNiceImageView);
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
        private TextView mTextView;
        private NiceImageView mNiceImageView;

        TagHolder(View itemView) {
            super(itemView);

            mNiceImageView = itemView.findViewById(R.id.playlist_photo);
            mTextView = itemView.findViewById(R.id.artist_name);
        }
    }
}
