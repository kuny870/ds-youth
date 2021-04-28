package org.ds.dsyouth.controller.rest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.ds.dsyouth.controller.rest.common.ResponseCode;
import org.ds.dsyouth.controller.rest.common.RestResponse;
import org.ds.dsyouth.exception.BirthYearDuplicatedException;
import org.ds.dsyouth.exception.DepartDuplicatedException;
import org.ds.dsyouth.exception.IdDuplicatedException;
import org.ds.dsyouth.exception.TeamDuplicatedException;
import org.ds.dsyouth.model.Depart;
import org.ds.dsyouth.model.Group;
import org.ds.dsyouth.model.Member;
import org.ds.dsyouth.model.SamePeriod;
import org.ds.dsyouth.model.Team;
import org.ds.dsyouth.model.YearSeason;
import org.ds.dsyouth.service.AdminService;
import org.ds.dsyouth.service.MemberService;
import org.ds.dsyouth.utils.StringHelper;
import org.ds.dsyouth.validator.TeamValidator;
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
public class AdminRestController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	TeamValidator teamValidator;
	
	@InitBinder("team")
	public void initRegEquipBinder(WebDataBinder dataBinder) {
		dataBinder.addValidators(teamValidator);
	}
	
	
	/**
	 * 부서 등록
	 * @param depart
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws IdDuplicatedException
	 */
	@RequestMapping(value = "/depart/regist", method = RequestMethod.POST, produces = "application/json")
	public RestResponse depart_regist(
			@ModelAttribute("depart") Depart depart,
			HttpServletRequest request) {

		RestResponse response = new RestResponse();
		
		try {
			adminService.registDepart(depart);
			
		} catch (DepartDuplicatedException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.DEPART_DUPLICATED);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 부서 수정
	 * @param depart
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/depart/modify", method = RequestMethod.POST, produces = "application/json")
	public RestResponse depart_modify(
			@ModelAttribute("depart") Depart depart,
			HttpServletRequest request) {

		RestResponse response = new RestResponse();
		
		try {
			adminService.modifyDepart(depart);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 부서 삭제
	 * @param depart
	 * @return
	 */
	@RequestMapping(value = "/depart/remove", method = RequestMethod.POST, produces = "application/json")
	public RestResponse depart_remove(
			@ModelAttribute Depart depart) {

		RestResponse response = new RestResponse();
		
		try {
			adminService.removeDepart(depart);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	
	/**
	 * 순명 등록
	 * @param group
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/group/regist", method = RequestMethod.POST, produces = "application/json")
	public RestResponse group_regist(
			@ModelAttribute Group group,
			HttpServletRequest request) {

		RestResponse response = new RestResponse();
		
		try {
			
			Team team = adminService.getTeamById(group.getTeamId());
			group.setDepartId(team.getDepartId());
			
			adminService.registGroup(group);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 순명 수정
	 * @param group
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/group/modify", method = RequestMethod.POST, produces = "application/json")
	public RestResponse group_modify(
			@ModelAttribute Group group,
			HttpServletRequest request) {

		RestResponse response = new RestResponse();
		
		try {
			adminService.modifyGroup(group);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 순명 삭제
	 * @param group
	 * @return
	 */
	@RequestMapping(value = "/group/remove", method = RequestMethod.POST, produces = "application/json")
	public RestResponse group_remove(
			@ModelAttribute Group group) {

		RestResponse response = new RestResponse();
		
		try {
			adminService.removeGroup(group);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 팀 등록
	 * @param team
	 * @param teamBindingResult
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws IdDuplicatedException
	 */
	@RequestMapping(value = "/team/regist", method = RequestMethod.POST, produces = "application/json")
	public RestResponse team_regist(
			@ModelAttribute("team") @Valid Team team,
			BindingResult teamBindingResult,
			HttpServletRequest request
			) throws UnsupportedEncodingException, NoSuchAlgorithmException, IdDuplicatedException {

		RestResponse response = teamValidator.bindingError(teamBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
	
		try {
			adminService.registTeam(team);
			
		} catch (TeamDuplicatedException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.TEAM_DUPLICATED);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	
	/**
	 * 팀 수정
	 * @param member
	 * @param request
	 * @param memberBindingResult
	 * @return
	 */
	@RequestMapping(value = "/team/modify", method = RequestMethod.POST, produces = "application/json")
	public RestResponse team_modify(
			@ModelAttribute("team") @Valid Team team,
			HttpServletRequest request,
			BindingResult teamBindingResult) {

		RestResponse response = teamValidator.bindingError(teamBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
		
		try {
			adminService.modifyTeam(team);
			
		} catch (TeamDuplicatedException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.TEAM_DUPLICATED);
		}  catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 팀 삭제
	 * @param team
	 * @return
	 */
	@RequestMapping(value = "/team/remove", method = RequestMethod.POST, produces = "application/json")
	public RestResponse team_remove(
			@ModelAttribute Team team) {

		RestResponse response = new RestResponse();
		
		try {
			adminService.removeTeam(team);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 동기 등록
	 * @param samePeriod
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/samePeriod/regist", method = RequestMethod.POST, produces = "application/json")
	public RestResponse samePeriod_regist(
			@ModelAttribute SamePeriod samePeriod,
			HttpServletRequest request) {

		RestResponse response = new RestResponse();
		
		try {
			
			adminService.registSamePeriod(samePeriod);
			
		} catch (BirthYearDuplicatedException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.BIRTHYEAR_DUPLICATED);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	
	/**
	 * 동기 적용
	 * @param sId
	 * @param arr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/samePeriodDetail/regist", method = RequestMethod.POST, produces = "application/json")
	public RestResponse samePeriodDetail_regist(
			Integer sId,
			String[] arr,
			HttpServletRequest request) {

		RestResponse response = new RestResponse();
		
		try {
			
			for (int i = 0; i < arr.length; i++) {
				Member mem = new Member();
				mem.setId(StringHelper.parseInt(arr[i]));
				mem.setSamePeriodId(sId);
				memberService.modifyMember(mem);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 동기 삭제
	 * @param samePeriod
	 * @return
	 */
	@RequestMapping(value = "/samePeriod/remove", method = RequestMethod.POST, produces = "application/json")
	public RestResponse samePeriod_remove(
			@ModelAttribute SamePeriod samePeriod) {

		RestResponse response = new RestResponse();
		
		try {
			adminService.removeSamePeriod(samePeriod);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 실시간 season select 변환 - 년도 선택시 시즌 불러오기
	 * @param year
	 * @param request
	 * @param httpResponse
	 * @return
	 */
	@RequestMapping(value = "/select/season", method = RequestMethod.POST, produces = "application/json")
	public RestResponse select_season(
			@ModelAttribute("year") String year) {
		
		RestResponse response = new RestResponse();
		
		try {
			
			List<YearSeason> seasonList = adminService.getYearSeasonList(year);
			
			response.setData(seasonList);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
}