/*
 * Date: 14/5/12
 *
 */
package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
 */

public class SearchCDResult { 
    private String candidateId ;
    private String firstName ;
    private String lastName ; 
    private String course ;
    private String stream ;
    private int passingYear ;
    private String primEmail;
    private String primCell;

    public SearchCDResult(){}
    
    public SearchCDResult(String cId, String fName, String lName, int courseId,
                int streamId, int pY, String pEmail, String pCell) { 
        this.candidateId=cId;
        this.firstName=fName;
        this.lastName=lName;
        this.course=CourseStreamInfo.getCourse(courseId);
        this.stream=CourseStreamInfo.getStream(streamId);
        this.passingYear=pY;
        this.primEmail=pEmail;
        this.primCell=pCell;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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

    public int getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(int passingYear) {
        this.passingYear = passingYear;
    }

    public String getPrimCell() {
        return primCell;
    }

    public void setPrimCell(String primCell) {
        this.primCell = primCell;
    }

    public String getPrimEmail() {
        return primEmail;
    }

    public void setPrimEmail(String primEmail) {
        this.primEmail = primEmail;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }  
}
