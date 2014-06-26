package com.edu.tpc.common;

import java.util.List;
import java.util.Map; 

import com.lowagie.text.Table;
import com.lowagie.text.Document; 
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.edu.tpc.domain.EligibilityReport;

/**
 *
 * @author Jaikishan
 * 2/2/13
 */
public class ERPdfView extends AbstractPdfView { 
    
        List<EligibilityReport>eligibilityReport;
        
        public ERPdfView(){}
        public ERPdfView(List<EligibilityReport>eligibilityReport) {
            this.eligibilityReport=eligibilityReport;
        }
        @Override
        public void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                                                     HttpServletRequest request, HttpServletResponse response) {
            try{
                    response.setContentType("application/pdf");
                    response.setHeader("Content-disposition", "attachment; filename=Report.pdf");

                    Table table=new Table(7);
                    table.addCell("Candidate Id");
                    table.addCell("First Name");
                    table.addCell("Last Name");
                    table.addCell("Course");
                    table.addCell("Stream");
                    table.addCell("Email Id");
                    table.addCell("Cell Number");
                    document.add(table);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    
}
