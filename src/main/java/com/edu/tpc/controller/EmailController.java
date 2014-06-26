package com.edu.tpc.controller; 
   
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
import com.edu.tpc.service.EmailService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
 
@Controller
public class  EmailController { 
    
        private static final Logger logger = LoggerFactory.getLogger(EmailController.class);    
        
        private EmailService emailService;

        public EmailService getEmailService() {
            return emailService;
        }

        @Autowired
        public void setEmailService(EmailService emailService) {
            this.emailService = emailService;
        }   
        
        public void sendEmail(String sender, String[]recepient, String subject, String message) 
                throws Exception { 
            emailService.sendEMail(sender,recepient,subject,message);
    }
}
