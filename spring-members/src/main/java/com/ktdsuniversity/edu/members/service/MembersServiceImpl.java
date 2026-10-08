package com.ktdsuniversity.edu.members.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.vo.MembersListVO;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.SearchMembersVO;

@Service
public class MembersServiceImpl implements MembersService {
	
	private static final Logger logger = LoggerFactory.getLogger(MembersServiceImpl.class);
	
	private MembersDao membersDao;

	public MembersServiceImpl(MembersDao membersDao) {
		this.membersDao = membersDao;
	}

	@Override
	public MembersListVO readAllMembers(SearchMembersVO searchMembersVO) {
		List<MembersVO> membersList = membersDao.getMembersList(searchMembersVO);
		long membersCount = membersDao.getMembersCount(searchMembersVO);
		
		searchMembersVO.calculatePage(membersCount);
		
		MembersListVO membersListVO = new MembersListVO();
		membersListVO.setMembersCount(membersCount);
		membersListVO.setMembersList(membersList);
		
		return membersListVO;
	}
	
	

}
