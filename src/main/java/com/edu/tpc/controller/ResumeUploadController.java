package com.edu.tpc.controller;

 
import com.edu.tpc.domain.ResumeUpload;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Jaikishan
 * 10 Feb 2013
 */

@Controller
public class ResumeUploadController extends BaseController { 
     private final Log logger = LogFactory.getLog(getClass());
     
    @RequestMapping(value="uploadresume.html", method=RequestMethod.GET)
    public String showuploadResumeForm(ModelMap model,HttpServletRequest request,
                                   HttpServletResponse response) { 
        checkSession(request,response);
        logger.info("showing resume upload form...");
        model.addAttribute("uploadForm", new ResumeUpload());
        return "candidate/UploadResume";
    }
    @RequestMapping(value="uploadresume.html", method=RequestMethod.POST)
    public String uploadResume(@ModelAttribute(value="uploadForm") ResumeUpload form, ModelMap model,
                                                    HttpServletRequest request, HttpServletResponse response) { 
        checkSession(request,response);
        logger.info("uploading resume...");
        FileOutputStream outputStream = null;
        String filePath = "d:/resumes/" + form.getFile().getOriginalFilename();
        try { 
                outputStream = new FileOutputStream(new File(filePath)); 
                outputStream.write(form.getFile().getFileItem().get()); 
                outputStream.close(); 
            } catch (Exception e) { 
                logger.info("Error while uploading resume..."); 
                model.addAttribute("candidateError","Error in uploading resume. Please try later.");
                return  "candidate/CandidateResult";
            }  
            model.addAttribute("candidateStatus","Resume uploaded successfully."); 
            return "candidate/CandidateResult";
    }
}
