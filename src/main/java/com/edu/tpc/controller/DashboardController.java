/*
 *  Date: 5/3/12
 * @author: Jaikishan
 *
*/

package com.edu.tpc.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.tpc.common.ERExcelView;
import com.edu.tpc.common.ERPdfView;
import com.edu.tpc.domain.CRResult;
import com.edu.tpc.domain.ConsolidatedReportCriteria;
import com.edu.tpc.domain.EligibilityReport;
import com.edu.tpc.domain.PECriteria;
import com.edu.tpc.domain.StatisticsCriteria;
import com.edu.tpc.domain.StatsResult;
import com.edu.tpc.domain.StreamWiseReportCriteria;
import com.edu.tpc.service.CoursesService;
import com.edu.tpc.service.DashboardService;
import com.edu.tpc.service.PlacementService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 
import org.springframework.beans.support.PagedListHolder; 
import org.springframework.web.servlet.View;

@Controller
public class DashboardController extends BaseController {
    private Map<Integer,String> streams;

    private DashboardService dashBoardService ;
    private PlacementService placementService;
    private CoursesService coursesService;
    private I18NMessageUtil i18NMessageUtil;
    private final Log logger = LogFactory.getLog(getClass());

    private PagedListHolder erpagedListHolder;

    public DashboardService getDashBoardService() {
        return dashBoardService;
    }

    @Autowired
    public void setDashBoardService(DashboardService dashBoardService) {
        this.dashBoardService = dashBoardService;
    }

    public PlacementService getPlacementService() {
        return placementService;
    }
    @Autowired
    public void setPlacementService(PlacementService placementService) {
        this.placementService = placementService;
    }

    public CoursesService getCoursesService() {
        return coursesService;
    }

    @Autowired
    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    public I18NMessageUtil getI18NMessageUtil() {
        return i18NMessageUtil;
    }

    @Autowired
    public void setI18NMessageUtil(I18NMessageUtil i18NMessageUtil) {
        this.i18NMessageUtil = i18NMessageUtil;
    }
    
    private void initialize(ModelMap model,HttpServletRequest request) {
        logger.info("Initializing dashboard report form..."); 
        HttpSession session=request.getSession();
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        model.addAttribute("secondaryCourses",coursesService.findSecondaryCourses());
        model.addAttribute("secondaryStreams",coursesService.findSecondaryStreams());
        model.addAttribute("ugCourses",coursesService.findUGCourses(orgId));
        model.addAttribute("ugStreams",coursesService.findUGStreams(orgId));
        model.addAttribute("pgCourses",coursesService.findPGCourses(orgId));
        model.addAttribute("pgStreams",coursesService.findPGStreams(orgId));   
    } 

    @RequestMapping(value="streamwisereport.html", method=RequestMethod.GET)
    public String showStreamWiseReportForm(ModelMap model, HttpServletRequest request,
                                                HttpServletResponse response) {
        checkSession(request,response);
        initialize(model,request);
        model.addAttribute("swReportCriteria", new StreamWiseReportCriteria());
        return "dashboard/StreamWiseReport";
    }
    @RequestMapping(value="consolidatedreport.html", method=RequestMethod.GET)
    public String showConsolidatedReportForm(ModelMap model, HttpServletRequest request,HttpServletResponse response){
        checkSession(request,response);
        model.addAttribute("crCriteria", new ConsolidatedReportCriteria());
        return "dashboard/ConsolidatedReport";
    }
    @RequestMapping(value="consolidatedreport.html", method=RequestMethod.POST)
    public String showConsolidatedReport(ModelMap model,ConsolidatedReportCriteria crCriteria,
                                         HttpServletRequest request,HttpServletResponse response){
        checkSession(request,response); 
        HttpSession session=request.getSession(); 
        return "dashboard/CRResult";
    } 

    @RequestMapping(value="eligibilityreport.html", method=RequestMethod.GET)
    public String showEligibilityReportForm(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        checkSession(request,response);
        initialize(model,request);
        model.addAttribute("erCriteria", new PECriteria());   
        return "dashboard/EligibilityReport"; 
    }

    @RequestMapping(value="eligibilityreport.html", method=RequestMethod.POST)
    public String showEligibilityReport(HttpServletRequest request,ModelMap model,
                                        PECriteria peCriteria,HttpServletResponse response) { 
        //List<EligibilityReport>eligibilityReport=new ArrayList<EligibilityReport>(); 
        return "dashboard/ERResults";
    }

    @RequestMapping(value="erexcelview.html", method=RequestMethod.POST)
    public View showEligibilityReportInExcel(HttpServletRequest request,ModelMap model,
                                        PECriteria peCriteria,HttpServletResponse response) { 
        List<EligibilityReport>eligibilityReport=new ArrayList<EligibilityReport>(); 
        return new ERExcelView(eligibilityReport);
    }
    
     @RequestMapping(value="erpdfview.html", method=RequestMethod.POST)
    public View showEligibilityReportInPdf(HttpServletRequest request,ModelMap model,
                                        PECriteria peCriteria,HttpServletResponse response) { 
        List<EligibilityReport>eligibilityReport=new ArrayList<EligibilityReport>(); 
        return new ERPdfView(eligibilityReport);
    }
     
     @RequestMapping(value="erchartview.html", method=RequestMethod.POST)
    public String showEligibilityReportInChart(HttpServletRequest request,ModelMap model,
                                        PECriteria peCriteria,HttpServletResponse response) { 
        List<EligibilityReport>eligibilityReport=new ArrayList<EligibilityReport>(); 
        return "dashboard/ERBarChart";
    }
     
    @RequestMapping(value="statistics.html", method=RequestMethod.GET)
    public String showStatisticsForm(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
        checkSession(request,response);
        model.addAttribute("statsCriteria", new StatisticsCriteria());
        return "dashboard/Statistics";
    }
    @RequestMapping(value="statistics.html", method=RequestMethod.POST)
    public String showStatisticsResults(ModelMap model, HttpServletRequest request,
                                        HttpServletResponse response,StatisticsCriteria sCriteria) { 
        checkSession(request,response);
        try { 
                List statResults = new ArrayList();
                StatsResult statResult = new StatsResult();
                List campusCompanies = placementService.
                                           getCampusCompanies(sCriteria.getPassingYear());
                for(Object campus:campusCompanies) {
                    statResult= new StatsResult();
                    statResult.setCompanyName((String)campus);
                    statResults.add(statResult);
                } 
                model.addAttribute("passingYear",sCriteria.getPassingYear());
                model.addAttribute("statResults",statResults);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return "dashboard/StatisticsResults";
    } 
}
