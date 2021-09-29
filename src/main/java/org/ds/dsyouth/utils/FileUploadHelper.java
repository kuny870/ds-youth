package org.ds.dsyouth.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @className : FileUploadHelper
 * @description : FileUploadHelper
 * 
 * @modification : 2019. 9. 25.(최건희) 최초생성
 * 
 * @author keon
 * @since 2019. 9. 25.
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Keon All right reserved.
 */
@Component
public class FileUploadHelper {
	
	/**
	 * 파일 등록
	 * @param mhsr 파일
	 * @return string 저장된 파일 이름
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String fileUpload(MultipartHttpServletRequest mhsr) throws IllegalStateException, IOException {
		
		String path = mhsr.getSession().getServletContext().getRealPath("/resources/assets/images/profileImg");
		
		Iterator iter = mhsr.getFileNames(); 
		MultipartFile mfile = null; 
		String fieldName = ""; 
		
		File dir = new File(path); 
		if (!dir.isDirectory()) { 
			dir.mkdirs(); 
		} 
		
		fieldName = (String) iter.next();
		mfile = mhsr.getFile(fieldName);
		String origName;
		
		origName = new String(mfile.getOriginalFilename().getBytes("8859_1"), "UTF-8");
		
		String ext = origName.substring(origName.lastIndexOf('.'));
		String saveFileName = getUuid() + ext; 
		
		File serverFile = new File(path + File.separator + saveFileName); 
		mfile.transferTo(serverFile);
		
		return saveFileName;
	}
	
	public static String getUuid() { 
		return UUID.randomUUID().toString().replaceAll("-", ""); 
	}
	
	
	
	String fileName = "";
    
//  프로젝트 내 지정된 경로에 파일을 저장하는 메소드
//  DB에는 업로드된 전체 경로명으로만 지정되기 때문에(업로드한 파일 자체는 경로에 저장됨)
//  fileUpload() 메소드에서 전체 경로를 리턴받아 DB에 경로 그대로 저장   
    public String fileUpload(MultipartHttpServletRequest request,
                                        MultipartFile uploadFile) {
        String path = "";
        String fileName = "";
        
        OutputStream out = null;
        PrintWriter printWriter = null;
        
        try {
            fileName = uploadFile.getOriginalFilename();
            byte[] bytes = uploadFile.getBytes();
            path = getSaveLocation(request);
            
            System.out.println("UtilFile fileUpload fileName : " + fileName);
            System.out.println("UtilFile fileUpload uploadPath : " + path);
            
            File file = new File(path);
            
//          파일명이 중복으로 존재할 경우
            if (fileName != null && !fileName.equals("")) {
                if (file.exists()) {
//                    파일명 앞에 업로드 시간 초단위로 붙여 파일명 중복을 방지
                    fileName = System.currentTimeMillis() + "_" + fileName;
                    
                    file = new File(path + fileName);
                }
            }
            
            System.out.println("UtilFile fileUpload final fileName : " + fileName);
            System.out.println("UtilFile fileUpload file : " + file);
            
            out = new FileOutputStream(file);
            
            System.out.println("UtilFile fileUpload out : " + out);
            
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return path + fileName;
    }
    
//  업로드 파일 저장 경로 얻는 메소드
//  업로드한 파일의 경로가 도메인 별로 달라야 했기 때문에 도메인의 형을 비교하여 파일 저장 정로를 다르게 지정함
    private String getSaveLocation(MultipartHttpServletRequest request) {
        
        String uploadPath = request.getSession().getServletContext().getRealPath("/");
        String attachPath = "resources/files/nas/";
        
        System.out.println("UtilFile getSaveLocation path : " + uploadPath + attachPath);
        
        return uploadPath + attachPath;
    }
	
}