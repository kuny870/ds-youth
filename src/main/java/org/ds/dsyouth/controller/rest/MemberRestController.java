package org.ds.dsyouth.controller.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.ds.dsyouth.controller.rest.common.ResponseCode;
import org.ds.dsyouth.controller.rest.common.RestResponse;
import org.ds.dsyouth.exception.IdDuplicatedException;
import org.ds.dsyouth.model.Attendance;
import org.ds.dsyouth.model.Member;
import org.ds.dsyouth.model.MemberTmp;
import org.ds.dsyouth.service.AttendanceService;
import org.ds.dsyouth.service.MemberService;
import org.ds.dsyouth.utils.FileUploadHelper;
import org.ds.dsyouth.validator.MemberValidator;
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
public class MemberRestController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private AttendanceService attendanceService;

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
			// 지체 불러오기 시 사진 등록
			if("1".equals(member.getRegistFlag())) {
				member.setOriginProfileImg(member.getOriginProfileImg2());
				member.setReplaceProfileImg(member.getReplaceProfileImg2());
			}
			String memberId = memberService.registMember(member);
			response.setText(memberId);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	/**
	 * 중복 이름 체크
	 * @param member
	 * @return
	 */
	@RequestMapping(value = "/member/regist/nameCheck", method = RequestMethod.POST, produces = "application/json")
	public RestResponse member_regist_nameCheck(Member member) {

		RestResponse response = new RestResponse();
	
		try {
			
			List<Member> memberList = memberService.getMemberByName(member);

			if(memberList != null) {
				
				MemberTmp mtp = new MemberTmp();
				mtp.setMemState(memberList.get(0).getMemState());
				mtp.setDateOfBirth(memberList.get(0).getDateOfBirth());
				mtp.setHtel(memberList.get(0).getHtel());
				mtp.setMemo(memberList.get(0).getMemo());
				mtp.setSamePeriodId(memberList.get(0).getSamePeriodId());
				mtp.setGender(memberList.get(0).getGender());
				mtp.setOriginProfileImg(memberList.get(0).getOriginProfileImg());
				mtp.setReplaceProfileImg(memberList.get(0).getReplaceProfileImg());
				response.setData(mtp);
				
			}else {

				response.setSuccess(false);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	
	
	/**
	 * 프로필 사진 등록
	 * @param profile_img
	 * @param req
	 * @param rep
	 * @return
	 */
	@RequestMapping(value = "/profileImg/regist", method = RequestMethod.POST, produces = "multipart/form-data")
	public String profileImg_regist(
			MultipartHttpServletRequest mhsr) {
		
		String id = mhsr.getParameter("memberId");
		Integer memberId = Integer.parseInt(id);
		
		String profileImg = mhsr.getParameter("profileImg");
		
		RestResponse response = new RestResponse();
		
		try {
			String origName = new String(mhsr.getFile(mhsr.getFileNames().next()).getOriginalFilename().getBytes("8859_1"), "UTF-8");
			String saveFileName = "";
			
			if(profileImg.equals("프로필사진삭제") && origName.equals("")) {
				
			}else if(origName.equals("")) {
				return "SUCCESS";
			}else {
				saveFileName = FileUploadHelper.fileUpload(mhsr);
			}
			
			Member member = new Member();
			member.setId(memberId);
			member.setOriginProfileImg(origName);
			member.setReplaceProfileImg(saveFileName);
			
			memberService.modifyMember(member);
			
		} catch (IOException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return "SUCCESS";
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
	 * 멤버 메모 수정
	 * @param member
	 * @return
	 */
	@RequestMapping(value = "/member/memo", method = RequestMethod.POST, produces = "application/json")
	public RestResponse member_memo_edit( 
			@ModelAttribute Member member,
			String sundays, String onYn, String sayu, Integer attId, String thisYear, String userAuthId) {

		RestResponse response = new RestResponse();
		
		try {
			
			Attendance att = new Attendance();
			
			if("1".equals(sundays)) {
				att.setSayu1(sayu);
				att.setOnYn1(onYn);
			}else if("2".equals(sundays)) {
				att.setSayu2(sayu);
				att.setOnYn2(onYn);
			}else if("3".equals(sundays)) {
				att.setSayu3(sayu);
				att.setOnYn3(onYn);
			}else if("4".equals(sundays)) {
				att.setSayu4(sayu);
				att.setOnYn4(onYn);
			}else{
				att.setSayu5(sayu);
				att.setOnYn5(onYn);
			}
			att.setId(attId);
			att.setYear(thisYear);
			
			memberService.modifyMemberMemo(member, userAuthId);
			attendanceService.modifyAttendanceCheck(att);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 멤버 메모 수정
	 * @param member
	 * @return
	 */
	@RequestMapping(value = "/member/memoFlag", method = RequestMethod.POST, produces = "application/json")
	public RestResponse member_memoFlag_edit( 
			@ModelAttribute Member member) {

		RestResponse response = new RestResponse();
		
		try {
			
			memberService.modifyMemberMemoFlag(member);
			
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
	
	/**
	 * 멤버 복구
	 * @param member
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/member/restore", method = RequestMethod.POST, produces = "application/json")
	public RestResponse member_restore(
			@ModelAttribute Member member,
			HttpServletRequest request) {

		RestResponse response = new RestResponse();
		
		try {
			memberService.restoreMember(member);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
}