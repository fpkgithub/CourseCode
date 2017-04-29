package com.course.entity;

public class Semester
{
	private String sid;

	private String sname;

	public String getSid()
	{
		return sid;
	}

	public Semester(String sid, String sname)
	{
		super();
		this.sid = sid;
		this.sname = sname;
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
