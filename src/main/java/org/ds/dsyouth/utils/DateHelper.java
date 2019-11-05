package org.ds.dsyouth.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

/**
 * @className : DateHelper
 * @description : DateHelper
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
public class DateHelper {

	private static final String TIME_SERVER = "pool.ntp.org";
	
	// NTP 타임 서버 오늘 년도 가져오기
    public static String getNTPYear() {
		// 타임 서버에서 시간 가져오기!
		// --------------------------------------------------------
		NTPUDPClient timeClient = new NTPUDPClient();
		timeClient.setDefaultTimeout(1000);

		String year = null;
		
		try {
			timeClient.open();
			InetAddress address = InetAddress.getByName(TIME_SERVER);
	        TimeInfo timeInfo = timeClient.getTime(address);
	        long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime(); //서버로부터 시간 가져오는 코드
	        Date date = new Date(returnTime);
	        LocalDateTime localDateTime = 
	                date.toInstant()
	                .atZone(ZoneId.systemDefault())
	                .toLocalDateTime();//date to LocalDateTime
	        
	        year = String.valueOf(localDateTime.getYear());
	        
		} catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		return year;
	}
    
    // NTP 타임 서버 오늘 월 가져오기
    public static String getNTPMonth() {
		// 타임 서버에서 시간 가져오기!
		// --------------------------------------------------------
		NTPUDPClient timeClient = new NTPUDPClient();
		timeClient.setDefaultTimeout(1000);

		String month = null;
		
		try {
			timeClient.open();
			InetAddress address = InetAddress.getByName(TIME_SERVER);
	        TimeInfo timeInfo = timeClient.getTime(address);
	        long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime(); //서버로부터 시간 가져오는 코드
	        Date date = new Date(returnTime);
	        LocalDateTime localDateTime = 
	                date.toInstant()
	                .atZone(ZoneId.systemDefault())
	                .toLocalDateTime();//date to LocalDateTime
	        
	        month = String.valueOf(localDateTime.getMonthValue());
	        
		} catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		return month;
	}
	
    // NTP 타임 서버 오늘 일 가져오기
    public static String getNTPDay() {
		// 타임 서버에서 시간 가져오기!
		// --------------------------------------------------------
		NTPUDPClient timeClient = new NTPUDPClient();
		timeClient.setDefaultTimeout(1000);

		String day = null;
		
		try {
			timeClient.open();
			InetAddress address = InetAddress.getByName(TIME_SERVER);
	        TimeInfo timeInfo = timeClient.getTime(address);
	        long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime(); //서버로부터 시간 가져오는 코드
	        Date date = new Date(returnTime);
	        LocalDateTime localDateTime = 
	                date.toInstant()
	                .atZone(ZoneId.systemDefault())
	                .toLocalDateTime();//date to LocalDateTime
	        
	        day = String.valueOf(localDateTime.getDayOfMonth());
	        
		} catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		return day;
	}
    
    // NTP 타임 서버 오늘 일 가져오기
    public static String getNTPHour() {
		// 타임 서버에서 시간 가져오기!
		// --------------------------------------------------------
		NTPUDPClient timeClient = new NTPUDPClient();
		timeClient.setDefaultTimeout(1000);

		String hour = null;
		
		try {
			timeClient.open();
			InetAddress address = InetAddress.getByName(TIME_SERVER);
	        TimeInfo timeInfo = timeClient.getTime(address);
	        long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime(); //서버로부터 시간 가져오는 코드
	        Date date = new Date(returnTime);
	        LocalDateTime localDateTime = 
	                date.toInstant()
	                .atZone(ZoneId.systemDefault())
	                .toLocalDateTime();//date to LocalDateTime
	        
	        hour = String.valueOf(localDateTime.getHour());
	        
		} catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		return hour;
	}
    
    // 오늘 년
    public static String getYear() {
    	GregorianCalendar cal = new GregorianCalendar();
    	long date = cal.getTime().getTime() + 32400000;
    	String year = (new SimpleDateFormat("yyyy")).format(date);
    	return year;
    }
    
    // 오늘 월
    public static String getMonth() {
    	GregorianCalendar cal = new GregorianCalendar();
    	long date = cal.getTime().getTime() + 32400000;
    	String month = (new SimpleDateFormat("MM")).format(date);
    	return month;
    }
    
    // 오늘 일
    public static String getDay() {
    	GregorianCalendar cal = new GregorianCalendar();
    	long date = cal.getTime().getTime() + 32400000;
    	String day = (new SimpleDateFormat("dd")).format(date);
    	return day;
    }
    
    // 오늘 시간
    public static String getHour() {
    	GregorianCalendar cal = new GregorianCalendar();
    	long date = cal.getTime().getTime() + 32400000;
    	String hour = (new SimpleDateFormat("HH")).format(date);
    	return hour;
    }
	
	/**
	 * 오늘 날짜
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 * 
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd HH:mm:ss");
	}
	
	
	/**
	 * 오늘 날짜
	 * 
	 * @param pattern date format symbols
	 * @return string
	 * 
	 */
	public static String getDate(String pattern) {
		return (new SimpleDateFormat(pattern)).format(new Date());
	}
	
	
	/**
	 * 특정 날짜
	 * 
	 * @param date Date
	 * @return yyyy-MM-dd HH:mm:ss
	 * 
	 */
	public static String getDate(Date date) {
		return getDate(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 특정 날짜2
	 * 
	 * @param date Date
	 * @return yyyy-MM-dd
	 * 
	 */
	public static String getDate2(Date date) {
		return getDate(date, "yyyy-MM-dd");
	}
	
	
	/**
	 * 특정 날짜
	 * 
	 * @param date Date
	 * @param pattern date format symbols
	 * @return string
	 * 
	 */
	public static String getDate(Date date, String pattern) {
		return (new SimpleDateFormat(pattern)).format(date);
	}
	
	
	/**
	 * 특정 날짜
	 * 
	 * @param date Date
	 * @param pattern date format symbols
	 * @return string
	 * 
	 */
	public static String getDate(String sDate, String pattern) {
		int y = Integer.parseInt(sDate.substring(0, 4));
		int m = Integer.parseInt(sDate.substring(4, 6));
		int d = Integer.parseInt(sDate.substring(6, 8));
		
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.set(y, m - 1, d);
		return (new SimpleDateFormat(pattern)).format(cal.getTime());
	}
	
	
	/**
	 * 특정 날짜
	 *  
	 * @param cal Calendar
	 * @return yyyy-MM-dd HH:mm:ss
	 * 
	 */
	public static String getDate(Calendar cal) {
		return getDate(cal, "yyyy-MM-dd HH:mm:ss");
	}
	
	
	/**
	 * 특정 날짜
	 * @param cal Calendar
	 * @param pattern date format symbols
	 * @return string
	 * 
	 */
	public static String getDate(Calendar cal, String pattern) {
		return (new SimpleDateFormat(pattern)).format(cal.getTime());
	}
	
	
	/**
	 * 유효한 날짜인지 체크
	 * 
	 * @param sYear 년
	 * @param sMonth 월
	 * @param sDay 일
	 * @return boolean
	 * 
	 */
	public static boolean isDate(int sYear, int sMonth, int sDay) {
		if (sYear < 0 || sMonth <= 0 || sDay <= 0 || sMonth > 12 || sDay > 31) {
			return false;
		} else if (sDay > getLastDayOfMonth(sYear, sMonth)) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 유효한 날짜인지 체크
	 * 
	 * @param sDate 날짜 (yyyyMMdd)
	 * @return boolean
	 * 
	 */
	public static boolean isDate(String sDate) {
		if (sDate == null || sDate.length() != 8) {
			return false;
		}
		
		int sYear = Integer.parseInt(sDate.substring(0, 4));
		int sMonth = Integer.parseInt(sDate.substring(4, 6));
		int sDay = Integer.parseInt(sDate.substring(6, 8));
		
		return isDate(sYear, sMonth, sDay);
	}
	
	
	/**
	 * 해당 월의 마지막 일수
	 * 
	 * @param sYear 년
	 * @param sMonth 월
	 * @return boolean
	 * 
	 */
	public static int getLastDayOfMonth(int sYear, int sMonth) {
		Calendar cal = Calendar.getInstance();
		cal.set(sYear, sMonth - 1, 1);
		
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	

	/**
	 * 해당 월의 마지막 월수
	 * 
	 * @param sDate 날자(yyyyMM)
	 * @return boolean
	 * 
	 */
	public static int getLastDayOfMonth(String sDate) {
		int sYear = Integer.parseInt(sDate.substring(0, 4));
		int sMonth = Integer.parseInt(sDate.substring(4, 6));
		
		return getLastDayOfMonth(sYear, sMonth);
	}
	
	
	/**
	 * 두 날짜간의 날짜수 반환
	 * 
	 * @param sDate 시작 날짜 (yyyyMMdd)
	 * @param eDate 종료 날짜 (yyyyMMdd)
	 * @return long
	 * 
	 */
	public static long getDifferDay(String sDate, String eDate) {
		int sy = Integer.parseInt(sDate.substring(0, 4));
		int sm = Integer.parseInt(sDate.substring(4, 6));
		int sd = Integer.parseInt(sDate.substring(6, 8));
		int ey = Integer.parseInt(eDate.substring(0, 4));
		int em = Integer.parseInt(eDate.substring(4, 6));
		int ed = Integer.parseInt(eDate.substring(6, 8));
		
		GregorianCalendar s = new GregorianCalendar(sy, sm - 1, sd, 0, 0, 0);
		GregorianCalendar e = new GregorianCalendar(ey, em - 1, ed, 0, 0, 0);
		
		return (e.getTime().getTime() - s.getTime().getTime()) / 86400000;
	}
	
	
	/**
	 * 특정 날짜와 오늘 날짜간의 날짜수 반환
	 * 
	 * @param sDate 시작 날짜 (yyyyMMdd)
	 * @param eDate 종료 날짜 (yyyyMMdd)
	 * @return long
	 */
	public static long getDifferDay(String sDate) {
		return getDifferDay(sDate, getDate("yyyyMMdd"));
	}
	
	
	/**
	 * 두 날짜간의 시간수 반환
	 * 
	 * @param sDate 시작 날짜 (yyyyMMddHHmmss)
	 * @param eDate 종료 날짜 (yyyyMMddHHmmss)
	 * @return long
	 * 
	 */
	public static long getDifferTime(String sDate, String eDate) {
		int sy = Integer.parseInt(sDate.substring(0, 4));
		int sm = Integer.parseInt(sDate.substring(4, 6));
		int sd = Integer.parseInt(sDate.substring(6, 8));
		int sh = Integer.parseInt(sDate.substring(8, 10));
		int sn = Integer.parseInt(sDate.substring(10, 12));
		int ss = Integer.parseInt(sDate.substring(12, 14));
		int ey = Integer.parseInt(eDate.substring(0, 4));
		int em = Integer.parseInt(eDate.substring(4, 6));
		int ed = Integer.parseInt(eDate.substring(6, 8));
		int eh = Integer.parseInt(eDate.substring(8, 10));
		int en = Integer.parseInt(eDate.substring(10, 12));
		int es = Integer.parseInt(eDate.substring(12, 14));
		
		GregorianCalendar s = new GregorianCalendar(sy, sm - 1, sd, sh, sn, ss);
		GregorianCalendar e = new GregorianCalendar(ey, em - 1, ed, eh, en, es);
		
		return (e.getTime().getTime() - s.getTime().getTime()) / 1000;
	}
	
	
	/**
	 * 특정 날짜와 오늘 날짜간의 시간수 반환
	 * 
	 * @param sDate 시작 날짜 (yyyyMMddHHmmss)
	 * @return long
	 * 
	 */
	public static long getDifferTime(String sDate) {
		return getDifferTime(sDate, getDate("yyyyMMddHHmmss"));
	}
	
	
	/**
	 * 두 날짜간의 시간수 반환
	 * 
	 * @param sYmd 시작 일자 (yyyyMMdd)
	 * @param sHms 시작 시간 (HHmmss)
	 * @param eYmd 종료 일자 (yyyyMMdd)
	 * @param eHms 종료 시간 (HHmmss)
	 * @return long
	 * 
	 */
	public static long getDifferTime(String sYmd, String sHms, String eYmd, String eHms) {
		return getDifferTime(sYmd + sHms, eYmd + eHms);
	}
	
	
	/**
	 * 오늘 날짜에 입력된 일수를 더한 날짜
	 * 
	 * @param addDay 더할 일수
	 * @return string
	 * 
	 */
	public static String getAddDay(int addDay) {
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.add(Calendar.DATE, addDay);
		
		return (new SimpleDateFormat("yyyyMMdd")).format(cal.getTime());
	}
	
	
	/**
	 * 특정 날짜에 입력된 일수를 더한 날짜
	 * 
	 * @param sDate 날짜 (yyyyMMdd)
	 * @param addDay 더할 일수
	 * @return string
	 * 
	 */
	public static String getAddDay(String sDate, int addDay) {
		int y = Integer.parseInt(sDate.substring(0, 4));
		int m = Integer.parseInt(sDate.substring(4, 6));
		int d = Integer.parseInt(sDate.substring(6, 8));
		
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.set(y, m - 1, d);
		cal.add(Calendar.DATE, addDay);
		
		return (new SimpleDateFormat("yyyyMMdd")).format(cal.getTime());
	}
	
	
	/**
	 * 오늘 날짜에 입력된 일수를 더한 날짜
	 * 
	 * @param addMonth 더할 일수
	 * @return string
	 * 
	 */
	public static String getAddMonth(int addMonth) {
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.add(Calendar.DATE, addMonth);
		
		return (new SimpleDateFormat("yyyyMMdd")).format(cal.getTime());
	}
	
	
	/**
	 * 특정 날짜에 입력된 월수를 더한 날짜
	 * 
	 * @param sDate 날짜 (yyyyMMdd)
	 * @param addMonth 더할 월수
	 * @return string
	 * 
	 */
	public static String getAddMonth(String sDate, int addMonth) {
		int y = Integer.parseInt(sDate.substring(0, 4));
		int m = Integer.parseInt(sDate.substring(4, 6));
		int d = Integer.parseInt(sDate.substring(6, 8));
		
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.set(y, m - 1, d);
		cal.add(Calendar.DATE, addMonth);
		
		return (new SimpleDateFormat("yyyyMMdd")).format(cal.getTime());
	}
	
	
	/**
	 * 현재 날짜가 올해의 몇째주에 해당하는지 계산
	 * 
	 * @return int
	 * 
	 */
	public static int getWeekOfYear() {
		return Calendar.getInstance(Locale.getDefault()).get(Calendar.WEEK_OF_YEAR);
	}
	
	
	/**
	 * 특정 날짜가 해당 년의 몇째주에 해당하는지 계산
	 * 
	 * @param sDate 날짜 (yyyyMMdd)
	 * @return int
	 * 
	 */
	public static int getWeekOfYear(String sDate) {
		int y = Integer.parseInt(sDate.substring(0, 4));
		int m = Integer.parseInt(sDate.substring(4, 6));
		int d = Integer.parseInt(sDate.substring(6, 8));
		
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.set(y, m - 1, d);
		
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	
	
	/**
	 * 현재 날짜가 현재 월의 몇째주에 해당하는지 계산
	 * 
	 * @return int
	 * 
	 */
	public static int getWeekOfMonth() {
		return Calendar.getInstance(Locale.getDefault()).get(Calendar.WEEK_OF_MONTH);
	}
	
	
	/**
	 * 특정 날짜가 해당 월의 몇째주에 해당하는지 계산
	 * 
	 * @param sDate 날짜 (yyyyMMdd)
	 * @return int
	 * 
	 */
	public static int getWeekOfMonth(String sDate) {
		int y = Integer.parseInt(sDate.substring(0, 4));
		int m = Integer.parseInt(sDate.substring(4, 6));
		int d = Integer.parseInt(sDate.substring(6, 8));
		
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.set(y, m - 1, d);
		
		return cal.get(Calendar.WEEK_OF_MONTH);
	}
	
	
	/**
	 * 오늘 요일
	 * 
	 * @return int
	 * 
	 */
	public static int getDayOfWeek() {
		return Calendar.getInstance(Locale.getDefault()).get(Calendar.DAY_OF_WEEK);
	}
	
	
	/**
	 * 특정 날짜의 요일
	 * 
	 * @param sDate 날짜 (yyyyMMdd)
	 * @return int
	 * 
	 */
	public static int getDayOfWeek(String sDate) {
		int y = Integer.parseInt(sDate.substring(0, 4));
		int m = Integer.parseInt(sDate.substring(4, 6));
		int d = Integer.parseInt(sDate.substring(6, 8));
		
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.set(y, m - 1, d);
		
		return cal.get(Calendar.DAY_OF_WEEK); 
	}
	
	
	/**
	 * 특정 달의 일요일
	 * 
	 * @param sDate 날짜 (yyyyMM)
	 * @return int
	 * 
	 */
	public static List getDayOfWeekByMonth(String sDate) {
		int yyyy = Integer.parseInt(sDate.substring(0, 4));
		int mm = Integer.parseInt(sDate.substring(4, 6));
		List sunday = new ArrayList();
		Calendar cal = Calendar.getInstance();
	  
		cal.set(yyyy, mm-1, 1);
		int maxDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	
		for(int i=1; i<maxDate+1; i++){
			cal.clear();
	   
			cal.set(yyyy, mm-1, i);
			
			//System.out.println(yyyy+"-"+mm+"-"+i+"_"+cal.get(cal.DAY_OF_WEEK));
			
			switch(cal.get(cal.DAY_OF_WEEK)){
			case java.util.Calendar.SUNDAY:
				sunday.add(i); 
			}
	   
			cal.clear();
		}
		
		return sunday;
	}
	
}