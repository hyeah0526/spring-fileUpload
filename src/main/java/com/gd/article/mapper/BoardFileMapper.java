package com.gd.article.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gd.article.dto.BoardFile;

@Mapper
public interface BoardFileMapper {

	int insertBoardFile(BoardFile file);
}
