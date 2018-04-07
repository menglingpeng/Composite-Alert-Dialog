package com.menglingpeng.compositealertdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mengdroid on 2018/4/7.
 */

public class BaseDialog extends Dialog implements DialogInterface.OnShowListener{

    private OnShowListener showListener;

    public BaseDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    @Override
    public void setOnShowListener(@Nullable OnShowListener listener) {
        super.setOnShowListener(listener);
    }

    public final void setViewInternal(View view) {
        super.setContentView(view);
    }

    @Override
    public void onShow(DialogInterface dialog) {
        if (showListener != null) {
            showListener.onShow(dialog);
        }
    }

    @Override
    public void setContentView(int layoutResID)throws IllegalAccessError {
        throw new IllegalAccessError(
                "setContentView() is not supported in MaterialDialog. Specify a custom view in the Builder instead.");
    }

    @Override
    public void setContentView(@NonNull View view) throws IllegalAccessError {
        throw new IllegalAccessError(
                "setContentView() is not supported in MaterialDialog. Specify a custom view in the Builder instead.");
    }

    @Override
    public void setContentView(@NonNull View view, @Nullable ViewGroup.LayoutParams params)
            throws IllegalAccessError {
        throw new IllegalAccessError(
                "setContentView() is not supported in MaterialDialog. Specify a custom view in the Builder instead.");
    }

}
