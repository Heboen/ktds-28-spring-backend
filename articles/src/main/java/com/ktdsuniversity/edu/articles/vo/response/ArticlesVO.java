package com.ktdsuniversity.edu.articles.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter //멤버변수들의 Getter 자동생성
//@Setter //멤버변수들의 Setter 자동생성
//@ToString // ToString() 메소드 자동생성
@Data // 위의 3가지를 한번에 자동생성
//@NoArgsConstructor // 기본 생성자 자동생성
//@AllArgsConstructor // 모든 멤버변수를 파라미터로 가지는 생성자 자동생성
public class ArticlesVO {
	private String id;
	private String subject;
	private String content;
	private String email;
	private long viewCnt;
	private long recommendCnt;
	private String delYn;
	private String crtDt;
	private String mdfyDt;
	private String fileSetId;

}
