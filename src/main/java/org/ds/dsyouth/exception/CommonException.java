package org.ds.dsyouth.exception;


import org.ds.dsyouth.rest.common.ResponseCode;

/**
 * 공통 익셉션
 */
public class CommonException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 응답 코드<br />
	 * default: UNKNOWN
	 *
	 * @see org.ds.dsyouth.rest.common.ResponseCode
	 */
	private ResponseCode responseCode = ResponseCode.UNKOWN;

	/**
	 * 기본 생성자
	 *
	 * @param responseCode - 응답 코드
	 *
	 * @see org.ds.dsyouth.rest.common.ResponseCode
	 */
	public CommonException(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * 응답 코드 반환
	 *
	 * @return 응답 코드
	 *
	 * @see org.ds.dsyouth.rest.common.ResponseCode
	 */
	public ResponseCode getResponseCode() {
		return responseCode;
	}
}
