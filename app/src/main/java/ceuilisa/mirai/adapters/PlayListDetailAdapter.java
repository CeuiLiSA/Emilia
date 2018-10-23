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

import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.response.PlayListDetailResponse;


public class PlayListDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private List<PlayListDetailResponse.PlaylistBean.TracksBean> allIllust;

    public PlayListDetailAdapter(List<PlayListDetailResponse.PlaylistBean.TracksBean> list, Context context) {
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

        if (allIllust.get(position).getAr().size() == 1) {
            ((TagHolder) holder).mTextView3.setText(allIllust.get(position).getAr().get(0).getName());
        } else {
            StringBuilder artist = new StringBuilder();
            for (int i = 0; i < allIllust.get(position).getAr().size(); i++) {
                artist.append(allIllust.get(position).getAr().get(i).getName()).append(" / ");
            }
            ((TagHolder) holder).mTextView3.setText(artist.substring(0, artist.length() - 3));
        }
        if (allIllust.get(position).getAlia().size() != 0) {
            SpannableString spannableString = new SpannableString(String.format("%s (%s)",
                    allIllust.get(position).getName(),
                    allIllust.get(position).getAlia().get(0)));
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")),
                    allIllust.get(position).getName().length(), spannableString.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ((TagHolder) holder).mTextView.setText(spannableString);
        } else {
            ((TagHolder) holder).mTextView.setText(allIllust.get(position).getName());
        }

        ((TagHolder) holder).mTextView2.setText(String.valueOf(position + 1));
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
        private TextView mTextView, mTextView2, mTextView3;

        TagHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.song_name);
            mTextView2 = itemView.findViewById(R.id.number);
            mTextView3 = itemView.findViewById(R.id.song_author);
        }
    }
}
