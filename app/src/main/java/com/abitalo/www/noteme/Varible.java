package com.abitalo.www.noteme;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by asus on 2015/11/30.
 */
public class Varible {
    public static boolean hasHandler = false;
    public static final int VALIDATE_CLOCK = 1;
    public static SQLiteDatabase db;
    public static String user;

    public static void changeHasHandler(boolean state){
        hasHandler = state;
    }
}
