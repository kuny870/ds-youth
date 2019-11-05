package org.ds.dsyouth.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className : StringHelper
 * @description : StringHelper
 * 
 * @modification : 2019. 8. 27.(최건희) 최초생성
 * 
 * @author keon
 * @since 2019. 8. 27.
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Keon All right reserved.
 */
public class StringHelper {

	private static final String REMOVE_TAG_OR_ELEMENT = "javascript|vbscript|alert|confirm|prompt|expression|eval|body|embed|frame"
			+ "|script|link|iframe|object|style|frameset|meta|onclick|onkeyup|ondblclick|onplaying|onmousedown|oncanplay"
			+ "|onmouseup|onblur|onmouseover|onfocus|onmouseout|onscroll|onsubmit|onresize|onkeypress|onload|onunload|onabort|onerror|on|elect|onchange|onreset";
	
	
	/**
	 * 캐릭터셋 변경
	 * 
	 * @param arg 변경할 문자열
	 * @return string
	 * 
	 */
	public static String changeCharacterSet(String arg) {
		return changeCharacterSet(arg, "ISO-8859-1", "UTF-8");
	}
	
	
	/**
	 * 캐릭터셋 변경
	 * 
	 * @param arg 변경할 문자열
	 * @param source 변경 전 캐릭터 셋
	 * @param target 변경 후 캐릭터 셋
	 * @return string
	 * 
	 */
	public static String changeCharacterSet(String arg, String source, String target) {
		try {
			return new String(arg.getBytes(source), target);
		} catch (UnsupportedEncodingException e) {
			return arg;
		}
	}
	
	
	/**
	 * 빈 문자열 체크
	 * 
	 * @param arg 체크할 문자열
	 * @return boolean
	 */
	public static boolean isEmpty(String arg) {
		if (arg == null) {
			return true;
		} else if ("".equals(arg)) {
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * 빈 문자열일 경우 기본값 할당
	 * 
	 * @param arg 체크할 문자열
	 * @param defaultValue 기본값
	 * @return string
	 */
	public static String nvl(String arg, String defaultValue) {
		if (isEmpty(arg)) {
			return defaultValue;
		}
		
		return arg;
	}
	
	
	/**
	 * 인코딩
	 * 
	 * @param arg 인코딩할 문자열
	 * @return string
	 */
	public static String encode(String arg) {
		return encode(arg, "UTF-8");
	}
	
	
	/**
	 * 인코딩
	 * 
	 * @param arg 인코딩할 문자열
	 * @param charset 인코딩할 캐릭터셋
	 * @return string
	 */
	public static String encode(String arg, String charset) {
		if (isEmpty(arg)) {
			return "";
		}
		
		try {
			return URLEncoder.encode(arg, charset);
		} catch (UnsupportedEncodingException e) {
			return arg;
		}
	}
	

	/**
	 * 디코딩
	 * 
	 * @param arg 디코딩할 문자열
	 * @return string 
	 */ 
	public static String decode(String arg) {
		return encode(arg, "UTF-8");
	}
	
	
	/**
	 * 디코딩
	 * 
	 * @param arg 디코딩할 문자열
	 * @param charset 디코딩할 캐릭터셋
	 * @return string 
	 */ 
	public static String decode(String arg, String charset) {
		if (isEmpty(arg)) {
			return "";
		}
		
		try {
			return URLDecoder.decode(arg, charset);
		} catch (UnsupportedEncodingException e) {
			return arg;
		}
	}
	
	
	/**
	 * string -> int(배열의 크기 점검)
	 * 
	 * @param arg 변경할 문자열
	 * @return int
	 * 
	 */
	public static int parseIntAndArrayRange(String arg) {
		if (isEmpty(arg)) {
			return 0;
		}
		
		try {
			int size = new Integer(arg).intValue();
			if (size < 0) {
				return 0;
			}
			return Integer.parseInt(arg.trim());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	
	/**
	 * string -> int
	 * 
	 * @param arg 변경할 문자열
	 * @return int
	 * 
	 */
	public static int parseInt(String arg) {
		if (isEmpty(arg)) {
			return 0;
		}
		
		try {
			return Integer.parseInt(arg.trim());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	
	/**
	 * string -> long
	 * 
	 * @param arg 변경할 문자열
	 * @return long
	 * 
	 */
	public static long parseLong(String arg) {
		if (isEmpty(arg)) {
			return 0;
		}
		
		try {
			return Long.parseLong(arg.trim());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	
	/**
	 * string -> float
	 * 
	 * @param arg 변경할 문자열
	 * @return float
	 * 
	 */
	public static float parseFloat(String arg) {
		if (isEmpty(arg)) {
			return 0;
		}
		
		try {
			return Float.parseFloat(arg.trim());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	
	/**
	 * string -> double
	 * 
	 * @param arg 변경할 문자열
	 * @return double
	 * 
	 */
	public static double parseDouble(String arg) {
		if (isEmpty(arg)) {
			return 0;
		}
		
		try {
			return Double.parseDouble(arg.trim());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	
	/**
	 * string -> boolean
	 * 
	 * @param arg 변경할 문자열
	 * @return boolean
	 * 
	 */
	public static boolean parseBoolean(String arg) {
		if (isEmpty(arg)) {
			return false;
		}
		
		return Boolean.parseBoolean(arg.trim());
	}
	
	
	/**
	 * 기준 문자열로 정리
	 * 
	 * @param arg 문자열
	 * @param d1 체크할 기준 문자열
	 * @param d2 정리할 기준 문자열
	 * @return string
	 * 
	 */
	public static String arrange(String arg, String d1, String d2) {
		StringBuffer sb = new StringBuffer();
		
		if(!isEmpty(arg)) {
			int c = 0;
			
			for (String val : arg.split(d1)) {
				if (!isEmpty(val)) {
					if (c == 0) {
						sb.append(val);
					} else {
						sb.append(d2);
						sb.append(val);
					}
					
					c++;
				}
			}
		}
		
		return sb.toString();
	}
	
	
	/**
	 * 문자열 반복
	 * 
	 * @param arg 문자열
	 * @param cnt 반복 횟수
	 * @return string
	 * 
	 */
	public static String repeat(String arg, int cnt) {
		StringBuffer sb = new StringBuffer();
		
		for (int i = 1; i <= cnt; i++) {
			sb.append(arg);
		}
		
		return sb.toString();
	}
	
	
	/**
	 * 태그 제거
	 * 
	 * @param arg 변경할 문자열
	 * @return string
	 * 
	 */
	public static String stripTag(String arg) {
		if (arg == null) {
			return "";
		}
		
		return arg.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}
	
	
	/**
	 * 불필요한 태그 및 요소가 들어 있는 태그 전체 삭제
	 *  
	 * @param arg 변경할 문자열
	 * @return string
	 * 
	 */
	public static String removeTagOrElement(String arg) {
		if (arg == null) {
			return "";
		}
		
		return arg.replaceAll("(?i)<(/)?([ a-zA-Z0-9])*("+REMOVE_TAG_OR_ELEMENT+").*?>", "");
	}
	
	
	/**
	 * HTML 태그를 TEXT 코드로 변경
	 * 
	 * @param arg 변경할 문자열
	 * @return string
	 * 
	 */
	public static String filterHTML(String arg) {
		if (arg == null) {
			return "";
		}
		
		return arg.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"",  "&quot;").replaceAll("'", "&apos;");
	}
	
	
	/**
	 * 줄바꿈
	 * 
	 * @param arg 변경할 문자열
	 * @return string
	 * 
	 */
	public static String nl2br(String arg) {
		if (arg == null) {
			return "";
		}
		
		return arg.replaceAll("\r", "").replaceAll("\n", "<br />\r\n");
	}
	
	
	/**
	 * 링크 태그 생성
	 * 
	 * @param arg
	 * @return
	 */
	public static String createLink(String arg) {
		if (arg == null) {
			return "";
		}
		
		return arg.replaceAll("([\\p(Alnum)]+)://([a-zA-Z0-9.\\-&/%=?:@#$(),.+;~\\_]+)", "<a href=\"$1://$2\" target=\"_blank\" title=\"Open in new window\">$1://$2</a>");
	}
	
	
	/**
	 * 문자열 자르기
	 * 
	 * @param arg 문자열
	 * @param len 길이
	 * @param tail 꼬릿말
	 * @return string
	 * 
	 */
	public static String cut(String arg, int len, String tail) {
		if (isEmpty(arg)) {
			return "";
		}
		
		if (arg.length() <= len) {
			return arg;
		}
		
		int count = 0;
		
		for (int i = 0; i < arg.length(); i++) {
			if(count > len) {
				break;
			}
			
			if ((char) arg.charAt(i) >= 127) {
				count += 2;
			} else {
				count += 1;
			}
		}
		
		return arg.substring(0, count > arg.length() ? arg.length() : count) + tail;
	}
	
	
	/**
	 * 배열안에 값이 있는지 체크
	 * 
	 * @param var 문자열
	 * @param arr 배열
	 * @return boolean
	 * 
	 */
	public static boolean inArray(String var, String[] arr) {
		for (String val : arr) {
			if (val.equals(var)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * 배열 검사 후 정해진 배열크기가 아니면 강제로 크기 맞추기
	 * 
	 * @param arr 배열
	 * @param len 최대 크기
	 * @return string[]
	 * 
	 */
	public static String[] fixArray(String[] arr, int len) {
		if (arr.length < len) {
			String[] returnValue = new String[len];
			int i = 0;
			
			for (String val : arr) {
				returnValue[i++] = val;
			}
			
			return returnValue;
		}
		
		return arr;
	}
	
	
	/**
	 * URL 파라미터형 문자열 파싱
	 * 
	 * @param arg 문자열
	 * @param var 읽어올 변수명
	 * @return string
	 * 
	 */
	public static Map<String, String> parseQueryString(String arg) {
		Map<String, String> returnValue = new HashMap<String, String>();
		
		for (String val : arg.split("&")) {
			String[] col = val.split("=");
			
			if (col.length == 2) {
				returnValue.put(col[0], col[1]);
			}
		}
		
		return returnValue;
	}
	
	
	/**
	 * 역순 정렬
	 * 
	 * @param arg 문자열
	 * @return string
	 * 
	 */
	public static String reverse(String arg) {
		if (isEmpty(arg)) {
			return "";
		}
		
		return (new StringBuffer(arg)).reverse().toString();
	}
	
	
	/**
	 * 배열 조인
	 * 
	 * @param arg 배열
	 * @param dlm 구분자
	 * @return string
	 * 
	 */
	public static String join(String[] arg, String dlm) {
		if (arg == null) {
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		
		for (String v : arg) {
			if (sb.length() > 0) {
				sb.append(dlm);
			}
			
			sb.append(v);
		}
		
		return sb.toString();
	}
	
	
	/**
	 * 이메일 검증
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {
		boolean err = false;
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		if(m.matches()) {
			err = true;
		}
		return err;
	}
	
}