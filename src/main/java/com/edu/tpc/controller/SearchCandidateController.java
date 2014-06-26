package com.edu.tpc.controller;
/**
 *
 * @author Jaikishan
 * Start date: 15/10/11
 */

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.edu.tpc.domain.Candidate;
import com.edu.tpc.domain.NCCriteria;
import com.edu.tpc.domain.SearchCDResult;
import com.edu.tpc.domain.SearchCriteria;
import com.edu.tpc.service.CandidateService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.propertyeditors.CustomDateEditor;

 
@Controller
public class SearchCandidateController extends BaseController {
    private Map<Integer,String> unaryOperators;
    private Map<Integer,String> binaryOperators;
    private Map<Integer,String> eduSearchOptions;
    private Map<Integer,String> courses;
    private Map<Integer,String> streams;
    private CandidateService candidateService;
    
    private PagedListHolder contactPLH;  
    private PagedListHolder addressPLH; 
    private PagedListHolder eligibilityPLH;
    private PagedListHolder placementPLH;   
    private PagedListHolder personalInfoPLH;   
    private PagedListHolder candidateByKTsPLH;  
    private PagedListHolder candidateByUnivPLH;  
    private PagedListHolder candidateByStreamPLH;  
    private PagedListHolder candidateByDegreePLH;  
    private PagedListHolder candidateByPlacementPLH;  
    private PagedListHolder candidateByPercentagePLH;  

