package com.edu.tpc.service;

import java.util.*;


import com.edu.tpc.domain.Candidate;
import com.edu.tpc.domain.CandidateEmails;
import com.edu.tpc.domain.NCCriteria;
import com.edu.tpc.domain.PlacedCandidateResult;
import com.edu.tpc.domain.Qualification;
import com.edu.tpc.domain.SearchCDResult;
import com.edu.tpc.domain.SearchCriteria;
import com.edu.tpc.domain.SearchEligibiltyResult;
import com.edu.tpc.domain.SearchKTResult;
import com.edu.tpc.domain.SearchPlacementResult;
import com.edu.tpc.domain.SearchUnivResult;

/**
 *
 * @author Jaikishan
 */
import java.util.Date;

public interface CandidateService {
    public int getNextCandidateUID(String candidateId, int orgId);
    public int getCandidateUID(String candidateId, int orgId);
    public int findCandidatePassingYear(int candidateUID,int courseId);
    public String[] findCandidateEmailIds(int candidateUID);
    public boolean ifExistsCandidate(String candidateId, int orgId);
    public int registerCandidate(Candidate candidate); 
    public void editCandidate(Candidate candidate);
    public int deleteCandidate(String candidateId, int orgId);
    public Candidate findCandidateById(String candidateId,int orgId);
    public SearchCDResult findCandidateById(SearchCriteria searchCriteria,int orgId); 
    public List<Qualification> findCandidateQuals(String candidateId); 
    public int findCandidateType(String candidateId,int orgId);
    public List<Candidate> findAllCandidates();
    public int findRegisteredCandidates(int courseId, int streamId, int passingYear, int orgId);
    public int findNCPotentialMatch(NCCriteria ncCriteria,int orgId);
    public List<CandidateEmails>findEmailsForCriteria(NCCriteria ncCriteria,int orgId);
    public List<SearchCDResult>findCandidatesByPersonalInfo(SearchCriteria searchCriteria,int orgId);
    public List<PlacedCandidateResult> findPlacedCandidates(int placementYear);
    public List<SearchCDResult>findCandidatesByStream(SearchCriteria searchCriteria,int orgId);
    public List<SearchEligibiltyResult>findCandidatesByPercentage(SearchCriteria searchCriteria,int orgId);
    public List<SearchCDResult>findCandidatesByContact(SearchCriteria searchCriteria,int orgId);
    public List<SearchEligibiltyResult>findCandidatesByEligibility(SearchCriteria searchCriteria,int orgId);
    public List<SearchCDResult>findCandidatesByDegree(SearchCriteria searchCriteria,int orgId);
    public List<SearchKTResult>findCandidatesByKts(SearchCriteria searchCriteria,int orgId);
    public List<SearchCDResult>findCandidatesByPY(int courseId,int passingYear);
    public List<SearchUnivResult>findCandidatesByUniv(SearchCriteria searchCriteria, int orgId);
    public List<SearchCDResult>findCandidatesByAddress(SearchCriteria searchCriteria, int orgId);
    public List<SearchCDResult>findCandidatesByPlacement(SearchCriteria searchCriteria, int orgId); 
}
