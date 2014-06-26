package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
 * 26/1/2013
 */
public class SearchPlacementResult {
    private String candidateId ;
    private String firstName ;
    private String lastName ; 
    private String course ;
    private String stream ;
    private int passingYear ;
    private String primEmail;
    private String primCell;
    private Integer numPlacements;

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

    public int getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(int passingYear) {
        this.passingYear = passingYear;
    }

    public String getPrimEmail() {
        return primEmail;
    }

    public void setPrimEmail(String primEmail) {
        this.primEmail = primEmail;
    }

    public String getPrimCell() {
        return primCell;
    }

    public void setPrimCell(String primCell) {
        this.primCell = primCell;
    }

    public Integer getNumPlacements() {
        return numPlacements;
    }

    public void setNumPlacements(Integer numPlacements) {
        this.numPlacements = numPlacements;
    } 
    
}
