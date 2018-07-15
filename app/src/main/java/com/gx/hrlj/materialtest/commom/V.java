package com.gx.hrlj.materialtest.commom;

import android.app.Activity;
import android.view.View;
/*
 * 简化你的findViewById
 */
public class V {
	/**
     * activity.findViewById()
     * @param context
     * @param id
     */
    public static <T extends View> T f(Activity context, int id) {
        return (T) context.findViewById(id);
    }

    /**
     * rootView.findViewById()
     * @param rootView
     * @param id
     */
    public static <T extends View> T f(View rootView, int id) {
        return (T) rootView.findViewById(id);
    }
}
