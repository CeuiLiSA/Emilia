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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.othershe.library.NiceImageView;

import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.nodejs.AlbumBean;
import ceuilisa.mirai.utils.Common;


public class AlbumListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private List<AlbumBean> allIllust;

    public AlbumListAdapter(List<AlbumBean> list, Context context) {
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
        if (allIllust.get(position).getAlias() != null && allIllust.get(position).getAlias().size() != 0) {
            SpannableString spannableString = new SpannableString(String.format("%s (%s)",
                    allIllust.get(position).getName(),
                    allIllust.get(position).getAlias().get(0)));
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")),
                    allIllust.get(position).getName().length(), spannableString.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ((TagHolder) holder).mTextView.setText(spannableString);
        } else {
            //若歌曲Alia为空，查找歌曲tns
            ((TagHolder) holder).mTextView.setText(allIllust.get(position).getName());
        }

        ((TagHolder) holder).mTextView2.setText(String.format(mContext.getString(R.string.album_info),
                Common.timeStamp2Date(String.valueOf(allIllust.get(position).getSubTime())),
                allIllust.get(position).getSize()));
        Glide.with(mContext).load(allIllust.get(position).getPicUrl()).into(((TagHolder) holder).mNiceImageView);
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
