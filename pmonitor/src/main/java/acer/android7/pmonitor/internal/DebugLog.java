package acer.android7.pmonitor.internal;

import android.util.Log;

/**
 * Created by xiaozheng on 2017/2/18.
 */

public class DebugLog {
    private DebugLog() {}

    public static void log(String tag, String message) {
        Log.i(tag, message);
    }
}
