package com.course.entity;

public class ClassCourseTable {

	private int accid;
	private String sid;
	private String acnum;
	private String cdescr;
	private String classone;
	private String classtwo;
	private String classthree;
	private String classfour;
	private String classfive;
	public int getAccid() {
		return accid;
	}
	public void setAccid(int accid) {
		this.accid = accid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getAcnum() {
		return acnum;
	}
	public void setAcnum(String acnum) {
		this.acnum = acnum;
	}
	public String getCdescr() {
		return cdescr;
	}
	public void setCdescr(String cdescr) {
		this.cdescr = cdescr;
	}
	public String getClassone() {
		return classone;
	}
	public void setClassone(String classone) {
		this.classone = classone;
	}
	public String getClasstwo() {
		return classtwo;
	}
	public void setClasstwo(String classtwo) {
		this.classtwo = classtwo;
	}
	public String getClassthree() {
		return classthree;
	}
	public void setClassthree(String classthree) {
		this.classthree = classthree;
	}
	public String getClassfour() {
		return classfour;
	}
	public void setClassfour(String classfour) {
		this.classfour = classfour;
	}
	public String getClassfive() {
		return classfive;
	}
	public void setClassfive(String classfive) {
		this.classfive = classfive;
	}
	public ClassCourseTable(String sid, String acnum, String cdescr, String classone, String classtwo,
			String classthree, String classfour, String classfive) {
		super();
		this.sid = sid;
		this.acnum = acnum;
		this.cdescr = cdescr;
		this.classone = classone;
		this.classtwo = classtwo;
		this.classthree = classthree;
		this.classfour = classfour;
		this.classfive = classfive;
	}
	
	
	
}
