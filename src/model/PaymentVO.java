package model;

import java.sql.Date;

public class PaymentVO {
	private int paymentNo;
	private int paymentPrice;
	private Date paymentDate;
	private String memberId;
	public PaymentVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaymentVO(int paymentNo, int paymentPrice, Date paymentDate, String memberId) {
		super();
		this.paymentNo = paymentNo;
		this.paymentPrice = paymentPrice;
		this.paymentDate = paymentDate;
		this.memberId = memberId;
	}
	public int getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}
	public int getPaymentPrice() {
		return paymentPrice;
	}
	public void setPaymentPrice(int paymentPrice) {
		this.paymentPrice = paymentPrice;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
	    return String.format("[��ȣ: %2d | ȸ�����̵�: %-8s | ������¥: %-10s | �ѱݾ�: %6d��]",
	                         paymentNo, memberId, paymentDate, paymentPrice);
	}


	
	
}
