package com.menglingpeng.compositealertdialog.Util;

import android.graphics.drawable.Drawable;
import android.view.View;

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
        final CompositeAlertDialog builder = builder;

        dialog.titleTv = dialog.view.findViewById(R.id.md_title);
        dialog.iconIv = dialog.view.findViewById(R.id.md_icon);
        dialog.titleFrame = dialog.view.findViewById(R.id.md_titleFrame);
        dialog.contentTv = dialog.view.findViewById(R.id.md_content);
        dialog.recyclerView = dialog.view.findViewById(R.id.md_contentRecyclerView);
        dialog.checkBoxPrompt = dialog.view.findViewById(R.id.md_promptCheckbox);

        // Button views initially used by checkIfStackingNeeded()
        dialog.positiveButton = dialog.view.findViewById(R.id.buttonDefaultPositive);
        dialog.neutralButton = dialog.view.findViewById(R.id.buttonDefaultNeutral);
        dialog.negativeButton = dialog.view.findViewById(R.id.md_buttonDefaultNegative);

        // Don't allow the submit button to not be shown for input dialogs
        if (builder.inputCallback != null && builder.positiveText == null) {
            builder.positiveText = builder.context.getText(android.R.string.ok);
        }

        // Set up the initial visibility of action buttons based on whether or not text was set
        dialog.positiveButton.setVisibility(builder.positiveText != null ? View.VISIBLE : View.GONE);
        dialog.neutralButton.setVisibility(builder.neutralText != null ? View.VISIBLE : View.GONE);
        dialog.negativeButton.setVisibility(builder.negativeText != null ? View.VISIBLE : View.GONE);

        // Set up the focus of action buttons
        dialog.positiveButton.setFocusable(true);
        dialog.neutralButton.setFocusable(true);
        dialog.negativeButton.setFocusable(true);

        if (builder.positiveFocus) {
            dialog.positiveButton.requestFocus();
        }
        if (builder.neutralFocus) {
            dialog.neutralButton.requestFocus();
        }
        if (builder.negativeFocus) {
            dialog.negativeButton.requestFocus();
        }

        // Setup icon
        if (builder.icon != null) {
            dialog.icon.setVisibility(View.VISIBLE);
            dialog.icon.setImageDrawable(builder.icon);
        } else {
            Drawable d = DialogUtil.resolveDrawable(builder.context, R.attr.cd_icon);
            if (d != null) {
                dialog.icon.setVisibility(View.VISIBLE);
                dialog.icon.setImageDrawable(d);
            } else {
                dialog.icon.setVisibility(View.GONE);
            }
        }

    }
}
