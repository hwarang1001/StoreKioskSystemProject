package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.MemberVO;

public class MemberManager {
	// ȸ�� ���
	public void list() throws Exception {
		MemberDAO md = new MemberDAO();
		System.out.println("ȸ�� ��ü ����Ʈ");
		ArrayList<MemberVO> memberList = md.selectAll();
		if (memberList.size() <= 0) {
			System.out.println("ȸ����ü����Ʈ�� �����Ͱ� �����ϴ�.");
			return;
		} else if (memberList == null) {
			System.out.println("ȸ����ü����Ʈ�� ������ �߻��߽��ϴ�.");
			return;
		}
		for (MemberVO data : memberList) {
			System.out.println(data.toString());
		}
		System.out.println();
	}

	// �α���
	public MemberVO login() throws Exception {
		Scanner scan = new Scanner(System.in);
		MemberDAO md = new MemberDAO();
		MemberVO mvo = new MemberVO();
		boolean loginCheck = false;

		String memberID = null;
		String memberPW = null;

		System.out.println();
		System.out.println("�α��� ���̵�/�н����� �Է�");
		System.out.print("���̵� : ");
		memberID = scan.nextLine();
		System.out.print("��й�ȣ : ");
		memberPW = scan.nextLine();
		mvo.setMemberId(memberID);
		mvo.setMemberPW(memberPW);

		loginCheck = md.loginCheck(mvo);
		if (loginCheck == false) {
			System.out.println("�α��ο� �����Ͽ����ϴ�.");
			return null;
		} else {
			System.out.println("�α��ο� �����Ͽ����ϴ�.");
			mvo = md.selectLoginById(mvo);
			return mvo;
		}

	}

	// ȸ�� ���
	public void register() throws Exception {
		Scanner scan = new Scanner(System.in);
		MemberDAO md = new MemberDAO();
		MemberVO mvo = new MemberVO();
		int count = 0;
		boolean idCheck = false;

		String memberID = null;
		String memberPW = null;
		String memberName = null;
		String memberPhone = null;

		System.out.println();
		System.out.println("���ο� ���� ��� �Է�");
		do {
			System.out.print("���̵� : ");
			memberID = scan.nextLine();
			mvo.setMemberId(memberID);
			idCheck = md.selectByIdCheck(mvo);
			if (idCheck == true) {
				System.out.println("�ߺ��� ���̵��Դϴ�. �ٽ� �Է��ϼ���");
			}
		} while (idCheck == true);
		System.out.print("��й�ȣ : ");
		memberPW = scan.nextLine();
		System.out.print("ȸ���̸� : ");
		memberName = scan.nextLine();
		System.out.print("��ȭ��ȣ : ");
		memberPhone = scan.nextLine();

		mvo.setMemberId(memberID);
		mvo.setMemberPW(memberPW);
		mvo.setMemberName(memberName);
		mvo.setMemberPhone(memberPhone);

		count = md.insert(mvo);
		if (count == 0) {
			System.out.println("ȸ�����Կ� �����Ͽ����ϴ�.");
		} else {
			System.out.println("ȸ�����Կ� �����Ͽ����ϴ�.");
		}
		System.out.println();

	}

	// ȸ�� ���� ����
	public void update() throws Exception {
		Scanner scan = new Scanner(System.in);

		MemberDAO md = new MemberDAO();
		MemberVO mvo = new MemberVO();

		String memberID = null;
		String memberName = null;
		String memberPhone = null;

		list();
		System.out.println();

		System.out.println("������ ȸ�� ���̵� �Է�");
		System.out.print("ȸ�����̵� : ");
		memberID = scan.nextLine();

		System.out.println();
		System.out.println("���ο� ���� ��� �Է�");
		System.out.print("������ ȸ���̸�: : ");
		memberName = scan.nextLine();
		System.out.print("������ �ڵ�����ȣ : ");
		memberPhone = scan.nextLine();

		mvo.setMemberId(memberID);
		mvo.setMemberName(memberName);
		mvo.setMemberPhone(memberPhone);
		int count = md.update(mvo);
		if (count == 0) {
			System.out.println("ȸ�� ���� ������ ������ �߻��߽��ϴ�.");
			return;
		} else {
			System.out.println("ȸ�� ���� ������ �Ϸ�ƽ��ϴ�.");
		}
		System.out.println();
		list();
		System.out.println();
	}

	// ȸ�� ���� ����
	public void delete() throws Exception {
		Scanner scan = new Scanner(System.in);

		MemberDAO md = new MemberDAO();
		MemberVO mvo = new MemberVO();

		String memberID; // �Է��� ���̵�
		list();
		System.out.println();

		System.out.println("������ ȸ�����̵� �Է�");
		System.out.print("ȸ�����̵� : ");
		memberID = scan.nextLine();
		mvo.setMemberId(memberID);
		int count = md.deleteByNum(mvo);
		if (count == 0) {
			System.out.printf("%s���̵��� ȸ�� ������ ������ �߻��߽��ϴ�. \n", memberID);
			return;
		} else {
			System.out.printf("%s���̵��� ȸ�� ������ �����߽��ϴ�. \n", memberID);
		}
		System.out.println();
		list();
		System.out.println();
	}
}
