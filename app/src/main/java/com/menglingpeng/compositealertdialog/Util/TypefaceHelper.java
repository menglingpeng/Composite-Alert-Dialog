package com.menglingpeng.compositealertdialog.Util;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.SimpleArrayMap;

/**
 * Created by mengdroid on 2018/5/1.
 */

public class TypefaceHelper {

    private static final SimpleArrayMap<String, Typeface> cache = new SimpleArrayMap<>();

    public static Typeface get(Context c, String name) {
        synchronized (cache) {
            if (!cache.containsKey(name)) {
                try {
                    Typeface t = Typeface.createFromAsset(c.getAssets(), String.format("fonts/%s", name));
                    cache.put(name, t);
                    return t;
                } catch (RuntimeException e) {
                    return null;
                }
            }
            return cache.get(name);
        }
    }
}
