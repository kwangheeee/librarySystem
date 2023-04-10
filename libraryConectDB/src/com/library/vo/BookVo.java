package com.library.vo;

public class BookVo {
	int bookNo;
	String title;
	String author;
	String rentYN;
	
	
	public BookVo(int bookNo, String title, String author, String rentYN) {
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
		this.rentYN = rentYN;
	}

	public BookVo(){
		
	}
	
	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getRentYN() {
		return rentYN;
	}

	public void setRentYN(String rentYN) {
		this.rentYN = rentYN;
	}
	
	
	public String toString() {
		return getBookNo() + " " +getTitle() + " " +getAuthor() + " " + (getRentYN().equalsIgnoreCase("Y")? "대여중.." : "대여 가능");
	}
	
	public String info() {
		
		return "";
	}
}
