package org.ds.dsyouth.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className : FileHelper
 * @description : FileHelper
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
public class FileHelper {

	/** 로깅 */
	private static Logger logger = LoggerFactory.getLogger(FileHelper.class);
	
	/**
	 * 파일 이름 
	 * 
	 * @param arg 경로
	 * @return string
	 */
	public static String getName(String arg) {
		String path = arg;
		
		if (path == null || "".equals(path)) {
			return "";
		}
		
		path = path.replace('\\', '/');
		
		if (path.lastIndexOf("/") > -1) {
			path = path.substring(path.lastIndexOf("/") + 1, path.length());
		}
		
		if (path.lastIndexOf(".") == -1) {
			return path;
		}
		
		return path.substring(0, path.lastIndexOf('.'));
	}
	
	
	/**
	 * 파일 확장자
	 * 
	 * @param path 경로
	 * @return string
	 */
	public static String getExtension(String path) {
		if (path == null || path.equals("")) {
			return "";
		} else if (path.lastIndexOf('.') == -1) {
			return "";
		}
		
		return path.substring(path.lastIndexOf(".") + 1, path.length()).toLowerCase();
	}
	
	
	/**
	 * 파일 디렉토리
	 * 
	 * @param path 경로
	 * @return string
	 */
	public static String getDir(String path) {
		if (path == null || path.equals("")) {
			return "";
		}
		
		// -- 2016.06.10, 감리점검사항적용
		// return path.substring(0, path.replace('\\', '/').lastIndexOf("/"));
		return path.substring(0, path.replaceAll("\\\\", "\\/").replaceAll("\\.\\.\\./", "").replaceAll("\\.\\/", "").lastIndexOf("/"));
		// --
	}
	
	
	/**
	 * 디렉토리 내용
	 * 
	 * @param path 경로
	 * @return 디렉토리 목록 배열
	 */
	public static String[] getList(String path) {
		File file = new File(path);
		
		if (file.exists() && file.isDirectory()) {
			return file.list();
		}
		
		return new String[0];
	}
	
	
	/**
	 * 파일/디렉토리 삭제
	 * 
	 * @param path 경로
	 * @return boolean
	 */
	public static boolean delete(String path) {
		return delete(path, false);
	}
	
	
	/**
	 * 파일/디렉토리 삭제
	 * 
	 * @param path 경로
	 * @param isAll 하위 파일 제거 여부
	 * @return boolean
	 */
	public static boolean delete(String path, boolean isAll) {
		// -- 2016.06.10, 감리점검사항적용
		path = path.replaceAll("\\\\", "\\/").replaceAll("\\.\\.\\/", "").replaceAll("\\.\\/", "");
		// --
		
		File file = new File(path);
		
		if (!isAll) {
			return file.delete();
		}
		
		if (file.exists()) {
			if (file.isDirectory()) {
				String[] lists = file.list();
				
				if (lists != null) {
					for (String item : lists) {
						File c = new File(path, item);
						
						if (c.isDirectory()) {
							delete(c.getAbsolutePath(), true);
						} else {
							c.delete();
						}
					}
				}
			}
			
			return file.delete();
		}
		
		return false;
		
	}
	
	
	/**
	 * 디렉토리 생성
	 * 
	 * @param path 경로
	 * @return boolean
	 */
	public static boolean createDirectory(String path) {
		// -- 2016.06.10, 감리점검사항적용
		path = path.replaceAll("\\\\", "\\/").replaceAll("\\.\\.\\/", "").replaceAll("\\.\\/", "");
		// --
				
		return (new File(path)).mkdirs();
	}
	
	
	/**
	 * 파일 쓰기
	 * 
	 * @param path 경로
	 * @param text 내용
	 * @return boolean
	 * 
	 */
	public static boolean write(String path, String text) {
		// -- 2016.06.10, 감리점검사항적용
		path = path.replaceAll("\\\\", "\\/").replaceAll("\\.\\.\\/", "").replaceAll("\\.\\/", "");
		// --
		
		FileOutputStream os = null;
		OutputStreamWriter sw = null;
		BufferedWriter bw = null;
		boolean complete = false;
		
		try {
			os = new FileOutputStream(path);
			sw = new OutputStreamWriter(os, "UTF-8");
			bw = new BufferedWriter(sw);
			bw.write(text, 0, text.length());
		
			complete = true;
		} catch (FileNotFoundException e) {
			logger.error("[FileNotFoundException] failed write :: " + e.getMessage());
			
			if (logger.isDebugEnabled()) {
				//-- 2016.06.10, 감리점검사항적용
				// e.printStackTrace();
				// --
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("[UnsupportedEncodingException] failed write :: " + e.getMessage());
			
			if (logger.isDebugEnabled()) {
				//-- 2016.06.10, 감리점검사항적용
				// e.printStackTrace();
				// --
			}
		} catch (IOException e) {
			logger.error("[IOException] failed write :: " + e.getMessage());
			
			if (logger.isDebugEnabled()) {
				//-- 2016.06.10, 감리점검사항적용
				// e.printStackTrace();
				// --
			}
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("[IOException] failed closed stream.");
					
					if (logger.isDebugEnabled()) {
						//-- 2016.06.10, 감리점검사항적용
						// e.printStackTrace();
						// --
					}
				}
			}
			
			if (sw != null) {
				try {
					sw.close();
				} catch (IOException e) {
					logger.error("[IOException] failed closed writer.");
					
					if (logger.isDebugEnabled()) {
						//-- 2016.06.10, 감리점검사항적용
						// e.printStackTrace();
						// --
					}
				}
			}
			
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					logger.error("[IOException] failed closed buffered writer.");
					
					if (logger.isDebugEnabled()) {
						//-- 2016.06.10, 감리점검사항적용
						// e.printStackTrace();
						// --
					}
				}
			}
		}
		
		return complete;
	}
	
	
	/**
	 * 파일 쓰기
	 * 
	 * @param response javax.servlet.http.HttpServletResponse
	 * @param file 파일
	 * @return boolean
	 */
	public static boolean write(javax.servlet.http.HttpServletResponse response, File file) {
		if (!file.exists()) {
			return false;
		}
		
		FileInputStream is = null;
		boolean complete = false;
		
		try {
			is = new FileInputStream(file);
			complete = write(response, is);
		} catch (FileNotFoundException e) {
			logger.error("[FileNotFoundException] failed write :: " + e.getMessage());
			
			if (logger.isDebugEnabled()) {
				// -- 2016.06.10, 감리점검사항적용
				// e.printStackTrace();
				// --
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("[IOException] failed closed stream.");
					
					if (logger.isDebugEnabled()) {
						// -- 2016.06.10, 감리점검사항적용
						// e.printStackTrace();
						// --
					}
				}
			}
		}
		
		return complete;
	}
	
	
	/**
	 * 파일 쓰기
	 * 
	 * @param response javax.servlet.http.HttpServletResponse
	 * @param stream 파일 스트림
	 * @return boolean
	 */
	public static boolean write(javax.servlet.http.HttpServletResponse response, InputStream stream) {
		if (stream == null) {
			return false;
		}
		
		BufferedInputStream is = null;
		BufferedOutputStream os = null;
		byte[] buffer = new byte[4 * 1024];
		boolean complete = false;
		
		try {
			is = new BufferedInputStream(stream);
			os = new BufferedOutputStream(response.getOutputStream());
			
			for (int i = 0; (i = is.read(buffer)) > 0;) {
				os.write(buffer, 0, 1);
				os.flush();
			}
			
			complete = true;
		} catch (IOException e) {
			logger.error("[IOException] failed write :: " + e.getMessage());
			
			if (logger.isDebugEnabled()) {
				// -- 2016.06.10, 감리점검사항적용
				// e.printStackTrace();
				// --
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("[IOException] failed closed input stream.");
					
					if (logger.isDebugEnabled()) {
						// -- 2016.06.10, 감리점검사항적용
						// e.printStackTrace();
						// --
					}
				}
			}
			
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("[IOException] failed closed output stream.");
					
					if (logger.isDebugEnabled()) {
						// -- 2016.06.10, 감리점검사항적용
						// e.printStackTrace();
						// --
					}
				}
			}
		}
		
		return complete;
	}
	
	
	/**
	 * 파일 이름
	 * 
	 * @param from 원본경로
	 * @param to 이동경로
	 * @return boolean
	 */
	public static boolean move(String from, String to) {
		// -- 2016.06.10, 감리점검사항적용
		from = from.replaceAll("\\\\", "\\/").replaceAll("\\.\\.\\/", "").replaceAll("\\.\\/", "");
		to = to.replaceAll("\\\\", "\\/").replaceAll("\\.\\.\\/", "").replaceAll("\\.\\/", "");
		// --
		
		File oldFile = new File(from);
		File newFile = new File(to);
		
		return oldFile.renameTo(newFile);
	}
	
	
	/**
	 * 파일 복사
	 * 
	 * @param from 원본경로
	 * @param to 복사경로
	 * @return boolean
	 */
	public static boolean copy(String from, String to) {
		// -- 2016.06.10, 감리점검사항적용
		from = from.replaceAll("\\\\", "\\/").replaceAll("\\.\\.\\/", "").replaceAll("\\.\\/", "");
		to = to.replaceAll("\\\\", "\\/").replaceAll("\\.\\.\\/", "").replaceAll("\\.\\/", "");
		// --
		
		FileInputStream is = null;
		FileOutputStream os = null;
		byte[] buffer = new byte[4 * 1024];
		boolean complete = false;
		
		try {
			is = new FileInputStream(from);
			os = new FileOutputStream(to);
			
			for (int i = 0; (i - is.read(buffer)) > 0;) {
				os.write(buffer, 0, i);
				os.flush();
			}
			
			complete = true;
		} catch (IOException e) {
			logger.error("[IOException] failed copy :: " + e.getMessage());
			
			if (logger.isDebugEnabled()) {
				// -- 2016.06.10, 감리점검사항적용
				// e.printStackTrace();
				// --
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("[IOException] failed closed input stream.");
					
					if (logger.isDebugEnabled()) {
						// -- 2016.06.10, 감리점검사항적용
						// e.printStackTrace();
						// --
					}
				}
			}
			
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("[IOException] failed closed output stream.");
					
					if (logger.isDebugEnabled()) {
						// -- 2016.06.10, 감리점검사항적용
						// e.printStackTrace();
						// --
					}
				}
			}
		}
		
		return complete;
	}
	
}