package org.ds.dsyouth.exception;


import org.ds.dsyouth.rest.common.ResponseCode;

public class BirthYearDuplicatedException extends CommonException {
	/**
	 * 기본 생성자
	 *
	 * @see ResponseCode
	 */
	public BirthYearDuplicatedException() {
		super(ResponseCode.BIRTHYEAR_DUPLICATED);
	}
}
