package com.gd.article.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.article.dto.BoardArticle;
import com.gd.article.dto.BoardFileRequest;
import com.gd.article.dto.BoardRequest;
import com.gd.article.service.BoardArticleService;
import com.gd.article.service.BoardFileService;
import com.gd.article.util.Debug;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	@Autowired BoardArticleService boardArticleService;
	@Autowired BoardFileService boardFileService;
	
	/* 추가하기 */
	@GetMapping("/addBoard")
	public String addBoard() {
		
		return "addBoard";
	}
	
	@PostMapping("/addBoard")
	public String addBoard(BoardRequest boardRq) {	// BoardRequest boardRq 커맨드객체로 받기 (BoardRequest dto를생성)
		/* 커맨드 객체가 아니였다면 아래처럼 받았어야함
		 * public String addBoard (@RequestParam(name="boardTitle") String boardTitle, 
		 *							@RequestParam(name="boardContent") String boardContent,
		 *							@RequestParam(name="boardFile") String boardFile){}
		 */
		
		log.debug(Debug.PHA + "getBoardTitle -> " + boardRq.getBoardTitle() + Debug.END);
		log.debug(Debug.PHA + "getBoardContent -> " + boardRq.getBoardContent() + Debug.END);
		log.debug(Debug.PHA + "getBoardFile.OriginalFilename -> " + boardRq.getBoardFile().getOriginalFilename() + Debug.END);
		
		// 컨트롤러에서는 boardRq이거 그대로 넘겨주고 서비스에서 분리해야함
		boardArticleService.addBoard(boardRq);
		
		
		return "redirect:/boardList";	// boardList.jsp로 리다이렉트
	}
	
	
	
	/* 전체조회 */
	@GetMapping("/boardList")
	public String boardList(Model model,
							@RequestParam(name="rowPerPage", defaultValue="3") int rowPerPage,
							@RequestParam(name="currentPage", defaultValue="1") int currentPage,
							@RequestParam(name="searchWord", defaultValue="") String searchWord) {
		
		log.debug(Debug.PHA + "컨트롤러 searchWord -> " + searchWord + Debug.END);
		
		/* 전체목록조회 */
		List<Map<String, Object>> list = boardArticleService.getMemberList(currentPage, rowPerPage, searchWord);
		
		/* 라스트페이지 */
		int lastPage = boardArticleService.getLastPage(rowPerPage, searchWord);
		
		// view에서 뿌려줄 model담기
		model.addAttribute("list", list);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		
		return "boardList";
	}
	
	
	
	/* 상세보기 */
	@GetMapping("/boardDetail")
	public String boardDetail(Model model, 
								@RequestParam(name="articleNo") int articleNo) {
		
		log.debug(Debug.PHA + "컨트롤러 articleNo -> " + articleNo + Debug.END);
		
		Map<String, Object> map =  boardArticleService.getBoardOne(articleNo);
		log.debug(Debug.PHA + "컨트롤러 map -> " + map + Debug.END);
		
		model.addAttribute("map", map);
		
		return "boardDetail";
	}
	
	
	/* 삭제하기 */
	@GetMapping("/boardRemove")
	public String boardRemove(@RequestParam(name="articleNo") int articleNo) {
		
		boardArticleService.removeBoard(articleNo);
		
		return "redirect:/boardList";
	}
	
	
	
	/* 수정하기 폼 */
	@GetMapping("/boardUpdate")
	public String boardUpdate(Model model, 
									@RequestParam(name="articleNo") int articleNo) {
		
		Map<String, Object> map =  boardArticleService.getBoardOne(articleNo);
		model.addAttribute("map", map);
		
		return "boardUpdate";
	}
	
	/* 수정하기 액션 */
	@PostMapping("/boardUpdate")
	public String boardUpdate(BoardArticle boardArticle, BoardFileRequest boardFileRequest,
								@RequestParam(name="articleNo") int articleNo) {

		log.debug(Debug.PHA + "컨트롤러 articleNo -> " + articleNo + Debug.END);
		log.debug(Debug.PHA + "컨트롤러 getBoardContent -> " + boardArticle.getArticleContent() + Debug.END);
		log.debug(Debug.PHA + "컨트롤러 getArticleTitle -> " + boardArticle.getArticleTitle()+ Debug.END);
		log.debug(Debug.PHA + "컨트롤러 getFileNo -> " + boardFileRequest.getFileNo() + Debug.END);
		log.debug(Debug.PHA + "컨트롤러 getFile -> " + boardFileRequest.getFile().getOriginalFilename() + Debug.END);
		
		boardFileService.modifyBoardFile(boardFileRequest, boardArticle);
		
		return "redirect:/boardDetail?articleNo="+articleNo;
	}
	

}
