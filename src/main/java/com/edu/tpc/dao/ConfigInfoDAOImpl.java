/*
 * Date: 6/7/12
 * Author: Jaikishan
 */

package com.edu.tpc.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.edu.tpc.domain.ConfigInfo;

import java.util.List;

@Repository("configInfoDAO")
@Transactional 
public class ConfigInfoDAOImpl extends HibernateDaoSupport implements ConfigInfoDAO { 
    private final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    public void init(SessionFactory factory) { 
        setSessionFactory(factory);
    }

    @Override
    public int findMaxPlacementOffers(int orgId) { 
        logger.info("Getting max placement offers allowed.."); 
//        List<ConfigInfo> result=getHibernateTemplate().find("from ConfigInfo where OrgId=?", 
//                new Object[]{orgId});
       // return result.size()>0?result.get(0).getMaxPlacementOffers():0; 
       return DataAccessUtils.intResult(getHibernateTemplate().
                find("select " + "maxPlacementOffers from ConfigInfo where OrgId=?",new Object[]{orgId}));
    }
    @Override
     public String getOrganizationName(int orgId) {
        logger.info("Getting org name.."); 
//        List<ConfigInfo> result=getHibernateTemplate().find("select OrgName from ConfigInfo where OrgId=?", 
//                new Object[]{orgId});
        return (String)DataAccessUtils.singleResult(getHibernateTemplate().
                find("select " + "orgName from ConfigInfo where OrgId=?",new Object[]{orgId}));
         //return result.get(0).getOrgName();
    }
}
