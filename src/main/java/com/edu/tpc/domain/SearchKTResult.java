package com.edu.tpc.domain;

/**
 *  20/1/2013
 * @author Jaikishan
 */

public class SearchKTResult {
        private String candidateId ;
        private String firstName ;
        private String lastName ; 
        private Integer liveKT;
        private Integer pastKT;
        private String KTSubjects;
        private String PrimaryEmail;
        private String PrimaryCellNo;

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

    public Integer getLiveKT() {
        return liveKT;
    }

    public void setLiveKT(Integer liveKT) {
        this.liveKT = liveKT;
    }

    public Integer getPastKT() {
        return pastKT;
    }

    public void setPastKT(Integer pastKT) {
        this.pastKT = pastKT;
    }

    public String getKTSubjects() {
        return KTSubjects;
    }

    public void setKTSubjects(String KTSubjects) {
        this.KTSubjects = KTSubjects;
    } 

    public String getPrimaryEmail() {
        return PrimaryEmail;
    }

    public void setPrimaryEmail(String PrimaryEmail) {
        this.PrimaryEmail = PrimaryEmail;
    } 

    public String getPrimaryCellNo() {
        return PrimaryCellNo;
    }

    public void setPrimaryCellNo(String PrimaryCellNo) {
        this.PrimaryCellNo = PrimaryCellNo;
    } 
}
