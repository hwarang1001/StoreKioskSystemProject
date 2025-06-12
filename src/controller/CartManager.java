package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.CartVO;
import model.MemberVO;
import model.MenuVO;
import model.PaymentVO;

public class CartManager {
	// ��ٱ��� ���
	public void list(MemberVO memberVO) throws Exception {
		CartDAO cd = new CartDAO();
		System.out.println("��ٱ��� ��ü ����Ʈ");
		ArrayList<CartVO> cartList = cd.selectById(memberVO.getMemberId());
		if (cartList.size() <= 0) {
			System.out.println("��ٱ��� ����Ʈ�� �����Ͱ� �����ϴ�.");
			return;
		} else if (cartList == null) {
			System.out.println("��ٱ��� ����Ʈ�� ������ �߻��߽��ϴ�.");
			return;
		}
		for (CartVO data : cartList) {
			System.out.println(data.toString());
		}
		System.out.println();
	}

	// �����ϱ�
	public void payment(MemberVO memberVO) throws Exception {
		Scanner scan = new Scanner(System.in);
		CartDAO cd = new CartDAO();
		CartVO cvo = new CartVO();
		MenuDAO md = new MenuDAO();
		PaymentVO pvo = new PaymentVO();
		PaymentDAO pd = new PaymentDAO();
		char yN = 0;
		ArrayList<CartVO> cartList = null;
		String memberID = null; // �Է��� ���̵�
		int _count = 0;
		
		memberID = memberVO.getMemberId();
		cartList = cd.selectById(memberID);
		cvo.setMemberID(memberID);
		int total = cd.payMentByID(cvo);
		if (total == 0) {
			System.out.printf("%s���� ��ٱ��� ������ ������ �߻��߽��ϴ�. \n", memberID);
			return;
		} else {
			for (CartVO item : cartList) {
				System.out.println(item.toString());
			}
			System.out.printf("%s���� ��ٱ��� �� �ݾ���: %d�� �Դϴ�. \n�����Ͻðڽ��ϱ�?(y/n): ", memberID, total);
			yN = scan.nextLine().charAt(0);
			if (yN == 'y' || yN == 'Y') {
				cartList = cd.selectById(memberID);
				for (CartVO item : cartList) {
					MenuVO menu = md.selectByName(item.getMenuName());

					int inven = menu.getMenuInven();
					int count = item.getCartCount();

					if (inven < count) {
						System.out.printf("%s �޴��� ��� �����Ͽ� ������ �� �����ϴ�. (���� ���: %d)\n", menu.getMenuName(), inven);
						return;
					}
					// �޴� ��� ������Ʈ
					menu.setMenuInven(inven - count);
					md.updateByInven(menu); 
				}
				delete(memberVO); // ��ٱ��� ����
				// ���� ��� �߰�
				pvo.setPaymentPrice(total);
				pvo.setMemberId(memberID);
				_count = pd.insert(pvo);
				if(_count == -1) {
					System.out.println("���� ��� �߰��� ������ �߻��߽��ϴ�.");
				}
				System.out.printf("%s���� ��ٱ��� ������ �����߽��ϴ�. \n", memberID);
			} else {
				System.out.printf("%s���� ��ٱ��� ������ ��ҵǾ����ϴ�. \n", memberID);
				return;
			}
		}
		System.out.println();
	}

	// ��ٱ��� ���
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

		System.out.println("���� �޴� �̸� �Է�");
		System.out.print("�޴��̸� : ");
		String menuName = scan.nextLine();
		System.out.print("�޴����� : ");
		cartCount = Integer.parseInt(scan.nextLine());

		mvo = md.selectByName(menuName);

		// ��� Ȯ��
		if (mvo.getMenuInven() < cartCount) {
			System.out.println("��� �����մϴ�.");
			return;
		}
		cartTotal = mvo.getMenuPrice() * cartCount;

		cvo.setCartTotal(cartTotal);
		cvo.setMemberID(memberVO.getMemberId());
		cvo.setMenuName(mvo.getMenuName());
		cvo.setCartCount(cartCount);

		int count = cd.insert(cvo);

		if (count == 0) {
			System.out.println("��ٱ��� ��Ͽ� �����Ͽ����ϴ�.");
		} else {
			System.out.println("��ٱ��� ��Ͽ� �����Ͽ����ϴ�.");
		}
		System.out.println();
	}

	// ��ٱ��� ���� ����
	public void delete(MemberVO memberVO) throws Exception {
		Scanner scan = new Scanner(System.in);

		CartDAO cd = new CartDAO();
		CartVO cvo = new CartVO();

		String memberID; // �Է��� ���̵�

		memberID = memberVO.getMemberId();
		cvo.setMemberID(memberID);
		int count = cd.deleteByID(cvo);
		if (count == 0) {
			System.out.printf("%s���� ��ٱ��� ������ ������ �߻��߽��ϴ�. \n", memberID);
			return;
		} else {
			System.out.printf("%s���� ��ٱ��� ������ �����߽��ϴ�. \n", memberID);
		}
		System.out.println();
	}
}
