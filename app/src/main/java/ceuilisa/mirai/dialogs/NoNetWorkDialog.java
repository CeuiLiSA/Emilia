package ceuilisa.mirai.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

public class NoNetWorkDialog extends Dialog {

    public NoNetWorkDialog( Context context) {
        super(context);
    }

    public NoNetWorkDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected NoNetWorkDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
