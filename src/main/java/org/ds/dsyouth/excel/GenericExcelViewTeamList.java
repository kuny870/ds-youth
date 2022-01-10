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
import org.ds.dsyouth.model.Member;
import org.ds.dsyouth.utils.DateHelper;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class GenericExcelViewTeamList extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<Member> memberExcel = (List<Member>) model.get("memberList");
		
        String excelName = "청년부 전체 명단";
        
        response.setHeader("Content-Type", "application/vnd.ms-excel; charset=EUC-KR");
        response.setHeader("Content-Disposition", "attachment; filename=" + new String(excelName.getBytes("utf-8"),"8859_1") + ".xls");
        response.setHeader("Content-Description", "JSP Generated Data");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1");
        
	        	
        HSSFSheet sheet = workbook.createSheet("청년부 전체 명단");
        
        // 내용 셋팅
        for(int x = 0; x < memberExcel.size(); x++) {
	          HSSFRow row = sheet.createRow(x);
	          row.setHeight((short)380);
	          HSSFCell cell2_0 = row.createCell(0);
	          HSSFCell cell2_1 = row.createCell(1);
	          HSSFCell cell2_2 = row.createCell(2);
	          HSSFCell cell2_3 = row.createCell(3);
	          HSSFCell cell2_4 = row.createCell(4);
	          HSSFCell cell2_5 = row.createCell(5);
	              
	          cell2_0.setCellValue(new HSSFRichTextString(memberExcel.get(x).getTeam().gettShortName()));
	          
	          if(memberExcel.get(x).getGroup() != null) {
	        	  cell2_1.setCellValue(new HSSFRichTextString(memberExcel.get(x).getGroup().getgName()));
	          }else {
	        	  cell2_1.setCellValue(new HSSFRichTextString(""));
	          }
	          
	          cell2_2.setCellValue(new HSSFRichTextString(memberExcel.get(x).getMemberState().getmState()));
	          
	          // 동기
	          String samePeriodStr = "";
	          
           	  if(memberExcel.get(x).getSamePeriod() != null) {
           		  
             	  int yearTmp = Integer.parseInt(DateHelper.getYear()) - memberExcel.get(x).getSamePeriod().getBirthYear();
             	  
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
           			  samePeriodStr = "(" + memberExcel.get(x).getSamePeriod().getBirthYear().toString().substring(2, 4) + ")";
           		  }
           	  }
           	  
	          cell2_3.setCellValue(new HSSFRichTextString(memberExcel.get(x).getName() + samePeriodStr));
	          cell2_4.setCellValue(new HSSFRichTextString(memberExcel.get(x).getHtel()));
	          cell2_5.setCellValue(new HSSFRichTextString(memberExcel.get(x).getDateOfBirth()));
		}
	            
        // column 크기 조정
    	sheet.setColumnWidth(0, 2000 );
    	sheet.setColumnWidth(1, 4000 );
    	sheet.setColumnWidth(2, 3000 );
    	sheet.setColumnWidth(3, 3000 );
    	sheet.setColumnWidth(4, 3600 );
    	sheet.setColumnWidth(5, 3200 );
        	
	}	// size check end
        	

}