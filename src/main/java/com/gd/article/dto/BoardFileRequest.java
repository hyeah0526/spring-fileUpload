package com.gd.article.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardFileRequest {
	// 수정을 위한 dto
	private int fileNo;
	private MultipartFile file;
	
}
