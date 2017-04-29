package com.course.entity;

public class Campus
{
	private int caid;

	private String canum;

	private String caname;

	public int getCaid() {
		return caid;
	}

	public void setCaid(int caid) {
		this.caid = caid;
	}

	public String getCanum() {
		return canum;
	}

	public void setCanum(String canum) {
		this.canum = canum;
	}

	public String getCaname() {
		return caname;
	}

	public void setCaname(String caname) {
		this.caname = caname;
	}

	public Campus(String canum, String caname) {
		super();
		this.canum = canum;
		this.caname = caname;
	}
	

}
