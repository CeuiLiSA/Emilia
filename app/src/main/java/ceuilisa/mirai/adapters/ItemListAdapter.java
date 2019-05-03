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

import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.interf.FullClickListener;
import ceuilisa.mirai.response.ItemResponse;


public class ItemListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private FullClickListener mOnItemClickListener;
    private List<ItemResponse> allIllust;

    public ItemListAdapter(List<ItemResponse> list, Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        allIllust = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recy_item_list, parent, false);
        return new TagHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TagHolder) holder).mTextView.setText(allIllust.get(position).getName().substring(34));
        Glide.with(mContext).load(allIllust.get(position).getName()).into(((TagHolder) holder).mImageView);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v ->
                    mOnItemClickListener.onItemClick(((TagHolder) holder).itemView, position, 0));

            holder.itemView.setOnLongClickListener(v -> {
                mOnItemClickListener.onItemLongClick(((TagHolder) holder).itemView, position, 0);
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return allIllust.size();
    }

    public void setOnItemClickListener(FullClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public class TagHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private ImageView mImageView;

        TagHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.title);
            mImageView = itemView.findViewById(R.id.image);
        }
    }
}
