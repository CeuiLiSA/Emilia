package ceuilisa.mirai.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.othershe.library.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.UserDetailActivity;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.nodejs.LoginResponse;
import ceuilisa.mirai.nodejs.PlaylistBean;
import ceuilisa.mirai.response.CommentResponse;
import ceuilisa.mirai.utils.Common;
import ceuilisa.mirai.utils.Local;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 区分  创建的歌单，收藏的歌单，列表适配器（PlaylistAdapter 不区分）
 */
public class SeparatePlaylistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HOT_HEAD = 0;
    private static final int TYPE_NORMAL_HEAD = 1;
    private static final int HOT_HEAD_COUNT = 1;
    private static final int NORMAL_HEAD_COUNT = 1;
    private static final int TYPE_HOT_COMMENT = 2;
    private static final int TYPE_NORMAL_COMMENT = 3;

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private List<PlaylistBean> allIllust, partMine, partOthers;

    public SeparatePlaylistAdapter(List<PlaylistBean> list, Context context, int userID) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        allIllust = list;
        partMine = new ArrayList<>();
        partOthers = new ArrayList<>();
        for (int i = 0; i < allIllust.size(); i++) {
            if(allIllust.get(i).getUserId() == userID){
                partMine.add(allIllust.get(i));
            }else {
                partOthers.add(allIllust.get(i));
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HOT_HEAD) {
            view = mLayoutInflater.inflate(R.layout.comment_devider, parent, false);
            return new DividerHolder(view);
        } else if (viewType == TYPE_HOT_COMMENT) {
            view = mLayoutInflater.inflate(R.layout.recy_play_list, parent, false);
            return new TagHolder(view);
        } else if (viewType == TYPE_NORMAL_HEAD) {
            view = mLayoutInflater.inflate(R.layout.comment_devider, parent, false);
            return new DividerHolder(view);
        } else {
            view = mLayoutInflater.inflate(R.layout.recy_play_list, parent, false);
            return new TagHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HOT_HEAD;
        } else if (position > 0 && position < partMine.size() + HOT_HEAD_COUNT) {
            return TYPE_HOT_COMMENT;
        } else if (position == partMine.size() + HOT_HEAD_COUNT) {
            return TYPE_NORMAL_HEAD;
        } else {
            return TYPE_NORMAL_COMMENT;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_HOT_HEAD) {
            ((DividerHolder) holder).mTextView.setText("创建的歌单");
        } else if (viewType == TYPE_HOT_COMMENT) {
            int realPosition = position - HOT_HEAD_COUNT;
            ((TagHolder) holder).mTextView.setText(partMine.get(realPosition).getName());
            ((TagHolder) holder).mTextView2.setText(String.format("共%s首歌曲，播放%s次",
                    partMine.get(realPosition).getTrackCount(), partMine.get(realPosition).getPlayCount()));
            if (partMine.get(realPosition).getCoverImgUrl() == null || partMine.get(realPosition).getCoverImgUrl().length() == 0) {
                Glide.with(mContext).load(R.mipmap.default_playlist_cover).into(((TagHolder) holder).mNiceImageView);
            } else {
                Glide.with(mContext).load(partMine.get(realPosition).getCoverImgUrl()).into(((TagHolder) holder).mNiceImageView);
            }
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(v ->
                        mOnItemClickListener.onItemClick(((TagHolder) holder).mNiceImageView, realPosition, 0));
            }
        } else if (viewType == TYPE_NORMAL_HEAD) {
            ((DividerHolder) holder).mTextView.setText("收藏的歌单");
        } else {
            int realPosition = position - partMine.size() - HOT_HEAD_COUNT - NORMAL_HEAD_COUNT;
            ((TagHolder) holder).mTextView.setText(partOthers.get(realPosition).getName());
            ((TagHolder) holder).mTextView2.setText(String.format("共%s首歌曲，播放%s次",
                    partOthers.get(realPosition).getTrackCount(), partOthers.get(realPosition).getPlayCount()));
            if (partOthers.get(realPosition).getCoverImgUrl() == null || partOthers.get(realPosition).getCoverImgUrl().length() == 0) {
                Glide.with(mContext).load(R.mipmap.default_playlist_cover).into(((TagHolder) holder).mNiceImageView);
            } else {
                Glide.with(mContext).load(partOthers.get(realPosition).getCoverImgUrl()).into(((TagHolder) holder).mNiceImageView);
            }
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(v ->
                        mOnItemClickListener.onItemClick(((TagHolder) holder).mNiceImageView, position - 2, 0));
            }
        }
    }

    @Override
    public int getItemCount() {
        return allIllust.size() + HOT_HEAD_COUNT + NORMAL_HEAD_COUNT;
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

    public class DividerHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        DividerHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.comment_type);
        }
    }
}
