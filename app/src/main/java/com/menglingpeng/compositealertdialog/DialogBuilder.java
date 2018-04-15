package com.menglingpeng.compositealertdialog;

import android.content.Context;

import com.menglingpeng.compositealertdialog.Util.GravityEnum;

/**
 * Created by mengdroid on 2018/4/15.
 */

public  class DialogBuilder {

    protected Context context;
    protected CharSequence title;
    protected GravityEnum titleGravity = GravityEnum.START;
    protected GravityEnum contentGravity = GravityEnum.START;
    protected GravityEnum btnStackedGravity = GravityEnum.END;
    protected GravityEnum itemsGravity = GravityEnum.START;
    protected GravityEnum buttonsGravity = GravityEnum.START;

    protected CharSequence positiveText;
    protected CharSequence neutralText;
    protected CharSequence negativeText;

    public DialogBuilder(Context context){
        this.context = context;
    }
}
