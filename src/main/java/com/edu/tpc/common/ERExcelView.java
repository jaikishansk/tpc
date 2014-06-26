package com.edu.tpc.common;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.edu.tpc.domain.EligibilityReport;

/**
 *
 * @author Jaikishan
 * 2/2/2013
 */

public class ERExcelView extends AbstractExcelView {
    
        List<EligibilityReport>eligibilityReport;
        
        public ERExcelView(){}
        public ERExcelView(List<EligibilityReport>eligibilityReport) {
            this.eligibilityReport=eligibilityReport;
        }
         
        @Override
        public void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, 
                                                                     HttpServletRequest request, HttpServletResponse response) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment; filename=Report.xls");
            
            HSSFSheet sheet = workbook.createSheet("Spring");
            sheet.setDefaultColumnWidth(12);
            // Write a text at A1.
            HSSFCell cell = getCell(sheet, 0, 0);
            setText(cell, "Spring POI test");

            // Write the current date at A2.
            HSSFCellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            cell = getCell(sheet, 1, 0);
            cell.setCellValue(new Date());
            cell.setCellStyle(dateStyle); 
            // Write a number at A3
            getCell(sheet, 2, 0).setCellValue(458); 
        } 
}
