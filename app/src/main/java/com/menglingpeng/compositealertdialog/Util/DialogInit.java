package com.menglingpeng.compositealertdialog.Util;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.menglingpeng.compositealertdialog.CompositeAlertDialog;
import com.menglingpeng.compositealertdialog.DialogBuilder;
import com.menglingpeng.compositealertdialog.R;

import me.zhanghai.android.materialprogressbar.HorizontalProgressDrawable;
import me.zhanghai.android.materialprogressbar.IndeterminateCircularProgressDrawable;
import me.zhanghai.android.materialprogressbar.IndeterminateHorizontalProgressDrawable;

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

    private static void setupProgressDialog(final CompositeAlertDialog dialog) {
        final CompositeAlertDialog.DialogBuilder builder = dialog.builder;
        if (builder.indeterminateProgress || builder.progress > -2) {
            dialog.progressBar = dialog.view.findViewById(android.R.id.progress);
            if (dialog.progressBar == null) {
                return;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                if (builder.indeterminateProgress) {
                    if (builder.indeterminateIsHorizontalProgress) {
                        IndeterminateHorizontalProgressDrawable d =
                                new IndeterminateHorizontalProgressDrawable(builder.getContext());
                        d.setTint(builder.widgetColor);
                        dialog.progressBar.setProgressDrawable(d);
                        dialog.progressBar.setIndeterminateDrawable(d);
                    } else {
                        IndeterminateCircularProgressDrawable d =
                                new IndeterminateCircularProgressDrawable(builder.getContext());
                        d.setTint(builder.widgetColor);
                        dialog.progressBar.setProgressDrawable(d);
                        dialog.progressBar.setIndeterminateDrawable(d);
                    }
                } else {
                    HorizontalProgressDrawable d = new HorizontalProgressDrawable(builder.getContext());
                    d.setTint(builder.widgetColor);
                    dialog.progressBar.setProgressDrawable(d);
                    dialog.progressBar.setIndeterminateDrawable(d);
                }
            } else {
                TintHelper.setTint(dialog.progressBar, builder.widgetColor);
            }

            if (!builder.indeterminateProgress || builder.indeterminateIsHorizontalProgress) {
                dialog.progressBar.setIndeterminate(
                        builder.indeterminateProgress && builder.indeterminateIsHorizontalProgress);
                dialog.progressBar.setProgress(0);
                dialog.progressBar.setMax(builder.progressMax);
                dialog.progressLabel = dialog.view.findViewById(R.id.md_label);
                if (dialog.progressLabel != null) {
                    dialog.progressLabel.setTextColor(builder.contentColor);
                    dialog.setTypeface(dialog.progressLabel, builder.mediumFont);
                    dialog.progressLabel.setText(builder.progressPercentFormat.format(0));
                }
                dialog.progressMinMax = dialog.view.findViewById(R.id.md_minMax);
                if (dialog.progressMinMax != null) {
                    dialog.progressMinMax.setTextColor(builder.contentColor);
                    dialog.setTypeface(dialog.progressMinMax, builder.regularFont);

                    if (builder.showMinMax) {
                        dialog.progressMinMax.setVisibility(View.VISIBLE);
                        dialog.progressMinMax.setText(
                                String.format(builder.progressNumberFormat, 0, builder.progressMax));
                        ViewGroup.MarginLayoutParams lp =
                                (ViewGroup.MarginLayoutParams) dialog.progressBar.getLayoutParams();
                        lp.leftMargin = 0;
                        lp.rightMargin = 0;
                    } else {
                        dialog.progressMinMax.setVisibility(View.GONE);
                    }
                } else {
                    builder.showMinMax = false;
                }
            }
        }

        if (dialog.progressBar != null) {
            fixCanvasScalingWhenHardwareAccelerated(dialog.progressBar);
        }
    }

    private static void setupInputDialog(final CompositeAlertDialog dialog) {
        final CompositeAlertDialog.DialogBuilder builder = dialog.builder;
        dialog.inputEt = dialog.view.findViewById(android.R.id.input);
        if (dialog.inputEt == null) {
            return;
        }
        dialog.setTypeface(dialog.input, builder.regularFont);
        if (builder.inputPrefill != null) {
            dialog.inputEt.setText(builder.inputPrefill);
        }
        dialog.setInternalInputCallback();
        dialog.inputEt.setHint(builder.inputHint);
        dialog.inputEt.setSingleLine();
        dialog.inputEt.setTextColor(builder.contentColor);
        dialog.inputEt.setHintTextColor(DialogUtil.adjustAlpha(builder.contentColor, 0.3f));
        TintHelper.setTint(dialog.inputEt, dialog.builder.widgetColor);

        if (builder.inputType != -1) {
            dialog.inputEt.setInputType(builder.inputType);
            if (builder.inputType != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    && (builder.inputType & InputType.TYPE_TEXT_VARIATION_PASSWORD)
                    == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                // If the flags contain TYPE_TEXT_VARIATION_PASSWORD, apply the password transformation
                // method automatically
                dialog.inputEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }

        dialog.inputMinMax = dialog.view.findViewById(R.id.md_minMax);
        if (builder.inputMinLength > 0 || builder.inputMaxLength > -1) {
            dialog.invalidateInputMinMaxIndicator(
                    dialog.input.getText().toString().length(), !builder.inputAllowEmpty);
        } else {
            dialog.inputMinMax.setVisibility(View.GONE);
            dialog.inputMinMax = null;
        }

        if (builder.inputFilters != null) {
            dialog.inputEt.setFilters(builder.inputFilters);
        }
    }
}
