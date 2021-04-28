package org.ds.dsyouth.controller;

import java.util.List;

import org.ds.dsyouth.common.Constants;
import org.ds.dsyouth.model.BoardFree;
import org.ds.dsyouth.model.BoardOpinion;
import org.ds.dsyouth.model.LeaderInfo;
import org.ds.dsyouth.model.QT;
import org.ds.dsyouth.search.QTSearch;
import org.ds.dsyouth.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({Constants.SESSION_USER})
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	/**
	 * 리더배포자료 list
	 * @return
	 */
	@RequestMapping(value = "/leaderInfo/list", method = RequestMethod.GET)
	public ModelAndView leaderInfo_list() {

		List<LeaderInfo> leaderInfoList = boardService.getLeaderInfoList();
		
		ModelAndView mav = new ModelAndView("leaderInfo/list");
		
		mav.addObject("leaderInfoList", leaderInfoList);

		return mav;
	}
	
	/**
	 * 리더배포자료 상세보기
	 * @return
	 */
	@RequestMapping(value = "/leaderInfo/detail/{id}", method = RequestMethod.GET)
	public ModelAndView leaderInfo_detail(@PathVariable String id) {

		LeaderInfo leaderInfo = boardService.getLeaderInfo(id);
		
		ModelAndView mav = new ModelAndView("leaderInfo/detail");
		
		mav.addObject("leaderInfo", leaderInfo);

		return mav;
	}
	
	
	/**
	 * 오늘의 QT
	 * @return
	 */
	@RequestMapping(value = "/bible/qt", method = RequestMethod.GET)
	public ModelAndView bible_qt(QTSearch qtSearch) {

		QT qt = boardService.getQT(qtSearch);
		
		ModelAndView mav = new ModelAndView("bible/QT");
		
		mav.addObject("qt", qt);
		mav.addObject("qtSearch", qtSearch);

		return mav;
	}
	
	
	/**
	 * 의견 게시판
	 * @return
	 */
	@RequestMapping(value = "/board/opinion", method = RequestMethod.GET)
	public ModelAndView boardOpinion() {

		List<BoardOpinion> boardOpinionList = boardService.getBoardOpinionList();
		
		ModelAndView mav = new ModelAndView("board/opinion");
		
		mav.addObject("boardOpList", boardOpinionList);

		return mav;
	}
	
	/**
	 * 자유 게시판
	 * @return
	 */
	@RequestMapping(value = "/board/free", method = RequestMethod.GET)
	public ModelAndView boardFree() {

		List<BoardFree> boardFreeList = boardService.getBoardFreeList();
		
		ModelAndView mav = new ModelAndView("board/free");
		
		mav.addObject("boardFreeList", boardFreeList);

		return mav;
	}
	
	
}
