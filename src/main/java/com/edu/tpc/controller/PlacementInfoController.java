package com.edu.tpc.controller;

/**
 *
 * @author Jaikishan
 * Start date: 15/10/11
 */

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.*;
import javax.validation.Valid;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView; 
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.WebDataBinder; 
import org.springframework.web.bind.annotation.InitBinder; 
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler; 

import com.edu.tpc.domain.Candidate;
import com.edu.tpc.domain.PlacementCriteria;
import com.edu.tpc.domain.PlacementInfo;
import com.edu.tpc.domain.PlacementInfoContainer;
import com.edu.tpc.domain.PlacementInfoPK;
import com.edu.tpc.domain.Qualification;
import com.edu.tpc.service.CandidateService;
import com.edu.tpc.service.CompanyService;
import com.edu.tpc.service.ConfigInfoService;
import com.edu.tpc.service.CoursesService;
import com.edu.tpc.service.PlacementService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Controller
public class PlacementInfoController extends BaseController {
    
    private ConfigInfoService configInfoService;
    private PlacementService placementService;
    private CoursesService coursesService;
    private CandidateService candidateService;
    private CompanyService companyService;

    private I18NMessageUtil i18NMessageUtil; 

    private final Log logger = LogFactory.getLog(getClass());

    public I18NMessageUtil getI18NMessageUtil() {
        return i18NMessageUtil;
    }

    @Autowired
    public void setI18NMessageUtil(I18NMessageUtil i18NMessageUtil) {
        this.i18NMessageUtil = i18NMessageUtil;
    }
    
    public ConfigInfoService getConfigInfoService() {
        return configInfoService;
    }

    @Autowired
    public void setConfigInfoService(ConfigInfoService configInfoService) {
        this.configInfoService = configInfoService;
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

    public CandidateService getCandidateService() {
        return candidateService;
    }
    
    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    } 
 
