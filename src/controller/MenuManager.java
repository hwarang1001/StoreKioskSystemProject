package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.MenuVO;

public class MenuManager {
	// 메뉴 목록
	public void list() throws Exception {
		MenuDAO md = new MenuDAO();
		System.out.println("메뉴 전체 리스트");
		ArrayList<MenuVO> menuList = md.selectAll();
		if (menuList.size() <= 0) {
			System.out.println("메뉴전체리스트에 데이터가 없습니다.");
			return;
		} else if (menuList == null) {
			System.out.println("메뉴전체리스트에 에러가 발생했습니다.");
			return;
		}
		for (MenuVO data : menuList) {
			System.out.println(data.toString());
		}
		System.out.println();
	}
	// 메뉴 등록
	public void register() throws Exception {
		Scanner scan = new Scanner(System.in);
		MenuDAO md = new MenuDAO();
		MenuVO mvo = new MenuVO();
		int count = 0;
		boolean idCheck = false;

		String menuName = null;
		int menuPrice = 0;
		int menuInven = 0;
		
		System.out.println();
		System.out.println("새로운 메뉴 정보 입력");
		System.out.print("메뉴이름 : ");
		menuName = scan.nextLine();
		System.out.print("메뉴가격 : ");
		menuPrice = Integer.parseInt(scan.nextLine());
		System.out.print("메뉴수량 : ");
		menuInven = Integer.parseInt(scan.nextLine());

		mvo.setMenuName(menuName);
		mvo.setMenuPrice(menuPrice);
		mvo.setMenuInven(menuInven);

		count = md.insert(mvo);
		if (count == 0) {
			System.out.println("메뉴등록에 실패하였습니다.");
		} else {
			System.out.println("메뉴등록에 성공하였습니다.");
		}
		System.out.println();

	}

	// 메뉴 금액 수정 관리
	public void update() throws Exception {
		Scanner scan = new Scanner(System.in);

		MenuDAO md = new MenuDAO();
		MenuVO mvo = new MenuVO();

		String menuName = null;
		int menuPrice = 0;
		int menuInven = 0;
		list();

		System.out.println("수정할 메뉴 이름 입력");
		System.out.print("메뉴이름 : ");
		menuName = scan.nextLine();
		System.out.print("수정할 비용 : ");
		menuPrice = Integer.parseInt(scan.nextLine());
		System.out.print("수정할 재고 : ");
		menuInven = Integer.parseInt(scan.nextLine());

		mvo.setMenuName(menuName);
		mvo.setMenuPrice(menuPrice);
		mvo.setMenuInven(menuInven);
		int count = md.update(mvo);
		if (count == 0) {
			System.out.println("메뉴 금액 수정에 오류가 발생했습니다.");
			return;
		} else {
			System.out.println("메뉴 금액 수정이 완료됐습니다.");
		}
		System.out.println();
		
	}

	// 메뉴 삭제 관리
	public void delete() throws Exception {
		Scanner scan = new Scanner(System.in);

		MenuDAO md = new MenuDAO();
		MenuVO mvo = new MenuVO();

		String menuName; // 입력한 이름
		list();
		System.out.println();

		System.out.println("삭제할 메뉴이름 입력");
		System.out.print("메뉴이름 : ");
		menuName = scan.nextLine();
		mvo.setMenuName(menuName);
		int count = md.deleteByName(mvo);
		if (count == 0) {
			System.out.printf("메뉴 %s 삭제에 오류가 발생했습니다. \n", menuName);
			return;
		} else {
			System.out.printf("메뉴 %s 삭제가 성공했습니다. \n", menuName);
		}
		System.out.println();
	}
}
