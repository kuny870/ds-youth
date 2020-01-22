package org.ds.dsyouth.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.ds.dsyouth.model.Attendance;
import org.ds.dsyouth.model.Depart;
import org.ds.dsyouth.model.Group;
import org.ds.dsyouth.model.Team;
import org.ds.dsyouth.search.AttendanceSearch;
import org.ds.dsyouth.utils.DateHelper;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class GenericExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<List<Attendance>> attendanceExcel = (List<List<Attendance>>) model.get("attendanceExcel");
        List<Depart> departList = (List<Depart>) model.get("departList");
        List<Team> teamList = (List<Team>) model.get("teamList");
        AttendanceSearch attendanceSearch = (AttendanceSearch) model.get("attendanceSearch");
		
        String excelName = attendanceSearch.getYear() + "_" + attendanceSearch.getSeason() + "_출석부";
        
        response.setHeader("Content-Type", "application/vnd.ms-excel; charset=EUC-KR");
        response.setHeader("Content-Disposition", "attachment; filename=" + new String(excelName.getBytes("utf-8"),"8859_1") + ".xls");
        response.setHeader("Content-Description", "JSP Generated Data");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1");
        
        for(int i=0; i<attendanceExcel.size(); i++) {
        	
        	HSSFSheet sheet = workbook.createSheet(attendanceExcel.get(i).get(0).getTeam().gettShortName());
        	
        	// TODO 셀 선
        	// TODO 배경색
        	
        	// Menu 세로 셀병합
        	sheet.addMergedRegion(new CellRangeAddress(1,2,0,0));
        	sheet.addMergedRegion(new CellRangeAddress(1,2,1,1));
        	sheet.addMergedRegion(new CellRangeAddress(1,2,2,2));
        	
        	// 상단 메뉴명 생성 row0
        	HSSFRow menuRow0 = sheet.createRow(0);
        	menuRow0.setHeight((short)530);
        	
        	// 제목 cell0
        	HSSFCell cell0_0 = menuRow0.createCell(0);
        	cell0_0.setCellValue(new HSSFRichTextString(attendanceExcel.get(i).get(0).getTeam().gettFullName()));
        	HSSFCell cell0_3 = menuRow0.createCell(3);
        	cell0_3.setCellValue(new HSSFRichTextString(attendanceExcel.get(i).get(0).getTeam().gettTheme()));
        	
        	// 상단 메뉴명 생성 row1 + row2
        	HSSFRow menuRow1 = sheet.createRow(1);
        	HSSFRow menuRow2 = sheet.createRow(2);
        	menuRow1.setHeight((short)256);
        	menuRow2.setHeight((short)256);
        	
        	// 제목 cell1
        	HSSFCell cell1_0 = menuRow1.createCell(0);
        	HSSFCell cell1_1 = menuRow1.createCell(1);
        	HSSFCell cell1_2 = menuRow1.createCell(2);
        	
        	cell1_0.setCellValue(new HSSFRichTextString("No."));
        	cell1_1.setCellValue(new HSSFRichTextString("순"));
        	cell1_2.setCellValue(new HSSFRichTextString("이름(동기)"));
        	
        	int fir_month = 0;
    		int sec_month = 0;
    		int thir_month = 0;
    		int four_month = 0;
    		int fif_month =  0;
    		int six_month =  0;
    		
        	// 상반기 달, 주일 날짜 및 셀 셋팅
        	if("상반기".equals(attendanceSearch.getSeason())) {
        		List s1 = DateHelper.getDayOfWeekByMonth(attendanceSearch.getYear() + "01");
        		List s2 = DateHelper.getDayOfWeekByMonth(attendanceSearch.getYear() + "02");
        		List s3 = DateHelper.getDayOfWeekByMonth(attendanceSearch.getYear() + "03");
        		List s4 = DateHelper.getDayOfWeekByMonth(attendanceSearch.getYear() + "04");
        		List s5 = DateHelper.getDayOfWeekByMonth(attendanceSearch.getYear() + "05");
        		List s6 = DateHelper.getDayOfWeekByMonth(attendanceSearch.getYear() + "06");
        		
        		fir_month = 3+s1.size();
        		sec_month = 3+s1.size()+s2.size();
        		thir_month = 3+s1.size()+s2.size()+s3.size();
        		four_month = 3+s1.size()+s2.size()+s3.size()+s4.size();
        		fif_month =  3+s1.size()+s2.size()+s3.size()+s4.size()+s5.size();
        		six_month =  3+s1.size()+s2.size()+s3.size()+s4.size()+s5.size()+s6.size();
        		
        		// Menu 가로 셀병합
            	sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));
            	sheet.addMergedRegion(new CellRangeAddress(0,0,3,six_month-1));
            	
            	sheet.addMergedRegion(new CellRangeAddress(1,1,3,fir_month-1));
            	sheet.addMergedRegion(new CellRangeAddress(1,1,fir_month,sec_month-1));
            	sheet.addMergedRegion(new CellRangeAddress(1,1,sec_month,thir_month-1));
            	sheet.addMergedRegion(new CellRangeAddress(1,1,thir_month,four_month-1));
            	sheet.addMergedRegion(new CellRangeAddress(1,1,four_month,fif_month-1));
            	sheet.addMergedRegion(new CellRangeAddress(1,1,fif_month,six_month-1));
            	
        		HSSFCell cell1_3 = menuRow1.createCell(3);
        		HSSFCell cell1_s1 = menuRow1.createCell(fir_month);
        		HSSFCell cell1_s2 = menuRow1.createCell(sec_month);
        		HSSFCell cell1_s3 = menuRow1.createCell(thir_month);
        		HSSFCell cell1_s4 = menuRow1.createCell(four_month);
        		HSSFCell cell1_s5 = menuRow1.createCell(fif_month);
        		
        		cell1_3.setCellValue(new HSSFRichTextString("1월"));
        		cell1_s1.setCellValue(new HSSFRichTextString("2월"));
        		cell1_s2.setCellValue(new HSSFRichTextString("3월"));
        		cell1_s3.setCellValue(new HSSFRichTextString("4월"));
        		cell1_s4.setCellValue(new HSSFRichTextString("5월"));
        		cell1_s5.setCellValue(new HSSFRichTextString("6월"));
        		
        		// 1월
        		for(int j=0; j<s1.size(); j++) {
        			HSSFCell cell_first_month = menuRow2.createCell(3 + j);
        			cell_first_month.setCellValue(new HSSFRichTextString(s1.get(j).toString()));
        		}
        		// 2월
        		for(int j=0; j<s2.size(); j++) {
        			HSSFCell cell_second_month = menuRow2.createCell(fir_month + j);
        			cell_second_month.setCellValue(new HSSFRichTextString(s2.get(j).toString()));
        		}
        		// 3월
        		for(int j=0; j<s3.size(); j++) {
        			HSSFCell cell_third_month = menuRow2.createCell(sec_month + j);
        			cell_third_month.setCellValue(new HSSFRichTextString(s3.get(j).toString()));
        		}
        		// 4월
        		for(int j=0; j<s4.size(); j++) {
        			HSSFCell cell_fourth_month = menuRow2.createCell(thir_month + j);
        			cell_fourth_month.setCellValue(new HSSFRichTextString(s4.get(j).toString()));
        		}
        		// 5월
        		for(int j=0; j<s5.size(); j++) {
        			HSSFCell cell_fifth_month = menuRow2.createCell(four_month + j);
        			cell_fifth_month.setCellValue(new HSSFRichTextString(s5.get(j).toString()));
        		}
        		// 6월
        		for(int j=0; j<s6.size(); j++) {
        			HSSFCell cell_six_month = menuRow2.createCell(fif_month + j);
        			cell_six_month.setCellValue(new HSSFRichTextString(s6.get(j).toString()));
        		}
        		
    		// 하반기 달, 주일 날짜 및 셀 셋팅
        	}else {
        		
        		List s1 = DateHelper.getDayOfWeekByMonth(attendanceSearch.getYear() + "07");
        		List s2 = DateHelper.getDayOfWeekByMonth(attendanceSearch.getYear() + "08");
        		List s3 = DateHelper.getDayOfWeekByMonth(attendanceSearch.getYear() + "09");
        		List s4 = DateHelper.getDayOfWeekByMonth(attendanceSearch.getYear() + "10");
        		List s5 = DateHelper.getDayOfWeekByMonth(attendanceSearch.getYear() + "11");
        		List s6 = DateHelper.getDayOfWeekByMonth(attendanceSearch.getYear() + "12");
        		
        		fir_month = 3+s1.size();
        		sec_month = 3+s1.size()+s2.size();
        		thir_month = 3+s1.size()+s2.size()+s3.size();
        		four_month = 3+s1.size()+s2.size()+s3.size()+s4.size();
        		fif_month =  3+s1.size()+s2.size()+s3.size()+s4.size()+s5.size();
        		six_month =  3+s1.size()+s2.size()+s3.size()+s4.size()+s5.size()+s6.size();
        		
        		// Menu 가로 셀병합
            	sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));
            	sheet.addMergedRegion(new CellRangeAddress(0,0,3,six_month-1));
            	
            	sheet.addMergedRegion(new CellRangeAddress(1,1,3,fir_month-1));
            	sheet.addMergedRegion(new CellRangeAddress(1,1,fir_month,sec_month-1));
            	sheet.addMergedRegion(new CellRangeAddress(1,1,sec_month,thir_month-1));
            	sheet.addMergedRegion(new CellRangeAddress(1,1,thir_month,four_month-1));
            	sheet.addMergedRegion(new CellRangeAddress(1,1,four_month,fif_month-1));
            	sheet.addMergedRegion(new CellRangeAddress(1,1,fif_month,six_month-1));
        		
        		HSSFCell cell1_3 = menuRow1.createCell(3);
        		HSSFCell cell1_s1 = menuRow1.createCell(fir_month);
        		HSSFCell cell1_s2 = menuRow1.createCell(sec_month);
        		HSSFCell cell1_s3 = menuRow1.createCell(thir_month);
        		HSSFCell cell1_s4 = menuRow1.createCell(four_month);
        		HSSFCell cell1_s5 = menuRow1.createCell(fif_month);
        		
        		cell1_3.setCellValue(new HSSFRichTextString("7월"));
        		cell1_s1.setCellValue(new HSSFRichTextString("8월"));
        		cell1_s2.setCellValue(new HSSFRichTextString("9월"));
        		cell1_s3.setCellValue(new HSSFRichTextString("10월"));
        		cell1_s4.setCellValue(new HSSFRichTextString("11월"));
        		cell1_s5.setCellValue(new HSSFRichTextString("12월"));
        		
        		// 7월
        		for(int j=0; j<s1.size(); j++) {
        			HSSFCell cell_first_month = menuRow2.createCell(3 + j);
        			cell_first_month.setCellValue(new HSSFRichTextString(s1.get(j).toString()));
        		}
        		// 8월
        		for(int j=0; j<s2.size(); j++) {
        			HSSFCell cell_second_month = menuRow2.createCell(fir_month + j);
        			cell_second_month.setCellValue(new HSSFRichTextString(s2.get(j).toString()));
        		}
        		// 9월
        		for(int j=0; j<s3.size(); j++) {
        			HSSFCell cell_third_month = menuRow2.createCell(sec_month + j);
        			cell_third_month.setCellValue(new HSSFRichTextString(s3.get(j).toString()));
        		}
        		// 10월
        		for(int j=0; j<s4.size(); j++) {
        			HSSFCell cell_fourth_month = menuRow2.createCell(thir_month + j);
        			cell_fourth_month.setCellValue(new HSSFRichTextString(s4.get(j).toString()));
        		}
        		// 11월
        		for(int j=0; j<s5.size(); j++) {
        			HSSFCell cell_fifth_month = menuRow2.createCell(four_month + j);
        			cell_fifth_month.setCellValue(new HSSFRichTextString(s5.get(j).toString()));
        		}
        		// 12월
        		for(int j=0; j<s6.size(); j++) {
        			HSSFCell cell_six_month = menuRow2.createCell(fif_month + j);
        			cell_six_month.setCellValue(new HSSFRichTextString(s6.get(j).toString()));
        		}
        	}
        	
        	int cnt = 2;
        	Integer idx = 1;
        	
            // 내용 셋팅
            for(int x = 0; x < attendanceExcel.get(i).size(); x++) {
                  // 메뉴 ROW가 있기때문에 +3을 해준다.
                  HSSFRow row = sheet.createRow(x + 3);
                  row.setHeight((short)380);
                  HSSFCell cell2_0 = row.createCell(0);
                  HSSFCell cell2_1 = row.createCell(1);
                  HSSFCell cell2_2 = row.createCell(2);
                  if(attendanceExcel.get(i).get(x).getTeam().getId() == 4 || attendanceExcel.get(i).get(x).getTeam().getId() == 8) {
                	  sheet.addMergedRegion(new CellRangeAddress(x+cnt+1,x+cnt+2,0,0));
                	  cnt++;
                	  if(x % 2 == 0) {
                		  cell2_0.setCellValue(new HSSFRichTextString(idx.toString()));
                		  idx++;
                	  }else {
                		  
                	  }
                  }else {
                	  cell2_0.setCellValue(new HSSFRichTextString(idx.toString()));
                	  idx++;
                  }
                  Group group = attendanceExcel.get(i).get(x).getGroup();
                  if(group != null) {
                	  cell2_1.setCellValue(new HSSFRichTextString(attendanceExcel.get(i).get(x).getGroup().getgName()));
                  }
                  
                  // 동기
               	  String samePeriodStr = "";
               	  Attendance att = attendanceExcel.get(i).get(x);
               	  int yearTmp = Integer.parseInt(attendanceSearch.getYear()) - att.getSamePeriod().getBirthYear();
               	  
               	  if(att.getSamePeriod() != null) {
               		  if(yearTmp == 19) {
               			  samePeriodStr = "(1)";
               		  }else if(yearTmp == 20) {
               			  samePeriodStr = "(2)";
               		  }else if(yearTmp == 21) {
               			  samePeriodStr = "(3)";
               		  }else if(yearTmp == 22) {
               			  samePeriodStr = "(4)";
               		  }else if(yearTmp == 23) {
               			  samePeriodStr = "(5)";
               		  }else if(yearTmp == 24) {
               			  samePeriodStr = "(6)";
               		  }else if(yearTmp == 25) {
               			  samePeriodStr = "(7)";
               		  }else if(yearTmp == 26) {
               			  samePeriodStr = "(8)";
               		  }else {
               			  samePeriodStr = "(" + att.getSamePeriod().getBirthYear().toString().substring(2, 4) + ")";
               		  }
               	  }
               	  
                  if(attendanceExcel.get(i).get(x).getTeam().getId() == 4 || attendanceExcel.get(i).get(x).getTeam().getId() == 8) {
                	 if(x % 2 == 0) {
                		 cell2_2.setCellValue(new HSSFRichTextString(attendanceExcel.get(i).get(x).getMember().getName() + samePeriodStr));
                	 }else {
                		 cell2_2.setCellValue(new HSSFRichTextString("2부"));
                	 }
                  }else {
                	  
                	  cell2_2.setCellValue(new HSSFRichTextString(attendanceExcel.get(i).get(x).getMember().getName() + samePeriodStr));
                	  
                  }
    		}
            
            // column 크기 조정
//        	sheet.autoSizeColumn(0); // 자동조정
        	sheet.setColumnWidth(0, 1000 );
        	sheet.setColumnWidth(1, 4000 );
        	sheet.setColumnWidth(2, 3000 );
        	for(int c = 3; c < six_month; c++) {
        		sheet.setColumnWidth(c, 1200 );
        	}
        	
        }
        
     		
	}

}