    @InitBinder
    public void initBinder(WebDataBinder binder) {  
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    private void initializeAddPI(ModelMap model,HttpServletRequest request) { 
        logger.info("Initializing add candidate PI form..."); 
        HttpSession session=request.getSession();
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        model.addAttribute("companies",companyService.getCompanies(orgId));
        logger.info("Initialized add candidate PI form...");
    } 
    
    @RequestMapping(value="addcandidatepi.html", method=RequestMethod.GET)
    public String showPIForm(ModelMap model, HttpServletRequest request, HttpServletResponse response) { 
        checkSession(request,response); 
        initializeAddPI(model,request);
        model.addAttribute("candidatePI",new PlacementInfo()); 
        logger.info("Redirecting to add candidate PI form..."); 
        return "placement/AddCandidatePI";
    } 
    
    private ModelMap maxPlacementLimitExceededMessage(ModelMap model,String candidateId, int orgId){
         model.addAttribute("piError", i18NMessageUtil.getMessage("candidatepi.error.maxplacements",
                                                new Object[]{candidateId,configInfoService.getMaxPlacementOffers(orgId)}));
         return model;
    }
    
    private ModelMap checkMaxPlacementLimit(ModelMap model,HttpServletRequest request,
                                                                                String candidateId, int candidateUID) { 
        HttpSession session=request.getSession();
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        int maxPlacementOffers=configInfoService.getMaxPlacementOffers(orgId);
        logger.info("Max placement offers="+maxPlacementOffers); 
        int currentPlacements=placementService.numOfPlacements(candidateUID);
        logger.info("Current placements for candidate="+currentPlacements);
        if(currentPlacements>=maxPlacementOffers) { 
            return maxPlacementLimitExceededMessage(model,candidateId,orgId);
        }
        return model;
    }
    
    private String createDisplayList(Candidate cand,String candidateId) { 
        StringBuffer result= new StringBuffer();
        result.append(cand.getFirstName());
        result.append(",");
        result.append(cand.getLastName());
        for(Qualification qual:cand.getQuals()) {  
            if(qual.getPrimaryKey().getQualRollNo().equals(candidateId)) { 
                result.append(",");
                result.append(coursesService.findCourseName(qual.getCourse()));
                result.append(",");
                result.append(coursesService.findStreamName(qual.getStream()));
                result.append(",");
                result.append(qual.getPassingYear());
            }
        }
        return result.toString();
    } 
      
    @RequestMapping(value="dynaplacementinfo.html", method = RequestMethod.POST)
    public @ResponseBody String getCandidateInfo(@RequestParam String candidateId,
                                                 ModelMap model,HttpServletRequest request,HttpServletResponse response) { 
        try {  
                checkSession(request,response);
                HttpSession session=request.getSession();
                int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId")));  
                logger.info("Getting details of candidate with id '"+candidateId+" and org id='..."+orgId); 
                Candidate cand=candidateService.findCandidateById(candidateId, orgId);
                if(cand==null||cand.getQuals().size()==0){
                   return null;
                }
                int candidateUID=cand.getCandidateUID();
                if(checkMaxPlacementLimit(model,request,candidateId,candidateUID).get("piError")!=null) {
                   return "exceeds"; 
                }
                logger.info("Found details of candidate with id '"+candidateId+"'...");  
                return createDisplayList(cand,candidateId);
        }
        catch(Exception e) {  
            e.printStackTrace();
        }
        return null;
    }  
    
    private String savePISuccessPage(ModelMap model,String candidateId){
             model.addAttribute("piStatus", i18NMessageUtil.getMessage("candidatepi.result.save", new Object[]{candidateId}));  
             return "placement/PIStatus"; 
    }
    
    private void addPIAuditInfo(HttpSession session,PlacementInfo candidatePI) { 
        candidatePI.setCreatedBy((String)session.getAttribute("userId"));
        candidatePI.setCreatedOn(new Date());
        candidatePI.getPrimaryKey().setOrgId(((Integer)session.getAttribute("orgId")).intValue());
    } 
    
    private ModelMap duplicatePlErrorMessage(ModelMap model, String candidateId) {
        model.addAttribute("piError",
                     i18NMessageUtil.getMessage("candidatepi.error.duplicatePIRecord",
                                                                        new Object[]{candidateId}));
        return model;
    }
    
    private ModelMap checkDuplicatePlacementRecord(ModelMap model,HttpServletRequest request,
                                                PlacementInfo candidatePI, String candidateId, int candidateUID) {  
        List<PlacementInfo> placements=placementService.findPlacements(candidateUID);
        if(placements==null || placements.isEmpty()) { 
            return model;
        }
        for(PlacementInfo pi:placements) { 
            if(pi.getPrimaryKey().getCompanyId()==candidatePI.getPrimaryKey().getCompanyId()) { 
                return duplicatePlErrorMessage(model,candidateId);
            }
        }
        return model;
    }
    
    private boolean savePIRecord(ModelMap model,PlacementInfo candidatePI,String candidateId,
                                 HttpServletRequest request, HttpServletResponse response) { 
        checkSession(request,response);
        HttpSession session=request.getSession();
        int candidateUID=candidatePI.getPrimaryKey().getCandidateUID(); 
        if(checkMaxPlacementLimit(model,request,candidateId,candidateUID).get("piError")!=null) {
             return false; 
        }
        if(checkDuplicatePlacementRecord(model,request,candidatePI,candidateId,candidateUID).get("piError")!=null){
            return false;
        }
        addPIAuditInfo(session,candidatePI);
        logger.info("Saving PI of candidate "+candidateUID);
        placementService.savePlacementInfo(candidatePI);
        logger.info("Successfully saved PI of candidate "+candidateUID);
        return true;
    } 
    
    @RequestMapping(value="addcandidatepi.html", method=RequestMethod.POST)
    public String savePI(ModelMap model,PlacementInfo candidatePI,HttpServletRequest request,
                             HttpServletResponse response) { 
        checkSession(request,response);
        HttpSession session=request.getSession();
        String candidateId=(String)request.getParameter("candidateId");
        System.out.println("candidate id="+candidateId);
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId")));
        int candidateUID=candidateService.getCandidateUID(candidateId, orgId);
        candidatePI.getPrimaryKey().setCandidateUID(candidateUID);
        logger.info("Saving placement info for candidate with uid "+candidateUID);
        if(savePIRecord(model,candidatePI,candidateId,request,response)){
            return savePISuccessPage(model,candidateId);
        }
        return "placement/PIStatus"; 
    }
    
    @RequestMapping(value="addcandpiandnew.html", method=RequestMethod.POST)
    public String savePIAndNew(ModelMap model,PlacementInfo candidatePI, HttpServletRequest request,
                             HttpServletResponse response) { 
        checkSession(request,response); 
        String candidateId=(String)request.getParameter("candidateId");
        logger.info("Saving placement info for candidate with id "+ candidateId);
        if(!savePIRecord(model,candidatePI,candidateId,request,response)){
            return "placement/PIStatus";
        }
        initializeAddPI(model,request);
        model.addAttribute("candidatePI",new PlacementInfo());
        logger.info("Redirecting to add candidate PI form...");
        return "placement/AddCandidatePI";
    } 
    
    @RequestMapping(value="editcandidatepi.html", method=RequestMethod.GET)
    public String getCandidateIdForEdit(ModelMap model, HttpServletRequest request, HttpServletResponse response) { 
        checkSession(request,response);
        HttpSession session=request.getSession();
        session.setAttribute("piop", "edit");
        logger.info("Showing edit candidate criteria..."); 
        model.addAttribute("cPICriteria", new PlacementCriteria()); 
        return "placement/CandidatePICriteria";
    }
    
    @RequestMapping(value="saveEditedPI.html", method=RequestMethod.POST)
    public String saveEditedPI(ModelMap model, PlacementInfoContainer pic, HttpServletRequest request,
                               HttpServletResponse response) { 
        checkSession(request,response);
        HttpSession session=request.getSession();
        int orgId=((Integer)session.getAttribute("orgId")).intValue();
        ArrayList<PlacementInfo>placements=pic.getPlacements();
        String candidateId=(String)request.getParameter("primaryKey.candidateId");
        String userId=(String)session.getAttribute("userId");
        int candidateUID=candidateService.getCandidateUID(candidateId, orgId);
        for(PlacementInfo pi: placements) { 
            pi.setPrimaryKey(new PlacementInfoPK(orgId,candidateUID,pi.getPrimaryKey().getCompanyId()));
            pi.setModifiedBy(userId);
            pi.setModifiedOn(new Date());
        } 
        placementService.saveEditedPI(candidateUID,placements);
        model.addAttribute("piStatus",i18NMessageUtil.getMessage("candidatepi.result.edit", new Object[]{candidateId}));
        logger.info("Placement info of candidate with id '"+candidateId+"' updated successfully...");
        return "placement/PIStatus";
    } 
    
    private void placementInfoDoesNotExists(ModelMap model,String candidateId) {
            model.addAttribute("piError", i18NMessageUtil.getMessage("candidatepi.error.edit",
                                                new Object[]{candidateId})); 
    }
    
    private boolean commonEditViewPIOps(HttpServletRequest request, ModelMap model, String candidateId) {
         initializeAddPI(model,request);
         HttpSession session=request.getSession();
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId")));
        int candidateUID=candidateService.getCandidateUID(candidateId, orgId);
        ArrayList<PlacementInfo> placements=(ArrayList)placementService.findPlacements(candidateUID);
        if(placements==null||placements.isEmpty()) { 
            placementInfoDoesNotExists(model,candidateId);
            return false;
        } 
        Candidate candidate=candidateService.findCandidateById(candidateId,orgId); 
        model.addAttribute("candidate",candidate);
        model.addAttribute("passingYear",candidateService.findCandidatePassingYear(candidateUID,
                                                                                                                                     candidate.getCourse())); 
        model.addAttribute("placements",placements); 
        // required for edit placement info
        model.addAttribute("pic",new PlacementInfoContainer(placements)); 
        return true;
    }
    
    @RequestMapping(value="editcandidatepi.html", method=RequestMethod.POST)
    public String getPIForEditForm(ModelMap model,@Valid PlacementCriteria pc, HttpServletRequest request,
                HttpServletResponse response) { 
        checkSession(request,response);  
        String candidateId=pc.getCandidateId();
        logger.info("editing placement info of candidateid="+candidateId);
        if(!commonEditViewPIOps(request,model,candidateId)){
                return "placement/PIStatus";
        }  
        return "placement/EditCandidatePI";
    } 
    
    @RequestMapping(value="viewcandidatepi.html", method=RequestMethod.GET)
    public String getCandidateIdForView(ModelMap model, HttpServletRequest request, 
                                                                   HttpServletResponse response) { 
        checkSession(request,response);
        HttpSession session=request.getSession();
        session.setAttribute("piop", "view");
        model.addAttribute("cPICriteria", new PlacementCriteria());
        return "placement/CandidatePICriteria";
    }
    
    @RequestMapping(value="viewcandidatepi.html", method=RequestMethod.POST)
    public String viewCandidatePI(ModelMap model,@Valid PlacementCriteria pc,
                                  HttpServletRequest request,HttpServletResponse response) { 
        checkSession(request,response); 
        String candidateId=pc.getCandidateId();
        logger.info("Searching Candidate "+candidateId+" placement records...");
        if(!commonEditViewPIOps(request,model,candidateId)){
                return "placement/PIStatus";
        } 
        return "placement/ViewCandidatePI";
    }
    
    @RequestMapping(value="deletecandidatepi.html", method=RequestMethod.GET)
    public String candidateIdforDeletePI(ModelMap model,@Valid PlacementCriteria pc,
                                  HttpServletRequest request,HttpServletResponse response) { 
        checkSession(request,response);
        HttpSession session=request.getSession();
        session.setAttribute("piop", "delete");
        logger.info("Showing delete candidate criteria..."); 
        model.addAttribute("cPICriteria", new PlacementCriteria());
        return "placement/CandidatePICriteria";
    }
    
    private void placementInfoDeleteError(ModelMap model, String candidateId) {
            model.addAttribute("piError",i18NMessageUtil.getMessage("candidatepi.result.error.delete",
                                                        new Object[]{candidateId}));
            logger.info("Placement info of candidate with id '"+candidateId+"' does not exists...");
    }
    private void placementInfoDeleteSuccess(ModelMap model, String candidateId) {
            model.addAttribute("piStatus", i18NMessageUtil.getMessage("candidatepi.result.delete",
                                                        new Object[]{candidateId}));
            logger.info("Placement info of candidate with id '"+candidateId+"' deleted successfully...");
    }
    
    @RequestMapping(value="deletecandidatepi.html", method=RequestMethod.POST)
    public String deleteCandidatePI(ModelMap model,@Valid PlacementCriteria pc,
                                  HttpServletRequest request,HttpServletResponse response) { 
        checkSession(request,response);
        logger.info("Deleting candidate's pi...");
        HttpSession session=request.getSession();
        int orgId=((Integer)session.getAttribute("orgId")).intValue();
        String candidateId=pc.getCandidateId();
        int candidateUID=candidateService.getCandidateUID(candidateId, orgId);
        if(placementService.deletePI(candidateUID)==0) { 
            placementInfoDeleteError(model,candidateId);
            return "placement/PIStatus";
        }
        placementInfoDeleteSuccess(model,candidateId);
        return "placement/PIStatus"; 
    }
    
    @RequestMapping(value="home.html", method=RequestMethod.GET) 
    public String goHome(ModelMap model,HttpServletRequest request,HttpServletResponse response) { 
        checkSession(request,response);
        logger.info("Returning to home page");
        return "login/Home";
    }
    
    @Override
    @ExceptionHandler(ObjectRetrievalFailureException.class)
    public ModelAndView handleORFException (Exception exception) { 
        ModelMap model = new ModelMap();
        model.addAttribute("class", exception.getClass());
        model.addAttribute("message", "No matching record with given id was found.");
        logger.fatal(exception);
        return new ModelAndView("error/ErrorPage", model);
    }
}
