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
        View view = mLayoutInflater.inflate(R.layout.recy_comment_list, parent, false);
        return new TagHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position < allIllust.size()) {
            Glide.with(mContext).load(allIllust.get(position).getUser().getAvatarUrl())
                    .into(((TagHolder) holder).mCircleImageView);
            ((TagHolder) holder).mTextView.setText(allIllust.get(position).getUser().getNickname());
            ((TagHolder) holder).mTextView2.setText(
                    Common.timeStamp2Date(String.valueOf(allIllust.get(position).getTime())));
            ((TagHolder) holder).mTextView3.setText(String.valueOf(allIllust.get(position).getLikedCount()));
            ((TagHolder) holder).mTextView4.setText(String.valueOf(allIllust.get(position).getContent()));
            if (allIllust.get(position).getBeReplied().size() == 0) {
                ((TagHolder) holder).mRelativeLayout.setVisibility(View.GONE);
            } else {
                ((TagHolder) holder).mRelativeLayout.setVisibility(View.VISIBLE);
                ((TagHolder) holder).mTextView5.setText(String.format("@%s：%s",
                        allIllust.get(position).getBeReplied().get(0).getUser().getNickname(),
                        allIllust.get(position).getBeReplied().get(0).getContent()));
            }
        } else {
            int p = position - allIllust.size();
            Common.showLog(p);
            Glide.with(mContext).load(comment.get(p).getUser().getAvatarUrl())
                    .into(((TagHolder) holder).mCircleImageView);
            ((TagHolder) holder).mTextView.setText(comment.get(p).getUser().getNickname());
            ((TagHolder) holder).mTextView2.setText(
                    Common.timeStamp2Date(String.valueOf(comment.get(p).getTime())));
            ((TagHolder) holder).mTextView3.setText(String.valueOf(comment.get(p).getLikedCount()));
            ((TagHolder) holder).mTextView4.setText(String.valueOf(comment.get(p).getContent()));
            if (comment.get(p).getBeReplied().size() == 0) {
                ((TagHolder) holder).mRelativeLayout.setVisibility(View.GONE);
            } else {
                ((TagHolder) holder).mRelativeLayout.setVisibility(View.VISIBLE);
                SpannableString spannableString = new SpannableString(String.format("@%s：%s",
                        comment.get(p).getBeReplied().get(0).getUser().getNickname(),
                        comment.get(p).getBeReplied().get(0).getContent()));
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#507daf")),
                        0, comment.get(p).getBeReplied().get(0).getUser().getNickname().length() + 1,
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
        return allIllust.size() + comment.size();
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
}
