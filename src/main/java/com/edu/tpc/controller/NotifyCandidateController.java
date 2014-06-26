package com.edu.tpc.controller;
/**
 *
 * @author Jaikishan
 * Date: 29/2/12
 */  

import java.util.*;

import javax.servlet.http.*;
import javax.validation.Valid;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.tpc.domain.NCCriteria;
import com.edu.tpc.service.CandidateService;
import com.edu.tpc.service.CoursesService;
import com.edu.tpc.service.NotificationService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NotifyCandidateController extends BaseController { 
    private CoursesService coursesService; 
    private CandidateService candidateService;
    private NotificationService notificationService;
    
    private I18NMessageUtil i18NMessageUtil; 
    
    public I18NMessageUtil getI18NMessageUtil() {
        return i18NMessageUtil;
    }

    @Autowired
    public void setI18NMessageUtil(I18NMessageUtil i18NMessageUtil) {
        this.i18NMessageUtil = i18NMessageUtil;
    }

    public CoursesService getCoursesService() {
        return coursesService;
    }
    
    @Autowired
    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    public CandidateService getCandidateService() {
        return candidateService;
    }

    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    } 

    public NotificationService getNotificationService() {
        return notificationService;
    }

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }  
    
    @RequestMapping(value="notifycandidate.html", method=RequestMethod.GET)
    public String showNotifyCandidateForm(ModelMap model,HttpServletRequest request,
                                                         HttpServletResponse response) {
        checkSession(request,response);
        HttpSession session=request.getSession(); 
        int orgId=Integer.valueOf((Integer)session.getAttribute("orgId"));   
        model.addAttribute("courses", mergeMaps(coursesService.findUGCourses(orgId),
                                                                            coursesService.findPGCourses(orgId)));  
        model.addAttribute("streams",mergeMaps(coursesService.findUGStreams(orgId),
                                                                            coursesService.findPGStreams(orgId))); 
        model.addAttribute("ncCriteria", new NCCriteria()); 
        return "candidate/NotifyCandidate";
    } 
    
    private NCCriteria mapNCCriteria(HttpServletRequest request){
            NCCriteria ncCriteria=new NCCriteria(); 
            ncCriteria.setCourse(Integer.valueOf(request.getParameter("courseId")));
            ncCriteria.setStream(Integer.valueOf(request.getParameter("streamId")));
            String passingYear=request.getParameter("passingYear");
            if(passingYear!=null && !passingYear.isEmpty()){
                    ncCriteria.setPassingYear(Integer.valueOf(passingYear));
            }
            String startPercent=request.getParameter("startPercent");
            if(startPercent!=null && !startPercent.isEmpty()){
                     ncCriteria.setStartPercent(Double.valueOf(startPercent));
            }
            String endPercent=request.getParameter("endPercent");
            if(endPercent!=null && !endPercent.isEmpty()){
                     ncCriteria.setEndPercent(Double.valueOf(endPercent));
            } 
            String livekt=request.getParameter("livekt");
             if(livekt!=null && !livekt.isEmpty()){
                     ncCriteria.setLiveKT(Integer.valueOf(livekt));
            } 
            String numPlacements=request.getParameter("numPlacements");
             if(numPlacements!=null && !numPlacements.isEmpty()){
                     ncCriteria.setNumPlacements(Integer.valueOf(numPlacements));
            }  
            return ncCriteria;
    }
    
    @RequestMapping(value="potentialNCMatch.html", method=RequestMethod.POST) 
    public @ResponseBody String showNCPotentialMatch(HttpServletRequest request, HttpServletResponse response) {
        try { 
                checkSession(request,response);  
                HttpSession session=request.getSession(); 
                int orgId=Integer.valueOf((Integer)session.getAttribute("orgId"));   
                NCCriteria ncCriteria=mapNCCriteria(request);
                int result=notificationService.findNCPotentialMatch(ncCriteria,orgId);
                return Integer.toString(result); 
          }catch(Exception e) { 
              e.printStackTrace();
          } 
        return null;
    }
    
    private String notifyCandidateSuccessMessage(ModelMap model, String candidateId) {
        model.addAttribute("nStatus",i18NMessageUtil.getMessage("notifycandidate.single.result.success",
                                                      new Object[]{candidateId}));
        return "candidate/NotificationStatus";
    }
    
    private String notifyCandidateFailureMessage(ModelMap model, String candidateId) {
        model.addAttribute("nError",i18NMessageUtil.getMessage("notifycandidate.single.result.error",
                                                    new Object[]{candidateId}));
        return "candidate/NotificationStatus";
    }
    
    private String notifyClassSuccessMessage(ModelMap model, NCCriteria ncCriteria) {
        model.addAttribute("nStatus",i18NMessageUtil.getMessage("notifycandidate.class.result.success"));
        return "candidate/NotificationStatus";
    }
    
    private String notifyClassFailureMessage(ModelMap model, NCCriteria ncCriteria) {
        model.addAttribute("nError",i18NMessageUtil.getMessage("notifycandidate.class.result.error")); 
        return "candidate/NotificationStatus";
    }
    
     @RequestMapping(value="notifycandidate.html", method=RequestMethod.POST)
     public String notifyCandidate(ModelMap model,HttpServletRequest request,
                                                       HttpServletResponse response,NCCriteria ncCriteria) {
        checkSession(request,response);
        HttpSession session=request.getSession(); 
        int orgId=Integer.valueOf((Integer)session.getAttribute("orgId"));   
        try {
                notificationService.notifyCandidates(orgId,ncCriteria); 
                if(ncCriteria.getNotificationScope()==1){
                    return  notifyCandidateSuccessMessage(model,ncCriteria.getCandidateId());
                }else{
                     return notifyClassSuccessMessage(model,ncCriteria);
                }
        }catch(Exception e) {
             if(ncCriteria.getNotificationScope()==1){
                return  notifyCandidateFailureMessage(model,ncCriteria.getCandidateId());
            }else{
                 return notifyClassFailureMessage(model,ncCriteria);
            }
        }
     }
}
