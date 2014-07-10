package com.edu.tpc.dao;
/**
 *
 * @author Jaikishan
 * Date: 22/10/11
 */

import java.util.*;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository; 
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edu.tpc.domain.company.Company;
import com.edu.tpc.domain.company.CompanyContact;

import java.sql.SQLException;

import javax.validation.ConstraintViolationException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

@Repository("companyDAO")
@Transactional
public class CompanyDAOImpl extends HibernateDaoSupport implements CompanyDAO { 
    
    @Autowired
    public void init(SessionFactory factory) { 
        setSessionFactory(factory);
    } 
    
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Company company) {   
        logger.info("Saving company with company name '"+company.getCompanyName()+"'...");   
        //getHibernateTemplate().merge(company);
        getHibernateTemplate().save(company);
        logger.info("Successfully saved company with company name '"+company.getCompanyName()+"'...");  
    }
    
    @Override
    public String getCompanyName(int companyId) { 
        Company company=(Company)getHibernateTemplate().get(Company.class, companyId);
        return company.getCompanyName();
    }
    @Override
    public Map<Integer,String> findAllByOrgId(int orgId) { 
        Map<Integer, String> allCompanies=new HashMap<Integer,String>();
        List result=getHibernateTemplate().find("select companyId, companyName from Company where OrgId=?", 
                                                                new Object[]{orgId});
        for(Object row:result) { 
            Object[] rowArray=(Object[])row;
            allCompanies.put((Integer)rowArray[0],(String)rowArray[1]);
        } 
        return allCompanies;
    }
    @Override
    public Company getCompany(int companyId) { 
        return getHibernateTemplate().get(Company.class, companyId);
    }
    @Override
     public int getNextCompanyID(final int orgId) {
          List result=(List)getHibernateTemplate().execute(new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException { 
                   Query q = session.createSQLQuery("select get_sequence_next(:seqname,:orgId)")
                            .setParameter("seqname", "company_seq").setParameter("orgId", orgId);
                  
                    return q.list();
                  }
              }); 
        return ((Integer)result.get(0)).intValue();
     }
    @Override
     public Company findCompanyByName(String name, int orgId) { 
        List<Company> result=getHibernateTemplate().find("from Company where companyName=? and orgId=?", 
                                                                         new Object[]{name, orgId});
        return result.size()>0?result.get(0):null;
    }
    @Override
     public void update(Company company) {
        getHibernateTemplate().update(company);
    }
    
    @Override
    public void deleteCompany(final String companyName, final int orgId) {
       getHibernateTemplate().execute(new HibernateCallback() { 
           @Override
           public Object doInHibernate(Session session) throws HibernateException,SQLException { 
               Query query = session.createQuery("delete from Company where companyName=? and orgId=?");
               query.setParameter(0, companyName); 
               query.setParameter(1, orgId); 
               int rowsUpdated=query.executeUpdate();
               if(rowsUpdated==0){
                   throw new DataIntegrityViolationException("error");
               } 
               return new Integer(rowsUpdated);
           };
        });
    }
}
