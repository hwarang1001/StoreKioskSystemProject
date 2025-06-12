package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.MemberVO;

public class MemberDAO {
	private String selectSQL = "SELECT * FROM MEMBER";
	private String selectByIdCheckSQL = "SELECT * FROM MEMBER WHERE UPPER(MEMBER_ID) = UPPER(?)";
	private String loginSQL = "SELECT MEMBER_ID, MEMBER_PW FROM MEMBER WHERE UPPER(MEMBER_ID) = UPPER(?) AND MEMBER_PW = ?";
	private String loginByIDSQL = "SELECT * FROM MEMBER WHERE UPPER(MEMBER_ID) = UPPER(?)";
	private String insertSql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, DEFAULT)";
	private String updateSQL = "UPDATE MEMBER SET MEMBER_NAME = ?, MEMBER_PHONE = ? WHERE MEMBER_ID = ?";
	private String deleteByIdSql = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";

	// 회원목록
	public ArrayList<MemberVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
				return null;
			}
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String memberID = rs.getString("MEMBER_ID");
				String memberPW = rs.getString("MEMBER_PW");
				String memberName = rs.getString("MEMBER_NAME");
				String memberPhone = rs.getString("MEMBER_PHONE");
				int manager = rs.getInt("MANAGER");

				MemberVO memberVO = new MemberVO(memberID, memberPW, memberName, memberPhone, manager);
				memberList.add(memberVO);
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return memberList;
	}

	// 회원로그인검색
	public boolean loginCheck(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean loginCheck = false;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
			}
			pstmt = con.prepareStatement(loginSQL);
			pstmt.setString(1, memberVO.getMemberId().trim());
			pstmt.setString(2, memberVO.getMemberPW().trim());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				loginCheck = true; // 아이디와 비밀번호가 맞으면 로그인 성공
				System.out.println("로그인 성공! 아이디: " + rs.getString("MEMBER_ID"));
			} else {
				System.out.println("아이디나 비밀번호가 틀렸습니다.");
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return loginCheck; // 로그인 성공여부 반환
	}

	// 회원아이디점검
	public boolean selectByIdCheck(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean idCheck = false;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
			}
			pstmt = con.prepareStatement(selectByIdCheckSQL);
			pstmt.setString(1, memberVO.getMemberId());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				idCheck = true;
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return idCheck;
	}
	// 로그인 회원 저장
	public MemberVO selectLoginById(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO _memberVO = null;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
			}
			pstmt = con.prepareStatement(selectByIdCheckSQL);
			pstmt.setString(1, memberVO.getMemberId());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String memberID = rs.getString("MEMBER_ID");
				String memberPW = rs.getString("MEMBER_PW");
				String memberName = rs.getString("MEMBER_NAME");
				String memberPhone = rs.getString("MEMBER_PHONE");
				int manager = rs.getInt("MANAGER");

				_memberVO = new MemberVO(memberID, memberPW, memberName, memberPhone, manager);
			}
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return _memberVO;
	}

	// 회원가입
	public int insert(MemberVO memberVO) {
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
			pstmt.setString(1, memberVO.getMemberId());
			pstmt.setString(2, memberVO.getMemberPW());
			pstmt.setString(3, memberVO.getMemberName());
			pstmt.setString(4, memberVO.getMemberPhone());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// 회원 수정
	public int update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setString(1, memberVO.getMemberName());
			pstmt.setString(2, memberVO.getMemberPhone());
			pstmt.setString(3, memberVO.getMemberId());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// 회원 정보 삭제
	public int deleteByNum(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결이 문제발생했습니다. 빨리 조치를 진행하겠습니다.");
				return -1;
			}
			pstmt = con.prepareStatement(deleteByIdSql);
			pstmt.setString(1, memberVO.getMemberId());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

}
