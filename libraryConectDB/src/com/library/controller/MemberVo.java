package com.library.controller;

public class MemberVo {
	String id;
	String pw;
	String name;
	String adminYN; // Y : 관리자 계정
	
	
	public MemberVo(String id, String pw, String name, String adminYN) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.adminYN = adminYN; 
	}


	public MemberVo(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAdminYN() {
		return adminYN;
	}


	public void setAdminYN(String adminYN) {
		this.adminYN = adminYN;
	}
	
	@Override
	public String toString () {
		return getId() + " " + getName() + " " + getAdminYN();
	}
}