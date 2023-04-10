package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library.dao.conn.DBUtill;
import com.library.vo.BookVo;

public class BookDao {

	public List<BookVo> getList() {
		List<BookVo> list = new ArrayList<>();
		String sql = "select * from book order by bookNo";

		try (Connection conn = DBUtill.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				int bookNo = rs.getInt("bookNo");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String rentYN = rs.getString("rentYN");
				list.add(new BookVo(bookNo, title, author, rentYN));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public int insertBook(BookVo bookVo) {
		String sql = "insert into book values(?, ?, ?, ?)";
		try (Connection cnn = DBUtill.getConnection(); PreparedStatement pstmt = cnn.prepareStatement(sql);) {

			pstmt.setInt(1, bookVo.getBookNo());
			pstmt.setString(2, bookVo.getTitle());
			pstmt.setString(3, bookVo.getAuthor());
			pstmt.setString(4, bookVo.getRentYN());

			int res = pstmt.executeUpdate();
			return res;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int delete(int bookNo) {
		String sql = "DELETE FROM book WHERE bookno = " + bookNo;
		try (Connection cnn = DBUtill.getConnection();
			 Statement stmt = cnn.createStatement();) {

			int res = stmt.executeUpdate(sql);
			return res;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	

	
	public int update(int bookNo, String rentYN) {
		String sql = "update book set rentYN = ? where bookNo = ?";
		try (Connection cnn = DBUtill.getConnection();
			PreparedStatement pstmt = cnn.prepareStatement(sql);) {

			pstmt.setString(1, rentYN);
			pstmt.setInt(2, bookNo);
			
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 대여/반납이 가능한 상태라면 true
	 * 아니라면 false
	 * @param bookNo
	 * @param rentYN
	 * @return
	 */
	public boolean selstatus(int no, String rentYN) {
		String sql = "select * from book where bookno = ? and rentYN = ?";
		try (Connection cnn = DBUtill.getConnection();
			PreparedStatement pstmt = cnn.prepareStatement(sql);) {
			
			pstmt.setInt(1, no);
			pstmt.setString(2, rentYN);
			
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}


}
