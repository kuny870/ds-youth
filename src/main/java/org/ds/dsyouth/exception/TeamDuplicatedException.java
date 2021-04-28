package org.ds.dsyouth.exception;


import org.ds.dsyouth.controller.rest.common.ResponseCode;

public class TeamDuplicatedException extends CommonException {
	/**
	 * 기본 생성자
	 *
	 * @see ResponseCode
	 */
	public TeamDuplicatedException() {
		super(ResponseCode.TEAM_DUPLICATED);
	}
}
