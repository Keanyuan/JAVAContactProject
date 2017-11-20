package com.iotek.entity;

public class DVD {
	private int id;
	private String dname;
	private Integer dcount;
	private int status;
	public DVD() {
	}
	public DVD(String dname, Integer dcount, int status) {
		super();
		this.dname = dname;
		this.dcount = dcount;
		this.status = status;
	}
	public DVD(int id, String dname, Integer dcount, int status) {
		super();
		this.id = id;
		this.dname = dname;
		this.dcount = dcount;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public Integer getDcount() {
		return dcount;
	}
	public void setDcount(Integer dcount) {
		this.dcount = dcount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	



}
