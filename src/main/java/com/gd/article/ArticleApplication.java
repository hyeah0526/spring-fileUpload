package com.gd.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//servlet : servlet(Controller가능), filter(Intercepter 가능), listener()
@ServletComponentScan 
@SpringBootApplication
public class ArticleApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(ArticleApplication.class, args);
	}
	
	
	// implements WebMvcConfigurer 이거 추가해서 인터셉터를 사용했었는데,
	// 이걸 또 사용해서 파일 업로드 폴더를 구현 할 것
	// 컨트롤+스페이스해서 addResourceHandlers를 추가
	// 안에 있는 내용은 삭제하고 아래처럼 추가
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/img/**").addResourceLocations("file:///c:/upload/");
		
	}
	
	
	// 로그인
	

	
	
}
