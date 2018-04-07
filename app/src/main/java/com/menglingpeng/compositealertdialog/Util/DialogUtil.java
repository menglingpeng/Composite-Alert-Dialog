package com.menglingpeng.compositealertdialog.Util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;

/**
 * Created by mengdroid on 2018/4/7.
 */

public class DialogUtil {

    public static int getDisabledColor(Context context) {
        final int primaryColor = resolveColor(context, android.R.attr.textColorPrimary);
        final int disabledColor = isColorDark(primaryColor) ? Color.BLACK : Color.WHITE;
        return adjustAlpha(disabledColor, 0.3f);
    }


    public static int adjustAlpha(
            @ColorInt int color, @SuppressWarnings("SameParameterValue") float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    public static int resolveColor(Context context, @AttrRes int attr) {
        return resolveColor(context, attr, 0);
    }

    public static int resolveColor(Context context, @AttrRes int attr, int fallback) {
        TypedArray a = context.getTheme().obtainStyledAttributes(new int[] {attr});
        try {
            return a.getColor(0, fallback);
        } finally {
            a.recycle();
        }
    }

    public static boolean isColorDark(@ColorInt int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return darkness >= 0.5;
    }
}
