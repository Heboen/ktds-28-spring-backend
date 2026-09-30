package com.ktdsuniversity.edu.replies.service;

import com.ktdsuniversity.edu.replies.vo.request.ModifyReplyVO;
import com.ktdsuniversity.edu.replies.vo.request.RegistReplyVO;
import com.ktdsuniversity.edu.replies.vo.response.RepliesListVO;
import com.ktdsuniversity.edu.replies.vo.response.RepliesVO;

public interface RepliesService {
	
	RepliesListVO readRepliesFromArticle(String articleId);
	
	RepliesVO createNewReply(String articleId, RegistReplyVO registReplyVO);

	RepliesVO updateReply(String replyId, ModifyReplyVO modifyReplyVO);

	String deleteReply(String replyId);

	Long recommnedOneReply(String replyId);
}
