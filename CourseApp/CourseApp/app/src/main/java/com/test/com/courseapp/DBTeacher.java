package com.test.com.courseapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/4/16.
 */

public class DBTeacher extends SQLiteOpenHelper
{

    Teacher teacher;


    private static String KEY_ID = "tid";
    private static String KEY_NAME = "tname";
    private static String KEY_DESC = "desc";

    public DBTeacher(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        final String sqlStr1 = "create table if not exists " + teacher.TABLE_NAME + " (" + KEY_ID + " integer primary key, " + KEY_NAME + " text not null," + KEY_DESC + " text); ";
        db.execSQL(sqlStr1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        final String sqlStr = "DROP TABLE IF EXISTS " + teacher.TABLE_NAME;
        db.execSQL(sqlStr);
        onCreate(db);
    }


}
