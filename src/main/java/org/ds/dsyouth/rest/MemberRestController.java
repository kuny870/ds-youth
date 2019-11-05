package org.ds.dsyouth.rest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.ds.dsyouth.exception.IdDuplicatedException;
import org.ds.dsyouth.model.Member;
import org.ds.dsyouth.rest.common.ResponseCode;
import org.ds.dsyouth.rest.common.RestResponse;
import org.ds.dsyouth.service.MemberService;
import org.ds.dsyouth.validator.MemberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class MemberRestController {

	@Autowired
	private MemberService memberService;

	@Autowired
	MemberValidator memberValidator;
	
	@InitBinder("member")
	public void initRegEquipBinder(WebDataBinder dataBinder) {
		dataBinder.addValidators(memberValidator);
	}


	
	/**
	 * 멤버 등록
	 * @param member
	 * @param memberBindingResult
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws IdDuplicatedException
	 */
	@RequestMapping(value = "/member/regist", method = RequestMethod.POST, produces = "application/json")
	public RestResponse member_regist(
			@ModelAttribute("member") @Valid Member member,
			BindingResult memberBindingResult,
			HttpServletRequest request
			) throws UnsupportedEncodingException, NoSuchAlgorithmException, IdDuplicatedException {

		RestResponse response = memberValidator.bindingError(memberBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
	
		try {
			memberService.registMember(member);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	
	/**
	 * 멤버 수정
	 * @param member
	 * @return
	 */
	@RequestMapping(value = "/member/edit", method = RequestMethod.POST, produces = "application/json")
	public RestResponse member_edit(
			@ModelAttribute Member member) {

		RestResponse response = new RestResponse();
		
		try {
			memberService.modifyMember(member);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 멤버 삭제
	 * @param member
	 * @return
	 */
	@RequestMapping(value = "/member/remove", method = RequestMethod.POST, produces = "application/json")
	public RestResponse member_remove(
			@ModelAttribute Member member) {

		RestResponse response = new RestResponse();
		
		try {
			memberService.removeMember(member);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
}