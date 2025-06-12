package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CartVO;

public class CartDAO {
	private String selectSQL = "SELECT * FROM CART WHERE MEMBER_ID = ?";
	private String insertSql = "INSERT INTO CART VALUES(CART_SEQ.NEXTVAL, ?, ?, SYSDATE, ?, ?)";
	private String deleteByIDSql = "DELETE FROM CART WHERE MEMBER_ID = ?";
	private String payMentByIDSql = "SELECT SUM(CART_TOTAL) AS TOTAL FROM CART WHERE MEMBER_ID = ? GROUP BY MEMBER_ID";
	// 장바구니목록
	public ArrayList<CartVO> selectById(String MemberId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
				return null;
			}
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, MemberId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cartIndex = rs.getInt("CART_INDEX");
				int cartTotal = rs.getInt("CART_TOTAL");
				int cartCount = rs.getInt("CART_COUNT");
				Date cartDate = rs.getDate("CART_DATE");
				String memberID = rs.getString("MEMBER_ID");
				String menuName = rs.getString("MENU_NAME");

				CartVO _cartVO = new CartVO(cartIndex, cartTotal, cartCount, cartDate, memberID, menuName);
				cartList.add(_cartVO);
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return cartList;
	}

	// 장바구니 추가
	public int insert(CartVO cartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(insertSql);
			pstmt.setInt(1, cartVO.getCartTotal());
			pstmt.setInt(2, cartVO.getCartCount());
			pstmt.setString(3, cartVO.getMemberID());
			pstmt.setString(4, cartVO.getMenuName());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// 장바구니 결제
	public int payMentByID(CartVO cartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
				return 0;
			}
			pstmt = con.prepareStatement(payMentByIDSql);
			pstmt.setString(1, cartVO.getMemberID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt("TOTAL");
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return total;
	}

	// 장바구니 삭제
	public int deleteByID(CartVO cartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(deleteByIDSql);
			pstmt.setString(1, cartVO.getMemberID());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

}
