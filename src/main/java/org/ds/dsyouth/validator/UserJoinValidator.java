package org.ds.dsyouth.validator;

import java.util.Arrays;
import java.util.List;

import org.ds.dsyouth.model.User;
import org.ds.dsyouth.rest.common.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserJoinValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target; // validate 하고 싶은 객체를 받아와서 셋팅!
		
		if(user.getLoginId().length() == 0) {
			errors.rejectValue("loginId", "emptyLoginId", "아이디를 입력해 주세요.");
		} else if(user.getLoginPw().length() == 0) {
			errors.rejectValue("loginPw", "emptyLoginPw", "비밀번호를 입력해 주세요.");
		} else if(user.getName().length() == 0) {
			errors.rejectValue("name", "emptyName", "이름을 입력해 주세요.");
		}/* else if(user.getDateOfBirth().length() == 0) {
			errors.rejectValue("dateOfBirth", "emptyDateOfBirth", "생년월일을 입력해 주세요.");
		} else if(user.getHtel().length() == 0) {
			errors.rejectValue("htel", "emptyHtel", "휴대폰 번호를 입력해 주세요.");
		} */else if(user.getDepartId() == 0) {
			errors.rejectValue("departId", "emptyDepartId", "부서를 선택해 주세요.");
		} else if(user.getTeamId() == 0) {
			errors.rejectValue("teamId", "emptyTeamId", "팀을 선택해 주세요.");
		} else if(user.getGender().length() == 0) {
			errors.rejectValue("gender", "emptyGender", "성별을 선택해 주세요.");
		}
		
	}
	
	
	public RestResponse bindingError(BindingResult bindingResult) {
		RestResponse response = new RestResponse(); // 유효성 검사 후 리턴 시킬 값을 세팅하기 위함, default값으로 success에 true가 설정되어 있다.
		
		if (bindingResult.getFieldError() == null) { // getFreldError()이 null이면 null포인터 익셉션이 생겨서 따로 처리해줘야한다.
			return response;
		}
		
		List<String> errorCode = Arrays.asList(
				"emptyLoginId", "emptyLoginPw", "emptyName", /* "emptyDateOfBirth", "emptyHtel", */ 
				"emptyDepartId", "emptyTeamId", "emptyGender");
		
		for (String temp : errorCode) {
			if(temp.equals(bindingResult.getFieldError().getCode())) {
				response.setSuccess(false);
				response.setMessage(bindingResult.getFieldError().getDefaultMessage());
				return response;
			}
		}
		
		return response; // 유효성 검사 메소드에서 에러가 발생했다면 위에서 response의 success에 false로 세팅되고 아무런 에러가 발생하지 않았다면 true로 세팅되었을 것이다.
	}
	
}
