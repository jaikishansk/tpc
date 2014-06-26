/*
 * Date: 13/5/12
 */

package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
 */

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class SearchCriteria { 
    private Integer placementYear ;
    private String firstName ;
    private String lastName;
    private String candidateId ;
    private Integer courseId;
    private Integer streamId;
    private Integer passingYear ;
    private Double startPercentage;
    private Double endPercentage;
    private Integer liveKTs ;
    private Integer pastKTs;
    private String univ;
    private String primEmail;
    private String primCell;
    private Double sscPercentage;
    private Double hscPercentage;
    private Double ugPercentage;
    private Double pgPercentage;
    @Temporal(TemporalType.DATE)
    private Date dob;
    
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pin;
    private Integer numPlacements;
    
    public Integer getPlacementYear() {
        return placementYear;
    }

    public void setPlacementYear(Integer placementYear) {
        this.placementYear = placementYear;
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

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStreamId() {
        return streamId;
    }

    public void setStreamId(Integer streamId) {
        this.streamId = streamId;
    }

    public Integer getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(Integer passingYear) {
        this.passingYear = passingYear;
    }

    public Double getStartPercentage() {
        return startPercentage;
    }

    public void setStartPercentage(Double startPercentage) {
        this.startPercentage = startPercentage;
    }

    public Double getEndPercentage() {
        return endPercentage;
    }

    public void setEndPercentage(Double endPercentage) {
        this.endPercentage = endPercentage;
    }

    public Integer getLiveKTs() {
        return liveKTs;
    }

    public void setLiveKTs(Integer liveKTs) {
        this.liveKTs = liveKTs;
    }

    public Integer getPastKTs() {
        return pastKTs;
    }

    public void setPastKTs(Integer pastKTs) {
        this.pastKTs = pastKTs;
    } 

    public String getUniv() {
        return univ;
    }

    public void setUniv(String univ) {
        this.univ = univ;
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

    public Double getSscPercentage() {
        return sscPercentage;
    }

    public void setSscPercentage(Double sscPercentage) {
        this.sscPercentage = sscPercentage;
    }

    public Double getHscPercentage() {
        return hscPercentage;
    }

    public void setHscPercentage(Double hscPercentage) {
        this.hscPercentage = hscPercentage;
    }

    public Double getUgPercentage() {
        return ugPercentage;
    }

    public void setUgPercentage(Double ugPercentage) {
        this.ugPercentage = ugPercentage;
    }

    public Double getPgPercentage() {
        return pgPercentage;
    }

    public void setPgPercentage(Double pgPercentage) {
        this.pgPercentage = pgPercentage;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    } 

    public Integer getNumPlacements() {
        return numPlacements;
    }

    public void setNumPlacements(Integer numPlacements) {
        this.numPlacements = numPlacements;
    } 
    
}
