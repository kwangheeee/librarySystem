package com.library.service;

import com.library.controller.MemberVo;
import com.library.dao.MemberDao;

public class MemberService {
	MemberDao memberDao = new MemberDao();
	
	/**
	 * 로그인
	 * id, pw를 입력받아서 사용자를 조회합니다.
	 * @param id
	 * @param pw
	 * @return
	 */
	public MemberVo login(String id, String pw) {
		MemberVo memberVo = memberDao.login(id, pw);
		if(memberVo != null) {
			System.out.println(memberVo.getName() + "님 환영합니다.");
			return memberVo;
		}else {
			System.err.println("아이디/비밀번호가 일치하지 않습니다.");
			return null;
		}
	}

	/**
	 * 사용자 등록
	 * @param memberVo
	 */
	public void insertMember(MemberVo memberVo) {
		int res = memberDao.insertMember(memberVo);
		if(res > 0) {
			System.out.println("입력 되었습니다.");
		}else {
			System.out.println("입력중 오류 발생");
		}
	}

	public int deleteMember(MemberVo memberVo) {
		int res = memberDao.deleteMember(memberVo);
		if(res > 0) {
			System.out.println("삭제 되었습니다.");
		}else {
			System.out.println("사용자를 찾을 수 없습니다.");
		}
		return 0;
	}
	
	
}
