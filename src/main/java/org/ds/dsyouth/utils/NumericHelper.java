package org.ds.dsyouth.utils;

/**
 * @className : NumericHelper
 * @description : NumericHelper
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
public class NumericHelper {
	
	
	/**
	 * int -> string
	 * 
	 * @param arg 숫자
	 * @return string
	 * 
	 */
	public static String parseString(int arg) {
		return String.valueOf(arg);
	}

	
	/**
	 * long -> string
	 * 
	 * @param arg 숫자
	 * @return string
	 * 
	 */
	public static String parseString(long arg) {
		return String.valueOf(arg);
	}
	
	
	/**
	 * float -> string
	 * 
	 * @param arg 숫자
	 * @return string
	 * 
	 */
	public static String parseString(float arg) {
		return String.valueOf(arg);
	}
	
	
	/**
	 * double -> string
	 * 
	 * @param arg 숫자
	 * @return string
	 * 
	 */
	public static String parseString(double arg) {
		return String.valueOf(arg);
	}
	
	
	/**
	 * 소수점 끊기
	 * 
	 * @param arg 숫자
	 * @param cipher 자릿수
	 * @return double
	 * 
	 */
	public static double round(double arg, int cipher) {
		return Math.round(arg * Math.pow(10,  cipher)) / Math.pow(10, cipher);
	}
	
	
	/**
	 * 소수점 끊기
	 * 
	 * @param arg 숫자
	 * @param cipher 자릿수
	 * @return double
	 * 
	 */
	public static double round(float arg, int cipher) {
		return round((double) arg, cipher);
	}
	
	
	/**
	 * 배열안에 같이 있는지 체크
	 * 
	 * @param var 숫자
	 * @param arr 배열
	 * @return boolean
	 * 
	 */
	public static boolean inArray(int var, int[] arr) {
		for (int val : arr) {
			if (val == var) {
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * 배열안에 같이 있는지 체크
	 * 
	 * @param var 숫자
	 * @param arr 배열
	 * @return boolean
	 * 
	 */
	public static boolean inArray(long var, long[] arr) {
		for (long val : arr) {
			if (val == var) {
				return true;
			}
		}
		
		return false;
	}
	

	/**
	 * 배열안에 같이 있는지 체크
	 * 
	 * @param var 숫자
	 * @param arr 배열
	 * @return boolean
	 * 
	 */
	public static boolean inArray(float var, float[] arr) {
		for (float val : arr) {
			if (val == var) {
				return true;
			}
		}
		
		return false;
	}
	

	/**
	 * 배열안에 같이 있는지 체크
	 * 
	 * @param var 숫자
	 * @param arr 배열
	 * @return boolean
	 * 
	 */
	public static boolean inArray(double var, double[] arr) {
		for (double val : arr) {
			if (val == var) {
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
	 * @return int[]
	 * 
	 */
	public static int[] fixArray(int[] arr, int len) {
		if(arr.length < len) {
			int[] returnValue = new int[len];
			int i = 0;
			
			for(int val : arr) {
				returnValue[i++] = val;
			}
			
			return returnValue;
		}
		
		return arr;
	}
	
	
	/**
	 * 배열 검사 후 정해진 배열크기가 아니면 강제로 크기 맞추기
	 * 
	 * @param arr 배열
	 * @param len 최대 크기
	 * @return long[]
	 * 
	 */
	public static long[] fixArray(long[] arr, int len) {
		if(arr.length < len) {
			long[] returnValue = new long[len];
			int i = 0;
			
			for(long val : arr) {
				returnValue[i++] = val;
			}
			
			return returnValue;
		}
		
		return arr;
	}
	
	
	/**
	 * 배열 검사 후 정해진 배열크기가 아니면 강제로 크기 맞추기
	 * 
	 * @param arr 배열
	 * @param len 최대 크기
	 * @return float[]
	 * 
	 */
	public static float[] fixArray(float[] arr, int len) {
		if(arr.length < len) {
			float[] returnValue = new float[len];
			int i = 0;
			
			for(float val : arr) {
				returnValue[i++] = val;
			}
			
			return returnValue;
		}
		
		return arr;
	}
	
	
	/**
	 * 배열 검사 후 정해진 배열크기가 아니면 강제로 크기 맞추기
	 * 
	 * @param arr 배열
	 * @param len 최대 크기
	 * @return double[]
	 * 
	 */
	public static double[] fixArray(double[] arr, int len) {
		if(arr.length < len) {
			double[] returnValue = new double[len];
			int i = 0;
			
			for(double val : arr) {
				returnValue[i++] = val;
			}
			
			return returnValue;
		}
		
		return arr;
	}
	
}