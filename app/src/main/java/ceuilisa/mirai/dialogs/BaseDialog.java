package ceuilisa.mirai.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ceuilisa.mirai.R;

public abstract class BaseDialog extends DialogFragment {

    public Button sure, cancel;
    protected AlertDialog mAlertDialog;
    protected int index;
    protected int mLayoutID = -1;
    protected TextView title, content;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        initLayout();
        View view = mLayoutID == -1 ?
                LayoutInflater.from(getActivity()).inflate(R.layout.dialog_base, null) :
                LayoutInflater.from(getActivity()).inflate(mLayoutID, null);

        title = view.findViewById(R.id.title);
        content = view.findViewById(R.id.content);
        sure = view.findViewById(R.id.sure);
        cancel = view.findViewById(R.id.cancel);
        cancel.setOnClickListener(v -> mAlertDialog.dismiss());
        initView(view);
        builder.setView(view);
        builder.setIcon(R.mipmap.ic_logo_round);
        mAlertDialog = builder.create();
        return mAlertDialog;
    }


    abstract void initLayout();

    abstract void initView(View v);
}
