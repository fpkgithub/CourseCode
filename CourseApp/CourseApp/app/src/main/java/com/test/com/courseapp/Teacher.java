package com.test.com.courseapp;

/**
 * Created by XJTUSE-PC on 2017/4/14.
 */
//教师
public class Teacher
{

    public static final String TABLE_NAME = "Teacherinfo";

    //属性

    public int tid0;
    public String tid;
    public String tname;

    public Teacher()
    {
    }

    public Teacher(String tid, String tname, int tid0)
    {
        this.tid = tid;
        this.tname = tname;
        this.tid0 = tid0;
    }

    public int getTid0()
    {
        return tid0;
    }

    public void setTid0(int tid0)
    {
        this.tid0 = tid0;
    }

    public String getTid()
    {
        return tid;
    }

    public void setTid(String tid)
    {
        this.tid = tid;
    }

    public String getTname()
    {
        return tname;
    }

    public void setTname(String tname)
    {
        this.tname = tname;
    }
}
