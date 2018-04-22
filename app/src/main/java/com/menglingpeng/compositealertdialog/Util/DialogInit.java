package com.menglingpeng.compositealertdialog.Util;

import com.menglingpeng.compositealertdialog.CompositeAlertDialog;
import com.menglingpeng.compositealertdialog.DialogBuilder;
import com.menglingpeng.compositealertdialog.R;

/**
 * Created by mengdroid on 2018/4/22.
 */

public class DialogInit {
    public static int getInflateLayout(CompositeAlertDialog.DialogBuilder builder) {
        if (builder.customView != null) {
            return R.layout.dialog_custom;
        } else if (builder.items != null || builder.adapter != null) {
            if (builder.checkBoxPrompt != null) {
                return R.layout.dialog_list_check;
            }
            return R.layout.dialog_list;
        } else if (builder.progress > -2) {
            return R.layout.dialog_progress;
        } else if (builder.indeterminateProgress) {
            if (builder.indeterminateIsHorizontalProgress) {
                return R.layout.stub_progress_indeterminate_horizontal;
            }
            return R.layout.stub_progress_indeterminate;
        } else if (builder.inputCallback != null) {
            if (builder.checkBoxPrompt != null) {
                return R.layout.dialog_input_check;
            }
            return R.layout.dialog_input;
        } else if (builder.checkBoxPrompt != null) {
            return R.layout.dialog_basic_check;
        } else {
            return R.layout.dialog_basic;
        }
    }

    public static void init(final CompositeAlertDialog dialog) {
        final DialogBuilder builder = builder;
    }

    }
