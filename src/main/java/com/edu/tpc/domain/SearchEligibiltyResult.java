package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
 * 19/01/2013
 */
public class SearchEligibiltyResult {
    
    private String candidateId;
    private String firstName;
    private String lastName;
    private Double percentage;
    private String emailId;
    private String cellNumber;

    public SearchEligibiltyResult(){}

    public SearchEligibiltyResult(String candidateId, String firstName, String lastName, Double percentage, 
                                          String emailId, String cellNumber) {
        this.candidateId = candidateId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.percentage = percentage;
        this.emailId = emailId;
        this.cellNumber = cellNumber;
    } 
    
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

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }  
    
}
