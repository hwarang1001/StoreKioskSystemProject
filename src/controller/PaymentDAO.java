package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PaymentVO;

public class PaymentDAO {
	private String selectSQL = "SELECT * FROM PAYMENT";
	private String insertSql = "INSERT INTO PAYMENT VALUES(PAYMENT_SEQ.NEXTVAL, ?, SYSDATE, ?)";

	// �������
	public ArrayList<PaymentVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PaymentVO> paymentList = new ArrayList<PaymentVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB ������ �����߻��߽��ϴ�. ���� ��ġ�� �����ϰڽ��ϴ�.");
				return null;
			}
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {				
				int paymentNO = rs.getInt("PAYMENT_NO");
				int paymentPrice = rs.getInt("PAYMENT_PRICE");
				Date paymentDate = rs.getDate("PAYMENT_DATE");
				String memberID = rs.getString("MEMBER_ID");
				PaymentVO paymentVO = new PaymentVO(paymentNO, paymentPrice, paymentDate, memberID);
				paymentList.add(paymentVO);
			}
		} catch (SQLException e) {
			System.out.println("createStatement ���� �߻�");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return paymentList;
	}
	
	// �����߰�
	public int insert(PaymentVO paymentVO) {
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
			pstmt.setInt(1, paymentVO.getPaymentPrice());
			pstmt.setString(2, paymentVO.getMemberId());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement ���� �߻�");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
}
