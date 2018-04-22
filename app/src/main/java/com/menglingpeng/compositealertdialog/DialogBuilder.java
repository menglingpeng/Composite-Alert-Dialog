package com.menglingpeng.compositealertdialog;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;

import com.menglingpeng.compositealertdialog.Util.GravityEnum;

import java.util.ArrayList;

/**
 * Created by mengdroid on 2018/4/15.
 */

public  class DialogBuilder {

    protected Context context;
    protected CharSequence title;
    public View customView;
    protected GravityEnum titleGravity = GravityEnum.START;
    protected GravityEnum contentGravity = GravityEnum.START;
    protected GravityEnum btnStackedGravity = GravityEnum.END;
    protected GravityEnum itemsGravity = GravityEnum.START;
    protected GravityEnum buttonsGravity = GravityEnum.START;

    protected ArrayList<CharSequence> items;

    protected ListLongCallback listLongCallback;
    private InputCallback inputCallback;

    protected CharSequence positiveText;
    protected CharSequence neutralText;
    protected CharSequence negativeText;

    protected int listSelector;

    public DialogBuilder(Context context){
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
