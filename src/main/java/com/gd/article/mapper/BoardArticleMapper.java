package com.gd.article.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.article.dto.BoardArticle;

@Mapper
public interface BoardArticleMapper { 
	
	// interface는 public abstract만 가지고 있음 public abstract int insertBoardArticle(BoardArticle article)
	// public abstract를 생략하고 아래처럼 작성한 것
	int insertBoardArticle(BoardArticle article);
	
	List<Map<String, Object>> selectBoardList(int beginRow, int rowPerPage, String searchWord);
	
	int getListTotalCnt(String searchWord);
}
