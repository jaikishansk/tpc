/*
 * Date: 14/5/12
 *  
 */

package com.edu.tpc.dao;

/**
 *
 * @author Jaikishan
 */

import java.util.*;
import java.sql.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.ProjectionList;
import org.springframework.stereotype.Repository;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edu.tpc.domain.Candidate;
import com.edu.tpc.domain.EligibilityReport;
import com.edu.tpc.domain.PECriteria;
import com.edu.tpc.domain.Qualification;

import org.hibernate.FetchMode;


@Repository("dashboardDAO")
@Transactional
public class DashboardDAOImpl extends HibernateDaoSupport
                              implements DashboardDAO
{
    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    public void init(SessionFactory factory)
    {
        setSessionFactory(factory);
    }

    private void addCourseStreamConstraint(DetachedCriteria candidate,Integer[] streamIds,
                                     int index)
    {
        int[] courseIds={1,2,3,100,200};
        
        System.out.println("Adding course restriction...");
        candidate.add(Restrictions.eq("q.course",courseIds[index]));
        candidate.add(Restrictions.conjunction());

        if(streamIds[index]==100&&index==0)
        {
            System.out.println("Adding ssc in stream constraint...");
            candidate.add(Restrictions.in("q.stream",new Object[]{1,2,3}));
        }
        else if(streamIds[index]==100&&index==1)
        {
            System.out.println("Adding hsc in stream constraint...");
            candidate.add(Restrictions.in("q.stream",new Object[]{1,2,3}));
        }
        else if(streamIds[index]==100&&index==2)
        {
            candidate.add(Restrictions.in("q.stream",new Object[]{50,51,52,53,54}));
        }
        else if(streamIds[index]==100&&index==3)
        {
            candidate.add(Restrictions.in("q.stream",new Object[]{50,51,52,53,54}));
        }
        else if(streamIds[index]==100&&index==4)
        {
            candidate.add(Restrictions.in("q.stream",new Object[]{50,51,52,53,54}));
        }
        else 
        { 
            System.out.println("Adding eq stream constraint...");
            candidate.add(Restrictions.eq("q.stream",streamIds[index] ));
        }
    }

    @Override
    public List<EligibilityReport> getEligibleCandidates(PECriteria pec)
    {
        /*
        Integer[] streamIds=pec.getStreamIds();
        Integer[] passingYears=pec.getPassingYears();
        Double[] startPercents=pec.getStartPercents();
        Double[] endPercents=pec.getEndPercents();
        Integer[] liveKTs=pec.getLiveKTs();
        int[] courses={1,2,3,100,200};

        int i;
        boolean toAddCSInfo=false;;
        StringBuffer query=new StringBuffer();
        query.append("select c.candidateId,c.firstName,c.lastName,c.course,c.stream,q.percentMarks from ");
        query.append("Candidate c, Qualification q where c.candidateId in ");
        for(i=4;i>=0;i--)
        {
            toAddCSInfo=false; 
            if(passingYears[i]!=null||startPercents[i]!=null ||
                                      endPercents[i]!=null||liveKTs[i]!=null)
            {
                query.append("(select q.primaryKey.candidateId from Qualification q where ");
            }
            else
                continue;
            if(passingYears[i]!=null)
            { 
                query.append("q.passingYear='");query.append(passingYears[i]);query.append("'");
                toAddCSInfo=true;
            }
            if(startPercents[i]!=null)
            {
                if(passingYears[i]!=null)
                    query.append(" and ");
                query.append("q.percentMarks>='");query.append(startPercents[i]);query.append("'");
                toAddCSInfo=true;
            }
            if(endPercents[i]!=null)
            {
                if(startPercents[i]!=null)
                    query.append(" and ");
                query.append("q.percentMarks<='");query.append(endPercents[i]);query.append("'");
                toAddCSInfo=true;
            }
            if(liveKTs[i]!=null)
            {
                if(endPercents[i]!=null)
                    query.append(" and ");
                query.append("q.liveKTs='");query.append(liveKTs[i]);query.append("'");
                toAddCSInfo=true;
            }
            if(toAddCSInfo)
            {
                query.append(" and ");
                query.append("q.course='");query.append(courses[i]);query.append("'");
                query.append(" and ");
                if(streamIds[i]==100)
                {
                    if(i==4||i==3||i==2)
                        query.append("q.stream in(50,51,52,53,54,55)");
                    else if (i==1||i==0)
                        query.append("q.stream in(1,2,3)");
                }
                else
                {
                    query.append("q.stream='");query.append(streamIds[i]);query.append("'");
                }
                if(i!=0)
                    query.append(" and q.primaryKey.candidateId in ");
            }
        }
        query.append(")))))");
        System.out.println("Query ="+query.toString());
        List results=getHibernateTemplate().find(query.toString());
        List<EligibilityReport>finalResult=new ArrayList<EligibilityReport>();
        for(int j=0;j<results.size();j++)
        {
            Object[] result=(Object[])results.get(j);
            finalResult.add(
                                new EligibilityReport((String)result[0],(String)result[1],
                                  (String)result[2],(Integer)result[3],
                                  (Integer)result[4],(Double)result[5])
                            );
        }
        return finalResult;
    }
    */
        return null;
    }
}
