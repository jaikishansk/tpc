package com.edu.tpc.controller;

/**
 *
 * @author Jaikishan
 * Date modified: 20/7/12
*/

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import com.edu.tpc.domain.Candidate;
import com.edu.tpc.domain.DeleteCriteria;
import com.edu.tpc.domain.EditCandidateCriteria;
import com.edu.tpc.domain.Qualification;
import com.edu.tpc.domain.QualificationPK;
import com.edu.tpc.domain.UserType;
import com.edu.tpc.service.CandidateService;
import com.edu.tpc.service.CoursesService;
import com.edu.tpc.service.UserService;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.beans.propertyeditors.CustomDateEditor; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.*;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CandidateController extends BaseController { 
    
    private UserService userService;
    private CoursesService coursesService;
    private CandidateService candidateService ;
    
    private I18NMessageUtil i18NMessageUtil; 
    private final Log logger = LogFactory.getLog(getClass());

    public CandidateService getCandidateService() {
        return candidateService;
    }
    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }
    public CoursesService getCoursesService() {
        return coursesService;
    }
    @Autowired
    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }
    public UserService getUserService() {
        return userService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    } 
    @Override
    public I18NMessageUtil getI18NMessageUtil() {
        return i18NMessageUtil;
    } 
    @Autowired
    @Override
    public void setI18NMessageUtil(I18NMessageUtil i18NMessageUtil) {
        this.i18NMessageUtil = i18NMessageUtil;
    } 

    @InitBinder
    public void initBinder(WebDataBinder binder) { 
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
    } 
    
    private void initialize(ModelMap model,HttpServletRequest request) { 
        logger.info("Initializing register candidate form with course and stream info...");
        HttpSession session=request.getSession();
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        model.addAttribute("candidate",new Candidate()); 
        model.addAttribute("secondaryCourses",coursesService.findSecondaryCourses());
        model.addAttribute("secondaryStreams",coursesService.findSecondaryStreams());
        model.addAttribute("ugCourses",coursesService.findUGCourses(orgId));
        model.addAttribute("ugStreams",coursesService.findUGStreams(orgId));
        model.addAttribute("pgCourses",coursesService.findPGCourses(orgId));
        model.addAttribute("pgStreams",coursesService.findPGStreams(orgId));   
    } 
    
    private boolean checkIfCandidateExists(HttpServletRequest request, String candidateId) {  
        HttpSession session=request.getSession();
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        logger.info("Checking if candidate with id '"+candidateId+"' and orgId="+ orgId+" exists..."); 
        return candidateService.ifExistsCandidate(candidateId,orgId);
    }
    
    private boolean isExamIdsUnique(Candidate candidate) {
        logger.info("Checking examid uniqueness in register candidate....");
        String[] examIds=new String[5];
        int count=0;
        List<Qualification> quals=candidate.getQuals();
        for(Qualification qual:quals) {  
            examIds[count++]=qual.getPrimaryKey().getQualRollNo();
        }
        for(int i=0; i<count; i++) {          
                String examId=examIds[i];
                for(int j=i+1;j<count;j++){
                    if(examId.equals(examIds[j])){
                        return false;
                    }
                }
        }
        return true;
    } 
    
    private String regCandidateAlreadyExistsPage(ModelMap model,String candidateId) {
         model.addAttribute("candidateError",
                     i18NMessageUtil.getMessage("registercandidate.userexists",new Object[]{candidateId})); 
        return "candidate/CandidateResult";
    }  
    
    private String regCandidateDoesNotExistsPage(ModelMap model, String candidateId) { 
            model.addAttribute("candidateError", i18NMessageUtil.getMessage("registercandidate.userdoesnotexists",
                                                                         new Object[]{candidateId}));
            return "candidate/CandidateResult";
    }
    
   private String regCandidateErrorPage(ModelMap model, String candidateId) {
            model.addAttribute("candidateStatus",
                     i18NMessageUtil.getMessage("registercandidate.candidatecreationrerror", new Object[]{ candidateId})); 
             return "candidate/CandidateResult";
   }
    
    private String regCandidateSuccessPage(ModelMap model, String candidateId){
             model.addAttribute("candidateStatus",
                     i18NMessageUtil.getMessage("registercandidate.userregistered", new Object[]{ candidateId})); 
             return "candidate/CandidateResult";
    }
    
    private String examIdsNotUniquePage(ModelMap model, String candidateId){
            model.addAttribute("candidateError",
                     i18NMessageUtil.getMessage("registercandidate.uniqueexamids", new Object[]{candidateId}));
            return "candidate/CandidateResult";
    } 
    
    private String editCandidateSuccesPage(ModelMap model, String candidateId){
        model.addAttribute("candidateStatus",
                     i18NMessageUtil.getMessage("registercandidate.useredited", new Object[]{candidateId})); 
        logger.info("Successfully edited candidate with id='"+candidateId+"'...");
        return "candidate/CandidateResult";
    }
    
     private String deleteCandidateSuccessPage(ModelMap model, String candidateId){
        model.addAttribute("candidateStatus",
                         i18NMessageUtil.getMessage("deletecandidate.result.delete",new Object[]{candidateId}));
        logger.info("Successfully deleted candidate with id='"+candidateId+"'...");
        return "candidate/CandidateResult";
    }  
    
    private String deleteCandidateErrorPage(ModelMap model, String candidateId){
        model.addAttribute("candidateError",
                             i18NMessageUtil.getMessage("deletecandidate.result.error.delete", new Object[]{candidateId}));
        logger.info("Candidate with id='"+ candidateId +"' does not exists...");
        return "candidate/CandidateResult";
    }
    
    private String regCandidatePageSetup(HttpSession session,int candidateType, String editMode){
        session.setAttribute("edit",editMode);
        switch(candidateType){
                case UserType.UGSTUDENT: 
                                    session.setAttribute("ugMaxSemDisplay", 6);
                                    session.setAttribute("candidateType", 2);
                                    break;
                case UserType.PGSTUDENT: 
                                    session.setAttribute("ugMaxSemDisplay", 8);
                                    session.setAttribute("pgMaxSemDisplay", 2);
                                    session.setAttribute("candidateType", 3);
                                    break;
        }  
        return "candidate/RegisterCandidate";
    } 
    
    @RequestMapping(value="dynacandexistsinfo.html", method = RequestMethod.POST)
    public @ResponseBody String ifCandidateExists(@RequestParam String candidateId,
                                                 HttpServletRequest request,HttpServletResponse response) { 
        checkSession(request,response); 
        return Boolean.toString(checkIfCandidateExists(request, candidateId));
    }
    
    private String commonUGPGNewCandidateRegFormOp(ModelMap model, HttpServletRequest request,
                                                HttpServletResponse response, int candidateType) {
        checkSession(request,response);
        HttpSession session=request.getSession();
        String candidateId=(String)session.getAttribute("userId");
        // in case candidate calls register twice
        if(checkIfCandidateExists(request,candidateId)) {
            return regCandidateAlreadyExistsPage(model,candidateId);
        }    
        initialize(model,request);
        return regCandidatePageSetup(session,candidateType, "false");
    }
    
    @RequestMapping(value="registerugcandidate.html", method=RequestMethod.GET)
    public String showNewUGCandRegForm(ModelMap model, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception { 
        logger.info("Showing new UG register candidate form..."); 
        return commonUGPGNewCandidateRegFormOp(model,request,response,UserType.UGSTUDENT); 
    }
    
    @RequestMapping(value="registerpgcandidate.html", method=RequestMethod.GET)
    public String showNewPGCandRegForm(ModelMap model, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception { 
        logger.info("Showing new PG register candidate form..."); 
        return commonUGPGNewCandidateRegFormOp(model,request,response,UserType.PGSTUDENT); 
    }
    
    
    /************************************************************************************************************************/
        // Following methods are for admin
    /************************************************************************************************************************/
     
    @RequestMapping(value="registercandidate.html", method=RequestMethod.GET)
    public void handleAdminRegCandidateGet(HttpServletRequest request,HttpServletResponse response){ 
        redirectToLoginPage(request,response);
    } 
    
    private String saveCandidateInfo(Candidate candidate,HttpServletRequest request, ModelMap model) { 
            logger.info("Registering candidate with id '"+candidate.getCandidateId()+"'...");
            HttpSession session=request.getSession();
            String userId=(String)session.getAttribute("userId");
            int numOfQuals=candidate.getQuals().size();
            String candidateId=candidate.getQuals().get(numOfQuals-1).getPrimaryKey().getQualRollNo();
            int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
            int candUID=candidateService.getNextCandidateUID(candidateId, orgId);
            candidate.setCandidateUID(candUID); 
            session.setAttribute("candidateId", candidateId);
            logger.info("CandidateId before registration="+session.getAttribute("candidateId"));
            candidate.setCandidateId(candidateId);
            candidate.setCandidateType(((Integer)session.getAttribute("candidateType")).intValue()); 
            candidate.setOrgId(orgId);
            candidate.setCreatedBy(userId);
            candidate.setModifiedBy(userId);
            logger.info("Calling save candidate method for candidate id' "+candidateId+"'...");
            if(candidateService.registerCandidate(candidate)!=0) {
                logger.info("Create candidate with id  "+candidateId+"' failed...");
                return regCandidateErrorPage(model,candidateId);
            }
            logger.info("Create candidate with id  "+candidateId+"' succeded...");
            return  regCandidateSuccessPage(model,candidateId);
    }
    
    private String createCandidate(Candidate candidate, ModelMap model, HttpServletRequest request) {  
        String candidateId=candidate.getCandidateId();
        logger.info("Validating roll no uniqueness in register candidate....");
        if(!isExamIdsUnique(candidate)) {   
            return examIdsNotUniquePage(model,candidateId); 
        } 
        else if(checkIfCandidateExists(request,candidateId)) {
            return regCandidateAlreadyExistsPage(model,candidateId);
        }  
        else {
            return saveCandidateInfo(candidate,request,model); 
        }
    }   
    
    @RequestMapping(value="registercandidate.html", method=RequestMethod.POST)
    public String createAdminRegCandidate(@Valid Candidate candidate,ModelMap model,
                          HttpServletRequest request,HttpServletResponse response) {
        checkSession(request,response);
        String candidateId=candidate.getQuals().get(candidate.getQuals().size()-1).getPrimaryKey().getQualRollNo(); 
        logger.info("Creating candidate with candidateId '"+candidateId+"' ...");
        candidate.setCandidateId(candidateId);
        return createCandidate(candidate,model,request); 
    }
    
    @RequestMapping(value="registercandidateandnew.html", method=RequestMethod.GET)
    public void handleRCandNewGet(HttpServletRequest request,HttpServletResponse response) {
        redirectToLoginPage(request,response);
    }
    
    @RequestMapping(value="registercandidateandnew.html", method=RequestMethod.POST)
    public String createAdminCandidateAndNew(@Valid Candidate candidate,ModelMap model,
                                HttpServletRequest request,HttpServletResponse response) {
        checkSession(request,response);
        logger.info("Creating candidate with candidateId '"+candidate.getCandidateId()+"' ...");
        createCandidate(candidate,model,request);  
        // If error, show error page else new reg page
        if(model.get("candidateError")!=null){
            return "candidate/CandidateResult";
        }
        initialize(model,request);
        logger.info("Redirecting to new candidate reg. page...");
        return "candidate/RegisterCandidate"; 
    } 
    
    @RequestMapping(value="editcandidate.html", method=RequestMethod.GET)
    public String showAdminECandCriteriaForm(ModelMap model, 
                                             HttpServletRequest request,HttpServletResponse response) { 
        checkSession(request,response);
        model.addAttribute("editCandCriteria",new EditCandidateCriteria());
        logger.info("Redirecting to form to get candidate id to edit....");
        return "candidate/EditCandidateCriteria";
    } 
    private String setUpEditCandidatePage(ModelMap model,HttpServletRequest request, String candidateId) {
        initialize(model,request); 
        HttpSession session=request.getSession();
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        logger.info("Finding edit candidate info about candidate with id '"+ candidateId+ "'..."); 
        if(!checkIfCandidateExists(request, candidateId)) {
            return regCandidateDoesNotExistsPage(model,candidateId);  
        }  
        Candidate candidate=candidateService.findCandidateById(candidateId,orgId);
        model.addAttribute("candidate",candidate); 
        session.setAttribute("candidateId", candidate.getCandidateId());
        session.setAttribute("orgId", orgId);
        session.setAttribute("candidateType", candidate.getCandidateType());
        return regCandidatePageSetup(session,candidate.getCandidateType(), "true"); 
    }
    
    @RequestMapping(value="editcandidate.html", method=RequestMethod.POST)
    public String showPopulatedAdminEditCandidateForm(@Valid EditCandidateCriteria editCandCriteria,
                                              ModelMap model,HttpServletRequest request, HttpServletResponse response) { 
        checkSession(request,response); 
        String candidateId=editCandCriteria.getCandidateId(); 
        return  setUpEditCandidatePage(model, request, candidateId) ;  
    } 
    
    @RequestMapping(value="update.html", method=RequestMethod.GET)
    public String showPopulatedAdminUpdateCandidateForm(@Valid EditCandidateCriteria editCandCriteria,
                                              ModelMap model,HttpServletRequest request, HttpServletResponse response) { 
        checkSession(request,response); 
        String candidateId=request.getParameter("candidateId");
        return  setUpEditCandidatePage(model, request, candidateId) ;  
    }
    // This method is called if candidate wants to edit its data
    @RequestMapping(value="edit.html", method=RequestMethod.GET)
    public String showPopulatedECForm(ModelMap model, HttpServletRequest request, HttpServletResponse response) { 
        checkSession(request,response);
        HttpSession session=request.getSession(); 
        String userId=(String)session.getAttribute("userId"); 
        return  setUpEditCandidatePage(model, request, userId) ;  
    }  
    
    private String setUpSaveEditedCandidate(Candidate candidate, HttpSession session,ModelMap model) {
        candidate.setCandidateId((String)session.getAttribute("candidateId"));
        candidate.setCandidateType(((Integer)session.getAttribute("candidateType")).intValue());
        candidate.setOrgId(Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId")))); 
        candidate.setModifiedBy((String)session.getAttribute("userId"));
        logger.info("Saving edited info of candidate with id '"+candidate.getCandidateId()+"'...");
        candidateService.registerCandidate(candidate); 
        return editCandidateSuccesPage(model, candidate.getCandidateId());  
    }
    
    @RequestMapping(value="edit.html", method=RequestMethod.POST)
    public String saveEditedCandidate(@Valid Candidate candidate,ModelMap model,
                          HttpServletRequest request,HttpServletResponse response) { 
        checkSession(request,response); 
        HttpSession session=request.getSession();
        logger.info("Saving edited candidate info...");
        logger.info("#of quals while updating="+candidate.getQuals().size());
        if(!isExamIdsUnique(candidate)) {
            return examIdsNotUniquePage(model,candidate.getCandidateId());
        } 
        return setUpSaveEditedCandidate(candidate,session,model);
    }   
    
    @RequestMapping(value="deletecandidate.html", method=RequestMethod.GET)
    public String showDCForm(ModelMap model,HttpServletRequest request, HttpServletResponse response) { 
        checkSession(request,response);
        logger.info("Redirecting to delete candidate form...");
        model.addAttribute("deleteCriteria",new DeleteCriteria());
        return "candidate/DeleteCandidate";
    } 
    
    @RequestMapping(value="deletecandidate.html", method=RequestMethod.POST)
    public String deleteCandidate(ModelMap model,@Valid DeleteCriteria deleteCriteria, HttpServletRequest request,
                                   HttpServletResponse response) { 
        checkSession(request,response);
        HttpSession session=request.getSession();
        String candidateId=deleteCriteria.getCandidateId();
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        logger.info("Deleting candiate with id='"+candidateId+" and orgId="+orgId+"'...");
        if(candidateService.deleteCandidate(candidateId, orgId)==0){ 
           return deleteCandidateErrorPage(model,candidateId);
        }
        return deleteCandidateSuccessPage(model,candidateId);
    } 
    
    @RequestMapping(value="delete.html", method=RequestMethod.POST)
    public String deleteSearchCandidate(ModelMap model,@Valid DeleteCriteria deleteCriteria, HttpServletRequest request,
                                   HttpServletResponse response) { 
        checkSession(request,response);
        HttpSession session=request.getSession();
        String candidateId=deleteCriteria.getCandidateId();
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        logger.info("Deleting candiate with id='"+candidateId+" and orgId="+orgId+"'...");
        if(candidateService.deleteCandidate(candidateId, orgId)==0){ 
           return deleteCandidateErrorPage(model,candidateId);
        }
        return deleteCandidateSuccessPage(model,candidateId);
    } 
}