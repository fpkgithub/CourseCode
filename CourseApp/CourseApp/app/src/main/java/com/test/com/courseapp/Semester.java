package com.test.com.courseapp;

/**
 * Created by XJTUSE-PC on 2017/4/15.
 */
//学期
public class Semester
{

    public static final String TABLE_NAME = "Semesterinfo";

    //属性
    public String sid;

    public String sname;

    public Semester()
    {
    }

    public Semester(String sid, String sname)
    {
        this.sid = sid;
        this.sname = sname;
    }

    public String getSid()
    {
        return sid;
    }

    public void setSid(String sid)
    {
        this.sid = sid;
    }

    public String getSname()
    {
        return sname;
    }

    public void setSname(String sname)
    {
        this.sname = sname;
    }
}
