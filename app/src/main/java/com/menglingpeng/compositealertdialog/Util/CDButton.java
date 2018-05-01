package com.menglingpeng.compositealertdialog.Util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.text.AllCapsTransformationMethod;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.menglingpeng.compositealertdialog.R;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

/**
 * Created by mengdroid on 2018/4/12.
 */

public class CDButton extends android.support.v7.widget.AppCompatTextView {

    private boolean stacked = false;
    private GravityEnum stackedGravity;

    private int stackedEndPadding;
    private Drawable stackedBackground;
    private Drawable defaultBackground;

    public CDButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CDButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        stackedEndPadding =
                context.getResources().getDimensionPixelSize(R.dimen.dialog_frame_margin);
        stackedGravity = GravityEnum.END;
    }

        void setStacked(boolean stacked, boolean force) {
        if (this.stacked != stacked || force) {

            setGravity(
                    stacked ? (Gravity.CENTER_VERTICAL | stackedGravity.getGravityInt()) : Gravity.CENTER);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                //noinspection ResourceType
                setTextAlignment(stacked ? stackedGravity.getTextAlignment() : TEXT_ALIGNMENT_CENTER);
            }

            DialogUtil.setBackgroundCompat(this, stacked ? stackedBackground : defaultBackground);
            if (stacked) {
                setPadding(stackedEndPadding, getPaddingTop(), stackedEndPadding, getPaddingBottom());
            } /* Else the padding was properly reset by the drawable */

            this.stacked = stacked;
        }
    }

    public void setStackedGravity(GravityEnum gravity) {
        stackedGravity = gravity;
    }

    public void setStackedSelector(Drawable d) {
        stackedBackground = d;
        if (stacked) {
            setStacked(true, true);
        }
    }

    public void setDefaultSelector(Drawable d) {
        defaultBackground = d;
        if (!stacked) {
            setStacked(false, true);
        }
    }


}
