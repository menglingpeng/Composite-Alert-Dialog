package com.menglingpeng.compositealertdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.menglingpeng.compositealertdialog.Util.CDButton;
import com.menglingpeng.compositealertdialog.Util.DialogActionEnum;
import com.menglingpeng.compositealertdialog.Util.DialogUtil;
import com.menglingpeng.compositealertdialog.Util.GravityEnum;

import java.util.ArrayList;

/**
 * Created by mengdroid on 2018/4/5.
 */

public class CompositeAlertDialog extends BaseDialog implements View.OnClickListener {

    protected Context context;
    public DialogBuilder builder;
    public  View view;
    public ImageView iconIv;
    public TextView titleTv;
    public TextView contentTv;
    public View titleFrame;
    public RecyclerView recyclerView;
    private EditText inputEt;
    public ProgressBar progressBar;
    public CheckBox checkBoxPrompt;
    public CDButton positiveButton;
    public CDButton neutralButton;
    public CDButton negativeButton;
    private String titleText;
    private String contentText;
    private boolean showCancel;
    private boolean showContent;
    private String cancelText;
    private String confirmText;
    private int alertType;

    public static final int TYPE_NOMAL = 0;
    public static final int TYPE_SUCCESS = 1;
    public static final int TYPE_ERROR = 2;

    public CompositeAlertDialog(@NonNull Context context) {
        super(context, TYPE_NOMAL);
        this.context = context;
    }

    final Drawable getListSelector() {
        if (builder.listSelector != 0) {
            return ResourcesCompat.getDrawable(getContext().getResources(), builder.listSelector, null);
        }
        final Drawable d = DialogUtil.resolveDrawable(getContext(), R.attr.md_list_selector);
        if (d != null) {
            return d;
        }
        return DialogUtil.resolveDrawable(getContext(), R.attr.md_list_selector);
    }

    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public final CDButton getActionButton(DialogActionEnum which) {
        switch (which) {
            case NEUTRAL:
                return neutralButton;
            case NEGATIVE:
                return negativeButton;
            default:
                return positiveButton;
        }
    }

    @Override
    public void onClick(View v) {

    }

    public final View getView() {
        return dialogView;
    }

    @Nullable
    public final EditText getInputEditText() {
        return inputEt;
    }

    public final TextView getTitleView() {
        return titleTv;
    }

    public ImageView getIconView() {
        return iconIv;
    }

    public final void setActionButton(final DialogActionEnum which, @Nullable final CharSequence title) {
        switch (which) {
            default:
                builder.positiveText = title;
                positiveButton.setText(title);
                positiveButton.setVisibility(title == null ? View.GONE : View.VISIBLE);
                break;
            case NEUTRAL:
                builder.neutralText = title;
                neutralButton.setText(title);
                neutralButton.setVisibility(title == null ? View.GONE : View.VISIBLE);
                break;
            case NEGATIVE:
                builder.negativeText = title;
                negativeButton.setText(title);
                negativeButton.setVisibility(title == null ? View.GONE : View.VISIBLE);
                break;
        }
    }

    @Nullable
    public final TextView getContentView() {
        return contentTv;
    }

    public final Context getContext() {
        return context;
    }

    private static void setInputDialog(){

    }

    @Override
    public void onShow(DialogInterface dialog) {
        super.onShow(dialog);
    }

    @Override
    public void dismiss() {
        if(inputEt != null) {
            DialogUtil.hideKeyboard(this);
        }
        super.dismiss();
    }

    public static interface OnCompositeClickListener{
        void onClick(CompositeAlertDialog compositeAlertDialog);
    }

    public interface InputCallback {
        void onInput(CompositeAlertDialog dialog, CharSequence input);
    }

    public static class DialogBuilder {

        protected Context context;
        protected CharSequence title;
        public View customView;
        protected GravityEnum titleGravity = GravityEnum.START;
        protected GravityEnum contentGravity = GravityEnum.START;
        protected GravityEnum btnStackedGravity = GravityEnum.END;
        protected GravityEnum itemsGravity = GravityEnum.START;
        protected GravityEnum buttonsGravity = GravityEnum.START;

        public ArrayList<CharSequence> items;
        public CharSequence checkBoxPrompt;

        public int progress = -2;

        protected ListLongCallback listLongCallback;
        public InputCallback inputCallback;

        protected CharSequence positiveText;
        protected CharSequence neutralText;
        protected CharSequence negativeText;

        public boolean indeterminateProgress;
        public boolean indeterminateIsHorizontalProgress;

        protected int listSelector;

        public DialogBuilder(Context context) {
            this.context = context;
        }

        public interface ListLongCallback {
            boolean onLongSelection(CompositeAlertDialog dialog, View itemView, int position, CharSequence text);
        }


        public DialogBuilder listSelector(@DrawableRes int selectorRes) {
            this.listSelector = selectorRes;
            return this;
        }
    }

}
