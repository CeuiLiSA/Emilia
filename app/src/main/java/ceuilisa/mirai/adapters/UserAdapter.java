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
import com.othershe.library.NiceImageView;

import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.nodejs.ArtistBean;
import ceuilisa.mirai.nodejs.SearchUserResponse;
import ceuilisa.mirai.nodejs.UserprofilesBean;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 搜索用户列表
 */
public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private List<UserprofilesBean> allIllust;

    public UserAdapter(List<UserprofilesBean> list, Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        allIllust = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recy_search_user, parent, false);
        return new TagHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TagHolder) holder).mTextView.setText(allIllust.get(position).getNickname());
        ((TagHolder) holder).mTextView2.setText(allIllust.get(position).getSignature());
        Glide.with(mContext).load(allIllust.get(position).getBackgroundUrl()).into(((TagHolder) holder).mImageView);
        Glide.with(mContext).load(allIllust.get(position).getAvatarUrl()).into(((TagHolder) holder).mNiceImageView);
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
        private CircleImageView mNiceImageView;
        private ImageView mImageView;

        TagHolder(View itemView) {
            super(itemView);

            mNiceImageView = itemView.findViewById(R.id.playlist_photo);
            mTextView = itemView.findViewById(R.id.artist_name);
            mTextView2 = itemView.findViewById(R.id.info);
            mImageView = itemView.findViewById(R.id.back);
        }
    }
}
