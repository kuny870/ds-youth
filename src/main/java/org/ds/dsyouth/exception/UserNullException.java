package org.ds.dsyouth.exception;

import org.ds.dsyouth.rest.common.ResponseCode;

public class UserNullException extends CommonException {
	/**
	 * 기본 생성자
	 *
	 * @see ResponseCode
	 */
	public UserNullException() {
		super(ResponseCode.USER_NULL);
	}
}
