package com.edu.tpc.dao;

/**
 *
 * @author Jaikishan
*/

import java.util.*;
import java.sql.SQLException; 

import com.edu.tpc.domain.Candidate;
import com.edu.tpc.domain.CandidateAddress;
import com.edu.tpc.domain.CandidateEmails;
import com.edu.tpc.domain.CourseStreamInfo;
import com.edu.tpc.domain.NCCriteria;
import com.edu.tpc.domain.PlacedCandidateResult;
import com.edu.tpc.domain.Qualification;
import com.edu.tpc.domain.SearchCDResult;
import com.edu.tpc.domain.SearchCriteria;
import com.edu.tpc.domain.SearchEligibiltyResult;
import com.edu.tpc.domain.SearchKTResult;
import com.edu.tpc.domain.SearchPlacementResult;
import com.edu.tpc.domain.SearchUnivResult;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;  
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.DetachedCriteria; 
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback; 
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

@Repository("candidateDAO")
@Transactional
public class CandidateDAOImpl extends HibernateDaoSupport implements CandidateDAO { 
    private final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    public void init(SessionFactory factory) { 
        setSessionFactory(factory);
    }  
     
    private boolean isEmpty(String data){
            return data==null || (data.trim().length())==0;
    }
    
    @Override
    public int getNextCandidateUID(String candidateId, final int orgId) { 
        List result=(List)getHibernateTemplate().execute(new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException { 
                    Query q = session.createSQLQuery("select get_sequence_next(:seqname,:orgId)")
                                                 . setParameter("seqname", "candidate_seq").setParameter("orgId", orgId);
                    //q.setString("seqname", "candidate_seq");
                    return q.list();
                  }
              }); 
        return ((Integer)result.get(0)).intValue();
    }
     
    @Override
    public int findCandidateUID(String candidateId, int orgId){
          List<Candidate> result=getHibernateTemplate().find("from Candidate where candidateId=? and orgId=?", 
                                                                         new Object[]{candidateId, orgId});
        return result.size()>0?result.get(0).getCandidateUID():0;
    }
    
    @Override
    public int findCandidatePassingYear(final int candidateUID, final int courseId) { 
        List result=getHibernateTemplate().find(Queries.CANDIDATE_PASSING_YEAR,
                                           new Object[]{candidateUID,courseId});
        return ((Integer)result.get(0)).intValue(); 
    }
    
    @Override
    public String[] findCandidateEmailIds(int candidateUID){
        String[] emailIds=null;
        List<String> primaryEmail=getHibernateTemplate().find("select primaryEmail from CandidateContact where candidateUID=?",
                                           new Object[]{candidateUID});  
        List<String> secondaryEmail=getHibernateTemplate().find("select secondaryEmail from CandidateContact where candidateUID=?",
                                           new Object[]{candidateUID});
        if(!secondaryEmail.get(0).equals("")){
            emailIds=new String[2];
            emailIds[0]=(String)primaryEmail.get(0);
            emailIds[1]=(String)secondaryEmail.get(0);
        } 
        else {
            emailIds=new String[1];
            emailIds[0]=(String)primaryEmail.get(0);
        }
        return emailIds;
    }
    
    @Override
    public boolean ifExistsCandidate(String candidateId,int orgId) { 
        List result=getHibernateTemplate().find("from Candidate where candidateId=? and orgId=?", 
                                                                         new Object[]{candidateId, orgId});
        return result.size()>0;
    } 
     
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int saveCandidate(Candidate candidate) {  
        try {  
                logger.info("Saving candidate with candidate id '"+candidate.getCandidateId()+"'...");   
                getHibernateTemplate().merge(candidate); 
                logger.info("Successfully saved candidate with candidate id '"+candidate.getCandidateId()+"'...");   
        }catch(Exception e) { 
            e.printStackTrace(); 
            return 1;
        } 
        return 0;
    }  
    
    @Override
    public int deleteCandidate(final String candidateId, final int orgId){ 
         return ((Integer)getHibernateTemplate().execute(new HibernateCallback() { 
           @Override
           public Object doInHibernate(Session session) throws HibernateException,SQLException { 
               Query query = session.createQuery("delete from Candidate where candidateId=? and orgId=?");
               query.setParameter(0, candidateId); 
               query.setParameter(1, orgId); 
               return new Integer(query.executeUpdate());
           }
       })).intValue();
    }
    
    @Override 
    public Candidate findCandidateById(String candidateId,int orgId) { 
        List<Candidate> result=getHibernateTemplate().find("from Candidate where candidateId=? and orgId=?", 
                new Object[]{candidateId,orgId});
        return result.size()>0?result.get(0):null;
    }
    
    private SearchCDResult findCandByIdResults(Integer cuid){
            return (SearchCDResult)getHibernateTemplate().find(Queries.SEARCH_CAND_BY_ADDR_RESULTS,
                                                                                                  new Object[]{cuid,cuid}).get(0); 
    }
    @Override 
    public SearchCDResult findCandidateById(SearchCriteria searchCriteria,int orgId) {  
        return findCandByIdResults(findCandidateUID(searchCriteria.getCandidateId(),orgId));  
    }
    
    @Override
    public int findCandidateType(String candidateId,int orgId) { 
        String query="select candidateType  from Candidate"
                       + " where candidateId=? and orgId=?";
        List<Candidate> result=getHibernateTemplate().find(query,new Object[]{candidateId, orgId});
        return result.size()>0?result.get(0).getCandidateType():-1;
    }
    
    @Override
    public List<Qualification> findCandidateQuals(String candidateId) { 
        return getHibernateTemplate().find(Queries.GET_CANDIDATE_QUALIFICATIONS, candidateId);
    }

    @Override
     public List<CandidateEmails>findEmailsForCriteria(final NCCriteria ncCriteria,final int orgId){
            String query=formatNotifyClassQuery(ncCriteria,orgId);
            List<Integer> candidateUIDs=(List<Integer>)getHibernateTemplate().execute(new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException { 
                    String query=formatNotifyClassQuery(ncCriteria,orgId); 
                    SQLQuery sqlQuery =session.createSQLQuery(query);
                    return sqlQuery.list(); 
                } 
            });  
            List<CandidateEmails> result=new ArrayList<CandidateEmails>();
            for(Integer candidateUID:candidateUIDs){
                CandidateEmails cEmails=new CandidateEmails();
                cEmails.setCandidateUID(candidateUID);
                cEmails.setEmailIds(findCandidateEmailIds(candidateUID));
                result.add(cEmails); 
            }
            return result;
     }
    
    @Override
    public int findRegisteredCandidates(int courseId, int streamId, int passingYear, int orgId){
            String query="select count(*) from Candidate c where  course=? and stream=? and passingYear=? and orgId=?";
            List result=getHibernateTemplate().find(query, new Object[]{courseId,streamId, passingYear, orgId});
            return ((Long)result.get(0)).intValue();
    }
    private String formatNotifyClassQuery(final NCCriteria ncCriteria,final int orgId){
            StringBuffer query=new StringBuffer();
            query.append("select cuid from Qualification q where ");
            query.append(" q.courseId="); query.append(ncCriteria.getCourse());
            query.append(" and q.streamId="); query.append(ncCriteria.getStream());
            query.append(" and q.passingYear="); query.append(ncCriteria.getPassingYear());
            query.append(" and q.orgId="); query.append(orgId); 
            if(ncCriteria.getStartPercent()!=null){
                query.append(" and q.percentMarks>=");
                query.append(ncCriteria.getStartPercent());
            }
            if(ncCriteria.getEndPercent()!=null){
                query.append(" and q.percentMarks<=");
                query.append(ncCriteria.getEndPercent());
            }    
            if(ncCriteria.getLiveKT()!=null){
                query.append(" and q.liveKT=");
                query.append(ncCriteria.getLiveKT());
            }    
            if(ncCriteria.getNumPlacements()!=null){
                query.append(" and Cuid in ( select Cuid from Placement p where p.orgId=");
                query.append(orgId);
                query.append(" group by Cuid having count(Cuid)=");
                query.append(ncCriteria.getNumPlacements());
                query.append(')');
            }    
            return query.toString();
    }
     @Override
     public int findNCPotentialMatch(final NCCriteria ncCriteria,final int orgId) {  
               List result=(List)getHibernateTemplate().execute(new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException { 
                    String query=formatNotifyClassQuery(ncCriteria,orgId); 
                    SQLQuery sqlQuery =session.createSQLQuery(query);
                    return sqlQuery.list(); 
                } 
         }); 
         return result.size();
     }
     
     
    @Override
    public List<Candidate> findAllCandidates() { 
        return getHibernateTemplate().find("from Candidate");
    }
    @Override
     public String findCandidateEmailId(final String candidateId, final int orgId) { 
        String query="select primaryEmailId from CandidateContact"
                       + " where candidateId=? and orgId=?";
        return  (String) getHibernateTemplate().find(query).get(0); 
    }
    private String formatCandidatesByPersonalInfoQuery(SearchCriteria searchCriteria,int orgId){
            StringBuffer query=new StringBuffer();
            query.append("select cuid from Candidate c where orgId=");query.append(orgId);
            if(searchCriteria.getPassingYear()!=null){
                    query.append(" and passingYear=");searchCriteria.getPassingYear();
            }  
            if(!isEmpty(searchCriteria.getFirstName())) {
                    query.append(" and firstname like '%"); searchCriteria.getFirstName();query.append("%'");
            }
            if(!isEmpty(searchCriteria.getLastName())) {
                    query.append(" and lastname like '%"); searchCriteria.getLastName();query.append("%'");
            }
            if(searchCriteria.getDob()!=null) {
                    System.out.println("dob="+searchCriteria.getDob());
                    String pattern = "yyyy-MM-dd";
                    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
                    query.append(" and dateofbirth='"); query.append(formatter.format(searchCriteria.getDob()));
                    query.append("'");
            } 
            return query.toString();
    }
    private SearchCDResult findCandByPersonalInfoResults(Integer cuid){
            return (SearchCDResult)getHibernateTemplate().find(Queries.SEARCH_CAND_BY_ADDR_RESULTS,
                                                                                                  new Object[]{cuid,cuid}).get(0); 
    }
    @Override
    public List<SearchCDResult>findCandidatesByPersonalInfo(final SearchCriteria searchCriteria,final int orgId) { 
        List<SearchCDResult>result = new ArrayList<SearchCDResult>();  
         List<Integer> candidateUIDs=(List<Integer>)getHibernateTemplate().execute(new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException { 
                    String query=formatCandidatesByPersonalInfoQuery(searchCriteria,orgId);
                    System.out.println("personalinfo query="+query);
                    SQLQuery sqlQuery =session.createSQLQuery(query);
                    return sqlQuery.list(); 
                } 
            }); 
            for(Integer cuid:candidateUIDs) {
                result.add(findCandByPersonalInfoResults(cuid));
            }   
        return result;
    }
    @Override
    public List<PlacedCandidateResult>findPlacedCandidates(int placementYear) { 
        List<PlacedCandidateResult> result = null ; 
        logger.info("Finding placed candidates....");
        result = getHibernateTemplate().find(Queries.SEARCH_CAND_BY_PLACED, placementYear);  
        return result;
    }
    
    private String formatCandidatesByStreamQuery(SearchCriteria searchCriteria,int orgId){
            StringBuffer query=new StringBuffer();
            query.append("select cuid from Candidate where orgId=");query.append(orgId);
            query.append(" and courseid=");query.append(searchCriteria.getCourseId());
            query.append(" and streamid=");query.append(searchCriteria.getStreamId());
            if(searchCriteria.getPassingYear()!=null){
                 query.append(" and passingYear=");query.append(searchCriteria.getPassingYear());
            }
            return query.toString();
    }
    private SearchCDResult findCandByStreamResults(Integer cuid){
            return (SearchCDResult)getHibernateTemplate().find(Queries.SEARCH_CAND_BY_ADDR_RESULTS,
                                                                                                  new Object[]{cuid,cuid}).get(0); 
    }
    @Override
    public List<SearchCDResult>findCandidatesByStream(final SearchCriteria searchCriteria,final int orgId) { 
        List<SearchCDResult>result = new ArrayList<SearchCDResult>();  
        List<Integer> candidateUIDs=(List<Integer>)getHibernateTemplate().execute(new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException { 
                    String query=formatCandidatesByStreamQuery(searchCriteria,orgId);
                    System.out.println("stream query="+query);
                    SQLQuery sqlQuery =session.createSQLQuery(query);
                    return sqlQuery.list(); 
                } 
            }); 
            for(Integer cuid:candidateUIDs) {
                result.add(findCandByStreamResults(cuid));
            }
            return result;
    }
    private String formatCandidatesByPercentageQuery(SearchCriteria searchCriteria,int orgId) {
            StringBuffer query=new StringBuffer();
            query.append("select cuid from Qualification where orgId=");query.append(orgId);
            query.append(" and courseid=");query.append(searchCriteria.getCourseId());
            query.append(" and streamid=");query.append(searchCriteria.getStreamId());
            if(searchCriteria.getPassingYear()!=null) {
                    query.append(" and passingYear=");query.append(searchCriteria.getPassingYear());
            }
            if(searchCriteria.getStartPercentage()!=null) {
                 query.append(" and percentmarks >=");query.append(searchCriteria.getStartPercentage());
            }
            if(searchCriteria.getEndPercentage()!=null) {
                 query.append(" and percentmarks <=");query.append(searchCriteria.getEndPercentage());
            }
            return query.toString();
    }
    @Override
    public List<SearchEligibiltyResult>findCandidatesByPercentage(final SearchCriteria searchCriteria,final int orgId) { 
            List<SearchEligibiltyResult> result= new ArrayList<SearchEligibiltyResult>();
            List<Integer> candidateUIDs=(List<Integer>)getHibernateTemplate().execute(new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException { 
                    String query=formatCandidatesByPercentageQuery(searchCriteria,orgId); 
                    SQLQuery sqlQuery =session.createSQLQuery(query);
                    return sqlQuery.list(); 
                } 
            }); 
            for(Integer cuid:candidateUIDs) {
                result.add(findEligibilityResults(cuid));
            }
            return result;
    }
    
    private boolean allContactFieldsEmpty(SearchCriteria searchCriteria){
        return searchCriteria.getPassingYear()==null
                   &&
                  !isEmpty(searchCriteria.getPrimCell())
                    &&
                  !isEmpty(searchCriteria.getPrimEmail());
    }
    private String formatCandidatesByContactQuery(SearchCriteria searchCriteria,int orgId){
            StringBuffer query=new StringBuffer();
            query.append("select cuid from Candidate c where orgId=");query.append(orgId);
            if(searchCriteria.getPassingYear()!=null){
                    query.append(" and passingYear=");searchCriteria.getPassingYear();
            }
            if(allContactFieldsEmpty(searchCriteria)) {
                return query.toString();
            }
            query.append(" and cuid in (select cuid from candidatecontact where orgId=");query.append(orgId);
            if(!isEmpty(searchCriteria.getPrimEmail())) {
                    query.append(" and primaryEmail like '%"); searchCriteria.getPrimEmail();query.append("%'");
            }
            if(!isEmpty(searchCriteria.getPrimCell())) {
                    query.append(" and primaryCellNo like '%"); searchCriteria.getPrimCell();query.append("%'");
            }
            query.append(')');
            return query.toString();
    }
    private SearchCDResult findCandByContactResults(Integer cuid){
            return (SearchCDResult)getHibernateTemplate().find(Queries.SEARCH_CAND_BY_ADDR_RESULTS,
                                                                                                  new Object[]{cuid,cuid}).get(0); 
    }
    @Override
    public List<SearchCDResult>findCandidatesByContact(final SearchCriteria searchCriteria,final int orgId) { 
        List<SearchCDResult>result = new ArrayList<SearchCDResult>();  
        List<Integer> candidateUIDs=(List<Integer>)getHibernateTemplate().execute(new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException { 
                    String query=formatCandidatesByContactQuery(searchCriteria,orgId);
                    System.out.println("contact query="+query);
                    SQLQuery sqlQuery =session.createSQLQuery(query);
                    return sqlQuery.list(); 
                } 
            }); 
            for(Integer cuid:candidateUIDs) {
                result.add(findCandByContactResults(cuid));
            }
            return result;
    } 
    
    private String formatEligilbleCandidatesQuery(SearchCriteria searchCriteria,int orgId){
            StringBuffer query=new StringBuffer();
            query.append("select cuid from Qualification q where ");
            query.append(" q.orgId="); query.append(orgId);
            query.append(" and q.courseId=");query.append(searchCriteria.getCourseId());
            query.append(" and q.streamId=");query.append(searchCriteria.getStreamId());
            query.append(" and q.passingYear=");query.append(searchCriteria.getPassingYear()); 
            if(searchCriteria.getSscPercentage()!=null) {  
                    query.append(" and q.cuid in (select cuid from Qualification q"
                    + " where courseId=1 and percentMarks>=");query.append(searchCriteria.getSscPercentage());
                    query.append(" and orgId=");query.append(orgId);
                    query.append(')');
            }
            if(searchCriteria.getHscPercentage()!=null) {  
                    query.append(" and cuid in (select cuid from Qualification q"
                    + " where courseId in (2,3,4) and percentMarks>=");query.append(searchCriteria.getHscPercentage());
                    query.append(" and orgId=");query.append(orgId);
                    query.append(')');
            }
            if(searchCriteria.getUgPercentage()!=null) {  
                    query.append(" and cuid in (select cuid from Qualification q"
                    + " where orgId=");query.append(orgId);
                    if(searchCriteria.getPgPercentage()==null) {
                        query.append(" and courseId=");query.append(searchCriteria.getCourseId());
                        query.append(" and streamId=");query.append(searchCriteria.getStreamId());
                        query.append(" and passingYear=");query.append(searchCriteria.getPassingYear());
                        query.append(" and liveKT=");query.append(searchCriteria.getLiveKTs()); 
                    }
                    else{
                        query.append(" and courseId in(100,101,102,103)");
                    }
                    query.append(" and percentMarks>=");query.append(searchCriteria.getUgPercentage()); 
                    query.append(')');
            }
            if(searchCriteria.getPgPercentage()!=null) {  
                    query.append(" and q.primaryKey.candidateUID in (select q.primaryKey.candidateUID from Qualification q"
                    + " where courseId=");query.append(searchCriteria.getCourseId()); 
                    query.append(" and streamId=");query.append(searchCriteria.getStreamId()); 
                    query.append(" and percentMarks>=");query.append(searchCriteria.getUgPercentage());
                    query.append(" and liveKT=");query.append(searchCriteria.getLiveKTs()); 
                    query.append(" and orgId=");query.append(orgId); 
                    query.append(" and passingYear=");query.append(searchCriteria.getPassingYear());
                    query.append(')');
            } 
            return query.toString();
    }
    private SearchEligibiltyResult findEligibilityResults(Integer cuid) { 
            return (SearchEligibiltyResult)getHibernateTemplate().find(Queries.SEARCH_CAND_BY_ELIGIBILITY,
                                           new Object[]{cuid,cuid,cuid}).get(0);
    }
    @Override
    public List<SearchEligibiltyResult>findCandidatesByEligibility(final SearchCriteria searchCriteria,final int orgId) {   
            List<SearchEligibiltyResult> result= new ArrayList<SearchEligibiltyResult>();
            List<Integer> candidateUIDs=(List<Integer>)getHibernateTemplate().execute(new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException { 
                    String query=formatEligilbleCandidatesQuery(searchCriteria,orgId); 
                    SQLQuery sqlQuery =session.createSQLQuery(query);
                    return sqlQuery.list(); 
                } 
            }); 
            for(Integer cuid:candidateUIDs) {
                result.add(findEligibilityResults(cuid));
            }
            return result;
    }
    
    private SearchCDResult findCandByDegreeResults(Integer cuid){
            return (SearchCDResult)getHibernateTemplate().find(Queries.SEARCH_CAND_BY_ADDR_RESULTS,
                                                                                                  new Object[]{cuid,cuid}).get(0); 
    }
     private String formatCandidatesByDegreeQuery(SearchCriteria searchCriteria,int orgId) {
            StringBuffer query=new StringBuffer();
            query.append("select cuid from Candidate where orgId=");query.append(orgId);
            query.append(" and courseId=");query.append(searchCriteria.getCourseId());
            if(searchCriteria.getPassingYear()!=null) {
                     query.append(" and passingYear=");query.append(searchCriteria.getPassingYear());
            }
            query.append(" order by streamid");
            return query.toString();
     }
    @Override
    public List<SearchCDResult>findCandidatesByDegree(final SearchCriteria searchCriteria,final int orgId) {
        List<SearchCDResult>result = new ArrayList<SearchCDResult>();  
        List<Integer> candidateUIDs=(List<Integer>)getHibernateTemplate().execute(new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException { 
                    String query=formatCandidatesByDegreeQuery(searchCriteria,orgId);
                    System.out.println("degree query="+query);
                    SQLQuery sqlQuery =session.createSQLQuery(query);
                    return sqlQuery.list(); 
                } 
            }); 
            for(Integer cuid:candidateUIDs) {
                result.add(findCandByDegreeResults(cuid));
            }
            return result;
    }
       
     private String formatCandidatesByKTsQuery(SearchCriteria searchCriteria,int orgId) {
            StringBuffer query=new StringBuffer();
            query.append("select candidateId,firstName,lastName,liveKT, pastKT,"); 
            query.append("ktSubjects,primaryemail,primarycellno from "); 
            query.append("Candidate c, Qualification q, CandidateContact cd where c.cuid=q.cuid and  c.cuid=cd.cuid and ");
            query.append(" q.courseid=");query.append(searchCriteria.getCourseId());
            query.append(" and c.orgId=q.orgId and ");
            query.append(" c.OrgId=");query.append(orgId);
            query.append(" and q.orgId=");query.append(orgId);
            query.append(" and q.streamid=");query.append(searchCriteria.getStreamId());  
            if(searchCriteria.getLiveKTs()!=null) {
                query.append("and q.liveKT >=");query.append(searchCriteria.getLiveKTs());
            }
            if(searchCriteria.getPastKTs()!=null) {
                query.append("and q.pastKT >=");query.append(searchCriteria.getPastKTs());
            }
            if(searchCriteria.getPassingYear()!=null) {
                query.append(" and passingYear=");query.append(searchCriteria.getPassingYear());
            }
            return query.toString();
     }
     
    @Override
    public List<SearchKTResult>findCandidatesByKts(final SearchCriteria searchCriteria,final int orgId) { 
        List<SearchKTResult>result =(List<SearchKTResult>)getHibernateTemplate().execute(new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException { 
                    String query=formatCandidatesByKTsQuery(searchCriteria,orgId);
                    System.out.println("kt query="+query);
                    Query sqlQuery=session.createSQLQuery(query).setResultTransformer(
                                                                Transformers.aliasToBean(SearchKTResult.class));  
                    return sqlQuery.list(); 
                } 
            });  
        return result;
    }
    @Override
    public List<SearchCDResult>findCandidatesByPY(int courseId, int passingYear) { 
        return getHibernateTemplate().find(Queries.SEARCH_CAND_BY_PY,
                                           new Object[]{courseId,passingYear});
    }
    
    private String formatCandidatesByUnivQuery(SearchCriteria searchCriteria, int orgId) {
            StringBuffer query=new StringBuffer();
            query.append("select c.candidateId, c.firstName, c.lastName, q.courseid, q.streamid, q.university,");
            query.append(" q.passingYear, cd.primaryEmail, cd.primaryCellNo ");
            query.append(" from Candidate c, Qualification q, CandidateContact cd where ");
            query.append(" c.cuid=q.cuid and cd.cuid=c.cuid");
            query.append(" and c.orgId=");query.append(orgId);
            query.append(" and q.orgId=");query.append(orgId);
            query.append(" and cd.orgId=");query.append(orgId);
            query.append(" and q.courseId not in (1,2,3,4) ");
            if(searchCriteria.getUniv()!=null && searchCriteria.getUniv().trim().length()!=0) {
                query.append(" and q.university like '%");query.append(searchCriteria.getUniv());query.append("%'");
            }
            if(searchCriteria.getPassingYear()!=null) {
                 query.append(" and q.passingYear=");query.append(searchCriteria.getPassingYear());
            } 
            return query.toString();
    }
    
    @Override
    public List<SearchUnivResult>findCandidatesByUniv(final SearchCriteria searchCriteria, final int orgId) { 
          List<SearchUnivResult>result =(List<SearchUnivResult>)getHibernateTemplate().execute(new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException { 
                    String query=formatCandidatesByUnivQuery(searchCriteria,orgId);
                    System.out.println("univ query="+query);
                    Query sqlQuery=session.createSQLQuery(query).setResultTransformer(
                                                                                        Transformers.aliasToBean(SearchUnivResult.class));  
                    return sqlQuery.list(); 
                } 
            });  
        return result;
    }
    
    private boolean allAddressFieldsEmpty(SearchCriteria searchCriteria)  {
            return  isEmpty(searchCriteria.getAddressLine1()) &&  isEmpty(searchCriteria.getAddressLine2())
                      && isEmpty(searchCriteria.getCity()) &&  isEmpty(searchCriteria.getState())
                      &&  isEmpty(searchCriteria.getPin());
    }
    
    private String formatCandidateByAddressQuery(SearchCriteria searchCriteria, int orgId){
            StringBuffer query=new StringBuffer(); 
            query.append("select cuid from Candidate where orgId=");query.append(orgId);
            if(searchCriteria.getPassingYear()!=null) {
                query.append(" and passingYear=");query.append(searchCriteria.getPassingYear());
            }
            if(allAddressFieldsEmpty(searchCriteria)) {
                return query.toString();
            }
            query.append(" and cuid in (select cuid from CandidateAddress where orgId=");query.append(orgId);
            if(!isEmpty(searchCriteria.getAddressLine1())) {
                query.append(" and addressline1 like '%");
                query.append(searchCriteria.getAddressLine1());query.append("%'"); 
            }
            if(!isEmpty(searchCriteria.getAddressLine2())) {
                query.append(" and addressline2 like '%");
                query.append(searchCriteria.getAddressLine2());query.append("%'"); 
            }
            if(!isEmpty(searchCriteria.getCity())) {
                query.append(" and city like '%");
                query.append(searchCriteria.getCity());query.append("%'");
            }
            if(!isEmpty(searchCriteria.getState())) {
                query.append(" and residentstate like '%");
                query.append(searchCriteria.getState());query.append("%'");
            }
            if(!isEmpty(searchCriteria.getPin())) {
                query.append(" and pin like '%");
                query.append(searchCriteria.getPin());query.append("%'");
            }
            query.append(')');
            return query.toString();
    }
    
    private SearchCDResult findCandByAddressResults(Integer cuid){
            return (SearchCDResult)getHibernateTemplate().find(Queries.SEARCH_CAND_BY_ADDR_RESULTS,
                                                                                                  new Object[]{cuid,cuid}).get(0); 
    } 
    @Override
    public List<SearchCDResult>findCandidatesByAddress(final SearchCriteria searchCriteria, final int orgId) { 
        List<SearchCDResult>result = new ArrayList<SearchCDResult>();  
        List<Integer> candidateUIDs=(List<Integer>)getHibernateTemplate().execute(new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException { 
                    String query=formatCandidateByAddressQuery(searchCriteria,orgId);
                    System.out.println("address query="+query);
                    SQLQuery sqlQuery =session.createSQLQuery(query);
                    return sqlQuery.list(); 
                } 
            }); 
            for(Integer cuid:candidateUIDs) {
                result.add(findCandByAddressResults(cuid));
            }
        return result;
    } 
    
    private String formatCandidatesByPlacementQuery(SearchCriteria searchCriteria, int orgId) {
            StringBuffer query=new StringBuffer();
            query.append("select cuid from candidate c where orgId=");query.append(orgId);
            query.append(" and courseId=");query.append(searchCriteria.getCourseId());
            query.append(" and streamId=");query.append(searchCriteria.getStreamId());
            if(searchCriteria.getPassingYear()!=null) {
                query.append(" and passingYear=");query.append(searchCriteria.getPassingYear());
            }
            query.append(" and cuid in");
            query.append(" (select cuid from placement group by cuid,orgid having count(*) >=");
            query.append(searchCriteria.getNumPlacements());
            query.append(" and orgId=");query.append(orgId);   
            query.append(")");
            return query.toString();
    }   

    private SearchCDResult findCandByPlacementResults(Integer cuid){
            return (SearchCDResult)getHibernateTemplate().find(Queries.SEARCH_CAND_BY_ADDR_RESULTS,
                                                                                                  new Object[]{cuid,cuid}).get(0); 
    }
    
    @Override
    public List<SearchCDResult>findCandidatesByPlacement(final SearchCriteria searchCriteria, final int orgId) {
        List<SearchCDResult>result = new ArrayList<SearchCDResult>();  
        List<Integer>candidateUIDs =(List<Integer>)getHibernateTemplate().execute(new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException { 
                    String query=formatCandidatesByPlacementQuery(searchCriteria,orgId);
                    System.out.println("placement query="+query);
                    Query sqlQuery=session.createSQLQuery(query);  
                    return sqlQuery.list(); 
                } 
            });  
            for(Integer cuid:candidateUIDs) {
                result.add(findCandByPlacementResults(cuid));
            }
        return result;
    }
}
