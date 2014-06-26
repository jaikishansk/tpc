package com.edu.tpc.service;
/**
 *
 * @author Jaikishan
 */
import java.util.*;

import com.edu.tpc.dao.CandidateDAO;
import com.edu.tpc.domain.Candidate;
import com.edu.tpc.domain.CandidateAddress;
import com.edu.tpc.domain.CandidateContact;
import com.edu.tpc.domain.CandidateEmails;
import com.edu.tpc.domain.CandidatePlacementPref;
import com.edu.tpc.domain.NCCriteria;
import com.edu.tpc.domain.PlacedCandidateResult;
import com.edu.tpc.domain.Qualification;
import com.edu.tpc.domain.QualificationPK;
import com.edu.tpc.domain.SearchCDResult;
import com.edu.tpc.domain.SearchCriteria;
import com.edu.tpc.domain.SearchEligibiltyResult;
import com.edu.tpc.domain.SearchKTResult;
import com.edu.tpc.domain.SearchPlacementResult;
import com.edu.tpc.domain.SearchUnivResult;
import com.edu.tpc.domain.Semester;
import com.edu.tpc.domain.SemesterPK;
import com.edu.tpc.domain.UserType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service("candidateService")
public class CandidateServiceImpl implements CandidateService {
    private final Log logger = LogFactory.getLog(getClass());
    private CandidateDAO candidateDAO;

    public CandidateDAO getCandidateDAO() {
        return candidateDAO;
    }
    @Autowired
    public void setCandidateDAO(CandidateDAO candidateDAO) {
        this.candidateDAO = candidateDAO;
    }
    @Override
    public int getNextCandidateUID(String candidateId, int orgId){
         return candidateDAO.getNextCandidateUID(candidateId,orgId);
    }
    @Override
    public int getCandidateUID(String candidateId, int orgId){
         return candidateDAO.findCandidateUID(candidateId,orgId);
    }
    @Override
    public int findCandidatePassingYear(int candidateUID,int courseId) { 
        return candidateDAO.findCandidatePassingYear(candidateUID,courseId);
    }
    @Override
     public String[] findCandidateEmailIds(int candidateUID){
        return candidateDAO.findCandidateEmailIds(candidateUID);
     }
    @Override
    public boolean ifExistsCandidate(String candidateId,int orgId) { 
        return candidateDAO.ifExistsCandidate(candidateId,orgId);
    }
    private void initializeCandidateAuditInfo(Candidate candidate) { 
        Calendar cal = Calendar.getInstance();
        candidate.setCreatedOn(cal.getTime()); 
        candidate.setModifiedOn(cal.getTime());
    }
    @Override
    public int registerCandidate(Candidate candidate) { 
        int maxMarks=0,marksObtd=0,semester=0;       // per semester
        logger.info("Saving candidate with id '"+candidate.getCandidateId()+"'...");   
        // set up candidate for bi-directional inverse mapping
        CandidateAddress cAddress=candidate.getAddress();
        cAddress.setCandidate(candidate);
        cAddress.setOrgId(candidate.getOrgId());
        cAddress.setCandidateUID(candidate.getCandidateUID());
        
        CandidateContact cContact=candidate.getContact();
        cContact.setCandidate(candidate);
        cContact.setCandidateUID(candidate.getCandidateUID());
        cContact.setOrgId(candidate.getOrgId()); 
        List<CandidatePlacementPref> selectedPref=new ArrayList<CandidatePlacementPref>();
        // set placement pref 
        for(CandidatePlacementPref placementPrefObject:candidate.getPlacementPrefs()) {
                if(placementPrefObject.getPrimaryKey().getPlacementPref()!=null) { 
                    placementPrefObject.getPrimaryKey().setCandidateUID(candidate.getCandidateUID());
                    placementPrefObject.setOrgId(candidate.getOrgId()); 
                    placementPrefObject.getPrimaryKey().setPlacementPref(
                                                       placementPrefObject.getPrimaryKey().getPlacementPref()); 
                    selectedPref.add(placementPrefObject);
                }
        } 
        candidate.setPlacementPref(selectedPref);
         
        //manually map all candidateId field for qualifications
        int candidateType=candidate.getCandidateType();
        if(candidateType==UserType.UGSTUDENT) {  
            candidate.setCourse(candidate.getQuals().get(2).getCourse());
            candidate.setStream(candidate.getQuals().get(2).getStream());
            candidate.setPassingYear(candidate.getQuals().get(2).getPassingYear());
        }
        else if (candidateType==UserType.PGSTUDENT) { 
            candidate.setCourse(candidate.getQuals().get(3).getCourse());
            candidate.setStream(candidate.getQuals().get(3).getStream());
            candidate.setPassingYear(candidate.getQuals().get(3).getPassingYear());
        } 
        logger.info("No. of qualifications while saving candidate="+candidate.getQuals().size()); 
        for(Qualification qual: candidate.getQuals()) {  
                qual.setPrimaryKey(new QualificationPK(candidate.getCandidateUID(),qual.getPrimaryKey().getQualRollNo()));  
                semester=0;
                qual.setOrgId(candidate.getOrgId());
                // for ug and pg qualifications, not reqd for secondary qual
                logger.info("semesters--->"+qual.getSemesters());
                if(qual.getSemesters()!=null && !qual.getSemesters().isEmpty()) {
                    for(Semester sem:qual.getSemesters()) {  
                            sem.setPrimaryKey(new SemesterPK(candidate.getCandidateId(),
                                            qual.getPrimaryKey().getQualRollNo(),qual.getCourse(),new Integer(semester)));
                            sem.setCandidateUID(candidate.getCandidateUID());
                            sem.setOrgId(candidate.getOrgId());
                            logger.info("sem="+sem);
                            maxMarks+=sem.getMaxMarks().intValue();
                            marksObtd+=sem.getMarksObtd().intValue();
                            semester++;
                    }
                    // As percent marks are not available for ug and pg
                    // compute from all semester and store values in qualification
                    qual.setMaxMarks(maxMarks);
                    qual.setObtdMarks(marksObtd);
                    qual.setPercentMarks(new Double(100*(double)marksObtd/maxMarks));
                    maxMarks=marksObtd=0; 
                }
        }
        logger.info("Initializing audit info in save candidate...");
        initializeCandidateAuditInfo(candidate);  
        return candidateDAO.saveCandidate(candidate);
    }
    
