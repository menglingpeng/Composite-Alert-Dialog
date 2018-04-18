package com.menglingpeng.compositealertdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.menglingpeng.compositealertdialog.Util.CDButton;
import com.menglingpeng.compositealertdialog.Util.DialogActionEnum;
import com.menglingpeng.compositealertdialog.Util.DialogUtil;

/**
 * Created by mengdroid on 2018/4/5.
 */

public class CompositeAlertDialog extends BaseDialog implements View.OnClickListener {

    protected Context context;
    private DialogBuilder builder;
    private View dialogView;
    protected ImageView iconIv;
    protected TextView titleTv;
    protected TextView contentTv;
    private EditText inputEt;
    private CDButton positiveButton;
    private CDButton neutralButton;
    private CDButton negativeButton;
    private String titleText;
    private String contentText;
    private boolean showCancel;
    private boolean showContent;
    private String cancelText;
    private String confirmText;
    private int alertType;

    public static final int TYPE_NOMAL = 0;
    public static final int TYPE_SUCCESS = 1;
    public static final int TYPE_ERROR = 2;

    public CompositeAlertDialog(@NonNull Context context) {
        super(context, TYPE_NOMAL);
        this.context = context;
    }

    final Drawable getListSelector() {
        if (builder.listSelector != 0) {
            return ResourcesCompat.getDrawable(getContext().getResources(), builder.listSelector, null);
        }
        final Drawable d = DialogUtil.resolveDrawable(getContext(), R.attr.md_list_selector);
        if (d != null) {
            return d;
        }
        return DialogUtil.resolveDrawable(getContext(), R.attr.md_list_selector);
    }

    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public final CDButton getActionButton(DialogActionEnum which) {
        switch (which) {
            case NEUTRAL:
                return neutralButton;
            case NEGATIVE:
                return negativeButton;
            default:
                return positiveButton;
        }
    }

    @Override
    public void onClick(View v) {

    }

    public final View getView() {
        return dialogView;
    }

    @Nullable
    public final EditText getInputEditText() {
        return inputEt;
    }

    public final TextView getTitleView() {
        return titleTv;
    }

    public ImageView getIconView() {
        return iconIv;
    }

    public final void setActionButton(final DialogActionEnum which, @Nullable final CharSequence title) {
        switch (which) {
            default:
                builder.positiveText = title;
                positiveButton.setText(title);
                positiveButton.setVisibility(title == null ? View.GONE : View.VISIBLE);
                break;
            case NEUTRAL:
                builder.neutralText = title;
                neutralButton.setText(title);
                neutralButton.setVisibility(title == null ? View.GONE : View.VISIBLE);
                break;
            case NEGATIVE:
                builder.negativeText = title;
                negativeButton.setText(title);
                negativeButton.setVisibility(title == null ? View.GONE : View.VISIBLE);
                break;
        }
    }

    @Nullable
    public final TextView getContentView() {
        return contentTv;
    }

    public final Context getContext() {
        return context;
    }

    private static void setInputDialog(){

    }

    @Override
    public void onShow(DialogInterface dialog) {
        super.onShow(dialog);
    }

    @Override
    public void dismiss() {
        if(inputEt != null) {
            DialogUtil.hideKeyboard(this);
        }
        super.dismiss();
    }

    public static interface OnCompositeClickListener{
        void onClick(CompositeAlertDialog compositeAlertDialog);
    }
}
