package com.gd.article.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gd.article.dto.BoardArticle;
import com.gd.article.dto.BoardFile;
import com.gd.article.dto.BoardFileRequest;
import com.gd.article.mapper.BoardArticleMapper;
import com.gd.article.mapper.BoardFileMapper;
import com.gd.article.util.Debug;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class BoardFileService {

	@Autowired BoardFileMapper boardFileMapper;
	@Autowired BoardArticleMapper boardArticelMapper;
	
	/* 수정하기 */
	public void modifyBoardFile(BoardFileRequest boardFileRequest, BoardArticle boardArticle) { 
		//log.debug(Debug.PHA + "서비스 getBoardTitle -> " + boardArticle.getArticleContent() + Debug.END);
		//log.debug(Debug.PHA + "서비스 getFileNo -> " + boardFileRequest.getFileNo() + Debug.END);
		//log.debug(Debug.PHA + "서비스 getOriginalFilename -> " + boardFileRequest.getFile().getOriginalFilename() + Debug.END);
		
		
		//board_article 테이블 수정
		int updateRow1 = boardArticelMapper.updateBoardArticle(boardArticle);
		
		// fileno + multipartFIle수정파일 ===> 수정할 키(fileNo) + 새로 업로드 될 수정 파일(multiPartFile)
		// boardFileRequest ---> BoardFile로 변경
		// updateRow1 성공시 파일 저장
		if(updateRow1 == 1) {
			
			MultipartFile mf = boardFileRequest.getFile();
			BoardFile file = new BoardFile();
			
			log.debug(Debug.PHA + "서비스 mf -> " + mf.getOriginalFilename() + Debug.END);
			
			file.setOriginalName(mf.getOriginalFilename());					//파일 원래 이름 
			file.setFileType(mf.getContentType());							//파일타입
			file.setFileSize(mf.getSize());									//파일사이즈
			String prefix = UUID.randomUUID().toString().replace("-", "");	//파일랜덤UUID세팅
			int p = mf.getOriginalFilename().lastIndexOf(".");				//파일랜덤UUID세팅
			String suffix = mf.getOriginalFilename().substring(p);			//파일랜덤UUID세팅
			file.setFileName(prefix + suffix);								//파일랜덤UUID세팅
			file.setFileNo(boardFileRequest.getFileNo());					//수정할 file_no
			
			log.debug(Debug.PHA + "서비스 file -> " + file.getFileName() + Debug.END);
			
			// 이전 file이 있다면 삭제해주기 (수정하기 전에 실행)
			String preFileName = boardFileMapper.selectFileNameByKey(boardFileRequest.getFileNo());
			log.debug(Debug.PHA + "서비스 preFileName -> " + preFileName + Debug.END);
			if(preFileName != null) {
				File f = new File("c:/upload/"+preFileName);
				if(f.exists()) {
					f.delete();
				}
			}
			
			
			// 파일 저장(c밑의 upload밑에 파일을 넣어주고)
			File emptyFile = new File("c:/upload/"+file.getFileName());
			try {
				// mf안에 있는 getinputStream을 가져와서 비어있는 emptyFile로 복사를 함 
				mf.transferTo(emptyFile);
			} catch (Exception e) {
				e.printStackTrace(); // 예외나면 전부 취소시켜야함
				throw new RuntimeException(); // 일부러 예외를 발생시켜서 위에도 했던 명령도 전부 취소
			}
			
			
			// board_file 테이블 수정
			int updateRow2 = boardFileMapper.updateBoardFile(file);
		}
	}
	
}