    @Override
    public List<CandidateEmails>findEmailsForCriteria(NCCriteria ncCriteria,int orgId){
            return candidateDAO.findEmailsForCriteria(ncCriteria,orgId);
    }
    
    @Override
    public int findRegisteredCandidates(int passingYear, int courseId, int streamId, int orgId){
            return candidateDAO.findRegisteredCandidates(passingYear, courseId, streamId, orgId);
    }
    @Override
     public int findNCPotentialMatch(NCCriteria ncCriteria,int orgId){
            return candidateDAO.findNCPotentialMatch(ncCriteria,orgId);
    }
    
    @Override
    public void editCandidate(Candidate candidate) { 
        candidateDAO.saveCandidate(candidate);
    }
    @Override
    public int deleteCandidate(String candidateId, int orgId) { 
       return candidateDAO.deleteCandidate(candidateId,orgId);
    }
    @Override
    public List<Qualification> findCandidateQuals(String candidateId) { 
       return candidateDAO.findCandidateQuals(candidateId);
    }
    @Override
    public List<SearchCDResult>findCandidatesByPersonalInfo(SearchCriteria searchCriteria,int orgId) { 
        return candidateDAO.findCandidatesByPersonalInfo(searchCriteria,orgId);
    }
    @Override
    public Candidate findCandidateById(String candidateId,int orgId) { 
        return candidateDAO.findCandidateById(candidateId,orgId);
    }
    @Override
    public SearchCDResult findCandidateById(SearchCriteria searchCriteria,int orgId) {
        return candidateDAO.findCandidateById(searchCriteria,orgId);
    }
    @Override
    public int findCandidateType(String candidateId,int orgId) { 
        return candidateDAO.findCandidateType(candidateId,orgId);
    }
    @Override
    public List<Candidate> findAllCandidates() { 
        return candidateDAO.findAllCandidates();
    }
    @Override
    public List<PlacedCandidateResult> findPlacedCandidates(int placementYear) { 
        return candidateDAO.findPlacedCandidates(placementYear);
    }
    @Override
    public List<SearchCDResult>findCandidatesByStream(SearchCriteria searchCriteria,int orgId) { 
        return candidateDAO.findCandidatesByStream(searchCriteria,orgId);
    }
    @Override
    public List<SearchCDResult>findCandidatesByContact(SearchCriteria searchCriteria,int orgId) { 
        return candidateDAO.findCandidatesByContact(searchCriteria,orgId);
    }
    @Override
    public List<SearchEligibiltyResult>findCandidatesByEligibility(SearchCriteria searchCriteria,int orgId) { 
        return candidateDAO.findCandidatesByEligibility(searchCriteria,orgId);
    }
    @Override
    public List<SearchEligibiltyResult>findCandidatesByPercentage(SearchCriteria searchCriteria,int orgId) { 
        return candidateDAO.findCandidatesByPercentage(searchCriteria, orgId);
    }
    @Override
    public List<SearchCDResult>findCandidatesByDegree(SearchCriteria searchCriteria,int orgId) { 
        return candidateDAO.findCandidatesByDegree(searchCriteria,orgId);
    }
    @Override
    public List<SearchKTResult>findCandidatesByKts(SearchCriteria searchCriteria,int orgId) { 
        return candidateDAO.findCandidatesByKts(searchCriteria,orgId);
    }
    @Override
    public List<SearchCDResult>findCandidatesByPY(int courseId,int passingYear) { 
        return candidateDAO.findCandidatesByPY(courseId,passingYear);
    }
    @Override
    public List<SearchUnivResult>findCandidatesByUniv(SearchCriteria searchCriteria, int orgId) { 
        return candidateDAO.findCandidatesByUniv(searchCriteria, orgId);
    }
    @Override
    public List<SearchCDResult>findCandidatesByAddress(SearchCriteria searchCriteria, int orgId) { 
        return candidateDAO.findCandidatesByAddress(searchCriteria,orgId);
    }
    @Override
    public List<SearchCDResult>findCandidatesByPlacement(SearchCriteria searchCriteria, int orgId){
        return candidateDAO.findCandidatesByPlacement(searchCriteria,orgId);
    }
}
