package com.ktdsuniversity.edu.replies.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ktdsuniversity.edu.replies.vo.request.ModifyReplyVO;
import com.ktdsuniversity.edu.replies.vo.request.RegistReplyVO;
import com.ktdsuniversity.edu.replies.vo.response.RepliesVO;

@Mapper
public interface RepliesDao {

	List<RepliesVO> selectRepliesByArticleId(String articleId);

	int insertNewReply(@Param("articleId") String articleId, @Param("registReplyVO")RegistReplyVO registReplyVO);

	RepliesVO selectRepliesByReplyId(String replyId);

	int updateReply(@Param("replyId")String replyId, @Param("modifyReplyVO") ModifyReplyVO modifyReplyVO);

	int deleteReply(String replyId);

	int updateRecommendReply(String replyId);

}
