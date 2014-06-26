package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
 * 09th Feb 2013
 */

public class NotifyCompany {
    private String emailId;
    private String message;
    private String subject;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
     
}
