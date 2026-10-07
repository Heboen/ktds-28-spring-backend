package com.ktdsuniversity.edu.replies.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.articles.dao.ArticlesDao;
import com.ktdsuniversity.edu.articles.vo.response.ArticlesVO;
import com.ktdsuniversity.edu.commons.exceptions.ArticleException;
import com.ktdsuniversity.edu.commons.exceptions.enums.ArticleCodes;
import com.ktdsuniversity.edu.commons.exceptions.enums.ExceptionType;
import com.ktdsuniversity.edu.files.components.MultipartHandler;
import com.ktdsuniversity.edu.replies.dao.RepliesDao;
import com.ktdsuniversity.edu.replies.vo.request.ModifyReplyVO;
import com.ktdsuniversity.edu.replies.vo.request.RegistReplyVO;
import com.ktdsuniversity.edu.replies.vo.response.RepliesListVO;
import com.ktdsuniversity.edu.replies.vo.response.RepliesVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RepliesServiceImpl implements RepliesService {

	private static final Logger logger = LoggerFactory.getLogger(RepliesServiceImpl.class);
	
	private ArticlesDao articlesDao;
	private RepliesDao replyDao;
	private MultipartHandler multipartHandler;

	@Override
	public RepliesListVO readAllRepliesByArticleId(String articleId) {
		
		ArticlesVO articles = this.articlesDao.selectArticleByArticleId(articleId);
		if (articles == null) {
//			throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
			throw new ArticleException(ExceptionType.REPLIES, ArticleCodes.NOT_EXISTS);
		}
		
		RepliesListVO list = new RepliesListVO();
		list.setReplyCount(this.replyDao.selectRepliesCount(articleId));
		list.setReplyList(this.replyDao.selectAllRepliesByArticleId(articleId));
		return list;
	}
	
	@Override
	public RepliesListVO readRepliesFromArticle(String articleId) {
		List<RepliesVO> replylist = this.replyDao.selectAllRepliesByArticleId(articleId);
		long cnt = replylist.size();

		RepliesListVO list = new RepliesListVO();
		list.setReplyCount(cnt);
		list.setReplyList(replylist);

		return list;
	}

	@Transactional
	@Override
	public RepliesVO createNewReply(String articleId, RegistReplyVO registReplyVO) {
		
		String fileSetId = this.multipartHandler.storeFiles(registReplyVO.getFile(), registReplyVO.getEmail());

		registReplyVO.setFileSetId(fileSetId);
		registReplyVO.setArticleId(articleId);

		int insertRows = this.replyDao.insertNewReply(articleId, registReplyVO);
//		System.out.println(insertRows + "개의 row가 생성되었습니다.");
		logger.info("{}개의 Row가 생성되었습니다.", insertRows);

		if (insertRows > 0) {
			return this.replyDao.selectReplyByReplyId(articleId, registReplyVO.getId());
		}

//		throw new IllegalArgumentException("입력값이 유효하지 않습니다.");
		throw new ArticleException(ExceptionType.REPLIES, ArticleCodes.SYSTEM_ERROR);
	}

	@Transactional
	@Override
	public RepliesVO updateReply(String articleId, String replyId, ModifyReplyVO modifyReplyVO) {
		RepliesVO reply = this.replyDao.selectReplyByReplyId(articleId, replyId);
		
		String fileSetId = this.multipartHandler.storeFiles(modifyReplyVO.getFile(), modifyReplyVO.getEmail(),
				modifyReplyVO.getFileSetId());
		
		modifyReplyVO.setFileSetId(fileSetId);
		
		int updatedRows = this.replyDao.updateReply(articleId, replyId, modifyReplyVO);
		
		if(updatedRows == 0) {
//			throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
			throw new ArticleException(ExceptionType.ARTICLES, ArticleCodes.NOT_EXISTS);
		}
		return this.replyDao.selectReplyByReplyId(articleId, replyId);	
	}

	@Transactional
	@Override
	public String deleteReply(String articleId, String replyId) {
		RepliesVO reply = this.replyDao.selectReplyByReplyId(articleId, replyId);
		
		int deleteRow = this.replyDao.deleteReply(articleId, replyId);
		if(deleteRow == 0) {
//			throw new IllegalArgumentException("삭제된 게시글이 없습니다.");
			throw new ArticleException(ExceptionType.REPLIES, ArticleCodes.BAD_REQUEST);
		}
		int deleteFileCnt = this.multipartHandler.deleteFiles(reply.getFileSetId());
//		System.out.println(deleteFileCnt + "개의 파일이 삭제되었습니다.");
		logger.info("{}개의 파일이 삭제되었습니다.", deleteFileCnt);
		return replyId;
	}

	@Transactional
	@Override
	public Long recommnedOneReply(String articleId, String replyId) {
		
		int updatedRow = this.replyDao.updateRecommendReply(articleId, replyId);
		
		if(updatedRow == 0) {
//			throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
			throw new ArticleException(ExceptionType.ARTICLES, ArticleCodes.NOT_EXISTS);
		}
//		System.out.println(updatedRow + "개의 게시글이 추천되었습니다.");
		logger.info("{}개의 댓글이 추천되었습니다.", updatedRow);
		return this.replyDao.selectReplyByReplyId(articleId, replyId).getRecommendCnt();

	}
	
	

}
