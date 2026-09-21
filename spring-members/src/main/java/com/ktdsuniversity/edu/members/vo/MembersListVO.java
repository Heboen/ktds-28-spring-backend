package com.ktdsuniversity.edu.members.vo;

import java.util.List;

public class MembersListVO {
	
	private long membersCount;
	
	private List<MembersVO> membersList;

	public long getMembersCount() {
		return this.membersCount;
	}

	public void setMembersCount(long membersCount) {
		this.membersCount = membersCount;
	}

	public List<MembersVO> getMembersList() {
		return this.membersList;
	}

	public void setMembersList(List<MembersVO> membersList) {
		this.membersList = membersList;
	}
	
	

}
