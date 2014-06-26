package com.edu.tpc.dao;
/**
 *
 * @author Jaikishan
 * Date: 18/10/11
 */
import java.util.*;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edu.tpc.domain.BaseInfo;
import com.edu.tpc.domain.NonSecondaryCourseInfo;
import com.edu.tpc.domain.NonSecondaryStreamInfo;

@Repository("coursesDAO")
public class CoursesDAOImpl  extends HibernateDaoSupport
                             implements CoursesDAO
{ 
    @Autowired
    public void init(SessionFactory factory)
    { 
        setSessionFactory(factory);
    }

    private Map<Integer,String> listToMap(List<? extends BaseInfo> sBaseInfos)
    {
        Map<Integer,String> result=new HashMap<Integer,String>();
        for (BaseInfo sBaseInfo : sBaseInfos)
            result.put(sBaseInfo.getId(), sBaseInfo.getName());
        return result;
    }
    @Override
    public Map<Integer,String> findSecondaryCourses()
    { 
        return listToMap(getHibernateTemplate().find("from SecondaryCourseInfo"));
    }
    @Override
    public Map<Integer,String> findSecondaryStreams()
    {
        return listToMap(getHibernateTemplate().find("from SecondaryStreamInfo"));
    } 
    @Override
    public Map<Integer,String> findUGCourses(int orgId)
    {
        // ug courseId >=100 and courseId<200
        String query="select distinct new NonSecondaryCourseInfo(nsc.id, nsc.name) from NonSecondaryCourseInfo nsc, CoursesStream cs "
                + "where cs.primaryKey.courseId>=100 and cs.primaryKey.courseId<200 and cs.primaryKey.courseId=nsc.id and cs.primaryKey.orgId=?";
        return listToMap(getHibernateTemplate().find(query, orgId));
    }
    @Override
    public Map<Integer,String> findPGCourses(int orgId)
    {
        // pg courseId >=200 and courseId<300 
        String query="select distinct new NonSecondaryCourseInfo(nsc.id, nsc.name) from NonSecondaryCourseInfo nsc, CoursesStream cs "
                + "where cs.primaryKey.courseId>=200 and cs.primaryKey.courseId<300 and cs.primaryKey.courseId=nsc.id and cs.primaryKey.orgId=?";
        return listToMap(getHibernateTemplate().find(query, orgId));
    }
    @Override
    public Map<Integer,String> findUGStreams(int orgId)
    {
        String query="select distinct new NonSecondaryStreamInfo(nss.id, nss.name) from NonSecondaryStreamInfo nss, CoursesStream cs "
                + "where cs.primaryKey.courseId>=100 and cs.primaryKey.courseId<200 and cs.primaryKey.streamId=nss.id and cs.primaryKey.orgId=?";
        return listToMap(getHibernateTemplate().find(query, orgId));
    }
    @Override
    public Map<Integer,String> findPGStreams(int orgId)
    {
        String query="select distinct new NonSecondaryStreamInfo(nss.id, nss.name) from NonSecondaryStreamInfo nss, CoursesStream cs "
                + "where cs.primaryKey.courseId>=200 and cs.primaryKey.courseId<300 and cs.primaryKey.streamId=nss.id and cs.primaryKey.orgId=?";
        return listToMap(getHibernateTemplate().find(query, orgId));
    } 
    @Override
    public String findCourseName(int courseId)
    { 
        return getHibernateTemplate().load(NonSecondaryCourseInfo.class,courseId).getName();
    }
    @Override
    public String findStreamName(int streamId)
    {
        return getHibernateTemplate().load(NonSecondaryStreamInfo.class,streamId).getName();
    }
}
