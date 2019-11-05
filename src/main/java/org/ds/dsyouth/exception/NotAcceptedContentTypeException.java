package org.ds.dsyouth.exception;


import org.ds.dsyouth.rest.common.ResponseCode;

public class NotAcceptedContentTypeException extends CommonException {
	public NotAcceptedContentTypeException() {
		super(ResponseCode.FILE_CONTENTTYPE);
	}
}
