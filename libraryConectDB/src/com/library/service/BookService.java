package com.library.service;

import java.sql.Connection;
import java.util.List;

import com.library.dao.BookDao;
import com.library.dao.conn.DBUtill;
import com.library.vo.BookVo;

public class BookService {
	
	BookDao bookDao = new BookDao();
	
	
	public void getList() {
		List<BookVo> list = bookDao.getList();
		for(BookVo book : list) {
			System.out.println(book.toString());
		}
	}
	
	/**
	 * 책이 rent 가능한지 확인
	 * 가능 -> 대여
	 * 불가능 -> 메세지 처리
	 * @param bookNo
	 * @return
	 */
	public void retrunBook(int bookNo) {
		boolean flg = selStatus(bookNo,"Y");
		if(flg) {
			int res = bookDao.update(bookNo, "N");
			if(res > 0) {
				System.out.println("반납 완료");
				getList();
			}else {
				System.err.println("반납중 오류가 발생하였습니다.");
			}
		}else {
			System.out.println("반납이 불가능합니다.");
		}
	}
	
	public void rentBook(int bookNo) {
		System.out.println("바뀜?");
		boolean flg = selStatus(bookNo,"N");
		if(flg) {
			int res = bookDao.update(bookNo, "Y");
			if(res > 0) {
				System.out.println("대여 가능");
				getList();
			}else {
				System.err.println("대여중 오류가 발생하였습니다.");
			}
		}else {
			System.out.println("대여가 불가능합니다.");
		}
	}
	
	public int insertBook(BookVo bookVo) {
		int res = bookDao.insertBook(bookVo);
		if(res > 0) {
			System.out.println("입력되었습니다.");
			return 1;
		}else {
			System.out.println("입력중 오류가 발생하였습니다.");
			return 0;
		}
	}
	
	public int deleteBook(int bookNo) {
		int res = bookDao.delete(bookNo);
		if(res > 0) {
			System.out.println("삭제되었습니다.");
			getList();
			return 1;
		}else {
			System.out.println("입력중 오류가 발생하였습니다.");
			return 0;
		}
	}
	
	/**
	 * 대여 반납 가능 책인지 조회하는 메서드
	 * @param no
	 * @param rentYN
	 * @return
	 */
	public boolean selStatus(int no, String rentYN) {
		boolean res = bookDao.selstatus(no, rentYN);
		return res;
	}

}
