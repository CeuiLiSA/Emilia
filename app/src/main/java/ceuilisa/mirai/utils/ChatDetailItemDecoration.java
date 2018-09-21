package ceuilisa.mirai.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ChatDetailItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public ChatDetailItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        if (parent.getChildPosition(view) == 0) {
            outRect.top = space;
        }
    }
}
