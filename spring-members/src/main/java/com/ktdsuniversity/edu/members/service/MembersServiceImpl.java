package com.ktdsuniversity.edu.members.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.vo.MembersListVO;
import com.ktdsuniversity.edu.members.vo.MembersVO;

@Service
public class MembersServiceImpl implements MembersService {
	
	private MembersDao membersDao;

	public MembersServiceImpl(MembersDao membersDao) {
		this.membersDao = membersDao;
	}

	@Override
	public MembersListVO readAllMembers() {
		List<MembersVO> membersList = membersDao.getMembersList();
		long membersCount = membersDao.getMembersCount();
		
		MembersListVO membersListVO = new MembersListVO();
		membersListVO.setMembersCount(membersCount);
		membersListVO.setMembersList(membersList);
		
		return membersListVO;
	}
	
	

}
