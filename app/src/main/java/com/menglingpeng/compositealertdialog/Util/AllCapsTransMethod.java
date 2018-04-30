package com.menglingpeng.compositealertdialog.Util;

import android.content.Context;
import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;

import java.util.Locale;

/**
 * Created by mengdroid on 2018/4/30.
 */

public class AllCapsTransMethod implements TransformationMethod {
    private Locale locale;

    public AllCapsTransMethod(Context context) {
        mLocale = context.getResources().getConfiguration().locale;
    }

    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return source != null ? source.toString().toUpperCase(locale) : null;
    }

    @Override
    public void onFocusChanged(
            View view,
            CharSequence sourceText,
            boolean focused,
            int direction,
            Rect previouslyFocusedRect) {}
}
