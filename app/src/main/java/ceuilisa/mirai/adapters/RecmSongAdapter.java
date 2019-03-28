package ceuilisa.mirai.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.nodejs.RecommendSong;
import ceuilisa.mirai.response.TracksBean;


public class RecmSongAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private List<RecommendSong> allIllust;

    public RecmSongAdapter(List<RecommendSong> list, Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        allIllust = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recy_recm_song, parent, false);
        return new TagHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //展示歌手名
        if (allIllust.get(position).getArtists().size() == 1) {
            ((TagHolder) holder).mTextView3.setText(String.format("%s - %s",
                    allIllust.get(position).getArtists().get(0).getName(),
                    allIllust.get(position).getAlbum().getName()));
        } else {
            StringBuilder artist = new StringBuilder();
            for (int i = 0; i < allIllust.get(position).getArtists().size(); i++) {
                artist.append(allIllust.get(position).getArtists().get(i).getName()).append(" / ");
            }
            ((TagHolder) holder).mTextView3.setText(String.format("%s - %s",
                    artist.substring(0, artist.length() - 3),
                    allIllust.get(position).getAlbum().getName()));
        }
        //展示歌曲Alia
        if (allIllust.get(position).getAlias() != null && allIllust.get(position).getAlias().size() != 0) {
            SpannableString spannableString = new SpannableString(String.format("%s (%s)",
                    allIllust.get(position).getName(),
                    allIllust.get(position).getAlias().get(0)));
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")),
                    allIllust.get(position).getName().length(), spannableString.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ((TagHolder) holder).mTextView.setText(spannableString);
        } else {
            ((TagHolder) holder).mTextView.setText(allIllust.get(position).getName());
        }
        //歌曲位置标号
        Glide.with(mContext).load(allIllust.get(position).getAlbum().getPicUrl()).into(((TagHolder) holder).mImageView);

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
        private TextView mTextView, mTextView3;
        private ImageView mImageView;

        TagHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.song_name);
            mImageView = itemView.findViewById(R.id.number);
            mTextView3 = itemView.findViewById(R.id.song_author);
        }
    }
}
