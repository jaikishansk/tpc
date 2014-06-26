/*
 * Date: 4/5/12
 * Author: Jaikishan 
 */

package com.edu.tpc.domain;

import java.util.Date;

/**
 *
 * @author Jaikishan
 */

public class PlacedCandidateResult {
    private String candidateId ;
    private String firstName;
    private String lastName;
    private String course;
    private String stream ;
    private String company ;
    private Date placementDate;

    public PlacedCandidateResult(){}

    public PlacedCandidateResult(String cId) { 
        this.candidateId=cId;
    }
    public PlacedCandidateResult(String cId, String firstName, String lastName, 
                    int courseId,int streamId, int companyId, Date placementDate) { 
        this.candidateId=cId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.course=CourseStreamInfo.getCourse(courseId);
        this.stream=CourseStreamInfo.getStream(streamId);
        company=Integer.toString(companyId);
        this.placementDate=placementDate;
    } 

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public Date getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(Date placementDate) {
        this.placementDate = placementDate;
    } 
    
}
