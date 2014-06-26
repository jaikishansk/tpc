package com.edu.tpc.controller;
/**
 *
 * @author Jaikishan
 */

import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView; 

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import com.edu.tpc.domain.User ;

@Controller
@RequestMapping("logout.html")
public class LogoutController
{ 
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response,ModelMap map)
    {
        ModelAndView view = new ModelAndView("logout/Logout");
        request.getSession().invalidate();
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setDateHeader("Expires", 0);
        //map.addAttribute("user",new User());
        return view;
    }
}
