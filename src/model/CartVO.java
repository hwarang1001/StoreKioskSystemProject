package model;

import java.sql.Date;

import oracle.sql.DATE;

public class CartVO {
	private int cartIndex;
	private int cartTotal;
	private int cartCount;
	private Date cartDate;
	private String memberID;
	private String menuName;
	public CartVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartVO(int cartIndex, int cartTotal, int cartCount, Date cartDate, String memberID, String menuName) {
		super();
		this.cartIndex = cartIndex;
		this.cartTotal = cartTotal;
		this.cartCount = cartCount;
		this.cartDate = cartDate;
		this.memberID = memberID;
		this.menuName = menuName;
	}
	public int getCartIndex() {
		return cartIndex;
	}
	public void setCartIndex(int cartIndex) {
		this.cartIndex = cartIndex;
	}
	public int getCartTotal() {
		return cartTotal;
	}
	public void setCartTotal(int cartTotal) {
		this.cartTotal = cartTotal;
	}
	public int getCartCount() {
		return cartCount;
	}
	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}
	public Date getCartDate() {
		return cartDate;
	}
	public void setCartDate(Date cartDate) {
		this.cartDate = cartDate;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	@Override
	public String toString() {
	    return String.format(
	        "[번호: %2d | 메뉴이름: %-10s | 수량: %2d | 회원아이디: %-10s | 총금액: %6d원]",
	        cartIndex, menuName, cartCount, memberID, cartTotal
	    );
	}
	
}
