package com.edu.tpc.domain;

// Date: 12 Jan 2013

/**
 *
 * @author Jaikishan
 * 
 */
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table; 
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="CandidateNotificationsLog") 
public class CandidateNotificationsLog implements java.io.Serializable { 
    @Id
    @Column(name="id")
    private int id;
    @Column(name="Cuid")
    private int candidateUID;
    @Column(name="orgId")
    private int orgId;  
    @Column(name="CandidatePrimaryEmailId") 
    private String primaryEmailId;
    @Column(name="CandidateSecondaryEmailId") 
    private String secondaryEmailId;
    @Column(name="NotificationDate")  
    @Temporal(TemporalType.DATE) 
    private Date notificationDate; 
    @Column(name="NotifierEmailId")
    private String notifierEmailId;
    @Column(name="Message")
    private String message;
    @Column(name="Status")
    private int status;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCandidateUID() {
        return candidateUID;
    }

    public void setCandidateUID(int candidateUID) {
        this.candidateUID = candidateUID;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getPrimaryEmailId() {
        return primaryEmailId;
    }

    public void setPrimaryEmailId(String primaryEmailId) {
        this.primaryEmailId = primaryEmailId;
    }

    public String getSecondaryEmailId() {
        return secondaryEmailId;
    }

    public void setSecondaryEmailId(String secondaryEmailId) {
        this.secondaryEmailId = secondaryEmailId;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    } 

    public String getNotifierEmailId() {
        return notifierEmailId;
    }

    public void setNotifierEmailId(String notifierEmailId) {
        this.notifierEmailId = notifierEmailId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }  

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    } 
}
