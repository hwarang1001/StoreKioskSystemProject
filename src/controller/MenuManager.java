package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.MenuVO;

public class MenuManager {
	// �޴� ���
	public void list() throws Exception {
		MenuDAO md = new MenuDAO();
		System.out.println("�޴� ��ü ����Ʈ");
		ArrayList<MenuVO> menuList = md.selectAll();
		if (menuList.size() <= 0) {
			System.out.println("�޴���ü����Ʈ�� �����Ͱ� �����ϴ�.");
			return;
		} else if (menuList == null) {
			System.out.println("�޴���ü����Ʈ�� ������ �߻��߽��ϴ�.");
			return;
		}
		for (MenuVO data : menuList) {
			System.out.println(data.toString());
		}
		System.out.println();
	}
	// �޴� ���
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
		System.out.println("���ο� �޴� ���� �Է�");
		System.out.print("�޴��̸� : ");
		menuName = scan.nextLine();
		System.out.print("�޴����� : ");
		menuPrice = Integer.parseInt(scan.nextLine());
		System.out.print("�޴����� : ");
		menuInven = Integer.parseInt(scan.nextLine());

		mvo.setMenuName(menuName);
		mvo.setMenuPrice(menuPrice);
		mvo.setMenuInven(menuInven);

		count = md.insert(mvo);
		if (count == 0) {
			System.out.println("�޴���Ͽ� �����Ͽ����ϴ�.");
		} else {
			System.out.println("�޴���Ͽ� �����Ͽ����ϴ�.");
		}
		System.out.println();

	}

	// �޴� �ݾ� ���� ����
	public void update() throws Exception {
		Scanner scan = new Scanner(System.in);

		MenuDAO md = new MenuDAO();
		MenuVO mvo = new MenuVO();

		String menuName = null;
		int menuPrice = 0;
		int menuInven = 0;
		list();

		System.out.println("������ �޴� �̸� �Է�");
		System.out.print("�޴��̸� : ");
		menuName = scan.nextLine();
		System.out.print("������ ��� : ");
		menuPrice = Integer.parseInt(scan.nextLine());
		System.out.print("������ ��� : ");
		menuInven = Integer.parseInt(scan.nextLine());

		mvo.setMenuName(menuName);
		mvo.setMenuPrice(menuPrice);
		mvo.setMenuInven(menuInven);
		int count = md.update(mvo);
		if (count == 0) {
			System.out.println("�޴� �ݾ� ������ ������ �߻��߽��ϴ�.");
			return;
		} else {
			System.out.println("�޴� �ݾ� ������ �Ϸ�ƽ��ϴ�.");
		}
		System.out.println();
		
	}

	// �޴� ���� ����
	public void delete() throws Exception {
		Scanner scan = new Scanner(System.in);

		MenuDAO md = new MenuDAO();
		MenuVO mvo = new MenuVO();

		String menuName; // �Է��� �̸�
		list();
		System.out.println();

		System.out.println("������ �޴��̸� �Է�");
		System.out.print("�޴��̸� : ");
		menuName = scan.nextLine();
		mvo.setMenuName(menuName);
		int count = md.deleteByName(mvo);
		if (count == 0) {
			System.out.printf("�޴� %s ������ ������ �߻��߽��ϴ�. \n", menuName);
			return;
		} else {
			System.out.printf("�޴� %s ������ �����߽��ϴ�. \n", menuName);
		}
		System.out.println();
	}
}
