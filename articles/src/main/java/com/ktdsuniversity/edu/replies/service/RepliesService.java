package com.ktdsuniversity.edu.replies.service;

import com.ktdsuniversity.edu.replies.vo.request.ModifyReplyVO;
import com.ktdsuniversity.edu.replies.vo.request.RegistReplyVO;
import com.ktdsuniversity.edu.replies.vo.response.RepliesListVO;
import com.ktdsuniversity.edu.replies.vo.response.RepliesVO;

import jakarta.validation.constraints.Pattern;

public interface RepliesService {
	
	RepliesListVO readRepliesFromArticle(String articleId);
	
	RepliesVO createNewReply(String articleId, RegistReplyVO registReplyVO);

	RepliesVO updateReply(String articleId, String replyId, ModifyReplyVO modifyReplyVO);

	String deleteReply(String articleId, String replyId);

	Long recommnedOneReply(String articleId, String replyId);

	RepliesListVO readAllRepliesByArticleId(
			@Pattern(regexp = "^AR-[0-9]{8}-[0-9]{6,8}$", message = "잘못된 요청입니다.") String articleId);
}
