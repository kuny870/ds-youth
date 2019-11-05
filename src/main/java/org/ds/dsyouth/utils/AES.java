package org.ds.dsyouth.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className : AES
 * @description : AES 암호화 알고리즘
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
public class AES {
	
	private Logger logger = LoggerFactory.getLogger(AES.class);
	private static volatile AES INSTANCE;
	private static SecretKeySpec KEY_SPEC = null;
	private static IvParameterSpec IV_SPEC = null;
	
	/**
	 * AES
	 * 
	 * @param secretKey 대칭키
	 */
	private AES(String secretKey) {
		if (KEY_SPEC == null) {
			KEY_SPEC = new SecretKeySpec(secretKey.getBytes(), "AES");
			IV_SPEC = new IvParameterSpec(secretKey.getBytes());
		}
	}
	
	
	/**
	 * 인스턴스 생성
	 * 
	 * @param secretKey 대칭키
	 * @return 인스턴스
	 * @throws Exception
	 */
	public static AES getInstance(String secretKey) {
		if (INSTANCE == null) {
			synchronized (AES.class) {
				if (INSTANCE == null) {
					INSTANCE = new AES(secretKey);
				}
			}
		}
		
		return INSTANCE;
	}
	

	/**
	 * 암호화
	 * 
	 * @param arg 암호화할 문자열
	 * @return 암호화된 문자열
	 * @throws Exception
	 */
	public String encrypt(String arg) {
		String encryptedText = null;
		
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCSSPadding");
			cipher.init(Cipher.ENCRYPT_MODE, KEY_SPEC, IV_SPEC);
			
//			encryptedText = new String(Base64.encodeBase64(cipher.doFinal(arg.getBytes("UTF-8"))));
			sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
			encryptedText = encoder.encode(cipher.doFinal(arg.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException e) {
			logger.error("[NoSuchAlgorithmException] failed encrypt " + arg + " :: " + e.getMessage());
		} catch (NoSuchPaddingException e) {
			logger.error("[NoSuchPaddingException] failed encrypt " + arg + " :: " + e.getMessage());
		} catch (InvalidKeyException e) {
			logger.error("[InvalidKeyException] failed encrypt " + arg + " :: " + e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			logger.error("[InvalidAlgorithmParameterException] failed encrypt " + arg + " :: " + e.getMessage());
		} catch (IllegalBlockSizeException e) {
			logger.error("[IllegalBlockSizeException] failed encrypt " + arg + " :: " + e.getMessage());
		} catch (BadPaddingException e) {
			logger.error("[BadPaddingException] failed encrypt " + arg + " :: " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error("[UnsupportedEncodingException] failed encrypt " + arg + " :: " + e.getMessage());
		}
		
		return encryptedText;
	}
	
	
	/**
	 * 복호화
	 * 
	 * @param arg 복호화할 문자열
	 * @return 복호환된 문자열
	 * @throws Exception
	 */
	public String decrypt(String arg) {
		String decryptedText = null;
		
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCSSPadding");
			cipher.init(Cipher.DECRYPT_MODE, KEY_SPEC, IV_SPEC);
			
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			byte[] inputBytes1 = decoder.decodeBuffer(arg);
			byte[] outputBytes2 = cipher.doFinal(inputBytes1);
			decryptedText = new String(outputBytes2, "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			logger.error("[NoSuchAlgorithmException] failed dencrypt " + arg + " :: " + e.getMessage());
		} catch (NoSuchPaddingException e) {
			logger.error("[NoSuchPaddingException] failed dencrypt " + arg + " :: " + e.getMessage());
		} catch (InvalidKeyException e) {
			logger.error("[InvalidKeyException] failed dencrypt " + arg + " :: " + e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			logger.error("[InvalidAlgorithmParameterException] failed dencrypt " + arg + " :: " + e.getMessage());
		} catch (IllegalBlockSizeException e) {
			logger.error("[IllegalBlockSizeException] failed dencrypt " + arg + " :: " + e.getMessage());
		} catch (BadPaddingException e) {
			logger.error("[BadPaddingException] failed dencrypt " + arg + " :: " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error("[UnsupportedEncodingException] failed dencrypt " + arg + " :: " + e.getMessage());
		} catch (Exception e) {
			logger.error("[Exception] failed dencrypt " + arg + " :: " + e.getMessage());
		}
		
		return decryptedText;
	}
	
}