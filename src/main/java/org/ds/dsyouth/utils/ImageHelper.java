package org.ds.dsyouth.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className : ImageHelper
 * @description : ImageHelper
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
public class ImageHelper {
	/** 로깅 */
	private static Logger logger = LoggerFactory.getLogger(ImageHelper.class);
	
	/**
	 * 길이 구하기
	 * 
	 * @param source 경로
	 * @return 가로/세로 길이
	 */
	public static int[] getLength(String source) {
		// -- 2016.06.10, 감리점검사항적용
		source = source.replaceAll("\\\\", "\\/").replaceAll("\\.\\.\\/", "").replaceAll("\\.\\/", "");
		// --
		
		Image image = new ImageIcon(source).getImage();
		int[] returnValue = {image.getWidth(null), image.getHeight(null)};
		
		return returnValue;
	}
	
	
	/**
	 * 길이 보정
	 * 
	 * @param imgW 가로
	 * @param imgH 세로
	 * @param maxW 가로 최대
	 * @param maxH 세로 최대
	 * @return 보정된 가로/세로 길이
	 */
	public static int[] getAdjustedLength(int imgW, int imgH, int maxW, int maxH) {
		int resW = imgW;
		int resH = imgH;
		double ratW;
		double ratH;
		int[] returnValue = {0, 0};
		
		if (resW == 0 || resH == 0) {
			resW = maxW;
			resH = maxH;
		} else if (maxW > 0 && maxH > 0) {
			if (resW > maxW || resH > maxH) {
				ratW = (double) maxW / resW;
				ratH = (double) maxH / resH;
				
				if (ratW < ratH) {
					resH = (int) Math.ceil((double) resH * ratW);
					resW = maxW;
				} else {
					resW = (int) Math.ceil((double) resW * ratH);
					resH = maxH;
				}
			}
		} else if (maxW > 0) {
			if (resW > maxW) {
				ratW = (double) maxW / resW;
				resW = maxW;
			} else {
				ratW = 1;
			}
			
			resH = (int) Math.ceil((double) resH * ratW);
		} else if (maxH > 0) {
			if (resH > maxH) {
				ratH = (double) maxH / resH;
				resH = maxH;
			} else {
				ratH = 1;
			}
			
			resW = (int) Math.ceil((double) resW * ratH);
		}
		
		returnValue[0] = resW;
		returnValue[1] = resH;
		
		
		return returnValue;
	}
	
	
	/**
	 * 썸네일 만들기
	 *  
	 * @param source 원본 경로
	 * @param target 저장 경로
	 * @param width 기준 가로
	 * @param height 기준 세로
	 * @return 생성된 파일 객체
	 */
	public static File createThumbnail(String source, String target, int width, int height) {
		// -- 2016.06.10, 감리점검사항적용
		source = source.replaceAll("\\\\", "\\/").replaceAll("\\.\\.\\/", "").replaceAll("\\.\\/", "");
		target = target.replaceAll("\\\\", "\\/").replaceAll("\\.\\.\\/", "").replaceAll("\\.\\/", "");
		// --
		
		if (width == 0 && height == 0) {
			return null;
		}
		
		OutputStream os = null;
		//com.sun.image.codec.jpeg.JPEGImageEncoder encodoer = null;
		File file = null;
		
		try {
			Image image = new ImageIcon((new File(source)).toURI().toURL()).getImage();
			int[] orgs = {0, 0};
			orgs[0] = image.getWidth(null);
			orgs[1] = image.getHeight(null);
			
			int[] adjs = getAdjustedLength(orgs[0], orgs[1], width, height);
			double[] scale = {0, 0};
			
			if (orgs[0] > 0) {
				scale[0] = (double) adjs[0] / (double) orgs[0];
			}
			
			if (orgs[1] > 0) {
				scale[1] = (double) adjs[1] / (double) orgs[1];
			}
			
			AffineTransform at = new AffineTransform();
			at.scale(scale[0], scale[1]);
			
			RenderingHints rHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
			rHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			
			BufferedImage bi = new BufferedImage(adjs[0], adjs[1], BufferedImage.TYPE_INT_RGB);
			Graphics2D grps2D = bi.createGraphics();
			grps2D.setRenderingHints(rHints);
			grps2D.drawImage(image, at, null);
			grps2D.dispose();
			
			os = new FileOutputStream(target);
			//encoder = com.sun.image.codec.jpg.JPEGCodec.createJPEGEncoder(os);
			//encoder.encode(bi);
			file = new File(target);
			ImageIO.write(bi, "jpg", file);
		} catch (MalformedURLException e) {
			logger.error("[MalformedURLException] failed create thumbnail :: " + e.getMessage());
			
			if (logger.isDebugEnabled()) {
				// -- 2016.06.10, 감리점검사항적용
				// e.printStackTrace();
				// --
			}
		} catch (FileNotFoundException e) {
			logger.error("[FileNotFoundException] failed create thumbnail :: " + e.getMessage());
			
			if (logger.isDebugEnabled()) {
				// -- 2016.06.10, 감리점검사항적용
				// e.printStackTrace();
				// --
			}
		}/* catch (com.sun.image.codec.jpeg.ImageFormatException e) {
			logger.error("[ImageFormatException] failed create thumbnail :: " + e.getMessage());
			
			if (logger.isDebugEnabled()) {
				// -- 2016.06.10, 감리점검사항적용
				// e.printStackTrace();
				// --
			}
		}*/ catch (IOException e) {
			logger.error("[IOException] failed create thumbnail :: " + e.getMessage());
			
			if (logger.isDebugEnabled()) {
				// -- 2016.06.10, 감리점검사항적용
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
						// -- 2016.06.10, 감리점검사항적용
						// e.printStackTrace();
						// --
					}
				}
			}
		}
		
		return file;
	}
	
}