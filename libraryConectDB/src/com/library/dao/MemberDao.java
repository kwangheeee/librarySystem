package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.library.controller.MemberVo;
import com.library.dao.conn.DBUtill;

public class MemberDao {
	public MemberVo login(String id, String pw) {
		
		String sql = "SELECT *FROM MEMBER WHERE ID = ? AND PW = ?"; // 매개변수를 물음표로
		
		try (Connection conn = DBUtill.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ){
			 pstmt.setString(1, id);
			 pstmt.setString(2, pw);
			 ResultSet rs = pstmt.executeQuery();
			 if(rs.next()) {
				 String name = rs.getString("name");
				 String adminYN = rs.getString("adminYN");
				 MemberVo memberVo = new MemberVo(id, "", name, adminYN);
				 return memberVo;
			 }
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// id/pw가 일치하는 사용자를 찾을 수 없음
		return null;
	}
	
	public int insertMember(MemberVo memberVo) {
		String sql = "insert into member values(?, ?, ?, ?)";
		 try (Connection conn = DBUtill.getConnection();
			    PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memberVo.getId());
			pstmt.setString(2, memberVo.getPw());
			pstmt.setString(3, memberVo.getName());
			pstmt.setString(4, memberVo.getAdminYN());
			
			int rs = pstmt.executeUpdate();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	
	/**
	 * 데이터베이스에 등록되 낫용자 정보를 삭제
	 * statement
	 * 쿼리 자체를 String 문자여롤 넣어주기 때문에 문자가 값으로 
	 * statement 클래스보다 기능이 향상된 클래스
	 * 코드의 안정...
	 *
	 * @param memberVo
	 * @return
	 */
	public int deleteMember(MemberVo memberVo) {
		String sql = "DELETE FROM member WHERE id = ? and pw = ?";
		 try (Connection conn = DBUtill.getConnection();
			    PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memberVo.getId());
			pstmt.setString(2, memberVo.getPw());

			int rs = pstmt.executeUpdate();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
