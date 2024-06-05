package com.gd.article.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 				//게터세터(아래 생성되는 것 외의 전부)
@NoArgsConstructor 	//BoardArticle()
@AllArgsConstructor	// 매개변수 다 있는 생성자 BoardArticle(int, String, String, String, String)
public class BoardArticle {
	private int articleNo;
	private String articleTitle;
	private String articleContent;
	private String createDate;
	private String updateDate;
}
