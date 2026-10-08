package com.ktdsuniversity.edu.members.vo.response;

import java.util.List;

import lombok.Data;

@Data
public class MembersListVO {

	private long membersCount;

	private List<MembersVO> membersList;
}
