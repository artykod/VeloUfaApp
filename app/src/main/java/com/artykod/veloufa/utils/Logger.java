package com.artykod.veloufa.utils;

import android.util.Log;

public class Logger {
    private static final String TAG = "VeloUfa";

    public static void log(String message) {
        Log.v(TAG, message);
    }

    public static void warning(String message) {
        Log.w(TAG, message);
    }

    public static void error(String message) {
        Log.e(TAG, message);
    }
}
