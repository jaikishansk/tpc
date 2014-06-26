package com.edu.tpc.service; 

import java.util.List;

import org.springframework.stereotype.Service; 
import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Autowired;

import com.edu.tpc.dao.NotificationDAO;
import com.edu.tpc.domain.CandidateEmails;
import com.edu.tpc.domain.CandidateNotificationsLog;
import com.edu.tpc.domain.NCCriteria;

/**
 *
 * @author Jaikishan
 * Date: 21/10/12
 */

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService { 
    
        private NotificationDAO notificationDAO;
        
        private CandidateService candidateService;
        private EmailService emailService;
        private SimpleMailMessage templateMessage; 

        public NotificationDAO getNotificationDAO() {
            return notificationDAO;
        }

        @Autowired 
        public void setNotificationDAO(NotificationDAO notificationDAO) {
            this.notificationDAO = notificationDAO;
        }
    
        public CandidateService getCandidateService() {
            return candidateService;
        } 
        
        @Autowired
        public void setCandidateService(CandidateService candidateService) {
            this.candidateService = candidateService;
        } 

        public EmailService getEmailService() {
            return emailService;
        }

        @Autowired
        public void setEmailService(EmailService emailService) {
            this.emailService = emailService;
        } 
    
        public SimpleMailMessage getTemplateMessage() {
               return templateMessage;
       }

        @Autowired
        public void setTemplateMessage(SimpleMailMessage templateMessage) {
            this.templateMessage = templateMessage;
        } 
        
        private void createNotificationLog(int uid, String[] emails, String message,int success, int orgId){
                CandidateNotificationsLog log=new CandidateNotificationsLog();
                log.setCandidateUID(uid); 
                log.setNotificationDate(new java.util.Date()); 
                log.setNotifierEmailId(templateMessage.getFrom());
                log.setOrgId(orgId);
                log.setPrimaryEmailId(emails[0]);
                if(emails.length>1) {
                    log.setSecondaryEmailId(emails[1]);
                }
                log.setMessage(message);
                log.setStatus(success);
                notificationDAO.logNotification(log);
        }
        private void notifyByEmailSingleCandidate(NCCriteria ncCriteria,int orgId) throws Exception{ 
                int candidateUID=candidateService.getCandidateUID(ncCriteria.getCandidateId(), orgId);
                String[] candEmailIds=candidateService.findCandidateEmailIds(candidateUID);
                try{ 
                    emailService.sendEMail(templateMessage.getFrom(), candEmailIds, templateMessage.getSubject(), 
                                                            ncCriteria.getMessage());
                    createNotificationLog(candidateUID,candEmailIds,ncCriteria.getMessage(),1,orgId);
            }catch(Exception e){
                    createNotificationLog(candidateUID,candEmailIds,ncCriteria.getMessage(),0,orgId);
                    throw e;
            }
        }
        
        private void notifyByEmailAllClass(NCCriteria ncCriteria,int orgId) throws Exception { 
                List<CandidateEmails>classEmails=candidateService.findEmailsForCriteria(ncCriteria,orgId);
                String[] emailIds=null;
                int candidateUID=0;
                try {
                        for(CandidateEmails cEmail:classEmails) {
                            emailIds=cEmail.getEmailIds();
                            emailService.sendEMail(templateMessage.getFrom(), emailIds, templateMessage.getSubject(), 
                                                                ncCriteria.getMessage());
                            candidateUID=cEmail.getCandidateUID();
                            createNotificationLog(candidateUID,emailIds,ncCriteria.getMessage(),1,orgId);
                        }
                } catch(Exception e) {
                   createNotificationLog(candidateUID,emailIds,ncCriteria.getMessage(),0,orgId);
                   throw e;
                } 
        }
        
        @Override
        public void notifyCandidates(int orgId,NCCriteria ncCriteria)  throws Exception {
            try{
                    if(ncCriteria.getNotificationScope()==1) {
                        notifyByEmailSingleCandidate(ncCriteria,orgId);
                    }
                    else{
                        notifyByEmailAllClass(ncCriteria,orgId);
            }
            }catch(Exception e){
                System.out.println(e);
                throw e;
            }
        }
        
        @Override
        public int findNCPotentialMatch(NCCriteria ncCriteria,int orgId){     
                return candidateService.findNCPotentialMatch(ncCriteria, orgId); 
        }
}
