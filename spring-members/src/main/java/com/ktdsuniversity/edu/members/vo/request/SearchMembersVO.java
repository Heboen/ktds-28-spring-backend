package com.ktdsuniversity.edu.members.vo.request;

import com.ktdsuniversity.edu.members.commons.vo.PaginationVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SearchMembersVO extends PaginationVO {

	private String email;
	private String name;
	private String nickname;
	private String registDate;
}
