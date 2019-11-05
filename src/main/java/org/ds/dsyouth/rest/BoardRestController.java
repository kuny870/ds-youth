package org.ds.dsyouth.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.ds.dsyouth.exception.IdNoMatchException;
import org.ds.dsyouth.exception.PasswordNoMatchException;
import org.ds.dsyouth.model.BoardFree;
import org.ds.dsyouth.model.BoardOpinion;
import org.ds.dsyouth.model.LeaderInfo;
import org.ds.dsyouth.rest.common.ResponseCode;
import org.ds.dsyouth.rest.common.RestResponse;
import org.ds.dsyouth.service.BoardService;
import org.ds.dsyouth.utils.DateHelper;
import org.ds.dsyouth.utils.FileUploadHelper;
import org.ds.dsyouth.validator.BoardFreeValidator;
import org.ds.dsyouth.validator.BoardOpinionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/rest")
public class BoardRestController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	BoardOpinionValidator boardOpinionValidator;
	
	@Autowired
	BoardFreeValidator boardFreeValidator;
	
	@InitBinder("boardOpinion")
	public void initRegEquipBinder(WebDataBinder dataBinder) {
		dataBinder.addValidators(boardOpinionValidator);
	}

	@InitBinder("boardFree")
	public void initRegEquipBinder2(WebDataBinder dataBinder) {
		dataBinder.addValidators(boardFreeValidator);
	}
	
	
	/**
	 * 의견 게시판 등록
	 * @param boardOpinion
	 * @param boardOpinionBindingResult
	 * @param request
	 * @param httpResponse
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws IdNoMatchException
	 * @throws PasswordNoMatchException
	 */
	@RequestMapping(value = "/boardOpinion", method = RequestMethod.POST, produces = "application/json")
	public RestResponse boardOpinion(
			@ModelAttribute("boardOpinion") @Valid BoardOpinion boardOpinion,
			BindingResult boardOpinionBindingResult,
			HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException, IdNoMatchException, PasswordNoMatchException {
		
		RestResponse response = boardOpinionValidator.bindingError(boardOpinionBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
		
		try {
			boardService.registBoardOpinion(boardOpinion);

		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 자유게시판 등록
	 * @param boardFree
	 * @param boardFreeBindingResult
	 * @param request
	 * @param httpResponse
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws IdNoMatchException
	 * @throws PasswordNoMatchException
	 */
	@RequestMapping(value = "/boardFree", method = RequestMethod.POST, produces = "application/json")
	public RestResponse boardFree(
			@ModelAttribute("boardFree") @Valid BoardFree boardFree,
			BindingResult boardFreeBindingResult,
			HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException, IdNoMatchException, PasswordNoMatchException {
		
		RestResponse response = boardOpinionValidator.bindingError(boardFreeBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
		
		try {
			boardService.registBoardFree(boardFree);

		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 리더배포자료 삭제
	 * @param leaderInfo
	 * @return
	 */
	@RequestMapping(value = "/leaderInfo/remove", method = RequestMethod.POST, produces = "application/json")
	public RestResponse leaderInfo_remove(
			@ModelAttribute LeaderInfo leaderInfo) {

		RestResponse response = new RestResponse();
		
		try {
			boardService.removeLeaderInfo(leaderInfo);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	/**
	 * 리더배포자료 등록
	 * @param leaderInfo
	 * @param req
	 * @param rep
	 * @return
	 */
	@RequestMapping(value = "/leaderInfo/regist", method = RequestMethod.POST, produces = "multipart/form-data")
	public String leaderInfo(
			MultipartHttpServletRequest mhsr) {
		
		String year = DateHelper.getDate().substring(0, 4);
		String month = mhsr.getParameter("month");
		String day = mhsr.getParameter("day");
		String title = year + "년 " + month + "월 " + day + "일";  
		String regUser = mhsr.getParameter("regUser");
		
		RestResponse response = new RestResponse();
		
		try {
			String saveFileName = FileUploadHelper.fileUpload(mhsr);
			
			String origName = new String(mhsr.getFile(mhsr.getFileNames().next()).getOriginalFilename().getBytes("8859_1"), "UTF-8");
			
			LeaderInfo li = new LeaderInfo();
			li.setTitle(title);
			li.setOriginImg(origName);
			li.setReplaceImg(saveFileName);
			li.setRegUser(Integer.parseInt(regUser));
			
			boardService.registLeaderInfo(li);
			
		} catch (IOException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return "SUCCESS";
	}
	
	
}