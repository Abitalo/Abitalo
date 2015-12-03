package com.abitalo.www.noteme.alarm;

/**
 * Created by asus on 2015/12/2.
 */
public class ClickUtil {
    private static long lastClickTime;
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 2000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
