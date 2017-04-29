package com.test.com.courseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XJTUSE-PC on 2017/4/14.
 */

public class CourseDao
{

    private static final String DB_NAME = "course.db";//数据库名称
    private static final String TABLE_NAME = "courseinfo";//数据表名称
    //  private static final int DB_VERSION = 1;//数据库版本

    //表的字段名
    private static String KEY_TID = "tid";
    private static String KEY_CID = "cid";
    private static String KEY_SID = "sid";
    private static String KEY_MON = "cone";
    private static String KEY_TUES = "ctwo";
    private static String KEY_WED = "cthree";
    private static String KEY_THURS = "cfour";
    private static String KEY_FRI = "cfive";
    private static String KEY_DESC = "cdesc";

    private SQLiteDatabase mDatabase;
    private Context mContext;
    private DBHelper mDbOpenHelper;//数据库打开帮助类

    public CourseDao(Context context)
    {
        mDbOpenHelper = new DBHelper(context);
        //getWriteableDatabase()内部调用了
        // mContext.openOrCreateDatabase(mName, 0, mFactory);
        mDatabase = mDbOpenHelper.getWritableDatabase();
    }


    //    public CourseDao(Context context) {
    //        mContext = context;
    //    }

    //打开数据库
    //    public void openDataBase() {
    //        mDbOpenHelper = new DBCourse(mContext, DB_NAME, null, DB_VERSION);
    //        try {
    //            mDatabase = mDbOpenHelper.getWritableDatabase();//获取可写数据库
    //        } catch (SQLException e) {
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
    public long insertData(Course course)
    {

        long temp = 0;
        mDatabase.beginTransaction();
        try
        {

            ContentValues values = new ContentValues();
            values.put(KEY_TID, course.getTid());
            values.put(KEY_SID, course.getSid());
            values.put(KEY_MON, course.getCone());
            values.put(KEY_TUES, course.getCtwo());
            values.put(KEY_WED, course.getCthree());
            values.put(KEY_THURS, course.getCfour());
            values.put(KEY_FRI, course.getCfive());
            values.put(KEY_DESC, course.getCdesc());
            temp = mDatabase.insert(TABLE_NAME, null, values);
            mDatabase.setTransactionSuccessful();
            mDatabase.endTransaction();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return temp;
    }

    //    //删除一条数据
    //    public long deleteData(long id) {
    //
    //        return mDatabase.delete(TABLE_NAME, KEY_ID + "=" + id, null);
    //    }
    //
    //    //删除所有数据
    //    public long deleteAllData() {
    //
    //        return mDatabase.delete(TABLE_NAME, null, null);
    //    }

    //    //更新一条数据
    //    public long updateData(long id, Course course) {
    //
    //        ContentValues values = new ContentValues();
    //        values.put(KEY_NAME, course.getCname());
    //        return mDatabase.update(TABLE_NAME, values, KEY_ID + "=" + id, null);
    //    }

    //查询一条数据
    public int queryData(String sid, String tid)
    {
        Cursor results = mDatabase.query(TABLE_NAME, new String[]{KEY_CID, KEY_SID, KEY_TID, KEY_MON, KEY_TUES, KEY_WED, KEY_THURS, KEY_FRI,KEY_DESC}, KEY_TID + "='" + tid + "' and " + KEY_SID + "='" + sid+"'", null, null, null, null);
        int temp = results.getCount();
        System.out.println("temp&&:"+temp);
        return temp;
    }

    //查询一条数据
    public List<Course> queryData2(String sid, String tid)
    {
        Cursor results = mDatabase.query(TABLE_NAME, new String[]{KEY_CID, KEY_SID, KEY_TID, KEY_MON, KEY_TUES, KEY_WED, KEY_THURS, KEY_FRI,KEY_DESC}, KEY_TID + "='" + tid + "' and " + KEY_SID + "='" + sid+"'", null, null, null, null);
        return convertToCourse(results);
    }

    //查询所有数据
    public int queryDataList()
    {
        Cursor results = mDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        return results.getCount();
    }
    public int queryDataList2()
    {
        Cursor results = mDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        return convertToCourse(results).size();
    }

    private List<Course> convertToCourse(Cursor cursor)
    {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst())
        {
            return null;
        }
        List<Course> mTreeList = new ArrayList<>();
        for (int i = 0; i < resultCounts; i++)
        {
            Course course = new Course();
            course.setSid(cursor.getString(cursor.getColumnIndex(KEY_SID)));
            course.setTid(cursor.getString(cursor.getColumnIndex(KEY_TID)));
            course.setCone(cursor.getString(cursor.getColumnIndex(KEY_MON)));
            course.setCtwo(cursor.getString(cursor.getColumnIndex(KEY_TUES)));
            course.setCthree(cursor.getString(cursor.getColumnIndex(KEY_WED)));
            course.setCfour(cursor.getString(cursor.getColumnIndex(KEY_THURS)));
            course.setCfive(cursor.getString(cursor.getColumnIndex(KEY_FRI)));
            course.setCdesc(cursor.getString(cursor.getColumnIndex(KEY_DESC)));
            //  course.setCname(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            mTreeList.add(course);
            cursor.moveToNext();
        }
        return mTreeList;

    }
}
