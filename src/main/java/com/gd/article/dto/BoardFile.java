package com.gd.article.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardFile {
	private int fileNo;
	private int articleNo;
	private String fileName;
	private String originalName;
	private String fileType;
	private long fileSize;
	private String createDate;
	private String updateDate;
}
