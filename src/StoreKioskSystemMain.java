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
			System.out.println("DB ���ῡ �����߽��ϴ�.");
			return;
		}
		// ȭ�鼳�� ����
		while (!exitFlag) {
			try {
				Menu.loginMenu();
				MemberManager mbm = new MemberManager();
				boolean loginFlag = false;
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case LoginMenuChoice.�α���:
					mvo = mbm.login();
					if (mvo == null) {
						break;
					}
					userMenu(mvo);
					break;
				case LoginMenuChoice.ȸ������:
					mbm.register();
					break;
				case LoginMenuChoice.����:
					exitFlag = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("\n�Է¿� ������ �ֽ��ϴ�. \n ���α׷��� �ٽ� �����ϼ���.");
				exitFlag = true;
			}
		} // end of while
		System.out.println("������ Ű����ũ �ý��� ����");
	}// end of main

	// ���� �޴�
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
				case MemberMenuChoice.�޴�����:
					cm.register(memberVO);
					break;
				case MemberMenuChoice.��ٱ���Ȯ��:
					cm.list(memberVO);
					break;
				case MemberMenuChoice.��ٱ��Ϻ���:
					cm.delete(memberVO);
					break;
				case MemberMenuChoice.�����ϱ�:
					cm.payment(memberVO);
					break;
				case MemberMenuChoice.�����ڸ��:
					managerFlag = memberVO.getManager();
					if (managerFlag == 1) { // ������ ���̵�� ����
						managerMenu(memberVO);
					}else {
						System.out.println("������ ���̵� �ƴմϴ�.");
					}
					break;
				case MemberMenuChoice.�α׾ƿ�:
					exitFlag = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("������ Ű����ũ �ý��ۿ� ���� �߻��߽��ϴ�. ��ġ�ٶ��ϴ�.");
			} // end of try-catch
		} // end of while
	} // end of userMenu
	
	// manager �޴�
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
				case managerMenuChoice.ȸ������ƮȮ��:
					mbm.list();
					break;
				case managerMenuChoice.�޴�����ƮȮ��:
					mnm.list();
					break;
				case managerMenuChoice.�޴��߰�:
					mnm.register();
					break;
				case managerMenuChoice.�޴�����:
					mnm.delete();
					break;
				case managerMenuChoice.�޴�����:
					mnm.update();
					break;
				case managerMenuChoice.�������:
					pm.list();
					break;
				case managerMenuChoice.����ڸ��:
					exitFlag = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("������ Ű����ũ �ý��ۿ� ���� �߻��߽��ϴ�. ��ġ�ٶ��ϴ�.");
			} // end of try-catch
		} // end of while
	} // end of managerMenu
}// end of class