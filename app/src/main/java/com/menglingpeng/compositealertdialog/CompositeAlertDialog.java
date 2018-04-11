package com.menglingpeng.compositealertdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mengdroid on 2018/4/5.
 */

public class CompositeAlertDialog extends Dialog implements View.OnClickListener {

    protected Context context;
    private View dialogView;
    protected ImageView iconIv;
    protected TextView titleTv;
    protected TextView contentTv;
    private EditText inputEt;
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


    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
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

    @Nullable
    public final TextView getContentView() {
        return contentTv;
    }

    public final Context getContext() {
        return context;
    }



    public static interface OnCompositeClickListener{
        void onClick(CompositeAlertDialog compositeAlertDialog);
    }
}
