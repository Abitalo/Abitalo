package com.abitalo.www.noteme;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lancelot on 2015/11/29.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public DatabaseOpenHelper(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists TUSER(_ID integer primary key autoincrement,USERNAME text not null unique,PASSWORD varchar(20) not null)");
        db.execSQL("create table if not exists TALARM(_ID integer primary key autoincrement,USERNAME text not null,TIME bigint not null,EVENT text not null,FOREIGN KEY (USERNAME) REFERENCES TUSER(USERNAME))");
        db.execSQL("create table if not exists TMOOD(_ID integer primary key autoincrement,USERNAME text not null,DATE bigint not null,MOOD text not null,FOREIGN KEY (USERNAME) REFERENCES TUSER(USERNAME))");
        db.execSQL("create table if not exists TDIARY(_ID integer primary key autoincrement,USERNAME text not null,TITLE VARCHAR(100) not null,CONTENT VARCHAR(3000),DATE bigint not null,FOREIGN KEY (USERNAME) REFERENCES TUSER(USERNAME))");
        ContentValues values=new ContentValues();

        values.put("USERNAME","abitalo");
        values.put("PASSWORD", "131320119");
        db.insert("user", null, values);
    }

    @Override
         public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
