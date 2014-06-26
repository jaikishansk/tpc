package com.edu.tpc.controller; 
  
 
import com.edu.tpc.domain.CompanyCriteria;
import com.edu.tpc.domain.NotifyCompany;
import com.edu.tpc.domain.ViewCompanyPICriteria;
import com.edu.tpc.domain.company.Company;
import com.edu.tpc.service.CompanyService;
import com.edu.tpc.service.CoursesService;

import java.beans.PropertyEditorSupport;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Jaikishan
 * 26/1/2013
 */

@Controller
public class CompanyController  extends BaseController  {
    
    private final Log logger = LogFactory.getLog(getClass());
    private CoursesService coursesService;
    private CompanyService companyService;
    private I18NMessageUtil i18NMessageUtil; 
    
    public CoursesService getCoursesService() {
        return coursesService;
    }

    @Autowired
    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    } 

    public CompanyService getCompanyService() {
        return companyService;
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
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
    
    private void initCompanies(HttpServletRequest request,ModelMap model){
        HttpSession session=request.getSession();
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        // TODO all streams of ug/pg
        model.addAttribute("allStreams",coursesService.findUGStreams(orgId));
        model.addAttribute("ugCourses",coursesService.findUGCourses(orgId));
        model.addAttribute("pgCourses",coursesService.findPGCourses(orgId)); 
    } 
    
    @RequestMapping(value="registercompany.html", method=RequestMethod.GET)
    public String showNewCompanyRegForm(ModelMap model, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception { 
        checkSession(request,response); 
        HttpSession session=request.getSession();
        logger.info("Showing new register company form...");
        initCompanies(request,model); 
        session.setAttribute("edit","false");
        model.addAttribute("companyForm",new Company());  
        return "company/RegisterCompany";
    }
    
     private String regCompanySuccessPage(ModelMap model, String companyName) {
        model.addAttribute("companyStatus",
                i18NMessageUtil.getMessage("registercompany.register.success", new Object[]{companyName}));
        return "company/CompanyResult";
    }
    
    private String regCompanyFailurePage(ModelMap model, String companyName) {
        model.addAttribute("companyError",
                i18NMessageUtil.getMessage("registercompany.register.failure", new Object[]{companyName}));
        return "company/CompanyResult";
    }
     
    private String editCompanyNotFoundPage(ModelMap model, String companyName) {
        model.addAttribute("companyError",
                i18NMessageUtil.getMessage("editcompany.edit.notfound", new Object[]{companyName}));
        return "company/CompanyResult";
    }
    
    private String editCompanySuccessPage(ModelMap model, String companyName) {
        model.addAttribute("companyStatus",
                i18NMessageUtil.getMessage("editcompany.edit.success", new Object[]{companyName}));
        return "company/CompanyResult";
    }
     
    private String deleteCompanyFailurePage(ModelMap model, String companyName) {
        model.addAttribute("companyError",
                i18NMessageUtil.getMessage("registercompany.delete.failure", new Object[]{companyName}));
        return "company/CompanyResult";
    }
    
    private String deleteCompanyNotFoundPage(ModelMap model, String companyName) {
        model.addAttribute("companyError",
                i18NMessageUtil.getMessage("registercompany.delete.notfound", new Object[]{companyName}));
        return "company/CompanyResult";
    }
   
     private String deleteCompanySuccessPage(ModelMap model, String companyName) {
        model.addAttribute("companyStatus",
                i18NMessageUtil.getMessage("registercompany.delete.success", new Object[]{companyName}));
        return "company/CompanyResult";
    }
     
    @RequestMapping(value="registercompany.html", method=RequestMethod.POST)
    public String saveCompanyRegForm(ModelMap model, Company company, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception { 
        checkSession(request,response); 
        logger.info("Saving register company form...");
        HttpSession session=request.getSession();
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        company.setOrgId(orgId);
        try{
            companyService.registerCompany(company);
            return regCompanySuccessPage(model,company.getCompanyName());
        }catch(Exception e){
            return regCompanyFailurePage(model,company.getCompanyName());
        } 
    }
    
    @RequestMapping(value="editcompany.html", method=RequestMethod.GET)
    public String editCompanyCriteria(ModelMap model, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception { 
        checkSession(request,response); 
        logger.info("Showing edit company form..."); 
        model.addAttribute("editCmpnyCriteria",new CompanyCriteria()); 
        return "company/EditCompanyCriteria";
    }
    
    @RequestMapping(value="editcompany.html", method=RequestMethod.POST)
    public String editCompany(ModelMap model, CompanyCriteria criteria, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception { 
        checkSession(request,response); 
        logger.info("Showing company form for editing..."); 
        HttpSession session=request.getSession();
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId")));  
        Company company=companyService.getCompanyByName(criteria.getName(),orgId); 
        if(company !=null) {
            model.addAttribute("companyForm",company);
            initCompanies(request,model);
            session.setAttribute("edit","true");
            return "company/RegisterCompany";
        }
        else {
            return editCompanyNotFoundPage(model,criteria.getName()); 
        } 
    }
    
    @RequestMapping(value="updatecompany.html", method=RequestMethod.POST)
    public String updateCompany(ModelMap model, Company company, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception { 
        companyService.editCompany(company);
        return editCompanySuccessPage(model,company.getCompanyName());
    }
    
    @RequestMapping(value="deletecompany.html", method=RequestMethod.GET)
    public String deleteCompanyCriteria(ModelMap model, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception { 
        checkSession(request,response); 
        logger.info("Showing delete company form..."); 
        model.addAttribute("deleteCmpnyCriteria",new CompanyCriteria()); 
        return "company/DeleteCompanyCriteria";
    }
    
    @RequestMapping(value="deletecompany.html", method=RequestMethod.POST)
    public String deleteCompany(ModelMap model, HttpServletRequest request,
                                                HttpServletResponse response, CompanyCriteria criteria) throws Exception { 
        checkSession(request,response); 
        logger.info("Deleting company..."); 
        HttpSession session=request.getSession();
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId")));  
        try{
        companyService.deleteCompany(criteria.getName(), orgId);
        }catch(DataRetrievalFailureException e) {
            return deleteCompanyNotFoundPage(model,criteria.getName());
        }
        catch(DataIntegrityViolationException e){
            return deleteCompanyFailurePage(model, criteria.getName());
        }
        return deleteCompanySuccessPage(model,criteria.getName());
    } 
     
     @RequestMapping(value="searchcompany.html", method=RequestMethod.GET)
    public String searchCompanyCriteria(ModelMap model, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception { 
        checkSession(request,response); 
        logger.info("Showing search company form..."); 
        model.addAttribute("srchCmpnyCriteria",new CompanyCriteria()); 
        return "company/SearchCompanyCriteria";
    }
    @RequestMapping(value="searchcompany.html", method=RequestMethod.POST)
    public String searchCompany(ModelMap model, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception { 
        checkSession(request,response); 
        logger.info("searching company...");  
        return "company/SearchCompanyResults";
    }
    @RequestMapping(value="viewcomppi.html", method=RequestMethod.GET)
    public String viewCompanyPICriteria(ModelMap model, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception { 
        checkSession(request,response); 
        logger.info("view company pi..."); 
        model.addAttribute("viewCompPICriteria",new ViewCompanyPICriteria()); 
        return "company/ViewCompanyPI";
    }  
    @RequestMapping(value="companycommunicate.html", method=RequestMethod.GET)
    public String communicateCompany(ModelMap model, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception { 
        checkSession(request,response); 
        logger.info("communicate with company..."); 
        model.addAttribute("notifyCompany", new NotifyCompany());
        return "company/CompanyCommunication";
    } 
}
