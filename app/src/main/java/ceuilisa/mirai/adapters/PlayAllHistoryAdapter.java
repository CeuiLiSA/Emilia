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
import ceuilisa.mirai.response.HistorySongBean;
import ceuilisa.mirai.response.PlayAllHistoryResponse;


public class PlayAllHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private List<HistorySongBean> allIllust;

    public PlayAllHistoryAdapter(List<HistorySongBean> list, Context context) {
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
        if (allIllust.get(position).getSong().getAr().size() == 1) {
            ((TagHolder) holder).mTextView3.setText(String.format("%s - %s",
                    allIllust.get(position).getSong().getAr().get(0).getName(),
                    allIllust.get(position).getSong().getAl().getName()));
        } else {
            StringBuilder artist = new StringBuilder();
            for (int i = 0; i < allIllust.get(position).getSong().getAr().size(); i++) {
                artist.append(allIllust.get(position).getSong().getAr().get(i).getName()).append(" / ");
            }
            ((TagHolder) holder).mTextView3.setText(String.format("%s - %s",
                    artist.substring(0, artist.length() - 3),
                    allIllust.get(position).getSong().getAl().getName()));
        }

        if (allIllust.get(position).getSong().getAlia().size() != 0) {
            SpannableString spannableString = new SpannableString(String.format("%s (%s)",
                    allIllust.get(position).getSong().getName(),
                    allIllust.get(position).getSong().getAlia().get(0)));
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")),
                    allIllust.get(position).getSong().getName().length(), spannableString.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ((TagHolder) holder).mTextView.setText(spannableString);
        } else {
            ((TagHolder) holder).mTextView.setText(allIllust.get(position).getSong().getName());
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
