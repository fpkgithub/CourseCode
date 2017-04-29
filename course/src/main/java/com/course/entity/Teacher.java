package com.course.entity;

public class Teacher
{
	private int tid;

	private String tnum;

	private String tname;
	

	public Teacher(String tnum, String tname) {
		super();
		this.tnum = tnum;
		this.tname = tname;
	}

	public Teacher(int tid, String tnum, String tname)
	{
		super();
		this.tid = tid;
		this.tnum = tnum;
		this.tname = tname;
	}

	public int getTid()
	{
		return tid;
	}

	public void setTid(int tid)
	{
		this.tid = tid;
	}

	public String getTnum()
	{
		return tnum;
	}

	public void setTnum(String tnum)
	{
		this.tnum = tnum;
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
