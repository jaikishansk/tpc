/*
 * Date: 2/5/12
 * Author: Jaikishan
 */

package com.edu.tpc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;
import javax.servlet.http.*;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler; 

/**
 *
 * @author Jaikishan
 */
@Controller
public class BaseController
{
    private I18NMessageUtil i18NMessageUtil;

    public I18NMessageUtil getI18NMessageUtil() {
        return i18NMessageUtil;
    }

    @Autowired
    public void setI18NMessageUtil(I18NMessageUtil i18NMessageUtil) {
        this.i18NMessageUtil = i18NMessageUtil;
    } 

    private final Log logger = LogFactory.getLog(getClass());

    protected Map<Integer,String> mergeMaps(Map<Integer,String> first,
                                                  Map<Integer,String> second)
    {
        Map<Integer,String> result=new HashMap<Integer,String>();
        result.putAll(first);
        result.putAll(second);
        return result;
    }
    protected void checkSession(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Verifying session...");
        HttpSession session=request.getSession(true); 
        try {  
            if((session.getAttribute("userId")==null) ||
                       (session.getAttribute("userId").equals(""))) { 
                logger.info("Invalid session. Redirecting to login page...");
                response.sendRedirect("login.html");
            }
        }catch(Exception e) { 
            logger.fatal(i18NMessageUtil.getMessage("fatal.message"));
            logger.fatal(e); 
            session.invalidate(); 
        }
    }
    @ExceptionHandler(Exception.class)
    protected ModelAndView handleException(Exception exception)
    {
        String errMessage="Fatal Error has occurred. Please contact administrator.";
        ModelMap model = new ModelMap(); 
        model.addAttribute("message", errMessage);
        logger.fatal(errMessage);
        logger.fatal(exception);
        return new ModelAndView("error/ErrorPage", model);
    }
    @ExceptionHandler(DataAccessException.class)
    protected ModelAndView handleORFException (Exception exception)
    {
        ModelMap model = new ModelMap();
        model.addAttribute("class", exception.getClass());
        model.addAttribute("message", "No matching record with given id was found.");
        logger.fatal(exception);
        return new ModelAndView("error/ErrorPage", model);
    } 
    protected void redirectToLoginPage(HttpServletRequest request,
                    HttpServletResponse response)
    {
        logger.info("Redirecting to login page...");
        checkSession(request,response);
    }
}
