package com.gd.article.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gd.article.util.Debug;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UploadTest {
	
	/* 파일업로드 */
	@GetMapping("/fileUpload")
	public String fileUpload() {
		
		
		return "fileUpload";
	}
	
	
	/* 파일등록 */
	@PostMapping("/fileUpload")
	public String fileUpload(
								MultipartFile item,
								@RequestParam(name="title") String title) {
		// MultipartFile file은 input type이 file인 애를 받음
		
		// 파일 디버깅
		log.debug(Debug.PHA + "title -> " + title + Debug.END);
		log.debug(Debug.PHA + "item -> " + item.toString() + Debug.END);
		log.debug(Debug.PHA + "originalFilename -> " + item.getOriginalFilename() + Debug.END);
		log.debug(Debug.PHA + "getContentType -> " + item.getContentType() + Debug.END);
		log.debug(Debug.PHA + "getSize -> " + item.getSize() + Debug.END);
		
		// item.getInputStream()
		
		
		return "";
	}
	
	
}
