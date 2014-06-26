package com.edu.tpc.dao;

/**
 *
 * @author Jaikishan
 * Date: 21/10/12
 */

import com.edu.tpc.domain.CandidateNotificationsLog;
import com.edu.tpc.domain.NCCriteria;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport; 

@Repository("notificationDAO")
@Transactional
public class NotificationDAOImpl  extends HibernateDaoSupport implements NotificationDAO { 
    private final Log logger = LogFactory.getLog(getClass());
     
    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }   
    
    @Override
    public void logNotification(CandidateNotificationsLog log){
        logger.info("Logging candidate notification status...");
        getHibernateTemplate().save(log);
    }
}
 