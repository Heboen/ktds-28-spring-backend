package com.ktdsuniversity.edu.commons.beans;

import java.io.PrintWriter;

import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.ktdsuniversity.edu.commons.util.ApiResponse;
import com.ktdsuniversity.edu.members.vo.response.MembersVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CheckSessionInterceptor implements HandlerInterceptor {
	/**
	     * Controller 실행 전에 Interceptor가 개입
	     * @param request 브라우저가 서버에게 요청한 정보
	     * @param response 서버가 브라우저에게 응답할 정보
	     * @param handler 실행할 Controller
	     * @return controller 실행을 계속할것인지 여부 
	     *         (false 일 경우, 컨트롤러 실행을 하지 않고 즉시 응답해버린다.)
	*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 세션을 검사하고
		HttpSession session = request.getSession();
		
		MembersVO membersVO = (MembersVO) session.getAttribute("__LOGIN_USER__");
		// 세션이 있으면 컨트롤러 실행
		if(membersVO != null) {
			return true;
		}
		else {
			
			// Response의 Content-Type을 JSON(application/json)으로 설정
			response.setContentType("application/json");
			
			// 클라이언트가 표현할 인토딩을 UTF-8로 설정
			response.setCharacterEncoding("UTF-8");
			
			// 클라이언트에게 응답메세지를 직접 전달할 수 있는 객체
			// Servlet Code를 작성할 때에 필수코드
			PrintWriter printWriter = response.getWriter();
			
//			printWriter.write("{JSON 메세지 직접 작성}");
			
			ApiResponse<String> errorResponse = ApiResponse.FORBIDDEN("로그인이 필요한 기능입니다.");
			// Gson은 Jackson Databind 보다 느리지만 쉬움 (크게 차이는 안남)
			// errorResponse ==> JSON으로 변환 (Jackson Databind 라이브러리 ==> @ResponseBody, Gson
			Gson gson = new Gson();
			String errorJson = gson.toJson(errorResponse);
			
			// printWriter에게 write
			printWriter.write(errorJson);
			
			// printWriter에 작성한 내용들이 클라이언트에게 전달된다.
			printWriter.flush();
			
			// 세션이 없으면 컨트롤러 실행 X ==> 클라이언트에게 예외 메세지 전달
			return false;
		}
	}


}
