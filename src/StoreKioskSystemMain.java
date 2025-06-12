import java.sql.Connection;
import java.util.Scanner;

import controller.CartManager;
import controller.DBUtil;
import controller.MemberManager;
import controller.MenuManager;
import controller.PaymentManager;
import model.MemberVO;
import view.LoginMenuChoice;
import view.MemberMenuChoice;
import view.Menu;
import view.managerMenuChoice;

public class StoreKioskSystemMain {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Connection con = DBUtil.getConnection();
		int choice = 0;
		MemberVO mvo = new MemberVO();
		boolean exitFlag = false;
		if (con == null) {
			System.out.println("DB 연결에 실패했습니다.");
			return;
		}
		// 화면설계 진행
		while (!exitFlag) {
			try {
				Menu.loginMenu();
				MemberManager mbm = new MemberManager();
				boolean loginFlag = false;
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case LoginMenuChoice.로그인:
					mvo = mbm.login();
					if (mvo == null) {
						break;
					}
					userMenu(mvo);
					break;
				case LoginMenuChoice.회원가입:
					mbm.register();
					break;
				case LoginMenuChoice.종료:
					exitFlag = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("\n입력에 오류가 있습니다. \n 프로그램을 다시 시작하세요.");
				exitFlag = true;
			}
		} // end of while
		System.out.println("음식점 키오스크 시스템 종료");
	}// end of main

	// 유저 메뉴
	public static void userMenu(MemberVO memberVO) {
		int choice = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			try {
				Menu.userMenu();
				CartManager cm = new CartManager();
				int managerFlag = 0;
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case MemberMenuChoice.메뉴선택:
					cm.register(memberVO);
					break;
				case MemberMenuChoice.장바구니확인:
					cm.list(memberVO);
					break;
				case MemberMenuChoice.장바구니비우기:
					cm.delete(memberVO);
					break;
				case MemberMenuChoice.결제하기:
					cm.payment(memberVO);
					break;
				case MemberMenuChoice.관리자모드:
					managerFlag = memberVO.getManager();
					if (managerFlag == 1) { // 관리자 아이디면 접속
						managerMenu(memberVO);
					}else {
						System.out.println("관리자 아이디가 아닙니다.");
					}
					break;
				case MemberMenuChoice.로그아웃:
					exitFlag = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("음식점 키오스크 시스템에 예외 발생했습니다. 조치바랍니다.");
			} // end of try-catch
		} // end of while
	} // end of userMenu
	
	// manager 메뉴
	public static void managerMenu(MemberVO memberVO) {
		int choice = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			try {
				Menu.managerMenu();
				MemberManager mbm = new MemberManager();
				MenuManager mnm = new MenuManager();
				PaymentManager pm = new PaymentManager();
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case managerMenuChoice.회원리스트확인:
					mbm.list();
					break;
				case managerMenuChoice.메뉴리스트확인:
					mnm.list();
					break;
				case managerMenuChoice.메뉴추가:
					mnm.register();
					break;
				case managerMenuChoice.메뉴삭제:
					mnm.delete();
					break;
				case managerMenuChoice.메뉴수정:
					mnm.update();
					break;
				case managerMenuChoice.결제목록:
					pm.list();
					break;
				case managerMenuChoice.사용자모드:
					exitFlag = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("음식점 키오스크 시스템에 예외 발생했습니다. 조치바랍니다.");
			} // end of try-catch
		} // end of while
	} // end of managerMenu
}// end of class