    public CandidateService getCandidateService() {
        return candidateService;
    }
    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) { 
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    private void initialize(ModelMap model, HttpSession session) { 
        unaryOperators = new HashMap<Integer,String>();
        unaryOperators.put(new Integer(1),"Is");
        unaryOperators.put(new Integer(2),"Like");
        unaryOperators.put(new Integer(3),"Starts with");
        unaryOperators.put(new Integer(4),"Ends with");

        binaryOperators = new HashMap<Integer,String>();
        binaryOperators.put(new Integer(1),"");
        binaryOperators.put(new Integer(2),"And");
        binaryOperators.put(new Integer(3),"Or");

        eduSearchOptions=new HashMap<Integer,String>();
        eduSearchOptions.put(new Integer(1),"Search by Id");
        eduSearchOptions.put(new Integer(2),"Search by Course & Stream");
        eduSearchOptions.put(new Integer(3),"Search by Percentage");
        eduSearchOptions.put(new Integer(4),"Search by Degree");
        eduSearchOptions.put(new Integer(5),"Search by KTs"); 
        eduSearchOptions.put(new Integer(6),"Search by University");
        eduSearchOptions.put(new Integer(7),"Search by Placements");

        courses = new HashMap<Integer,String>();
        courses.put(new Integer(101),"BE");
        courses.put(new Integer(201),"ME");

        streams = new HashMap<Integer,String>();
        streams.put(new Integer(50),"CSE");
        streams.put(new Integer(51),"IT");
        streams.put(new Integer(52),"Mech");
        streams.put(new Integer(53),"Extc");
        streams.put(new Integer(54),"Elect");

        model.addAttribute("unaryOperators",unaryOperators);
        model.addAttribute("binaryOperators",binaryOperators);
        model.addAttribute("courses",courses);
        model.addAttribute("streams",streams);
        model.addAttribute("eduSearchOptions",eduSearchOptions);
        model.addAttribute("searchCriteria",new SearchCriteria());
        
        session.removeAttribute("placementPLH");
        session.removeAttribute("eligibilityPLH");
        session.removeAttribute("addressPLH");
        session.removeAttribute("contactPLH");
        session.removeAttribute("personalInfoPLH");
        session.removeAttribute("candidateByKTsPLH");
        session.removeAttribute("candidateByUnivPLH");
        session.removeAttribute("candidateByStreamPLH");
        session.removeAttribute("candidateByDegreePLH");
        session.removeAttribute("candidateByPlacementPLH");
        session.removeAttribute("candidateByPercentagePLH"); 
    }
    @RequestMapping(value="searchcandidate.html", method=RequestMethod.GET)
    public String showSearchCandidateForm(ModelMap model,HttpServletRequest request, HttpServletResponse response,
            NCCriteria ncCriteria){ 
        checkSession(request,response);
        initialize(model,request.getSession()); 
        return "candidate/SearchCandidate";
    }
    @RequestMapping(value="searchcandidate.html", method=RequestMethod.POST)
    public String viewAllCandidates(HttpServletRequest request,HttpServletResponse response,ModelMap model) { 
        checkSession(request,response);
        System.out.println("in viewallcandidates...");
        PagedListHolder pagedListHolder=(PagedListHolder)
                        request.getSession().getAttribute("candidateList");
        System.out.println("pagedListHolder=" +pagedListHolder==null);
        if(pagedListHolder==null) { 
            pagedListHolder=new PagedListHolder(candidateService.findAllCandidates());
            System.out.println("setting page size to 5...");
            pagedListHolder.setPageSize(5);
        }
        else { 
            String page = (String) request.getParameter("page");
            if("next".equals(page)){
                    pagedListHolder.nextPage();
            }
            else if("previous".equals(page)){
                    pagedListHolder.previousPage();
            }
        }
        request.getSession().setAttribute("candidateList", pagedListHolder);
        model.put("candidateList", pagedListHolder);
        return "search/ViewAllCandidates";
    } 
  
    @RequestMapping(value="showplacedcandidates.html", method=RequestMethod.GET)
    public String showPlacedCandidatesGET(SearchCriteria searchCriteria,HttpServletRequest request,
                    HttpServletResponse response,ModelMap model) { 
        HttpSession session=request.getSession(true); 
        placementPLH=(PagedListHolder)session.getAttribute("placementPLH");
        String page = (String) request.getParameter("page");
        if("next".equals(page)){
                    session.removeAttribute("placementPLH"); 
                    placementPLH.nextPage();
         }
        else if("previous".equals(page)){
                    session.removeAttribute("placementPLH");
                    placementPLH.previousPage();
        }
        session.setAttribute("placementPLH", placementPLH);
        model.put("placementPLH", placementPLH);
        return "search/ViewPlacedCandidates";
    }
    @RequestMapping(value="showplacedcandidates.html", method=RequestMethod.POST)
    public String showPlacedCandidates(SearchCriteria searchCriteria,HttpServletRequest request,
                    HttpServletResponse response,ModelMap model) { 
        checkSession(request,response);
        HttpSession session=request.getSession(true); 
        placementPLH=(PagedListHolder)session.getAttribute("placementPLH"); 
        if(placementPLH==null) { 
            placementPLH=new PagedListHolder(candidateService.findPlacedCandidates(searchCriteria.getPlacementYear())); 
            placementPLH.setPageSize(5);
        } 
        session.setAttribute("placementPLH", placementPLH);
        model.put("placementPLH", placementPLH);
        return "search/ViewPlacedCandidates";
    }
    @RequestMapping(value="showcandidatebyperinfo.html", method=RequestMethod.GET)
    public String showCandidatesByPersonalInfoGET(SearchCriteria searchCriteria,HttpServletRequest request,
                    HttpServletResponse response,ModelMap model) { 
        HttpSession session=request.getSession(true); 
        personalInfoPLH=(PagedListHolder)session.getAttribute("personalInfoPLH");
        String page = (String) request.getParameter("page");
        if("next".equals(page)){
                    session.removeAttribute("personalInfoPLH"); 
                    personalInfoPLH.nextPage();
         }
        else if("previous".equals(page)){
                    session.removeAttribute("personalInfoPLH");
                    personalInfoPLH.previousPage();
        }
        session.setAttribute("personalInfoPLH", personalInfoPLH);
        model.put("personalInfoPLH", personalInfoPLH);
        return "search/ViewPlacedCandidates";
    }
    @RequestMapping(value="showcandidatebyperinfo.html", method=RequestMethod.POST)
    public String showCandidateByPerInfo(HttpServletRequest request, HttpServletResponse response, 
                    SearchCriteria searchCriteria,ModelMap model) { 
        checkSession(request,response);
        HttpSession session=request.getSession(true);  
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        personalInfoPLH=(PagedListHolder)session.getAttribute("personalInfoPLH"); 
        if(personalInfoPLH==null) { 
            personalInfoPLH=new PagedListHolder(candidateService.findCandidatesByPersonalInfo(searchCriteria,orgId)); 
            personalInfoPLH.setPageSize(5);
        } 
        session.setAttribute("personalInfoPLH", personalInfoPLH);
        model.put("personalInfoPLH", personalInfoPLH); 
        return "search/ViewCandidateByPerInfo";
    }
    @RequestMapping(value="showcandidatebyid.html", method=RequestMethod.POST)
    public String showCandidateById(SearchCriteria searchCriteria,ModelMap model, 
                    HttpServletRequest request,HttpServletResponse response) { 
        checkSession(request,response);
        System.out.println("Candidateid for search===="+searchCriteria.getCandidateId());
        HttpSession session=request.getSession();
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        model.put("candidate", candidateService.findCandidateById(searchCriteria,orgId));
        return "search/ViewCandidateById";
    } 
    @RequestMapping(value="showcandidatebystream.html", method=RequestMethod.GET)
    public String showCandidatesByStreamGET(HttpServletRequest request, HttpServletResponse response,
                                                                            ModelMap model) { 
        HttpSession session=request.getSession(true); 
        candidateByStreamPLH=(PagedListHolder)session.getAttribute("candidateByStreamPLH");
        String page = (String) request.getParameter("page");
        if("next".equals(page)){
                session.removeAttribute("candidateByStreamPLH"); 
                candidateByStreamPLH.nextPage();
        }
        else if("previous".equals(page)){
                session.removeAttribute("candidateByStreamPLH");
                candidateByStreamPLH.previousPage();
        }
        session.setAttribute("candidateByStreamPLH", candidateByStreamPLH);
        model.put("candidateByStreamPLH", candidateByStreamPLH);
        return "search/ViewCandidateByStream";
    }
    @RequestMapping(value="showcandidatebystream.html", method=RequestMethod.POST)
    public String showCandidateByStream(SearchCriteria searchCriteria,ModelMap model,
                HttpServletRequest request,HttpServletResponse response) { 
        checkSession(request,response);
        HttpSession session=request.getSession(); 
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        candidateByStreamPLH=(PagedListHolder)session.getAttribute("candidateByStreamPLH"); 
        if(candidateByStreamPLH==null) { 
            candidateByStreamPLH=new PagedListHolder(candidateService.findCandidatesByStream(searchCriteria,orgId)); 
            candidateByStreamPLH.setPageSize(5);
        }
        session.setAttribute("candidateByStreamPLH", candidateByStreamPLH);
        model.put("candidateByStreamPLH", candidateByStreamPLH);
        return "search/ViewCandidateByStream";
    }
     @RequestMapping(value="showcandidatebypercentage.html", method=RequestMethod.GET)
    public String showCandidatesByPercentageGET(HttpServletRequest request, HttpServletResponse response,
                                                                            ModelMap model) { 
        HttpSession session=request.getSession(true); 
        candidateByPercentagePLH=(PagedListHolder)session.getAttribute("candidateByPercentagePLH");
        String page = (String) request.getParameter("page");
        if("next".equals(page)){
                session.removeAttribute("candidateByPercentagePLH"); 
                candidateByPercentagePLH.nextPage();
        }
        else if("previous".equals(page)){
                session.removeAttribute("candidateByPercentagePLH");
                candidateByPercentagePLH.previousPage();
        }
        session.setAttribute("candidateByPercentagePLH", candidateByPercentagePLH);
        model.put("candidateByPercentagePLH", candidateByPercentagePLH);
        return "search/ViewCandidateByPercentage";
    }
    @RequestMapping(value="showcandidatebypercentage.html", method=RequestMethod.POST)
    public String showCandidateByPercentage(SearchCriteria searchCriteria,ModelMap model,
                                HttpServletRequest request,HttpServletResponse response) {
        checkSession(request,response);
        HttpSession session=request.getSession(); 
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        candidateByPercentagePLH=(PagedListHolder)session.getAttribute("candidateByPercentagePLH"); 
        if(candidateByPercentagePLH==null) { 
            candidateByPercentagePLH=new PagedListHolder(candidateService.findCandidatesByPercentage(
                                                                                               searchCriteria,orgId)); 
            candidateByPercentagePLH.setPageSize(5);
        }
        session.setAttribute("candidateByPercentagePLH", candidateByPercentagePLH);
        model.put("candidateByPercentagePLH", candidateByPercentagePLH);
        return "search/ViewCandidateByPercentage";
    }
    @RequestMapping(value="showcandidatebycontact.html", method=RequestMethod.GET)
    public String showCandidatesByCDGET(HttpServletRequest request, HttpServletResponse response,
                                                                            ModelMap model) { 
        HttpSession session=request.getSession(true); 
        contactPLH=(PagedListHolder)session.getAttribute("contactPLH");
        String page = (String) request.getParameter("page");
        if("next".equals(page)){
                session.removeAttribute("contactPLH"); 
                contactPLH.nextPage();
        }
        else if("previous".equals(page)){
                session.removeAttribute("contactPLH");
                contactPLH.previousPage();
        }
        session.setAttribute("contactPLH", contactPLH);
        model.put("contactPLH", contactPLH);
        return "search/ViewCandidateByCD";
    }
    @RequestMapping(value="showcandidatebycontact.html", method=RequestMethod.POST)
    public String showCandidateByContact(SearchCriteria searchCriteria,ModelMap model,
                HttpServletRequest request,HttpServletResponse response) { 
        checkSession(request,response);
        HttpSession session=request.getSession(); 
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        contactPLH=(PagedListHolder)session.getAttribute("contactPLH"); 
        if(contactPLH==null) { 
            contactPLH=new PagedListHolder(candidateService.findCandidatesByContact(searchCriteria,orgId)); 
            contactPLH.setPageSize(5);
        }
        session.setAttribute("contactPLH", contactPLH);
        model.put("contactPLH", contactPLH); 
        return "search/ViewCandidateByCD";
    }
    @RequestMapping(value="showcandidatebyeligibility.html", method=RequestMethod.GET)
    public String showEligibleCandidatesGET(HttpServletRequest request, HttpServletResponse response,
                                                                            ModelMap model) { 
        HttpSession session=request.getSession(true); 
        eligibilityPLH=(PagedListHolder)session.getAttribute("eligibilityPLH");
        String page = (String) request.getParameter("page");
            if("next".equals(page)){
                    session.removeAttribute("eligibilityPLH"); 
                    eligibilityPLH.nextPage();
            }
            else if("previous".equals(page)){
                    session.removeAttribute("eligibilityPLH");
                    eligibilityPLH.previousPage();
            }
            session.setAttribute("eligibilityPLH", eligibilityPLH);
            model.put("eligibilityPLH", eligibilityPLH);
            return "search/ViewCandByEligibility";
    }
    @RequestMapping(value="showcandidatebyeligibility.html", method=RequestMethod.POST)
    public String showCandidateByEligibility(SearchCriteria searchCriteria,ModelMap model,
    HttpServletRequest request,HttpServletResponse response){ 
        checkSession(request,response);
        HttpSession session=request.getSession(); 
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        eligibilityPLH=(PagedListHolder)session.getAttribute("eligibilityPLH"); 
        if(eligibilityPLH==null) { 
            eligibilityPLH=new PagedListHolder(candidateService.findCandidatesByEligibility(searchCriteria,orgId)); 
            eligibilityPLH.setPageSize(5);
        }
        session.setAttribute("eligibilityPLH", eligibilityPLH);
        model.put("eligibilityPLH", eligibilityPLH);
        return "search/ViewCandByEligibility";
    }
     @RequestMapping(value="showcandidatebydegree.html", method=RequestMethod.GET)
    public String showCandidateByDegreeGET(HttpServletRequest request, HttpServletResponse response,
                                                                            ModelMap model) { 
        HttpSession session=request.getSession(true); 
        candidateByDegreePLH=(PagedListHolder)session.getAttribute("candidateByDegreePLH");
        String page = (String) request.getParameter("page");
            if("next".equals(page)){
                    session.removeAttribute("candidateByDegreePLH"); 
                    candidateByDegreePLH.nextPage();
            }
            else if("previous".equals(page)){
                    session.removeAttribute("candidateByDegreePLH");
                    candidateByDegreePLH.previousPage();
            }
            session.setAttribute("candidateByDegreePLH", candidateByDegreePLH);
            model.put("candidateByDegreePLH", candidateByDegreePLH);
            return "search/ViewCandByDegree";
    }
    @RequestMapping(value="showcandidatebydegree.html", method=RequestMethod.POST)
    public String showCandidateByDegree(SearchCriteria searchCriteria,ModelMap model,
        HttpServletRequest request,HttpServletResponse response) { 
        checkSession(request,response);
        HttpSession session=request.getSession(); 
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        candidateByDegreePLH=(PagedListHolder)session.getAttribute("candidateByDegreePLH"); 
        if(candidateByDegreePLH==null) { 
            candidateByDegreePLH=new PagedListHolder(candidateService.findCandidatesByDegree(searchCriteria,orgId)); 
            candidateByDegreePLH.setPageSize(5);
        }
        session.setAttribute("candidateByDegreePLH", candidateByDegreePLH);
        model.put("candidateByDegreePLH", candidateByDegreePLH); 
        return "search/ViewCandByDegree";
    }
    @RequestMapping(value="showcandidatebykts.html", method=RequestMethod.GET)
    public String showCandidateByKtsGET(HttpServletRequest request, HttpServletResponse response,
                                                                            ModelMap model) { 
        HttpSession session=request.getSession(true); 
        candidateByKTsPLH=(PagedListHolder)session.getAttribute("candidateByKTsPLH");
        String page = (String) request.getParameter("page");
            if("next".equals(page)){
                    session.removeAttribute("candidateByKTsPLH"); 
                    candidateByKTsPLH.nextPage();
            }
            else if("previous".equals(page)){
                    session.removeAttribute("candidateByKTsPLH");
                    candidateByKTsPLH.previousPage();
            }
            session.setAttribute("candidateByKTsPLH", candidateByKTsPLH);
            model.put("candidateByKTsPLH", candidateByKTsPLH);
            return "search/ViewCandByKts";
    }
    @RequestMapping(value="showcandidatebykts.html", method=RequestMethod.POST)
    public String showCandidateByKts(SearchCriteria searchCriteria,ModelMap model, 
    HttpServletRequest request,HttpServletResponse response) { 
        checkSession(request,response);
        HttpSession session=request.getSession(); 
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        candidateByKTsPLH=(PagedListHolder)session.getAttribute("candidateByKTsPLH"); 
        if(candidateByKTsPLH==null) { 
            candidateByKTsPLH=new PagedListHolder(candidateService.findCandidatesByKts(searchCriteria,orgId)); 
            candidateByKTsPLH.setPageSize(5);
        }
        session.setAttribute("candidateByKTsPLH", candidateByKTsPLH);
        model.put("candidateByKTsPLH", candidateByKTsPLH);  
        return "search/ViewCandByKts";
    } 
    
    @RequestMapping(value="showcandidatebyuniv.html", method=RequestMethod.GET)
    public String showCandidateByUnivGET(HttpServletRequest request, HttpServletResponse response,
                                                                            ModelMap model) { 
        HttpSession session=request.getSession(true); 
        candidateByUnivPLH=(PagedListHolder)session.getAttribute("candidateByUnivPLH");
        String page = (String) request.getParameter("page");
            if("next".equals(page)){
                    session.removeAttribute("candidateByUnivPLH"); 
                    candidateByUnivPLH.nextPage();
            }
            else if("previous".equals(page)){
                    session.removeAttribute("candidateByUnivPLH");
                    candidateByUnivPLH.previousPage();
            }
            session.setAttribute("candidateByUnivPLH", candidateByUnivPLH);
            model.put("candidateByUnivPLH", candidateByUnivPLH);
            return "search/ViewCandByKts";
    }
    
    @RequestMapping(value="showcandidatebyuniv.html", method=RequestMethod.POST)
    public String showCandidateByUniv(SearchCriteria searchCriteria,ModelMap model,
        HttpServletRequest request,HttpServletResponse response) { 
        checkSession(request,response);
        HttpSession session=request.getSession(); 
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        candidateByUnivPLH=(PagedListHolder)session.getAttribute("candidateByUnivPLH"); 
        if(candidateByUnivPLH==null) { 
            candidateByUnivPLH=new PagedListHolder(candidateService.findCandidatesByUniv(
                                                                                    searchCriteria,orgId)); 
            candidateByUnivPLH.setPageSize(5);
        }
        session.setAttribute("candidateByUnivPLH", candidateByUnivPLH);
        model.put("candidateByUnivPLH", candidateByUnivPLH);  
        return "search/ViewCandByUniv";
    } 
    
    @RequestMapping(value="showcandbyplacement.html", method=RequestMethod.GET)
    public String showCandByPlacementsGET(HttpServletRequest request, HttpServletResponse response,
                                                                            ModelMap model) { 
        HttpSession session=request.getSession(true); 
        candidateByPlacementPLH=(PagedListHolder)session.getAttribute("candidateByPlacementPLH");
        String page = (String) request.getParameter("page");
            if("next".equals(page)){
                    session.removeAttribute("candidateByPlacementPLH"); 
                    candidateByPlacementPLH.nextPage();
            }
            else if("previous".equals(page)){
                    session.removeAttribute("candidateByPlacementPLH");
                    candidateByPlacementPLH.previousPage();
            }
            session.setAttribute("candidateByPlacementPLH", candidateByPlacementPLH);
            model.put("candidateByPlacementPLH", candidateByPlacementPLH);
            return "search/ViewCandByPlacement";
    }
    
    @RequestMapping(value="showcandbyplacement.html", method=RequestMethod.POST)
    public String showCandByPlacements(SearchCriteria searchCriteria,ModelMap model,
        HttpServletRequest request,HttpServletResponse response) { 
        checkSession(request,response);
        HttpSession session=request.getSession(); 
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        candidateByPlacementPLH=(PagedListHolder)session.getAttribute("candidateByPlacementPLH"); 
        if(candidateByPlacementPLH==null) { 
            candidateByPlacementPLH=new PagedListHolder(candidateService.findCandidatesByPlacement(
                                                                                    searchCriteria,orgId)); 
            candidateByPlacementPLH.setPageSize(5);
        }
        session.setAttribute("candidateByPlacementPLH", candidateByPlacementPLH);
        model.put("candidateByPlacementPLH", candidateByPlacementPLH);  
        return "search/ViewCandByPlacement";
    } 
     
    @RequestMapping(value="showcandidatebyaddress.html", method=RequestMethod.GET)
    public String showCandidatesByAddressGET(HttpServletRequest request, HttpServletResponse response,
                                                                            ModelMap model) { 
        HttpSession session=request.getSession(true); 
        addressPLH=(PagedListHolder)session.getAttribute("addressPLH");
        String page = (String) request.getParameter("page");
            if("next".equals(page)){
                    session.removeAttribute("addressPLH"); 
                    addressPLH.nextPage();
            }
            else if("previous".equals(page)){
                    session.removeAttribute("addressPLH");
                    addressPLH.previousPage();
            }
            session.setAttribute("addressPLH", addressPLH);
            model.put("addressPLH", addressPLH);
            return "search/ViewCandidateByAddress";
    }
    @RequestMapping(value="showcandidatebyaddress.html", method=RequestMethod.POST)
    public String showCandidateByAddress(SearchCriteria searchCriteria,ModelMap model,
        HttpServletRequest request,HttpServletResponse response) { 
        checkSession(request,response);
        HttpSession session=request.getSession(); 
        int orgId=Integer.parseInt(String.valueOf((Integer)session.getAttribute("orgId"))); 
        addressPLH=(PagedListHolder)session.getAttribute("addressPLH"); 
        if(addressPLH==null) { 
            addressPLH=new PagedListHolder(candidateService.findCandidatesByAddress(searchCriteria, orgId)); 
            addressPLH.setPageSize(5);
        }
        session.setAttribute("addressPLH", addressPLH);
        model.put("addressPLH", addressPLH);  
        return "search/ViewCandidateByAddress";
    }
}
