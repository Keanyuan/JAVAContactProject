package com.anjiplus.gip.dao;

/**
 * 将账务中的查询条件封装成对象
 * @author KeanQ
 *
 */
public class QueryForm {
	private String begin;
	private String end;
	private String parent;
	private String sname;
	
	public QueryForm() {
		super();
	}
	public QueryForm(String begin, String end, String parent, String sname) {
		super();
		this.begin = begin;
		this.end = end;
		this.parent = parent;
		this.sname = sname;
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	@Override
	public String toString() {
		return "QueryForm [begin=" + begin + ", end=" + end + ", parent=" + parent + ", sname=" + sname + "]";
	}
	
}
