package com.ktdsuniversity.edu.members.commons.vo;

import lombok.Data;

@Data
public class PaginationVO {

	private long pageNo;
	
	private int listSize;
	
	private long pageCount;
	
	public PaginationVO() {
		this.listSize = 10;
	}
	
	public void calculatePage(long itemCount) {
		this.pageCount = Math.ceilDiv(itemCount, listSize);
	}
}
