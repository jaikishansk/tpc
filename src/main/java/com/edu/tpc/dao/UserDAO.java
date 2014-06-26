package com.edu.tpc.dao;

import java.util.*;

import com.edu.tpc.domain.User;

public interface UserDAO
{
    public List<User> findUserByIdPassword(String id,String pwd);
    public void updateLastLogin(String userId);
    public void deleteUser(String userId);
}
