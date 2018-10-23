package ceuilisa.mirai.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.response.ItemResponse;


public class ItemListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
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
        ((TagHolder) holder).mTextView.setText(String.valueOf(allIllust.get(position).getName()));
        ((TagHolder) holder).mTextView2.setText(String.valueOf(allIllust.get(position).getId()));
        ((TagHolder) holder).mTextView3.setText(String.valueOf(allIllust.get(position).getPassword()));
        ((TagHolder) holder).mTextView4.setText(String.valueOf(allIllust.get(position).getVip()));
        /*if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v ->
                    mOnItemClickListener.onItemClick(((TagHolder) holder).mNiceImageView, position, 0));
        }*/
    }

    @Override
    public int getItemCount() {
        return allIllust.size();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public class TagHolder extends RecyclerView.ViewHolder {
        private TextView mTextView, mTextView2, mTextView3, mTextView4;

        TagHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.title);
            mTextView2 = itemView.findViewById(R.id.title_2);
            mTextView3 = itemView.findViewById(R.id.title_3);
            mTextView4 = itemView.findViewById(R.id.title_4);
        }
    }
}
