package com.menglingpeng.compositealertdialog.Util;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.CheckBox;
import android.widget.RadioButton;

/**
 * Created by mengdroid on 2018/4/28.
 */

public class TintHelper {

    public static void setTint(RadioButton radioButton, ColorStateList colors) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            radioButton.setButtonTintList(colors);
        } else {
            Drawable radioDrawable =
                    ContextCompat.getDrawable(radioButton.getContext(), R.drawable.abc_btn_radio_material);
            Drawable d = DrawableCompat.wrap(radioDrawable);
            DrawableCompat.setTintList(d, colors);
            radioButton.setButtonDrawable(d);
        }
    }

    public static void setTint(RadioButton radioButton, @ColorInt int color) {
        final int disabledColor = DialogUtil.getDisabledColor(radioButton.getContext());
        ColorStateList sl =
                new ColorStateList(
                        new int[][] {
                                new int[] {android.R.attr.state_enabled, -android.R.attr.state_checked},
                                new int[] {android.R.attr.state_enabled, android.R.attr.state_checked},
                                new int[] {-android.R.attr.state_enabled, -android.R.attr.state_checked},
                                new int[] {-android.R.attr.state_enabled, android.R.attr.state_checked}
                        },
                        new int[] {
                                DialogUtil.resolveColor(radioButton.getContext(), R.attr.colorControlNormal),
                                color,
                                disabledColor,
                                disabledColor
                        });
        setTint(radioButton, sl);
    }

    public static void setTint(CheckBox box, ColorStateList colors) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            box.setButtonTintList(colors);
        } else {
            Drawable checkDrawable =
                    ContextCompat.getDrawable(box.getContext(), R.drawable.abc_btn_check_material);
            Drawable drawable = DrawableCompat.wrap(checkDrawable);
            DrawableCompat.setTintList(drawable, colors);
            box.setButtonDrawable(drawable);
        }
    }

    public static void setTint(CheckBox box, @ColorInt int color) {
        final int disabledColor = DialogUtil.getDisabledColor(box.getContext());
        ColorStateList sl =
                new ColorStateList(
                        new int[][] {
                                new int[] {android.R.attr.state_enabled, -android.R.attr.state_checked},
                                new int[] {android.R.attr.state_enabled, android.R.attr.state_checked},
                                new int[] {-android.R.attr.state_enabled, -android.R.attr.state_checked},
                                new int[] {-android.R.attr.state_enabled, android.R.attr.state_checked}
                        },
                        new int[] {
                                DialogUtil.resolveColor(box.getContext(), R.attr.colorControlNormal),
                                color,
                                disabledColor,
                                disabledColor
                        });
        setTint(box, sl);
    }

}