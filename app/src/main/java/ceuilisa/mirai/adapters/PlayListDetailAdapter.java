package ceuilisa.mirai.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Common;

/**
 * 歌曲列表
 */
public class PlayListDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private List<TracksBean> allIllust;

    public PlayListDetailAdapter(List<TracksBean> list, Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        allIllust = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recy_play_list_detail, parent, false);
        return new TagHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //展示歌手名
        if(allIllust.get(position).getAr() != null) {
            if (allIllust.get(position).getAr().size() == 1) {
                ((TagHolder) holder).mTextView3.setText(String.format("%s - %s",
                        allIllust.get(position).getAr().get(0).getName(),
                        allIllust.get(position).getAl().getName()));
            } else {
                StringBuilder artist = new StringBuilder();
                for (int i = 0; i < allIllust.get(position).getAr().size(); i++) {
                    artist.append(allIllust.get(position).getAr().get(i).getName()).append(" / ");
                }
                ((TagHolder) holder).mTextView3.setText(String.format("%s - %s",
                        artist.substring(0, artist.length() - 3),
                        allIllust.get(position).getAl().getName()));
            }
        }else {
            if (!TextUtils.isEmpty(allIllust.get(position).getArtistName())) {
                ((TagHolder) holder).mTextView3.setText(String.format("%s - %s",
                        allIllust.get(position).getArtistName(),
                        allIllust.get(position).getAlbumName()));
            }else {
                ((TagHolder) holder).mTextView3.setText(String.format("%s - %s",
                        "未知艺术家",
                        allIllust.get(position).getAlbumName()));
            }
        }
        //展示歌曲Alia
        if (allIllust.get(position).getAlia() != null && allIllust.get(position).getAlia().size() != 0) {
            SpannableString spannableString = new SpannableString(String.format("%s (%s)",
                    allIllust.get(position).getName(),
                    allIllust.get(position).getAlia().get(0)));
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")),
                    allIllust.get(position).getName().length(), spannableString.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ((TagHolder) holder).mTextView.setText(spannableString);
        } else {
            //若歌曲Alia为空，查找歌曲tns
            ((TagHolder) holder).mTextView.setText(allIllust.get(position).getName());
        }
        //歌曲位置标号
        ((TagHolder) holder).mTextView2.setText(String.valueOf(position + 1));

        if (allIllust.get(position).getMv() != 0) {
            ((TagHolder) holder).mView.setVisibility(View.VISIBLE);
        } else {
            ((TagHolder) holder).mView.setVisibility(View.GONE);
        }


        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v ->
                    mOnItemClickListener.onItemClick(((TagHolder) holder).itemView, position, 0));
            ((TagHolder) holder).mView.setOnClickListener(v ->
                    mOnItemClickListener.onItemClick(((TagHolder) holder).itemView, position, 1));
            ((TagHolder) holder).more.setOnClickListener(v ->
                    mOnItemClickListener.onItemClick(((TagHolder) holder).itemView, position, 2));
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
        private TextView mTextView, mTextView2, mTextView3;
        private ImageView mView, more;

        TagHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.song_name);
            mTextView2 = itemView.findViewById(R.id.number);
            mTextView3 = itemView.findViewById(R.id.song_author);
            mView = itemView.findViewById(R.id.show_video);
            more = itemView.findViewById(R.id.more);
        }
    }
}
