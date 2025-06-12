package view;


public class Menu {

	// 사용자 메뉴 출력
	public static void userMenu() {
		System.out.println("――――――――――음식점 키오스크 시스템―――――――――――");
		System.out.println("1.메뉴선택 ");
		System.out.println("2.장바구니확인");
		System.out.println("3.장바구니비우기");
		System.out.println("4.결제하기");
		System.out.println("5.관리자모드");
		System.out.println("6.로그아웃");
		System.out.println("――――――――――――――――――――――――――――――――――――――");
		System.out.print("번호 선택 : ");
	}
	// 관리자 메뉴 출력
	public static void managerMenu() {
		System.out.println("――――――――――관리자 모드―――――――――――");
		System.out.println("1.회원리스트확인 ");
		System.out.println("2.메뉴리스트확인");
		System.out.println("3.메뉴추가");
		System.out.println("4.메뉴삭제");
		System.out.println("5.메뉴수정");
		System.out.println("6.결제목록");
		System.out.println("7.사용자모드");
		System.out.println("―――――――――――――――――――――――――――――");
		System.out.print("번호 선택 : ");
	}

	// 로그인 메뉴
	public static void loginMenu() {
		System.out.println();
		System.out.println("――――――――――― 로그인 / 회원가입 ―――――――――――");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 프로그램 종료");
		System.out.println("―――――――――――――――――――――――――――――――――――――");
		System.out.print("번호 선택 : ");
	}

}