/*
 * Date: 28/2/12
 *
 */

package com.edu.tpc.dao;
/**
 *
 * @author Jaikishan
 */

public interface Queries
{
    public static final String GET_CANDIDATE_QUALIFICATIONS="from Qualification q "
            + " where q.primaryKey.candidateId=?";
    public static final String GET_PLACEMENT_COMPANIES =
            "select distinct company.companyName from Company company, PlacementInfo pi "+
            "where company.companyId=pi.primaryKey.companyId and pi.passingYear=?";

    public static final String GET_UG_COMPS_PLACED_CANDIDATES_COUNT =
            "select count(*) from PlacementInfo pi where pi.OrgId=? and pi.course=? and "
            + "pi.streamId=3 and passingYear=?";

    public static final String GET_UG_IT_PLACED_CANDIDATES_COUNT =
            "select count(*) from PlacementInfo pi where pi.OrgId=? and pi.course=? and "
            + "pi.streamId=4 and passingYear=?";

    public static final String GET_UG_MECH_PLACED_CANDIDATES_COUNT =
            "select count(*) from PlacementInfo pi where pi.OrgId=? and pi.course=? "
            + "and pi.streamId=5 and passingYear=?";

    public static final String GET_UG_ALL_STREAMS_PI =
            "select new com.edu.tpc.domain.CRResult(pi.course, pi.streamId, count(*)) "
            + "from PlacementInfo pi where pi.primaryKey.orgId=? "
            + "and pi.passingYear=? group by pi.course, pi.streamId order by pi.streamId";

    public static final String GET_ELIGIBLE_CAND_LIST =
        "select new com.edu.tpc.domain.EligibilityReport(c.candidateId,c.firstName,"
        + "c.lastName, q.course, q.stream, q.percentMarks) from Candidate c, "
        + "Qualification q, PlacementInfo p where q.stream=? and q.passingYear=? "
        + "and q.liveKT<=? and q.percentMarks between ? and ? and c.candidateId "
        + "in (select c.candidateId from PlacementInfo p group by (p.primaryKey.candidateId) "
        + "having count(p.primaryKey.candidateId)=?)";

    public static final String GET_ALL_ELIGIBLE_CAND_LIST =
        "select new com.edu.tpc.domain.EligibilityReport(c.candidateId,c.firstName,"
        + "c.lastName, q.course, q.stream, q.percentMarks) from Candidate c, "
        + "Qualification q, PlacementInfo p where q.stream in(50,51,52,53,54,55,56) and q.passingYear=? "
        + "and q.liveKT<=? and q.percentMarks between ? and ? and c.candidateId "
        + "in (select c.candidateId from PlacementInfo p group by (p.primaryKey.candidateId) "
        + "having count(p.primaryKey.candidateId)=?)";

    public static final String SEARCH_CAND_BY_CONTACT =
            "select new com.edu.tpc.domain.SearchCDResult(c.candidateId, c.firstName, "
            + "c.lastName, q.course, q.stream, q.passingYear, cd.primaryEmail, cd.primaryCellNo) "
            + "from Candidate c, Qualification q, CandidateContact cd where "
            + "c.candidateId=q.candidateId and cd.primaryEmail=? and cd.primaryCellNo=?";

    public static final String SEARCH_CAND_BY_PLACED =
            "select new com.edu.tpc.domain.PlacedCandidateResult(cand.candidateId,"
            + "cand.firstName,cand.lastName,cand.course,cand.stream,p.primaryKey.companyId,p.dateOfPlacement) "
            + "from Candidate cand, PlacementInfo p"
            + " where cand.orgId=p.primaryKey.orgId and cand.candidateUID=p.primaryKey.candidateUID "
            + " and cand.passingYear=?";

    public static final String SEARCH_CAND_BY_STREAM =
            "select new com.edu.tpc.domain.SearchCDResult(c.candidateId, c.firstName, "
            + "c.lastName, q.course, q.stream, q.passingYear, cd.primaryEmail, cd.primaryCellNo) "
            + "from Candidate c, Qualification q, CandidateContact cd where "
            + "c.candidateId=q.candidateId and q.course=? and q.stream=? and q.passingYear=?";

    public static final String SEARCH_CAND_BY_DEGREE =
            "select new com.edu.tpc.domain.SearchCDResult(c.candidateId, c.firstName, "
            + "c.lastName, q.course, q.stream, q.passingYear, cd.primaryEmail, cd.primaryCellNo) "
            + "from Candidate c, Qualification q, CandidateContact cd where "
            + "c.candidateId=q.candidateId and q.course=? and q.passingYear=?";

