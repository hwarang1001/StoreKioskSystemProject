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

	// ȸ�����
	public ArrayList<MemberVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB ������ �����߻��߽��ϴ�. ���� ��ġ�� �����ϰڽ��ϴ�.");
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
			System.out.println("createStatement ���� �߻�");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return memberList;
	}

	// ȸ���α��ΰ˻�
	public boolean loginCheck(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean loginCheck = false;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB ������ �����߻��߽��ϴ�. ���� ��ġ�� �����ϰڽ��ϴ�.");
			}
			pstmt = con.prepareStatement(loginSQL);
			pstmt.setString(1, memberVO.getMemberId().trim());
			pstmt.setString(2, memberVO.getMemberPW().trim());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				loginCheck = true; // ���̵�� ��й�ȣ�� ������ �α��� ����
				System.out.println("�α��� ����! ���̵�: " + rs.getString("MEMBER_ID"));
			} else {
				System.out.println("���̵� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
			}
		} catch (SQLException e) {
			System.out.println("createStatement ���� �߻�");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return loginCheck; // �α��� �������� ��ȯ
	}

	// ȸ�����̵�����
	public boolean selectByIdCheck(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean idCheck = false;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB ������ �����߻��߽��ϴ�. ������ġ�� �����ϰڽ��ϴ�.");
			}
			pstmt = con.prepareStatement(selectByIdCheckSQL);
			pstmt.setString(1, memberVO.getMemberId());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				idCheck = true;
			}
		} catch (SQLException e) {
			System.out.println("createStatement �����߻�");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return idCheck;
	}
	// �α��� ȸ�� ����
	public MemberVO selectLoginById(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO _memberVO = null;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB ������ �����߻��߽��ϴ�. ������ġ�� �����ϰڽ��ϴ�.");
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
			System.out.println("createStatement �����߻�");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return _memberVO;
	}

	// ȸ������
	public int insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB ������ �����߻��߽��ϴ�. ���� ��ġ�� �����ϰڽ��ϴ�.");
				return -1;
			}
			pstmt = con.prepareStatement(insertSql);
			pstmt.setString(1, memberVO.getMemberId());
			pstmt.setString(2, memberVO.getMemberPW());
			pstmt.setString(3, memberVO.getMemberName());
			pstmt.setString(4, memberVO.getMemberPhone());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement ���� �߻�");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// ȸ�� ����
	public int update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB ������ �����߻��߽��ϴ�. ���� ��ġ�� �����ϰڽ��ϴ�.");
				return -1;
			}
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setString(1, memberVO.getMemberName());
			pstmt.setString(2, memberVO.getMemberPhone());
			pstmt.setString(3, memberVO.getMemberId());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement ���� �߻�");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// ȸ�� ���� ����
	public int deleteByNum(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB ������ �����߻��߽��ϴ�. ���� ��ġ�� �����ϰڽ��ϴ�.");
				return -1;
			}
			pstmt = con.prepareStatement(deleteByIdSql);
			pstmt.setString(1, memberVO.getMemberId());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement ���� �߻�");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

}
