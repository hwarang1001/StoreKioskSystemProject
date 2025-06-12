package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.CartVO;
import model.MemberVO;
import model.MenuVO;
import model.PaymentVO;

public class CartManager {
	// 장바구니 목록
	public void list(MemberVO memberVO) throws Exception {
		CartDAO cd = new CartDAO();
		System.out.println("장바구니 전체 리스트");
		ArrayList<CartVO> cartList = cd.selectById(memberVO.getMemberId());
		if (cartList.size() <= 0) {
			System.out.println("장바구니 리스트에 데이터가 없습니다.");
			return;
		} else if (cartList == null) {
			System.out.println("장바구니 리스트에 에러가 발생했습니다.");
			return;
		}
		for (CartVO data : cartList) {
			System.out.println(data.toString());
		}
		System.out.println();
	}

	// 결제하기
	public void payment(MemberVO memberVO) throws Exception {
		Scanner scan = new Scanner(System.in);
		CartDAO cd = new CartDAO();
		CartVO cvo = new CartVO();
		MenuDAO md = new MenuDAO();
		PaymentVO pvo = new PaymentVO();
		PaymentDAO pd = new PaymentDAO();
		char yN = 0;
		ArrayList<CartVO> cartList = null;
		String memberID = null; // 입력한 아이디
		int _count = 0;
		
		memberID = memberVO.getMemberId();
		cartList = cd.selectById(memberID);
		cvo.setMemberID(memberID);
		int total = cd.payMentByID(cvo);
		if (total == 0) {
			System.out.printf("%s님의 장바구니 결제에 오류가 발생했습니다. \n", memberID);
			return;
		} else {
			for (CartVO item : cartList) {
				System.out.println(item.toString());
			}
			System.out.printf("%s님의 장바구니 총 금액은: %d원 입니다. \n결제하시겠습니까?(y/n): ", memberID, total);
			yN = scan.nextLine().charAt(0);
			if (yN == 'y' || yN == 'Y') {
				cartList = cd.selectById(memberID);
				for (CartVO item : cartList) {
					MenuVO menu = md.selectByName(item.getMenuName());

					int inven = menu.getMenuInven();
					int count = item.getCartCount();

					if (inven < count) {
						System.out.printf("%s 메뉴의 재고가 부족하여 결제할 수 없습니다. (남은 재고: %d)\n", menu.getMenuName(), inven);
						return;
					}
					// 메뉴 재고 업데이트
					menu.setMenuInven(inven - count);
					md.updateByInven(menu); 
				}
				delete(memberVO); // 장바구니 비우기
				// 결제 목록 추가
				pvo.setPaymentPrice(total);
				pvo.setMemberId(memberID);
				_count = pd.insert(pvo);
				if(_count == -1) {
					System.out.println("결제 목록 추가에 오류가 발생했습니다.");
				}
				System.out.printf("%s님의 장바구니 결제가 성공했습니다. \n", memberID);
			} else {
				System.out.printf("%s님의 장바구니 결제가 취소되었습니다. \n", memberID);
				return;
			}
		}
		System.out.println();
	}

	// 장바구니 등록
	public void register(MemberVO memberVO) throws Exception {
		Scanner scan = new Scanner(System.in);
		CartDAO cd = new CartDAO();
		CartVO cvo = new CartVO();
		MenuVO mvo = new MenuVO();
		MenuDAO md = new MenuDAO();
		MenuManager mnm = new MenuManager();

		int cartCount = 0;
		int cartTotal = 0;

		System.out.println();
		mnm.list();
		System.out.println();

		System.out.println("담을 메뉴 이름 입력");
		System.out.print("메뉴이름 : ");
		String menuName = scan.nextLine();
		System.out.print("메뉴수량 : ");
		cartCount = Integer.parseInt(scan.nextLine());

		mvo = md.selectByName(menuName);

		// 재고 확인
		if (mvo.getMenuInven() < cartCount) {
			System.out.println("재고가 부족합니다.");
			return;
		}
		cartTotal = mvo.getMenuPrice() * cartCount;

		cvo.setCartTotal(cartTotal);
		cvo.setMemberID(memberVO.getMemberId());
		cvo.setMenuName(mvo.getMenuName());
		cvo.setCartCount(cartCount);

		int count = cd.insert(cvo);

		if (count == 0) {
			System.out.println("장바구니 등록에 실패하였습니다.");
		} else {
			System.out.println("장바구니 등록에 성공하였습니다.");
		}
		System.out.println();
	}

	// 장바구니 삭제 관리
	public void delete(MemberVO memberVO) throws Exception {
		Scanner scan = new Scanner(System.in);

		CartDAO cd = new CartDAO();
		CartVO cvo = new CartVO();

		String memberID; // 입력한 아이디

		memberID = memberVO.getMemberId();
		cvo.setMemberID(memberID);
		int count = cd.deleteByID(cvo);
		if (count == 0) {
			System.out.printf("%s님의 장바구니 삭제에 오류가 발생했습니다. \n", memberID);
			return;
		} else {
			System.out.printf("%s님의 장바구니 삭제가 성공했습니다. \n", memberID);
		}
		System.out.println();
	}
}
