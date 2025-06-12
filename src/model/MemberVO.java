package model;

public class MemberVO {
	private String memberID;
	private String memberPW;
	private String memberName;
	private String memberPhone;
	private int manager;
	public MemberVO() {
		super();
	}
	public MemberVO(String memberId, String memberPW, String memberName, String memberPhone, int manager) {
		super();
		this.memberID = memberId;
		this.memberPW = memberPW;
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.manager = manager;
	}
	public String getMemberId() {
		return memberID;
	}
	public void setMemberId(String memberId) {
		this.memberID = memberId;
	}
	public String getMemberPW() {
		return memberPW;
	}
	public void setMemberPW(String memberPW) {
		this.memberPW = memberPW;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public int getManager() {
		return manager;
	}
	public void setManager(int manager) {
		this.manager = manager;
	}
	@Override
	public String toString() {
	    return String.format(
	        "[아이디: %-10s | 패스워드: %-10s | 이름: %-6s | 전화번호: %-13s]",
	        memberID, memberPW, memberName, memberPhone
	    );
	}

	
	
}
