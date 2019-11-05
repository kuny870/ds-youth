package org.ds.dsyouth.exception;


import org.ds.dsyouth.rest.common.ResponseCode;

public class UnsupportedEncodingException extends CommonException {
	/**
	 * 기본 생성자
	 *
	 * @see ResponseCode
	 */
	public UnsupportedEncodingException() {
		super(ResponseCode.UNSUPPORTED_ENCODING_EXCEPTION);
	}
}
