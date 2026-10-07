package com.ktdsuniversity.edu.replies.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ktdsuniversity.edu.replies.vo.request.ModifyReplyVO;
import com.ktdsuniversity.edu.replies.vo.request.RegistReplyVO;
import com.ktdsuniversity.edu.replies.vo.response.RepliesVO;

@Mapper
public interface RepliesDao {
	
	long selectRepliesCount(String articleId);
	
	List<RepliesVO> selectAllRepliesByArticleId(String articleId);
	
	int insertNewReply(@Param("articleId") String articleId, 
					   @Param("registReplies") RegistReplyVO registRepliesVO);

	RepliesVO selectReplyByReplyId(@Param("articleId") String articleId, 
								   @Param("replyId") String replyId);

	int updateReply(@Param("articleId") String articleId,
					@Param("replyId") String replyId,
					@Param("modifyReplies") ModifyReplyVO modifyRepliesVO);

	int deleteReply(@Param("articleId") String articleId, 
							 @Param("replyId") String replyId);

	int updateRecommendReply(@Param("articleId") String articleId, 
									 @Param("replyId") String replyId);
	
}
