package com.edu.tpc.service;

/**
 * Date: 21/9/11
 * @author Jaikishan
 */

import java.util.*;

import com.edu.tpc.domain.User;

public interface UserService
{
    public List<User> findUserByIdPassword(String id, String pwd);
    public void updateLastLogin(String userId);
    public void deleteUser(String userId);
}
