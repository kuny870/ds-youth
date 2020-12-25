package org.ds.dsyouth.rest.common;

/**
 * 응답 코드
 */
public enum ResponseCode {
	SUCCESS("SUCC00", "")
	, UNKOWN("ERR0000", "알 수 없는 오류입니다")
	, ID_DUPLICATED("ERR0001", "해당 아이디가 이미 존재합니다")
	, ID_NO_MATCH("ERR0002", "해당 아이디가 없습니다")
	, PASSWORD_NO_MATCH("ERR0003", "현재 비밀번호가 맞지 않습니다")
	, EMAIL_NO_AUTH("ERR0004", "이메일을 인증해 주세요")
	, EMPTY_ID("ERR0005", "아이디를 입력해 주세요")
	, EMPTY_PW("ERR0006", "비밀번호를 입력해 주세요")
	, EMPTY_NAME("ERR0007", "이름을 입력해 주세요")
	, INVALID_INPUT_VALUE("ERR0008", "유효하지 않은 입력값입니다")
	, USER_NULL("ERR0009", "존재하지 않는 소환사입니다")
	, CHAMP_NULL("ERR0010", "존재하지 않는 챔피언입니다")
	, FILEUPLOAD_FAIL("ERR0011", "파일업로드에 실패했습니다")
	, FILE_CONTENTTYPE("ERR0012", "파일 형식이 맞지 않습니다")
	, BIRTHYEAR_DUPLICATED("ERR0013", "해당 동기가 이미 존재합니다")
	, TEAM_DUPLICATED("ERR0014", "해당 팀이 이미 존재합니다")
	, DEPART_DUPLICATED("ERR0015", "해당 부서가 이미 존재합니다")
	, NO_SUCH_ALGORITHM_EXCEPTION("ERR0016", "현재의 환경에서는 암호 알고리즘이 사용 불가합니다")
	, UNSUPPORTED_ENCODING_EXCEPTION("ERR0017", "인코딩 예러가 발생했습니다")
	, RETREATTABLE_DUPLICATED("ERR0018", "이미 존재하는 수련회명입니다")
	, EXCELDOWN_FAIL("ERR0019", "순편성을 먼저 진행해 주세요")
	;

	/**
	 * 응답 코드
	 */
	private String code;

	/**
	 * 응답 메세지
	 */
	private String message;

	/**
	 * 기본 생성자
	 *
	 * @param code - 응답 코드
	 * @param message - 응답 메세지
	 */
	ResponseCode(String code, String message) {
		this.code = code;
		this.message = message;
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
	 * 응답 메세지 반환
	 *
	 * @return 응답 메세지
	 */
	public String getMessage() {
		return message;
	}

	public static ResponseCode getResponseCodeByCode(String code) {
		ResponseCode[] values = ResponseCode.values();
		
		for (ResponseCode responseCode : values) {
			if(responseCode.getCode().equals(code)) {
				return responseCode;
			}
		}
		
		return null;
	}
}
