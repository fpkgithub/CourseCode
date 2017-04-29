package com.course.entity;

public class Building
{
	private int bid;

	private String bnum;

	private String canum;
	
	private String bname;

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBnum() {
		return bnum;
	}

	public void setBnum(String bnum) {
		this.bnum = bnum;
	}

	public String getCanum() {
		return canum;
	}

	public void setCanum(String canum) {
		this.canum = canum;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public Building(String bnum, String canum, String bname) {
		super();
		this.bnum = bnum;
		this.canum = canum;
		this.bname = bname;
	}
	
}
