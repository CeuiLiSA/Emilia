package ceuilisa.mirai.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.interf.OnItemClickListener;

/**
 * 歌单类型
 */
public class PlayListTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private String[] allIllust;

    public PlayListTypeAdapter(String[] list, Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        allIllust = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recy_playlist_type, parent, false);
        return new TagHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TagHolder) holder).mTextView.setText(String.valueOf(allIllust[position]));
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v ->
                    mOnItemClickListener.onItemClick(holder.itemView, position, 0));
        }
    }

    @Override
    public int getItemCount() {
        return allIllust.length;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public class TagHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        TagHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.type);
        }
    }
}
