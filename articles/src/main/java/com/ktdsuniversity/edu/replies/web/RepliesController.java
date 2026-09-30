package com.ktdsuniversity.edu.replies.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.commons.util.ApiResponse;
import com.ktdsuniversity.edu.replies.service.RepliesService;
import com.ktdsuniversity.edu.replies.vo.request.ModifyReplyVO;
import com.ktdsuniversity.edu.replies.vo.request.RegistReplyVO;
import com.ktdsuniversity.edu.replies.vo.response.RepliesListVO;
import com.ktdsuniversity.edu.replies.vo.response.RepliesVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController // @Controller + @ResponseBody 메소드에 ResponseBody 생략 가능
public class RepliesController {
	
	private RepliesService repliesService;

	// Get /replies/{게시글 아이디}
	// 게시글에 등록된 댓글들을 반환
	@GetMapping("/articles/{articleId}/replies")
	public ApiResponse<RepliesListVO> getRepliesFromArticle(@PathVariable String articleId) {
		try {
			RepliesListVO result = this.repliesService.readRepliesFromArticle(articleId);
			return ApiResponse.OK(result);
		}catch(IllegalArgumentException iae) {
			return ApiResponse.FORBIDDEN(iae.getMessage());
		}
	}
	
	// POST /replies/{게시글아이디}
	// 게시글에 댓글 작성(파일 첨부 가능)
	@PostMapping("/articles/{articleId}/replies")
	public ApiResponse<RepliesVO> makeNewReply(@PathVariable String articleId, RegistReplyVO registReplyVO) {
		try {
			RepliesVO result = this.repliesService.createNewReply(articleId, registReplyVO);
			return ApiResponse.CREATED(result);
		}catch(IllegalArgumentException iae) {
			return ApiResponse.FORBIDDEN(iae.getMessage());
		}
		
	}

	// PUT /replies/{게시글아이디}/{댓글아이디}
	// 게시글에 등록된 댓글을 수정(파일 첨부 가능)
	@PutMapping("/articles/{articleId}/replies/{replyId}")
	public ApiResponse<RepliesVO> updateReply(@PathVariable String replyId, ModifyReplyVO modifyReplyVO){
		try {
			RepliesVO result = this.repliesService.updateReply(replyId, modifyReplyVO);
			return ApiResponse.OK(result);
		}catch(IllegalArgumentException iae) {
			return ApiResponse.FORBIDDEN(iae.getMessage());
		}
		
	}

	// DELETE /replies/{게시글아이디}/{댓글아이디}
	// 게시글에 등록된 댓글 하나를 삭제
	// 첨부된 파일 제거
	@DeleteMapping("/articles/{articleId}/replies/{replyId}")
	public ApiResponse<String> deleteReply(@PathVariable String replyId){
		try {
			String result = this.repliesService.deleteReply(replyId);
			return ApiResponse.OK(result);
		}catch(IllegalArgumentException iae) {
			return ApiResponse.FORBIDDEN(iae.getMessage());
		}
	}

	// PUT /replies/{게시글아이디}/recommend/{댓글아이디}
	// 게시글에 등록된 댓글 하나를 추천
	@PutMapping("/articles/{articleId}/replies/recommend/{replyId}")
	public ApiResponse<Long> recommendReply(@PathVariable String replyId){
		try {
			Long result = this.repliesService.recommnedOneReply(replyId);
			return ApiResponse.OK(result);
		}catch(IllegalArgumentException iae) {
			return ApiResponse.FORBIDDEN(iae.getMessage());
		}
		
	}

}
