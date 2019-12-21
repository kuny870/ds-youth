package org.ds.dsyouth.search.type;

/**
 * 응답 코드
 */
public enum ESeason {
	여름("summer", "여름"),
	겨울("winter", "겨울")
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
	ESeason(String code, String value) {
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

	public static ESeason getESeasonByCode(String code) {
		ESeason[] values = ESeason.values();
		
		for (ESeason responseCode : values) {
			if(responseCode.getCode().equals(code)) {
				return responseCode;
			}
		}
		
		return null;
	}
}
