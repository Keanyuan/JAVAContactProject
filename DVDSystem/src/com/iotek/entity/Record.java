package com.iotek.entity;

public class Record {
	private int id;
	private int uid;
	private int did;
	private String lendTime;
	private String returnTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setuid(int uid) {
		this.uid = uid;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getLendTime() {
		return lendTime;
	}
	public void setLendTime(String lendTime) {
		this.lendTime = lendTime;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public Record(int id, int uid, int did, String lendTime, String returnTime) {
		super();
		this.id = id;
		this.uid = uid;
		this.did = did;
		this.lendTime = lendTime;
		this.returnTime = returnTime;
	}
	public Record(int uid, int did, String lendTime, String returnTime) {
		super();
		this.uid = uid;
		this.did = did;
		this.lendTime = lendTime;
		this.returnTime = returnTime;
	}
	public Record() {
	}
	


}
