package com.edu.tpc.dao;

/**
 *
 * @author Jaikishan
 * Date: 19/10/11
 */

import java.util.*;
import java.sql.*;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edu.tpc.domain.CRResult;
import com.edu.tpc.domain.PlacementInfo;
import com.edu.tpc.domain.PlacementInfoPK;
import com.edu.tpc.domain.PlacementInfoResult;

@Repository("placementDAO")
@Transactional 
public class PlacementDAOImpl extends HibernateDaoSupport implements PlacementDAO{ 
    @Autowired
    public void init(SessionFactory factory) { 
        setSessionFactory(factory);
    }

    private void initializePIAuditInfo(PlacementInfo candidatePI) {  
        Calendar cal = Calendar.getInstance();
        candidatePI.setCreatedOn(cal.getTime()); 
        candidatePI.setModifiedOn(cal.getTime());
    }
    @Override
    public void savePlacementInfo(PlacementInfo pi) {  
        getHibernateTemplate().saveOrUpdate(pi); 
    }
    @Override
    public void saveEditedPI(int candidateUID,ArrayList<PlacementInfo> pi) { 
        deletePI(candidateUID);
        getHibernateTemplate().saveOrUpdateAll(pi);
    }
    @Override
    public int deletePI(final int candidateUID) { 
        return ((Integer)getHibernateTemplate().execute(new HibernateCallback() { 
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException { 
                Query query = session.createQuery("delete from PlacementInfo pi where "
                        + "pi.primaryKey.candidateUID=?" );
                query.setParameter(0, candidateUID);  
                return new Integer(query.executeUpdate());
            }
        })).intValue();
    }
    @Override
    public List<PlacementInfo> findPlacements(int candidateUID) { 
        logger.info("candidateUId for searching placements="+ candidateUID);
        List result=(List)getHibernateTemplate().find("from PlacementInfo pi where pi.primaryKey.candidateUID=? ",
                new Object[]{candidateUID});
        logger.info("found pi for candidateUId..."+ candidateUID);
        return result;
                                            
    }
    @Override
    public List<PlacementInfoResult>findPIForEdit(String candidateId) { 
        System.out.println("Finding pi for edit...");
        return getHibernateTemplate().find(Queries.GET_PI_FOR_EDIT,
                                            candidateId); 
    }
    @Override
    public List getCampusCompanies(int passingYear) { 
        try { 
        return getHibernateTemplate().find(Queries.GET_PLACEMENT_COMPANIES,passingYear);
        }catch(Exception e) { 
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<CRResult> findConsolidatedPInfo(int orgId, int passingYear) { 
        try { 
            Object[] args = {orgId, passingYear};
            return getHibernateTemplate().find(Queries.GET_UG_ALL_STREAMS_PI,args);
        }catch(Exception e) { 
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int findNumOfPlacements(int candidateUID) throws DataAccessException { 
        logger.info("Getting placement info about candidate with cuid "+candidateUID); 
        return DataAccessUtils.intResult(getHibernateTemplate().
                find("select " + "count(*) from PlacementInfo pi where pi.primaryKey.candidateUID=?",
                                        new Object[]{candidateUID})); 
    }
}
