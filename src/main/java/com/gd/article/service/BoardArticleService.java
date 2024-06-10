package com.gd.article.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gd.article.dto.BoardArticle;
import com.gd.article.dto.BoardFile;
import com.gd.article.dto.BoardFileRequest;
import com.gd.article.dto.BoardRequest;
import com.gd.article.mapper.BoardArticleMapper;
import com.gd.article.mapper.BoardFileMapper;
import com.gd.article.util.Debug;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class BoardArticleService {
	
	@Autowired BoardArticleMapper boardArticelMapper;
	@Autowired BoardFileMapper boardFileMapper;
	
	/* 추가하기 */
	public void addBoard(BoardRequest boardRq) {
		
		BoardArticle article = new BoardArticle();
		article.setArticleTitle(boardRq.getBoardTitle());
		article.setArticleContent(boardRq.getBoardContent());
		
		// 여기에 BoardArticle타입을 넘겨줘야하므로 받아온 boardRq에서 BoardArticle에 값을 채우기 
		int insertRow1 = boardArticelMapper.insertBoardArticle(article);
		if(insertRow1 != 1) {
			throw new RuntimeException();
		}
		
		// AI가 제대로 들어갔는지 확인
		log.debug(Debug.PHA + "articleNo -> " + article.getArticleNo() + Debug.END);
		
		// articleNo와 boardRq.getBoardFile() 를 이용해서 board_file테이블에 insert해줘야함
		MultipartFile mf = boardRq.getBoardFile();
		BoardFile file = new BoardFile();
		file.setArticleNo(article.getArticleNo());
		file.setOriginalName(mf.getOriginalFilename());
		file.setFileType(mf.getContentType());
		file.setFileSize(mf.getSize());
		
		// file name은 이제 셋팅해줘야함. 
		// 앞에들어가는 pre = 저장될 파일이름은 UUID사용 하면서 랜덤이름을 만들고 그 랜덤이름에 들어가있는 작대기-를 없애주기
		String prefix = UUID.randomUUID().toString().replace("-", "");
		
		// 뒤에 들어가는 = .의 위치를 찾아서(lastIndexOf(".")) -> 
		int p = mf.getOriginalFilename().lastIndexOf(".");
		// 그 이름의 p의 위치부터 자름
		String suffix = mf.getOriginalFilename().substring(p);
		
		file.setFileName(prefix + suffix);
		
		// BoardFile저장하는 맵퍼 호출
		int insertRow2 = boardFileMapper.insertBoardFile(file);
		if(insertRow2 != 1) {
			throw new RuntimeException();
		}
		
		// boardArticelMapper.insertBoardArticle(article); 랑 boardFileMapper.insertBoardFile(file); 랑 mf.transferTo(emptyFile);
		// Insertrow값이 1이아니면 강제로 또 예외발생 시켜야하므로 if문으로 강제로 발생시켜주기

		
		// 파일 저장(c밑의 upload밑에 파일을 넣어주고)
		File emptyFile = new File("c:/upload/"+prefix+suffix);
		try {
			// mf안에 있는 getinputStream을 가져와서 비어있는 emptyFile로 복사를 함 
			mf.transferTo(emptyFile);
		} catch (Exception e) {
			e.printStackTrace(); // 예외나면 전부 취소시켜야함
			throw new RuntimeException(); // 일부러 예외를 발생시켜서 위에도 했던 명령도 전부 취소
		}
		
	}
	
	
	/* 조회하기 */
	public List<Map<String, Object>> getMemberList(int currentPage, int rowPerPage, String searchWord) {
		
		// 디버깅
		log.debug(Debug.PHA + "서비스 currentPage -> " + currentPage + Debug.END);
		log.debug(Debug.PHA + "서비스 rowPerPage -> " + rowPerPage + Debug.END);
		
		// beginRow구하기
		int beginRow = (currentPage-1)*rowPerPage;
		log.debug(Debug.PHA + "서비스 beginRow -> " + beginRow + Debug.END);
		
		
		// 전체 조회 list에 담아주기
		List<Map<String, Object>> list =  boardArticelMapper.selectBoardList(beginRow, rowPerPage, searchWord);
		log.debug(Debug.PHA + "서비스 list -> " + list + Debug.END);
		
		return list;
	}
	
	
	/* 페이징 - 라이트페이지 */
	public int getLastPage(int rowPerPage, String searchWord) {
		
		// 총 행의 개수구하기
		int totalCnt = boardArticelMapper.getListTotalCnt(searchWord);
		log.debug(Debug.PHA + "서비스 totalCnt -> " + totalCnt + Debug.END);
						
		// 총행의 개수로 나머지 계산하기
		int lastPage = totalCnt / rowPerPage;
		if(totalCnt%rowPerPage != 0) {
			lastPage = lastPage+1;
		}
		log.debug(Debug.PHA + "서비스 lastPage -> " + lastPage + Debug.END);

		return lastPage;
	}
	
	
	
	/* 상세보기 */
	public Map<String, Object> getBoardOne(int articleNo){
		Map<String, Object> map = new HashMap<>();
		
		log.debug(Debug.PHA + "서비스 articleNo -> " + articleNo + Debug.END);
		
		map = boardArticelMapper.selectBoardOne(articleNo);
		log.debug(Debug.PHA + "서비스 map -> " + map + Debug.END);
	
		return map;
	}
	
	
	/* 삭제하기 */
	public void removeBoard(int articleNo) {
		
	// 물리적 파일을 먼저 삭제
	String fileName = boardFileMapper.selectFileName(articleNo);
	log.debug(Debug.PHA + "서비스 fileName -> " + fileName + Debug.END);
			
	// fileName이 있을 때만 file삭제 로직타기
	if(fileName != null) {
		// File을 불러오고 존재하면 삭제
		File f = new File("c:/upload/"+fileName);
		if(f.exists()) {
			f.delete();
		}
	}
			
	// file 먼저 삭제
	int deleteRow1 = boardFileMapper.deleteBoardFile(articleNo);
	log.debug(Debug.PHA + "서비스 deleteRow2 -> " + deleteRow1 + Debug.END);
		
	// article 삭제
	int deleteRow2 = boardArticelMapper.deleteBoardArticle(articleNo);
	log.debug(Debug.PHA + "서비스 deleteRow1 -> " + deleteRow2 + Debug.END);
		
		
	}
	
	
	
}
