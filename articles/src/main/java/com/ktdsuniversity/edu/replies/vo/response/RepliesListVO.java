package com.ktdsuniversity.edu.replies.vo.response;

import java.util.List;

import lombok.Data;

@Data
public class RepliesListVO {
	
	/**
	 * 검색된 댓글의 총 갯수
	 */
	private long replieCount;
	
	/**
	 * 검색된 댓글의 목록
	 */
	private List<RepliesVO> replieList;
}
