package com.menglingpeng.compositealertdialog.Util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.menglingpeng.compositealertdialog.CompositeAlertDialog;

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

    public static int getColor(Context context, @ColorRes int colorId) {
        return ContextCompat.getColor(context, colorId);
    }

    public static String resolveString(Context context, @AttrRes int attr) {
        TypedValue v = new TypedValue();
        context.getTheme().resolveAttribute(attr, v, true);
        return (String) v.string;
    }

    private static int gravityEnumToAttrInt(GravityEnum value) {
        switch (value) {
            case CENTER:
                return 1;
            case END:
                return 2;
            default:
                return 0;
        }
    }

    public static GravityEnum resolveGravityEnum(
            Context context, @AttrRes int attr, GravityEnum defaultGravity) {
        TypedArray a = context.getTheme().obtainStyledAttributes(new int[] {attr});
        try {
            switch (a.getInt(0, gravityEnumToAttrInt(defaultGravity))) {
                case 1:
                    return GravityEnum.CENTER;
                case 2:
                    return GravityEnum.END;
                default:
                    return GravityEnum.START;
            }
        } finally {
            a.recycle();
        }
    }

    public static void showKeyboard(final DialogInterface di) {
        final CompositeAlertDialog dialog = (CompositeAlertDialog) di;
        if (dialog.getInputEditText() == null) {
            return;
        }
        dialog
                .getInputEditText()
                .post(
                        new Runnable() {
                            @Override
                            public void run() {
                                dialog.getInputEditText().requestFocus();
                                InputMethodManager imm =
                                        (InputMethodManager)
                                                dialog.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                if (imm != null) {
                                    imm.showSoftInput(dialog.getInputEditText(), InputMethodManager.SHOW_IMPLICIT);
                                }
                            }
                        });
    }

    public static void hideKeyboard(final DialogInterface di) {
        final CompositeAlertDialog dialog = (CompositeAlertDialog) di;
        if (dialog.getInputEditText() == null) {
            return;
        }
        InputMethodManager imm =
                (InputMethodManager) dialog.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            final View currentFocus = dialog.getCurrentFocus();
            IBinder windowToken = null;
            if (currentFocus != null) {
                windowToken = currentFocus.getWindowToken();
            } else if (dialog.getView() != null) {
                windowToken = dialog.getView().getWindowToken();
            }
            if (windowToken != null) {
                imm.hideSoftInputFromWindow(windowToken, 0);
            }
        }
    }


    public static ColorStateList getActionTextStateList(Context context, int newPrimaryColor) {
        final int fallBackButtonColor =
                DialogUtil.resolveColor(context, android.R.attr.textColorPrimary);
        if (newPrimaryColor == 0) {
            newPrimaryColor = fallBackButtonColor;
        }
        int[][] states =
                new int[][] {
                        new int[] {-android.R.attr.state_enabled}, // disabled
                        new int[] {} // enabled
                };
        int[] colors = new int[] {DialogUtil.adjustAlpha(newPrimaryColor, 0.4f), newPrimaryColor};
        return new ColorStateList(states, colors);
    }

    public static int[] getColorArray(Context context, @ArrayRes int array) {
        if (array == 0) {
            return null;
        }
        TypedArray ta = context.getResources().obtainTypedArray(array);
        int[] colors = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            colors[i] = ta.getColor(i, 0);
        }
        ta.recycle();
        return colors;
    }

    public static void setBackgroundCompat(View view, Drawable d) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            //noinspection deprecation
            view.setBackgroundDrawable(d);
        } else {
            view.setBackground(d);
        }
    }
}
