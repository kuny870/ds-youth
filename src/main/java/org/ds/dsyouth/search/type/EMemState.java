package org.ds.dsyouth.search.type;

/**
 * 응답 코드
 */
public enum EMemState {
//	일반("1", "일반"),
//	군지체("2", "군지체"),
//	해외지방("3", "해외/지방"),
//	장기결석("4", "장기결석"),
//	새가족수료("5", "새가족수료"),
//	졸업("6", "졸업"),
//	기타("7", "기타")
	일반("1", "일반"),
	장기결석("3", "장기결석"),
	해외지방("4", "해외/지방"),
	군지체("5", "군지체"),
	새가족수료("6", "새가족수료"),
	졸업("7", "졸업"),
	기타("2", "기타")
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
