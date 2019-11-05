package org.ds.dsyouth.validator;

import org.ds.dsyouth.rest.common.ResponseCode;
import org.ds.dsyouth.rest.common.RestResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public final class ValidatorUtil {

	private ValidatorUtil() {

	}

	public static RestResponse createResponseByBindingResult(final BindingResult bindingResult) {
		final RestResponse response = new RestResponse();

		if (bindingResult.hasErrors()) {
			final ObjectError error = bindingResult.getAllErrors().get(0);

			response.setSuccess(false);
			response.setCode(error.getCode());
			response.setMessage(error.getDefaultMessage());
		}

		return response;
	}

	public static void reject(final Errors errors, final ResponseCode responseCode) {
		errors.reject(responseCode.getCode(), responseCode.getMessage());
	}
}
