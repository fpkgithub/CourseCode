package com.test.com.courseapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by XJTUSE-PC on 2017/4/14.
 */

public class DBCourse extends SQLiteOpenHelper {


   // private static final String TABLE_NAME = "courseinfo";//数据表名称

    Course course=new Course();

    private static String KEY_TID = "tid";
    private static String KEY_SID = "sid";
    private static String KEY_MON="mon";
    private static String KEY_TUES="tues";
    private static String KEY_WED="wed";
    private static String KEY_THURS="thurs";
    private static String KEY_FRI="fri";
    private static String KEY_SAT="sat";
    private static String KEY_SUN="sun";


    public DBCourse(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sqlStr1 = "create table if not exists " + course.TABLE_NAME + " (" +KEY_SID + " integer, " + KEY_TID + " integer ," + KEY_MON + " text , "
                + KEY_TUES +" text,"+ KEY_WED +" text ,"+ KEY_THURS +" text, "+ KEY_FRI +"text,"+KEY_SAT+"text,"+KEY_SUN+"text, primary key("+KEY_TID+","+KEY_SID+"));";
        db.execSQL(sqlStr1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String sqlStr = "DROP TABLE IF EXISTS " + course.TABLE_NAME;
        db.execSQL(sqlStr);
        onCreate(db);
    }
}