package com.ktdsuniversity.edu.articles.vo.response;

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

	public String getId() {
		return this.id;
	}

	public String getSubject() {
		return this.subject;
	}

	public String getContent() {
		return this.content;
	}

	public String getEmail() {
		return this.email;
	}

	public long getViewCnt() {
		return this.viewCnt;
	}

	public long getRecommendCnt() {
		return this.recommendCnt;
	}

	public String getDelYn() {
		return this.delYn;
	}

	public String getCrtDt() {
		return this.crtDt;
	}

	public String getMdfyDt() {
		return this.mdfyDt;
	}

	public String getFileSetId() {
		return this.fileSetId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setViewCnt(long viewCnt) {
		this.viewCnt = viewCnt;
	}

	public void setRecommendCnt(long recommendCnt) {
		this.recommendCnt = recommendCnt;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}

	public void setMdfyDt(String mdfyDt) {
		this.mdfyDt = mdfyDt;
	}

	public void setFileSetId(String fileSetId) {
		this.fileSetId = fileSetId;
	}

}
