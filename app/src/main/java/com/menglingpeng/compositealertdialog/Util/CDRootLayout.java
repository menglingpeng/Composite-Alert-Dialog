package com.menglingpeng.compositealertdialog.Util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.menglingpeng.compositealertdialog.R;

/**
 * Created by mengdroid on 2018/4/14.
 */

 class CDRootLayout extends ViewGroup {

    private View titleBar;
    private View content;
    private int noTitlePaddingFull;
    private boolean useFullPadding = true;
    private boolean reducePaddingNoTitleNoButtons;
    private boolean noTitleNoPadding;


    private static final int INDEX_NEUTRAL = 0;
    private static final int INDEX_NEGATIVE = 1;
    private static final int INDEX_POSITIVE = 2;


    public CDRootLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CDRootLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CDRootLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public CDRootLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    private static boolean isVisible(@Nullable View v) {
        boolean visible = v != null && v.getVisibility() != View.GONE;
        if (visible && v instanceof CDButton) {
            visible = ((CDButton) v).getText().toString().trim().length() > 0;
        }
        return visible;
    }

    private void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        Resources r = context.getResources();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CDRootLayout, defStyleAttr, 0);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if (isVisible(titleBar)) {
            int height = titleBar.getMeasuredHeight();
            titleBar.layout(l, t, r, t + height);
            t += height;
        } else if (!noTitleNoPadding && useFullPadding) {
            t += noTitlePaddingFull;
        }
        if (isVisible(content)) {
            content.layout(l, t, r, t + content.getMeasuredHeight());
        }

    }
}
