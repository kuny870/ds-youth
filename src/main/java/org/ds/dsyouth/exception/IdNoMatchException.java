package org.ds.dsyouth.exception;


import org.ds.dsyouth.controller.rest.common.ResponseCode;

public class IdNoMatchException extends CommonException {
	/**
	 * 기본 생성자
	 *
	 * @see ResponseCode
	 */
	public IdNoMatchException() {
		super(ResponseCode.ID_NO_MATCH);
	}
}
