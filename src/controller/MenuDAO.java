package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.MenuVO;

public class MenuDAO {
	private String selectSQL = "SELECT * FROM MENU";
	private String selectByName = "SELECT * FROM MENU WHERE MENU_NAME = ?";
	private String insertSql = "INSERT INTO MENU VALUES(?, ?, ?)";
	private String updateSQL = "UPDATE MENU SET MENU_PRICE = ?, MENU_INVEN = ? WHERE MENU_NAME = ?";
	private String updateByInvenSQL = "UPDATE MENU SET MENU_INVEN = ? WHERE MENU_NAME = ?";
	private String deleteByNameSql = "DELETE FROM menu WHERE MENU_NAME = ?";

	// �޴����
	public ArrayList<MenuVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MenuVO> menuList = new ArrayList<MenuVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB ������ �����߻��߽��ϴ�. ���� ��ġ�� �����ϰڽ��ϴ�.");
				return null;
			}
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String menuName = rs.getString("MENU_NAME");
				int menuPrice = rs.getInt("MENU_PRICE");
				int menuINVEN = rs.getInt("MENU_INVEN");

				MenuVO menuVO = new MenuVO(menuName, menuPrice, menuINVEN);
				menuList.add(menuVO);
			}
		} catch (SQLException e) {
			System.out.println("createStatement ���� �߻�");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return menuList;
	}
	// �޴��̸� �˻�
	public MenuVO selectByName(String menuName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MenuVO menuVO = null;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB ������ �����߻��߽��ϴ�. ���� ��ġ�� �����ϰڽ��ϴ�.");
				return null;
			}
			pstmt = con.prepareStatement(selectByName);
			pstmt.setString(1, menuName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String _menuName = rs.getString("MENU_NAME");
				int menuPrice = rs.getInt("MENU_PRICE");
				int menuINVEN = rs.getInt("MENU_INVEN");
				
				menuVO = new MenuVO(_menuName, menuPrice, menuINVEN);
			}
		} catch (SQLException e) {
			System.out.println("createStatement ���� �߻�");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return menuVO;
	}

	// �޴� ���� �� ��� ����
	public int updateByInven(MenuVO menuVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB ������ �����߻��߽��ϴ�. ���� ��ġ�� �����ϰڽ��ϴ�.");
				return -1;
			}
			pstmt = con.prepareStatement(updateByInvenSQL);
			pstmt.setInt(1, menuVO.getMenuInven());
			pstmt.setString(2, menuVO.getMenuName());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement ���� �߻�");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// �޴� �߰�
	public int insert(MenuVO menuVO) {
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
			pstmt.setString(1, menuVO.getMenuName());
			pstmt.setInt(2, menuVO.getMenuPrice());
			pstmt.setInt(3, menuVO.getMenuInven());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement ���� �߻�");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// �޴� ����
	public int update(MenuVO menuVO) {
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
			pstmt.setInt(1, menuVO.getMenuPrice());
			pstmt.setInt(2, menuVO.getMenuInven());
			pstmt.setString(3, menuVO.getMenuName());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement ���� �߻�");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	// �޴� ���� ����
	public int deleteByName(MenuVO menuVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB ������ �����߻��߽��ϴ�. ���� ��ġ�� �����ϰڽ��ϴ�.");
				return -1;
			}
			pstmt = con.prepareStatement(deleteByNameSql);
			pstmt.setString(1, menuVO.getMenuName());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement ���� �߻�");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

}
