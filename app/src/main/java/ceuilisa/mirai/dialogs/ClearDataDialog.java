package ceuilisa.mirai.dialogs;

import android.view.View;

public class ClearDataDialog extends BaseDialog{

    private View.OnClickListener positiveClick;

    @Override
    void initLayout() {

    }

    @Override
    void initView(View v) {
        title.setText("提示：");
        content.setText("这将会清楚所有本地数据");
        if(positiveClick != null) {
            sure.setOnClickListener(positiveClick);
        }
    }

    public void setPositiveClick(View.OnClickListener onClickListener){
        positiveClick = onClickListener;
    }
}
