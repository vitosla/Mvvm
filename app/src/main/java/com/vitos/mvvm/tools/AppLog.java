package com.vitos.mvvm.tools;

import android.util.Log;

import com.vitos.mvvm.BuildConfig;

/**
 * Created by Victor on 05.06.2017.
 */

public class AppLog {

    private static final String TAG = "MvvmApp  :";

    public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, msg == null ? "" : msg);
        }
    }

    public static void w(String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(TAG, msg == null ? "" : msg);
        }
    }

    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, msg == null ? "" : msg);
        }
    }

    public static void i(String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, msg == null ? "" : msg);
        }
    }
}
