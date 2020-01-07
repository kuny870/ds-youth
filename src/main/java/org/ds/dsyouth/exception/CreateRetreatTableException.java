package org.ds.dsyouth.exception;


import org.ds.dsyouth.rest.common.ResponseCode;

public class CreateRetreatTableException extends CommonException {
	/**
	 * 기본 생성자
	 *
	 * @see ResponseCode
	 */
	public CreateRetreatTableException() {
		super(ResponseCode.RETREATTABLE_DUPLICATED);
	}
}
