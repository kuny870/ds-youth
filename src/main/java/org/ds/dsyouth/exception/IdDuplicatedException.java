package org.ds.dsyouth.exception;


import org.ds.dsyouth.rest.common.ResponseCode;

public class IdDuplicatedException extends CommonException {
	/**
	 * 기본 생성자
	 *
	 * @see ResponseCode
	 */
	public IdDuplicatedException() {
		super(ResponseCode.ID_DUPLICATED);
	}
}
