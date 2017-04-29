package com.test.com.courseapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/4/16.
 */

public class DBHelper extends SQLiteOpenHelper
{

    private static final String DB_NAME = "course.db";//数据库名称
    private static final int DB_VERSION = 1;//数据库版本

    // private static final String TABLE_NAME = "courseinfo";//数据表名称
    //***********************************************************************

    Course course = new Course();

    private static String KEY_CID= "cid";
    private static String KEY_DESC = "cdesc";
    private static String KEY_MON= "cone";
    private static String KEY_TUES = "ctwo";
    private static String KEY_WED = "cthree";
    private static String KEY_THURS = "cfour";
    private static String KEY_FRI = "cfive";

    //***********************************************************************

    Teacher teacher;
    private static String KEY_TID0 = "tid0";
    private static String KEY_TID = "tid";
    private static String KEY_TNAME = "tname";

    //***********************************************************************

    Semester semester;
    private static String KEY_SID = "sid";
    private static String KEY_SNAME = "sname";

    //***********************************************************************

    //创建数据库
    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        final String sqlStr1 = "create table if not exists " + course.TABLE_NAME + " ("+KEY_CID +" integer PRIMARY KEY AUTOINCREMENT, "+ KEY_SID + " text, " + KEY_TID + " text ," + KEY_MON + " text , " + KEY_TUES + " text," + KEY_WED + " text ," + KEY_THURS + " text, " + KEY_FRI + " text, " +KEY_DESC+" text);";
        db.execSQL(sqlStr1);
        final String sqlStr2 = "create table if not exists " + teacher.TABLE_NAME + " (" + KEY_TID0 +" integer PRIMARY KEY AUTOINCREMENT, " + KEY_TID + " text , " + KEY_TNAME + " text not null); ";
        db.execSQL(sqlStr2);
        final String sqlStr3 = "create table if not exists " + semester.TABLE_NAME + " (" + KEY_SID + " text primary key, " + KEY_SNAME + " text not null);";
        db.execSQL(sqlStr3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        final String sqlStr1 = "DROP TABLE IF EXISTS " + course.TABLE_NAME;
        db.execSQL(sqlStr1);
        final String sqlStr2 = "DROP TABLE IF EXISTS " + teacher.TABLE_NAME;
        db.execSQL(sqlStr2);
        final String sqlStr3 = "DROP TABLE IF EXISTS " + semester.TABLE_NAME;
        db.execSQL(sqlStr3);
        onCreate(db);

    }

    //***********************************************************************


}
