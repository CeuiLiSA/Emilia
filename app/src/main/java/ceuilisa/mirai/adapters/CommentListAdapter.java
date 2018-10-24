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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ceuilisa.mirai.R;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.response.CommentResponse;
import ceuilisa.mirai.utils.Common;
import de.hdodenhof.circleimageview.CircleImageView;


public class CommentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HOT_HEAD = 0;
    private static final int TYPE_NORMAL_HEAD = 1;
    private static final int HOT_HEAD_COUNT = 1;
    private static final int NORMAL_HEAD_COUNT = 1;
    private static final int TYPE_HOT_COMMENT = 2;
    private static final int TYPE_NORMAL_COMMENT = 3;

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private List<CommentResponse.HotCommentsBean> allIllust;
    private List<CommentResponse.CommentsBean> comment;

    public CommentListAdapter(List<CommentResponse.HotCommentsBean> list,
                              List<CommentResponse.CommentsBean> list2,
                              Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        allIllust = list;
        comment = list2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HOT_HEAD) {
            view = mLayoutInflater.inflate(R.layout.comment_devider, parent, false);
            return new DividerHolder(view);
        } else if (viewType == TYPE_HOT_COMMENT) {
            view = mLayoutInflater.inflate(R.layout.recy_comment_list, parent, false);
            return new TagHolder(view);
        } else if (viewType == TYPE_NORMAL_HEAD) {
            view = mLayoutInflater.inflate(R.layout.comment_devider, parent, false);
            return new DividerHolder(view);
        } else {
            view = mLayoutInflater.inflate(R.layout.recy_comment_list, parent, false);
            return new TagHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HOT_HEAD;
        } else if (position > 0 && position < allIllust.size() + HOT_HEAD_COUNT) {
            return TYPE_HOT_COMMENT;
        } else if (position == allIllust.size() + HOT_HEAD_COUNT) {
            return TYPE_NORMAL_HEAD;
        } else {
            return TYPE_NORMAL_COMMENT;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_HOT_HEAD) {
            ((DividerHolder) holder).mTextView.setText("热门评论");
        } else if (viewType == TYPE_HOT_COMMENT) {
            int realPosition = position - HOT_HEAD_COUNT;
            Glide.with(mContext).load(allIllust.get(realPosition).getUser().getAvatarUrl())
                    .into(((TagHolder) holder).mCircleImageView);
            ((TagHolder) holder).mTextView.setText(allIllust.get(realPosition).getUser().getNickname());
            ((TagHolder) holder).mTextView2.setText(
                    Common.timeStamp2Date(String.valueOf(allIllust.get(realPosition).getTime())));
            ((TagHolder) holder).mTextView3.setText(String.valueOf(allIllust.get(realPosition).getLikedCount()));
            ((TagHolder) holder).mTextView4.setText(String.valueOf(allIllust.get(realPosition).getContent()));
            if (allIllust.get(realPosition).getBeReplied().size() == 0) {
                ((TagHolder) holder).mRelativeLayout.setVisibility(View.GONE);
            } else {
                ((TagHolder) holder).mRelativeLayout.setVisibility(View.VISIBLE);
                SpannableString spannableString = new SpannableString(String.format("@%s：%s",
                        allIllust.get(realPosition).getBeReplied().get(0).getUser().getNickname(),
                        allIllust.get(realPosition).getBeReplied().get(0).getContent()));
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#507daf")),
                        0, allIllust.get(realPosition).getBeReplied().get(0).getUser().getNickname().length() + 1,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ((TagHolder) holder).mTextView5.setText(spannableString);
            }
        } else if (viewType == TYPE_NORMAL_HEAD) {
            ((DividerHolder) holder).mTextView.setText("最新评论");
        } else {
            int realPosition = position - allIllust.size() - HOT_HEAD_COUNT - NORMAL_HEAD_COUNT;
            Common.showLog(realPosition);
            Glide.with(mContext).load(comment.get(realPosition).getUser().getAvatarUrl())
                    .into(((TagHolder) holder).mCircleImageView);
            ((TagHolder) holder).mTextView.setText(comment.get(realPosition).getUser().getNickname());
            ((TagHolder) holder).mTextView2.setText(
                    Common.timeStamp2Date(String.valueOf(comment.get(realPosition).getTime())));
            ((TagHolder) holder).mTextView3.setText(String.valueOf(comment.get(realPosition).getLikedCount()));
            ((TagHolder) holder).mTextView4.setText(String.valueOf(comment.get(realPosition).getContent()));
            if (comment.get(realPosition).getBeReplied().size() == 0) {
                ((TagHolder) holder).mRelativeLayout.setVisibility(View.GONE);
            } else {
                ((TagHolder) holder).mRelativeLayout.setVisibility(View.VISIBLE);
                SpannableString spannableString = new SpannableString(String.format("@%s：%s",
                        comment.get(realPosition).getBeReplied().get(0).getUser().getNickname(),
                        comment.get(realPosition).getBeReplied().get(0).getContent()));
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#507daf")),
                        0, comment.get(realPosition).getBeReplied().get(0).getUser().getNickname().length() + 1,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ((TagHolder) holder).mTextView5.setText(spannableString);
            }
        }
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v ->
                    mOnItemClickListener.onItemClick(holder.itemView, position, 0));
        }
    }

    @Override
    public int getItemCount() {
        return allIllust.size() + comment.size() + HOT_HEAD_COUNT + NORMAL_HEAD_COUNT;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public class TagHolder extends RecyclerView.ViewHolder {
        private RelativeLayout mRelativeLayout;
        private TextView mTextView, mTextView2, mTextView3, mTextView4, mTextView5;
        private CircleImageView mCircleImageView;

        TagHolder(View itemView) {
            super(itemView);

            mCircleImageView = itemView.findViewById(R.id.user_head);
            mTextView = itemView.findViewById(R.id.user_name);
            mTextView2 = itemView.findViewById(R.id.time);
            mTextView3 = itemView.findViewById(R.id.like_count);
            mTextView4 = itemView.findViewById(R.id.content);
            mTextView5 = itemView.findViewById(R.id.reply_content);
            mRelativeLayout = itemView.findViewById(R.id.reply_comment);
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
