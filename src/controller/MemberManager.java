package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.MemberVO;

public class MemberManager {
	// 회원 목록
	public void list() throws Exception {
		MemberDAO md = new MemberDAO();
		System.out.println("회원 전체 리스트");
		ArrayList<MemberVO> memberList = md.selectAll();
		if (memberList.size() <= 0) {
			System.out.println("회원전체리스트에 데이터가 없습니다.");
			return;
		} else if (memberList == null) {
			System.out.println("회원전체리스트에 에러가 발생했습니다.");
			return;
		}
		for (MemberVO data : memberList) {
			System.out.println(data.toString());
		}
		System.out.println();
	}

	// 로그인
	public MemberVO login() throws Exception {
		Scanner scan = new Scanner(System.in);
		MemberDAO md = new MemberDAO();
		MemberVO mvo = new MemberVO();
		boolean loginCheck = false;

		String memberID = null;
		String memberPW = null;

		System.out.println();
		System.out.println("로그인 아이디/패스워드 입력");
		System.out.print("아이디 : ");
		memberID = scan.nextLine();
		System.out.print("비밀번호 : ");
		memberPW = scan.nextLine();
		mvo.setMemberId(memberID);
		mvo.setMemberPW(memberPW);

		loginCheck = md.loginCheck(mvo);
		if (loginCheck == false) {
			System.out.println("로그인에 실패하였습니다.");
			return null;
		} else {
			System.out.println("로그인에 성공하였습니다.");
			mvo = md.selectLoginById(mvo);
			return mvo;
		}

	}

	// 회원 등록
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
		System.out.println("새로운 정보 모두 입력");
		do {
			System.out.print("아이디 : ");
			memberID = scan.nextLine();
			mvo.setMemberId(memberID);
			idCheck = md.selectByIdCheck(mvo);
			if (idCheck == true) {
				System.out.println("중복된 아이디입니다. 다시 입력하세요");
			}
		} while (idCheck == true);
		System.out.print("비밀번호 : ");
		memberPW = scan.nextLine();
		System.out.print("회원이름 : ");
		memberName = scan.nextLine();
		System.out.print("전화번호 : ");
		memberPhone = scan.nextLine();

		mvo.setMemberId(memberID);
		mvo.setMemberPW(memberPW);
		mvo.setMemberName(memberName);
		mvo.setMemberPhone(memberPhone);

		count = md.insert(mvo);
		if (count == 0) {
			System.out.println("회원가입에 실패하였습니다.");
		} else {
			System.out.println("회원가입에 성공하였습니다.");
		}
		System.out.println();

	}

	// 회원 수정 관리
	public void update() throws Exception {
		Scanner scan = new Scanner(System.in);

		MemberDAO md = new MemberDAO();
		MemberVO mvo = new MemberVO();

		String memberID = null;
		String memberName = null;
		String memberPhone = null;

		list();
		System.out.println();

		System.out.println("수정할 회원 아이디 입력");
		System.out.print("회원아이디 : ");
		memberID = scan.nextLine();

		System.out.println();
		System.out.println("새로운 정보 모두 입력");
		System.out.print("수정할 회원이름: : ");
		memberName = scan.nextLine();
		System.out.print("수정할 핸드폰번호 : ");
		memberPhone = scan.nextLine();

		mvo.setMemberId(memberID);
		mvo.setMemberName(memberName);
		mvo.setMemberPhone(memberPhone);
		int count = md.update(mvo);
		if (count == 0) {
			System.out.println("회원 정보 수정에 오류가 발생했습니다.");
			return;
		} else {
			System.out.println("회원 정보 수정이 완료됐습니다.");
		}
		System.out.println();
		list();
		System.out.println();
	}

	// 회원 삭제 관리
	public void delete() throws Exception {
		Scanner scan = new Scanner(System.in);

		MemberDAO md = new MemberDAO();
		MemberVO mvo = new MemberVO();

		String memberID; // 입력한 아이디
		list();
		System.out.println();

		System.out.println("삭제할 회원아이디 입력");
		System.out.print("회원아이디 : ");
		memberID = scan.nextLine();
		mvo.setMemberId(memberID);
		int count = md.deleteByNum(mvo);
		if (count == 0) {
			System.out.printf("%s아이디의 회원 삭제에 오류가 발생했습니다. \n", memberID);
			return;
		} else {
			System.out.printf("%s아이디의 회원 삭제가 성공했습니다. \n", memberID);
		}
		System.out.println();
		list();
		System.out.println();
	}
}
