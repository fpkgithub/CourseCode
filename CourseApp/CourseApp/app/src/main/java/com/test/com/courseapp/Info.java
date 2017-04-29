package com.test.com.courseapp;

/**
 * Created by Administrator on 2017/4/28.
 */

public class Info
{
    private int id; //信息ID
    private String title;   //信息标题
    private String details; //详细信息
    private int avatar; //图片ID

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public int getAvatar()
    {
        return avatar;
    }

    public void setAvatar(int avatar)
    {
        this.avatar = avatar;
    }
}
