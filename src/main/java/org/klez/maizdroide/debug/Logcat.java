package org.klez.maizdroide.debug;

import android.util.Log;

/**
 * Created by klez on 20/10/15.
 */
public class Logcat {
    private static String TAG = "MAIZ";
    private static boolean ENABLED = true;

    public static void initialize(String tag,boolean enabled){
        TAG = tag;
        ENABLED = enabled;
    }

    public static void debug(String msg){
        Log.i(TAG,msg);
    }

    public static void exception(String where,Exception e){
        Log.e(TAG,where,e);
    }
}
