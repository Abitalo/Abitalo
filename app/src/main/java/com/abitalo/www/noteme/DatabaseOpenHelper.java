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
        db.execSQL("create table if not exists author(_id integer primary key autoincrement,name text not null,stu_num bigint not null)");
        ContentValues values=new ContentValues();

        values.put("name","冯超唯");
        values.put("stu_num", 131320119);
        db.insert("author", null, values);

        values.clear();

        values.put("name","陈佳");
        values.put("stu_num", 131320120);
        db.insert("author", null, values);
    }

    @Override
         public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
