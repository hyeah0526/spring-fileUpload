package com.gd.article.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gd.article.dto.BoardFile;

@Mapper
public interface BoardFileMapper {
	
	// 등록
	int insertBoardFile(BoardFile file);
	
	// 삭제
	int deleteBoardFile(int articleNo);
	
	// 삭제를 위해 fileName만 가져오기
	String selectFileName(int articleNo);
	
	// 수정
	int updateBoardFile(BoardFile bf);
	
	// 수정을 위해 가져오기, file이 여러개이면 List가 되어야 함
	String selectFileNameByKey(int fileNo);
}
