package com.test.com.courseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XJTUSE-PC on 2017/4/15.
 */

public class SemesterDao
{

    private static final String DB_NAME = "course.db";//数据库名称
    private static final String TABLE_NAME = "semesterinfo";//数据表名称
    private static final int DB_VERSION = 1;//数据库版本

    //表的字段名
    private static String KEY_ID = "sid";
    private static String KEY_NAME = "sname";

    private SQLiteDatabase mDatabase;
    private Context mContext;
    private DBHelper mDbOpenHelper;//数据库打开帮助类

    public SemesterDao(Context context)
    {
        mDbOpenHelper = new DBHelper(context);
        //getWriteableDatabase()内部调用了
        // mContext.openOrCreateDatabase(mName, 0, mFactory);
        mDatabase = mDbOpenHelper.getWritableDatabase();
    }

//    public SemesterDao(Context context)
//    {
//        mContext = context;
//    }

//    //打开数据库
//    public void openDataBase()
//    {
//        mDbOpenHelper = new DBCourse(mContext, DB_NAME, null, DB_VERSION);
//        try
//        {
//            mDatabase = mDbOpenHelper.getWritableDatabase();//获取可写数据库
//        } catch (SQLException e)
//        {
//            mDatabase = mDbOpenHelper.getReadableDatabase();//获取只读数据库
//        }
//    }

    //关闭数据库
    public void closeDataBase()
    {
        if (mDatabase != null)
        {
            mDatabase.close();
        }
    }

    //插入一条数据
    public long insertData(Semester semester)
    {
        long temp = 0;
        mDatabase.beginTransaction();

        try
        {
            ContentValues values = new ContentValues();
            values.put(KEY_ID, semester.getSid());
            values.put(KEY_NAME, semester.getSname());
            temp = mDatabase.insert(TABLE_NAME, null, values);
            mDatabase.setTransactionSuccessful();
            mDatabase.endTransaction();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return temp;
    }

    //删除一条数据
    public long deleteData(long id)
    {

        return mDatabase.delete(TABLE_NAME, KEY_ID + "=" + id, null);
    }

    //删除所有数据
    public long deleteAllData()
    {

        return mDatabase.delete(TABLE_NAME, null, null);
    }

    //更新一条数据
    public long updateData(long id, Semester semester)
    {

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, semester.getSname());
        return mDatabase.update(TABLE_NAME, values, KEY_ID + "=" + id, null);
    }

    //查询一条数据
    public List<Semester> queryData(long id)
    {
        Cursor results = mDatabase.query(TABLE_NAME, new String[]{KEY_ID, KEY_NAME}, KEY_ID + "=" + id, null, null, null, null);
        return convertToSemester(results);
    }

    //查询所有数据
    public int queryDataList(String name)
    {
        Cursor results = mDatabase.query(name, new String[]{KEY_ID, KEY_NAME}, null, null, null, null, null);
        int resultCounts = results.getCount();
        return resultCounts;
    }

    //查询所有数据
    public List<Semester> queryDataList2(String name)
    {
        Cursor results = mDatabase.query(name, new String[]{KEY_ID, KEY_NAME}, null, null, null, null, null);
        return convertToSemester(results);
    }

    private List<Semester> convertToSemester(Cursor cursor)
    {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst())
        {
            return null;
        }
        List<Semester> semestersList = new ArrayList<>();
        for (int i = 0; i < resultCounts; i++)
        {
            Semester semester = new Semester();
            semester.setSid(cursor.getString(0));
            semester.setSname(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            semestersList.add(semester);
            cursor.moveToNext();
        }
        return semestersList;

    }

}
