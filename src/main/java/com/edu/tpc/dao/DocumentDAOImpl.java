package com.edu.tpc.dao;
/**
 *
 * @author Jaikishan
 * Date: 25/10/11
 */

import java.util.*;

import com.edu.tpc.domain.Document;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


@Repository("documentDAO")
@Transactional
public class DocumentDAOImpl extends HibernateDaoSupport
                             implements DocumentDAO
{
    @Autowired
    public void init(SessionFactory factory)
    {
        setSessionFactory(factory);
    }

    //TODO: set actual values
    private void initializeDocAuditInfo(Document doc)
    {
        doc.setCreatedBy("IT50010");
        Calendar cal = Calendar.getInstance();
        doc.setCreatedOn(cal.getTime());
        doc.setModifiedBy("IT50010");
        doc.setModifiedOn(cal.getTime());
    }

    @Override
    public void save(Document doc)
    {
            initializeDocAuditInfo(doc);
            getHibernateTemplate().save(doc);
    }
}
