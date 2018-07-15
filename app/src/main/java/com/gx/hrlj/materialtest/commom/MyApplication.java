package com.gx.hrlj.materialtest.commom;

import android.app.Application;
import android.content.Context;

/**
 * Created by 777 on 2018/7/14.
 */
public class MyApplication extends Application {

    public static Context context;
    public static MyApplication instance = null;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public synchronized static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }
}
