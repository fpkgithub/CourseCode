package com.test.com.courseapp;

/**
 * Created by XJTUSE-PC on 2017/4/14.
 */
//课程
public class Course
{

    public static final String TABLE_NAME = "Courseinfo";

    //属性
    public int cid; //确定星期
    public String sid;//
    public String tid;//确定课表唯一

    public String getCdesc()
    {
        return cdesc;
    }

    public void setCdesc(String cdesc)
    {
        this.cdesc = cdesc;
    }

    public String cdesc;
    public String cone;
    public String ctwo;
    public String cthree;
    public String cfour;
    public String cfive;

    public Course()
    {
    }

    public Course(int cid, String sid, String tid, String cdesc, String cone, String ctwo, String cthree, String cfour, String cfive)
    {
        this.cid = cid;
        this.sid = sid;
        this.tid = tid;
        this.cdesc = cdesc;
        this.cone = cone;
        this.ctwo = ctwo;
        this.cthree = cthree;
        this.cfour = cfour;
        this.cfive = cfive;
    }

    public int getCid()
    {
        return cid;
    }

    public void setCid(int cid)
    {
        this.cid = cid;
    }

    public String getSid()
    {
        return sid;
    }

    public void setSid(String sid)
    {
        this.sid = sid;
    }

    public String getTid()
    {
        return tid;
    }

    public void setTid(String tid)
    {
        this.tid = tid;
    }

    public String getCone()
    {
        return cone;
    }

    public void setCone(String cone)
    {
        this.cone = cone;
    }

    public String getCtwo()
    {
        return ctwo;
    }

    public void setCtwo(String ctwo)
    {
        this.ctwo = ctwo;
    }

    public String getCthree()
    {
        return cthree;
    }

    public void setCthree(String cthree)
    {
        this.cthree = cthree;
    }

    public String getCfour()
    {
        return cfour;
    }

    public void setCfour(String cfour)
    {
        this.cfour = cfour;
    }

    public String getCfive()
    {
        return cfive;
    }

    public void setCfive(String cfive)
    {
        this.cfive = cfive;
    }
}
