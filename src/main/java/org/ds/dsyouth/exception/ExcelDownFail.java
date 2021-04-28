package org.ds.dsyouth.exception;

import org.ds.dsyouth.controller.rest.common.ResponseCode;

public class ExcelDownFail extends CommonException {
	/**
	 * 기본 생성자
	 *
	 * @see ResponseCode
	 */
	public ExcelDownFail() {
		super(ResponseCode.EXCELDOWN_FAIL);
	}
}
