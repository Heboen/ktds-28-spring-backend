package com.ktdsuniversity.edu.members.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.members.service.MembersService;
import com.ktdsuniversity.edu.members.vo.MembersListVO;
import com.ktdsuniversity.edu.members.vo.request.SearchMembersVO;

@Controller
public class MembersController {
	
	private MembersService membersService;
	
	
	public MembersController(MembersService membersService) {
		this.membersService = membersService;
	}

	@GetMapping("/members/list")
	@ResponseBody
	public MembersListVO getMembers(SearchMembersVO searchMembersVO) {
		return this.membersService.readAllMembers(searchMembersVO);
	}

}
