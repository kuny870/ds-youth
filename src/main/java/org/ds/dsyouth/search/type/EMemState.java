package org.ds.dsyouth.search.type;

/**
 * 응답 코드
 */
public enum EMemState {
	일반("1", "일반"),
	군인("2", "군인"),
	해외("3", "해외"),
	장기결석("4", "장기결석"),
	새가족수료("5", "새가족수료"),
	졸업("6", "졸업"),
	기타("7", "기타")
	;

	/**
	 * 응답 코드
	 */
	private String code;

	/**
	 * 응답 메세지
	 */
	private String value;

	/**
	 * 기본 생성자
	 *
	 * @param code - 응답 코드
	 * @param value - 응답 값
	 */
	EMemState(String code, String value) {
		this.code = code;
		this.value = value;
	}

	/**
	 * 응답 코드 반환
	 *
	 * @return 응답 코드
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 응답 값 반환
	 *
	 * @return 응답 값
	 */
	public String getValue() {
		return value;
	}

	public static EMemState getEMemStateByCode(String code) {
		EMemState[] values = EMemState.values();
		
		for (EMemState responseCode : values) {
			if(responseCode.getCode().equals(code)) {
				return responseCode;
			}
		}
		
		return null;
	}
}
