package org.ds.dsyouth.exception;


import org.ds.dsyouth.rest.common.ResponseCode;

public class NoSuchAlgorithmException extends CommonException {
	/**
	 * 기본 생성자
	 *
	 * @see ResponseCode
	 */
	public NoSuchAlgorithmException() {
		super(ResponseCode.NO_SUCH_ALGORITHM_EXCEPTION);
	}
}
