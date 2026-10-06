package com.ktdsuniversity.edu.replies.web;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.commons.util.ApiResponse;
import com.ktdsuniversity.edu.members.vo.response.MembersVO;
import com.ktdsuniversity.edu.replies.service.RepliesService;
import com.ktdsuniversity.edu.replies.vo.request.ModifyReplyVO;
import com.ktdsuniversity.edu.replies.vo.request.RegistReplyVO;
import com.ktdsuniversity.edu.replies.vo.response.RepliesListVO;
import com.ktdsuniversity.edu.replies.vo.response.RepliesVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController // @Controller + @ResponseBody 메소드에 ResponseBody 생략 가능
public class RepliesController {
	
	private RepliesService repliesService;

	// Get /replies/{게시글 아이디}
	// 게시글에 등록된 댓글들을 반환
	@GetMapping("/articles/{articleId}/replies")
	public ApiResponse<RepliesListVO> getReplies(
			@Pattern(regexp = "^AR-[0-9]{8}-[0-9]{6,8}$", message="잘못된 요청입니다.") 
			@PathVariable String articleId) {
		try {
			return ApiResponse.OK(this.repliesService.readAllRepliesByArticleId(articleId));
		} catch (IllegalArgumentException iae) {
			return ApiResponse.FORBIDDEN(iae.getMessage());
		}
	}
	
	// POST /replies/{게시글아이디}
	// 게시글에 댓글 작성(파일 첨부 가능)
	@PostMapping("/articles/{articleId}/replies")
	public ApiResponse<RepliesVO> makeNewReply(
			@Pattern(regexp = "^AR-[0-9]{8}-[0-9]{6,8}$", message="잘못된 요청입니다.")
			@PathVariable String articleId, 
			@Valid @ModelAttribute RegistReplyVO registRepliesVO,
			BindingResult validationResult,
			HttpSession session,
			// HttpSession에 등록된 __LOGIN_USER__에 있는 MembersVO를 파라미터로 받아와라
			@SessionAttribute("__LOGIN_USER__") MembersVO membersVO) {

		if (validationResult.hasErrors()) {
			return ApiResponse.BAD_REQUEST(validationResult.getFieldErrors());
		}

		registRepliesVO.setEmail( membersVO.getEmail() );
		try {
			RepliesVO result = this.repliesService.createNewReply(articleId, registRepliesVO);
			return ApiResponse.CREATED(result);
		}catch(IllegalArgumentException iae) {
			return ApiResponse.FORBIDDEN(iae.getMessage());
		}
		
	}

	// PUT /replies/{게시글아이디}/{댓글아이디}
	// 게시글에 등록된 댓글을 수정(파일 첨부 가능)
	@PutMapping("/articles/{articleId}/replies/{replyId}")
	public ApiResponse<RepliesVO> updateReply(@Pattern(regexp = "^AR-[0-9]{8}-[0-9]{6,8}$", message="잘못된 요청입니다.")
			@PathVariable String articleId, 
			@Pattern(regexp = "^RP-[0-9]{8}-[0-9]{6,8}$", message="잘못된 요청입니다.")
			@PathVariable String replyId,
			@Valid @ModelAttribute ModifyReplyVO modifyRepliesVO,
			BindingResult validationResult){
		try {
			RepliesVO result = this.repliesService.updateReply(articleId, replyId, modifyRepliesVO);
			return ApiResponse.OK(result);
		}catch(IllegalArgumentException iae) {
			return ApiResponse.FORBIDDEN(iae.getMessage());
		}
		
	}

	// DELETE /replies/{게시글아이디}/{댓글아이디}
	// 게시글에 등록된 댓글 하나를 삭제
	// 첨부된 파일 제거
	@DeleteMapping("/articles/{articleId}/replies/{replyId}")
	public ApiResponse<String> deleteReply(
			@Pattern(regexp = "^AR-[0-9]{8}-[0-9]{6,8}$", message="잘못된 요청입니다.")
			@PathVariable String articleId, 
			@Pattern(regexp = "^RP-[0-9]{8}-[0-9]{6,8}$", message="잘못된 요청입니다.")
			@PathVariable String replyId){
		try {
			String result = this.repliesService.deleteReply(articleId, replyId);
			return ApiResponse.OK(result);
		}catch(IllegalArgumentException iae) {
			return ApiResponse.FORBIDDEN(iae.getMessage());
		}
	}

	// PUT /replies/{게시글아이디}/recommend/{댓글아이디}
	// 게시글에 등록된 댓글 하나를 추천
	@PutMapping("/articles/{articleId}/replies/recommend/{replyId}")
	public ApiResponse<Long> recommendReply(@Pattern(regexp = "^AR-[0-9]{8}-[0-9]{6,8}$", message="잘못된 요청입니다.")
			@PathVariable String articleId, 
			@Pattern(regexp = "^RP-[0-9]{8}-[0-9]{6,8}$", message="잘못된 요청입니다.")
			@PathVariable String replyId){
		try {
			Long result = this.repliesService.recommnedOneReply(articleId, replyId);
			return ApiResponse.OK(result);
		}catch(IllegalArgumentException iae) {
			return ApiResponse.FORBIDDEN(iae.getMessage());
		}
		
	}

}
