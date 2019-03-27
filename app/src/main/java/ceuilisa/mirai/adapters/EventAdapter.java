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
import com.google.gson.Gson;

import java.util.List;

import ceuilisa.mirai.MusicService;
import ceuilisa.mirai.R;
import ceuilisa.mirai.interf.OnItemClickListener;
import ceuilisa.mirai.nodejs.EventsBean;
import ceuilisa.mirai.nodejs.TempJson;
import ceuilisa.mirai.response.TracksBean;
import ceuilisa.mirai.utils.Common;
import de.hdodenhof.circleimageview.CircleImageView;


public class EventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private List<EventsBean> allIllust;
    private int nowPlayIndex;

    public EventAdapter(List<EventsBean> list, Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        allIllust = list;
        nowPlayIndex = MusicService.getInstance().getNowPlayIndex();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recy_event, parent, false);
        return new TagHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TagHolder currentOne = ((TagHolder) holder);
        currentOne.userName.setText(allIllust.get(position).getUser().getNickname());
        currentOne.date.setText(Common.timeStamp2Date(String.valueOf(allIllust.get(position).getEventTime())));
        Gson gson = new Gson();
        TempJson tempJson = gson.fromJson(allIllust.get(position).getJson(), TempJson.class);
        currentOne.content.setText(tempJson.getMsg());
        Glide.with(mContext).load(allIllust.get(position).getUser().getAvatarUrl()).into(currentOne.userHead);
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
        private TextView userName, date, content;
        private CircleImageView userHead;

        TagHolder(View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.user_name);
            date = itemView.findViewById(R.id.date);
            content = itemView.findViewById(R.id.content);
            userHead = itemView.findViewById(R.id.user_head);
        }
    }
}
