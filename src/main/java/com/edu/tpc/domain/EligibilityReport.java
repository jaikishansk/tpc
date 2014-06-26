/*
 * Date: 14/5/12
 */

package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
 */   

public class EligibilityReport {
    private String candidateId ;
    private String firstName ;
    private String lastName ;
    private String course;
    private String stream ;
    private Double percentage;
    private int liveKT;
    private int placements;
    private String emailId;
    private String mobileNumber;

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public int getLiveKT() {
        return liveKT;
    }

    public void setLiveKT(int liveKT) {
        this.liveKT = liveKT;
    }

    public int getPlacements() {
        return placements;
    }

    public void setPlacements(int placements) {
        this.placements = placements;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    } 
}
