package ceuilisa.mirai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ceuilisa.mirai.R;
import ceuilisa.mirai.activities.TemplateFragmentActivity;
import ceuilisa.mirai.activities.ViewPagerActivity;

public class FragmentLeft extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_left;
    }

    @Override
    View initView(View v) {
//        Toolbar toolbar = v.findViewById(R.id.toolbar);
//        ((MainActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(v1 -> {
//            if (((MainActivity) getActivity()).mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
//                ((MainActivity) getActivity()).mDrawerLayout.closeDrawer(GravityCompat.START);
//            } else {
//                ((MainActivity) getActivity()).mDrawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
        TextView textView = v.findViewById(R.id.textView7);
        textView.setOnClickListener(v12 -> {
            Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
            intent.putExtra("dataType", "推荐歌单");
            mContext.startActivity(intent);
        });

        TextView textView6 = v.findViewById(R.id.textView6);
        textView6.setOnClickListener(v12 -> {
            Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
            intent.putExtra("dataType", "我的歌单");
            mContext.startActivity(intent);
        });
//        TextView textView1 = v.findViewById(R.id.textView6);
//        textView1.setOnClickListener(v12 -> {
//            Intent intent = new Intent(mContext, PlayListDetailActivity.class);
//            UserBean userBean = Local.getUser();
//            intent.putExtra("name",  userBean.getUserName() + "喜欢的音乐");
//            intent.putExtra("author", userBean.getUserName());
//            intent.putExtra("dataType", "我的收藏");
//            intent.putExtra("coverImg", "https://p1.music.126.net/id-110tprTCxF4BXNbZIOw==/109951163198268119.jpg");
//            mContext.startActivity(intent);
//        });
        TextView textView2 = v.findViewById(R.id.textView2);
        textView2.setOnClickListener(v12 -> {
            Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
            intent.putExtra("dataType", "每日推荐");
            mContext.startActivity(intent);
        });
        TextView textView3 = v.findViewById(R.id.textView3);
        textView3.setOnClickListener(v12 -> {

            Intent intent = new Intent(mContext, TemplateFragmentActivity.class);
            intent.putExtra("dataType", "本地音乐");
            mContext.startActivity(intent);
        });
        TextView textView4 = v.findViewById(R.id.textView4);
        textView4.setOnClickListener(v12 -> {
            Intent intent = new Intent(mContext, ViewPagerActivity.class);
            intent.putExtra("dataType", "新歌速递");
            mContext.startActivity(intent);
        });
        TextView textView5 = v.findViewById(R.id.textView5);
        textView5.setOnClickListener(v12 -> {
            Intent intent = new Intent(mContext, ViewPagerActivity.class);
            intent.putExtra("dataType", "歌单分类");
            mContext.startActivity(intent);
        });
        return v;
    }

    @Override
    void initData() {

    }
}