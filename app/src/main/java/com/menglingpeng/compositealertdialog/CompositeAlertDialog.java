package com.menglingpeng.compositealertdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by mengdroid on 2018/4/5.
 */

public class CompositeAlertDialog extends Dialog implements View.OnClickListener {

    public static final int TYPE_NOMAL = 0;

    public CompositeAlertDialog(@NonNull Context context) {
        super(context, TYPE_NOMAL);
    }

    public CompositeAlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }

    public static interface OnCompositeClickListener{
        void onClick(CompositeAlertDialog compositeAlertDialog);
    }
}
