package com.ktdsuniversity.edu.members.service;

import com.ktdsuniversity.edu.members.vo.MembersListVO;
import com.ktdsuniversity.edu.members.vo.request.SearchMembersVO;

public interface MembersService {
	
	public MembersListVO readAllMembers(SearchMembersVO searchMembersVO);

}
