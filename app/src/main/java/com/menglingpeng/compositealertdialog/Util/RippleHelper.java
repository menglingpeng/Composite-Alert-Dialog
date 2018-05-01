package com.menglingpeng.compositealertdialog.Util;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.support.annotation.ColorInt;

/**
 * Created by mengdroid on 2018/5/1.
 */

public class RippleHelper {

    public static void applyColor(Drawable d, @ColorInt int color) {
        if (d instanceof RippleDrawable) {
            ((RippleDrawable) d).setColor(ColorStateList.valueOf(color));
        }
    }
}
