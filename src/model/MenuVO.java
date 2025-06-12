package model;

public class MenuVO {
	private String menuName;
	private int menuPrice;
	private int menuInven;
	public MenuVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MenuVO(String menuName, int menuPrice, int menuInven) {
		super();
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuInven = menuInven;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
	public int getMenuInven() {
		return menuInven;
	}
	public void setMenuInven(int menuInven) {
		this.menuInven = menuInven;
	}
	@Override
	public String toString() {
	    return String.format("[메뉴이름: %-10s | 가격: %6d원 | 재고: %3d개]", menuName, menuPrice, menuInven);
	}
	
	
}
