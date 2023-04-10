package com.library.controller;

import java.util.Scanner;

import com.library.service.BookService;
import com.library.service.MemberService;
import com.library.vo.BookVo;

public class LibraryController {
	
	static Scanner scan = new Scanner(System.in);
	BookService bookService = new BookService();
	MemberService memberService = new MemberService();
	BookVo bookVo = new BookVo();
	
	public void start() {
		System.out.println("====================");
		System.out.println("도서관에 오신걸 환영합니다~~");
		System.out.println("====================");
	
		while(true) {
			
			// 로그인 요청
			MemberVo memberVo = login();
			
			// 도서목록 출력
			bookService.getList();
			
			if("y".equalsIgnoreCase(memberVo.getAdminYN())) {
				adminMenu();
			}else {
				userMenu();
			}
		}
	}
	
	public void adminMenu() {
		Outter :
		while(true) {
		System.out.println("==================관리자 메뉴======================");
		System.out.println("1. 도서등록 2. 도서삭제 3. 사용자등록 4. 사용자삭제 0.이전메뉴");
		System.out.print("메뉴를 입력해 주세요 : ");
		int menu = getInt();
		int bookNo = 0;
		int res = 0;
		String id = "";
		String pw = "";
		switch (menu) {
			case 1:
				System.out.print("도서 일련번호를 입력해주세요 : ");
				bookNo = getInt();
				System.out.print("도서명을 입력해주세요 : ");
				String title = getString();
				System.out.print("작가를 입력해주세요 : ");
				String author = getString();
				res = bookService.insertBook(new BookVo(bookNo, title, author, "N"));
				break;
			case 2:
				System.out.print("삭제할 도서 번호를 입력하세요 : ");
				bookNo = getInt();
				res = bookService.deleteBook(bookNo);
				break;
			case 3:
				System.out.print("아이디를 입력하세요 : ");
				id = getString();
				System.out.print("비밀번호를 입력하세요 : ");
				pw = getString();
				System.out.print("이름을 입력하세요 : ");
				String name = getString();
				System.out.print("관리자 계정은 Y를 입력해주세요 : ");
				String adminYNStr = getString();
				String adminYN = adminYNStr.equalsIgnoreCase("Y") ? "Y" : "N";
				
				MemberVo memberVo = new MemberVo(id, pw, name, "N");
				memberService.insertMember(memberVo);
				break;
			case 4:
				System.out.print("삭제할 사용자 id를 입력해주세요 : ");
				id = getString();
				System.out.print("삭제할 사용자 pw를 입력해주세요 : ");
				pw = getString();
				res = memberService.deleteMember(new MemberVo(id, pw));
				break;
			case 0:
				break Outter;
			default: 
				System.err.println("알맞은 번호가 없습니다.");
				break;
			}
		}
	}
	
	public void userMenu() {
		while(true) {
			System.out.println("==========사용자 메뉴==========");
			System.out.println("1. 도서대여 2. 도서반납 0.이전메뉴 q.종료");
			System.out.print("메뉴를 입력해 주세요 : ");
			int menu = getInt();
			int bookNo = 0;
			int res = 0;
			switch (menu) {
			case 1:
				System.out.print("대여할 도서의 번호를입력하세요 : ");
				bookNo = getInt();
				bookService.rentBook(bookNo);
				break;
			case 2:
				System.out.print("반납할 도서의 번호를입력하세요 : ");
				bookNo = getInt();
				bookService.retrunBook(bookNo);
				break;
			case 0:
				return;
				
			default:
				System.err.println("알맞은 번호가 없습니다.");
				break;
			}
		}
	}
	
	/**
	 * 로그인
	 * id/pw를 입력 받아서 사용자 저오를 조회해 옵니다.
	 * 로그인 성공할 떄 까지
	 * @return
	 */
	public MemberVo login() {
		while(true) {
			System.out.print("id를 입력해주세요 : ");
			String id = getString();
			System.out.print("pw를 입력해주세요 : ");
			String pw = getString();
			
			MemberVo memberVo= memberService.login(id, pw);
			if(memberVo != null) {
				System.out.println("로그인성공");
				return memberVo;
			}
		}
		
	}
	
	public static String getString() {
		String str = "";
			str = scan.next();
			if("Q".equalsIgnoreCase(str)) {
				System.exit(0);
			}
		return str;
	}
	
	public static int getInt() {
		int i = 0;
		while(true) {
			
			try {
				String str = scan.next();
				if("Q".equalsIgnoreCase(str)) {
					System.exit(0);
				}
				i = Integer.parseInt(str);
				return i;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("숫자 입력중 오류 발생");
			}
		}
	}
}