    public static final String SEARCH_CAND_BY_KTS =
            "select new com.edu.tpc.domain.SearchCDResult(c.candidateId, c.firstName, "
            + "c.lastName, q.course, q.stream, q.passingYear, cd.primaryEmail, cd.primaryCellNo) "
            + "from Candidate c, Qualification q, CandidateContact cd where "
            + "c.candidateId=q.candidateId and q.course=? and q.liveKT=? and q.passingYear=?";

    public static final String SEARCH_CAND_BY_PY =
            "select new com.edu.tpc.domain.SearchCDResult(c.candidateId, c.firstName, "
            + "c.lastName, q.course, q.stream, q.passingYear, cd.primaryEmail, cd.primaryCellNo) "
            + "from Candidate c, Qualification q, CandidateContact cd where "
            + "c.candidateId=q.candidateId and q.course=? and q.passingYear=?"; 

    public static final String SEARCH_CAND_BY_PERCENTAGE =
            "select new com.edu.tpc.domain.SearchCDResult(c.candidateId, c.firstName, "
            + "c.lastName, q.course, q.stream, q.passingYear, cd.primaryEmail, cd.primaryCellNo) "
            + "from Candidate c, Qualification q, CandidateContact cd where "
            + "c.candidateId=q.candidateId and q.course=? and q.stream=? and q.passingYear=? "
            + "and q.percentMarks between ? and ?";


    public static final String SEARCH_CAND_BY_ELIGIBILITY_UG =
            "select distinct c.candidateId from Candidate c where exists"
            + "(select q.candidateId from Qualification q  where q.course=1 and q.percentMarks>=?) "
            + "and (c.candidateId in(select q.candidateId from Qualification q where "
            + "q.course=2 and q.percentMarks>=?)) and (c.candidateId in"
            + "(select q.candidateId from Qualification q where "
            + "q.course=100 and q.percentMarks>=? and q.passingYear=?))";
    
    public static final String SEARCH_CAND_BY_ELIGIBILITY_UG_RESULT =
            "select new com.edu.tpc.domain.SearchCDResult(c.candidateId, c.firstName, "
            + "c.lastName, q.course, q.stream, q.passingYear, cd.primaryEmail, cd.primaryCellNo) "
            + "from Candidate c, Qualification q, CandidateContact cd where c.candidateId=? "
            + "and c.candidateId=q.candidateId and q.course=100";

    public static final String SEARCH_CAND_BY_ELIGIBILITY_PG =
            "select distinct c.candidateId from Candidate c where exists"
            + "(select q.candidateId from Qualification q  where q.course=1 and q.percentMarks>=?) "
            + "and (c.candidateId in(select q.candidateId from Qualification q where "
            + "q.course=2 and q.percentMarks>=?)) and (c.candidateId in"
            + "(select q.candidateId from Qualification q where "
            + "q.course=100 and q.percentMarks>=?)and (c.candidateId in"
            + "(select q.candidateId from Qualification q where q.course=200 and "
            + "q.percentMarks>=? and q.passingYear=?)))";

    public static final String SEARCH_CAND_BY_ADDR_RESULTS =
            "select new com.edu.tpc.domain.SearchCDResult(c.candidateId, c.firstName, "
            + "c.lastName, c.course, c.stream, c.passingYear, cd.primaryEmail, cd.primaryCellNo) "
            + "from Candidate c,  CandidateContact cd where c.candidateUID=? "
            + "and cd.candidateUID=?";
  
    public static final String SEARCH_CAND_BY_ELIGIBILITY = 
            "select new com.edu.tpc.domain.SearchEligibiltyResult(c.candidateId,c.firstName,c.lastName, q.percentMarks,"
            + "cd.primaryEmail,cd.primaryCellNo) from Candidate c,Qualification q, CandidateContact cd where "
            + " c.candidateUID=? and q.primaryKey.candidateUID=? and cd.candidateUID=?";
    
    public static final String CANDIDATE_PASSING_YEAR="select passingYear from Qualification q "
            + " where primaryKey.candidateUID=? and course=?";
     
    public static final String GET_PI_FOR_EDIT="select new com.edu.tpc.domain.PlacementInfoResult"
            + "(pi.primaryKey.companyId,pi.dateOfPlacement,pi.dateOfJoining,pi.bondPeriod,"
            + " pi.salaryOffered) from PlacementInfo pi where pi.primaryKey.candidateId=?";
    
    
}
