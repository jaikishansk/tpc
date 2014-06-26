package com.edu.tpc.service;

/**
 * Date: 21/9/11
 * @author Jaikishan
 */

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.edu.tpc.dao.UserDAO;
import com.edu.tpc.domain.User;

@Service("userService")
public class UserServiceImpl implements UserService
{
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }
    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    public List<User> findUserByIdPassword(String id, String pwd)
    {
        return userDAO.findUserByIdPassword(id, pwd);
    }
    @Override
    public void deleteUser(String userId)
    {
        userDAO.deleteUser(userId);
    }
    @Override
    public void updateLastLogin(String userId)
    {
        userDAO.updateLastLogin(userId);
    }
}
