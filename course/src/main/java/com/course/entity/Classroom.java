package com.course.entity;

public class Classroom
{
	private int crid;

	private String crnum;

	private String bnum;
	
	private String crname;

	public int getCrid() {
		return crid;
	}

	public void setCrid(int crid) {
		this.crid = crid;
	}

	public String getCrnum() {
		return crnum;
	}

	public void setCrnum(String crnum) {
		this.crnum = crnum;
	}

	public String getBnum() {
		return bnum;
	}

	public void setBnum(String bnum) {
		this.bnum = bnum;
	}

	public String getCrname() {
		return crname;
	}

	public void setCrname(String crname) {
		this.crname = crname;
	}

	public Classroom(String crnum, String bnum, String crname) {
		super();
		this.crnum = crnum;
		this.bnum = bnum;
		this.crname = crname;
	}
	

	

}
