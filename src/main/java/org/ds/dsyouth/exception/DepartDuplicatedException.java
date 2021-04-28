package org.ds.dsyouth.exception;


import org.ds.dsyouth.controller.rest.common.ResponseCode;

public class DepartDuplicatedException extends CommonException {
	/**
	 * 기본 생성자
	 *
	 * @see ResponseCode
	 */
	public DepartDuplicatedException() {
		super(ResponseCode.DEPART_DUPLICATED);
	}
}
