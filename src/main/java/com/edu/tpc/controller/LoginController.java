package com.edu.tpc.controller;

/**
 *
 * @author Jaikishan
 * Date: 10 Sept 2011
 */

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import com.edu.tpc.domain.User;
import com.edu.tpc.domain.UserType;
import com.edu.tpc.service.ConfigInfoService;
import com.edu.tpc.service.UserService;

@Controller
public class LoginController extends BaseController {
    private UserService userService ;
    private ConfigInfoService configInfoService;
    private I18NMessageUtil i18NMessageUtil;
    private final Log logger = LogFactory.getLog(getClass());

    public UserService getUserService() {
        return userService;
    }
    @Autowired
    public void setUserService(UserService userService) {
       this.userService = userService;
    }

    public ConfigInfoService getConfigInfoService() {
        return configInfoService;
    }

    @Autowired
    public void setConfigInfoService(ConfigInfoService configInfoService) {
        this.configInfoService = configInfoService;
    } 
    
    public I18NMessageUtil getI18NMessageUtil() {
        return i18NMessageUtil;
    }

    @Autowired
    public void setI18NMessageUtil(I18NMessageUtil i18NMessageUtil) {
        this.i18NMessageUtil = i18NMessageUtil;
    }

    @RequestMapping(value="login.html",method = RequestMethod.GET)
    public String showLoginForm(ModelMap model,HttpServletRequest request, HttpServletResponse response) { 
        logger.info("Redirecting to login page...");
        model.addAttribute("user",new User());
        return "login/Login";
    }
    @RequestMapping(value="login.html",method = RequestMethod.POST)
    public String processLogin(@Valid User user, BindingResult result,
            ModelMap model,HttpServletRequest request,HttpServletResponse response) { 
        //checkSession(request,response);
        if(result.hasErrors()) { 
                return "login/Login";
        }
        logger.info("Finding user with given credentials...");
        List<User> users=userService.findUserByIdPassword(user.getUserId(),
                                                          user.getPassword());
        if(users.size()==1) { 
            logger.info("User with given credentials found...");
            User aUser=(User)users.get(0);
            HttpSession session =  request.getSession(true);

            session.setAttribute("userId", aUser.getUserId());
            session.setAttribute("userType", new Integer(aUser.getUserType()));
            session.setAttribute("orgId", new Integer(aUser.getOrgId()));
            session.setAttribute("orgName",configInfoService.getOrganizationName(aUser.getOrgId()));
            
            // This is used to prepopulate 'roll no' field in candidate registration
            if(aUser.getUserType()==UserType.UGSTUDENT ||
               aUser.getUserType()==UserType.PGSTUDENT ) {
                    session.setAttribute("candidateId", aUser.getUserId()); 
            }
            userService.updateLastLogin(user.getUserId());
            logger.info("Redirecting to home page...");
            return "login/Home";
        }
        else
        {
            model.addAttribute("loginError", i18NMessageUtil.getMessage("login.invalidcredentials"));
            logger.info("User with given credentials not found. Redirecting to error page...");
            return "login/Login";
        }
    } 
}
