package com.gx.hrlj.materialtest.commom;

import android.util.Log;

/**
 * @author  开发阶段将下面LOG_LEVEL设置为6,在发布的时候我们将LOG_LEVE设置为0
 */
public class YLog {

	public static int LOG_LEVEL = 6;
	public static int ERROR = 1;
	public static int WARN = 2;
	public static int INFO = 3;
	public static int DEBUG = 4;
	public static int VERBOS = 5;

	public static void e(String tag, String msg) {
		if (LOG_LEVEL > ERROR)
			Log.e(tag, msg);
	}

	public static void w(String tag, String msg) {
		if (LOG_LEVEL > WARN)
			Log.w(tag, msg);
	}

	public static void i(String tag, String msg) {
		if (LOG_LEVEL > INFO)
			Log.i(tag, msg);
	}

	public static void d(String tag, String msg) {
		if (LOG_LEVEL > DEBUG)
			Log.d(tag, msg);
	}

	public static void v(String tag, String msg) {
		if (LOG_LEVEL > VERBOS)
			Log.v(tag, msg);
	}
}