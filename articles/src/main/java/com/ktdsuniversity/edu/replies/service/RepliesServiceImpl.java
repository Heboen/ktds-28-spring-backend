package com.ktdsuniversity.edu.replies.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

	private RepliesDao replyDao;
	private MultipartHandler multipartHandler;

	@Override
	public RepliesListVO readRepliesFromArticle(String articleId) {
		List<RepliesVO> replylist = this.replyDao.selectRepliesByArticleId(articleId);
		long cnt = replylist.size();

		RepliesListVO list = new RepliesListVO();
		list.setReplieCount(cnt);
		list.setReplieList(replylist);

		return list;
	}

	@Override
	public RepliesVO createNewReply(String articleId, RegistReplyVO registReplyVO) {
		
		String fileSetId = this.multipartHandler.storeFiles(registReplyVO.getFile(), registReplyVO.getEmail());

		registReplyVO.setFileSetId(fileSetId);
		registReplyVO.setArticleId(articleId);

		int insertRows = this.replyDao.insertNewReply(articleId, registReplyVO);
		System.out.println(insertRows + "개의 row가 생성되었습니다.");

		if (insertRows > 0) {
			return this.replyDao.selectRepliesByReplyId(registReplyVO.getId());
		}

		throw new IllegalArgumentException("입력값이 유효하지 않습니다.");
	}

	@Override
	public RepliesVO updateReply(String replyId, ModifyReplyVO modifyReplyVO) {
		RepliesVO reply = this.replyDao.selectRepliesByReplyId(replyId);
		
		String fileSetId = this.multipartHandler.storeFiles(modifyReplyVO.getFile(), modifyReplyVO.getEmail(),
				modifyReplyVO.getFileSetId());
		
		modifyReplyVO.setFileSetId(fileSetId);
		
		int updatedRows = this.replyDao.updateReply(replyId, modifyReplyVO);
		
		if(updatedRows == 0) {
			throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
		}
		return this.replyDao.selectRepliesByReplyId(replyId);	
	}

	@Override
	public String deleteReply(String replyId) {
		RepliesVO reply = this.replyDao.selectRepliesByReplyId(replyId);
		
		int deleteRow = this.replyDao.deleteReply(replyId);
		if(deleteRow == 0) {
			throw new IllegalArgumentException("삭제된 게시글이 없습니다.");
		}
		int deleteFileCnt = this.multipartHandler.deleteFiles(reply.getFileSetId());
		System.out.println(deleteFileCnt + "개의 파일이 삭제되었습니다.");
		return replyId;
	}

	@Override
	public Long recommnedOneReply(String replyId) {
		
		int updatedRow = this.replyDao.updateRecommendReply(replyId);
		
		if(updatedRow == 0) {
			throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
		}
		System.out.println(updatedRow + "개의 게시글이 추천되었습니다.");
		return this.replyDao.selectRepliesByReplyId(replyId).getRecommendCnt();

	}
	
	

}
