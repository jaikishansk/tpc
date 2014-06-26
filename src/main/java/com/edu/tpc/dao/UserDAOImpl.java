package com.edu.tpc.dao;

/**
 *
 * Date: 21/9/11
 * @author Jaikishan
 */
import java.sql.*;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport; 

import com.edu.tpc.domain.User;

@Repository("userDAO")
public class UserDAOImpl extends HibernateDaoSupport
                         implements UserDAO
{
    @Autowired
    public void init(SessionFactory factory)
    {
        setSessionFactory(factory);
    }
    @Override
    public List<User> findUserByIdPassword(String userId, String password)
    {
        List<User> result = getHibernateTemplate().
                            find("from User user where userId='"+
                                  userId+"'"+ "and password='"+
                                  password+"'") ; 
        return result;
    }
    @Override
    public void deleteUser(String userId)
    {
        User user = new User();
        user.setUserId(userId);
        user.setIsActive(false);
        getHibernateTemplate().update(user);
    }
    @Override
    public void updateLastLogin(final String userId)
    {
        getHibernateTemplate().execute(new HibernateCallback() 
        {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, 
                                                                SQLException
            {
                Query query = session.createQuery("update User set lastLogin='"+
                        new java.sql.Timestamp(System.currentTimeMillis())
                        +"' where userId=?");
                query.setParameter(0, userId);
                query.executeUpdate();
                return null;
            }
        }); 
    }
}
