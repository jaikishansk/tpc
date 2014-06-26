 package com.edu.tpc.service;

/**
 *
 * @author Jaikishan
 */
 
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

 @Service("emailService")
public class EmailServiceImpl implements EmailService { 
    
        private JavaMailSender mailSender; 

        
        public JavaMailSender getMailSender() {
            return mailSender;
        }

        @Autowired
        public void setMailSender(JavaMailSender mailSender) {
            this.mailSender = mailSender;
        } 
 
        @Override
        public void sendEMail(String sender, String[]recepient, String subject, String message) throws Exception {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                helper.setTo(recepient);
                helper.setText(message, true);
                helper.setFrom(sender);
                helper.setSubject(subject);		
                mailSender.send(mimeMessage);
        }
}
