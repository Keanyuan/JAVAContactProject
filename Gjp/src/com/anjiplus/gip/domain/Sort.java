package com.anjiplus.gip.domain;

public class Sort {
	private int sid;
	private String sname;
	private String parent;
	private String sdesc;
	
	public Sort(String sname, String parent, String sdesc) {
		super();
		this.sname = sname;
		this.parent = parent;
		this.sdesc = sdesc;
	}
	public Sort(int sid, String sname, String parent, String sdesc) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.parent = parent;
		this.sdesc = sdesc;
	}
	public Sort() {
		super();
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getSdesc() {
		return sdesc;
	}
	public void setSdesc(String sdesc) {
		this.sdesc = sdesc;
	}
	@Override
	public String toString() {
		return "Sort [sid=" + sid + ", sname=" + sname + ", parent=" + parent + ", sdesc=" + sdesc + "]";
	}
	
	
	
}
