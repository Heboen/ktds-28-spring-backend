package com.ktdsuniversity.edu.files.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ktdsuniversity.edu.articles.vo.request.ModifyArticleVO;
import com.ktdsuniversity.edu.files.vo.request.RequestFileSetVO;
import com.ktdsuniversity.edu.files.vo.request.RequestFileVO;
import com.ktdsuniversity.edu.files.vo.response.FileSetVO;

@Mapper
public interface FilesDao {

	int insertNewFileSet(RequestFileSetVO requestFileSetVO);

	int insertNewFile(RequestFileVO requestFileVO);
	
	RequestFileSetVO selectFileSet(String fileSetId);

	int updateFileSet(String fileSetId);
}
