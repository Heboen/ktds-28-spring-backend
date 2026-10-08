package com.ktdsuniversity.edu.members.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.SearchMembersVO;

@Mapper
public interface MembersDao {
	
	long getMembersCount(SearchMembersVO searchMembersVO);
	
	List<MembersVO> getMembersList(SearchMembersVO searchMembersVO);

}
