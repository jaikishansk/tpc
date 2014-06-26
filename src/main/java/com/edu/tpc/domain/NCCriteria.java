/*
 * Date: 29/2/12
 * Author: Jaikishan
 */
package com.edu.tpc.domain;

public class NCCriteria {
    private int notificationType;
    private String candidateId ;
    private String message;
    private Integer course;
    private Integer stream;
    private Integer passingYear;
    private Double startPercent;
    private Double endPercent;
    private Integer liveKT;
    private Integer numPlacements;
    private int notificationScope;

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Double getEndPercent() {
        return endPercent;
    }

    public void setEndPercent(Double endPercent) {
        this.endPercent = endPercent;
    }

    public Integer getLiveKT() {
        return liveKT;
    }

    public void setLiveKT(Integer liveKT) {
        this.liveKT = liveKT;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(int notificationType) {
        this.notificationType = notificationType;
    }

    public Integer getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(Integer passingYear) {
        this.passingYear = passingYear;
    }

    public Integer getNumPlacements() {
        return numPlacements;
    }

    public void setNumPlacements(Integer placements) {
        this.numPlacements = placements;
    }

    public Double getStartPercent() {
        return startPercent;
    }

    public void setStartPercent(Double startPercent) {
        this.startPercent = startPercent;
    }

    public Integer getStream() {
        return stream;
    }

    public void setStream(Integer stream) {
        this.stream = stream;
    }  

    public int getNotificationScope() {
        return notificationScope;
    }

    public void setNotificationScope(int notificationScope) {
        this.notificationScope = notificationScope;
    } 
}
