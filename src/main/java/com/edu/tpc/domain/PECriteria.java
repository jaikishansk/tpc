/*
 * Date: 14/5/12
 *
 */
package com.edu.tpc.domain;
/**
 *
 * @author Jaikishan
 */

public class PECriteria
{
    private Integer[] courseIds     =new Integer[5] ;
    private Integer[] streamIds     =new Integer[5] ;
    private Integer[] passingYears  =new Integer[5] ;
    private Double[] startPercents  =new Double[5] ;
    private Double[] endPercents    =new Double[5] ;
    private Integer[] liveKTs       =new Integer[5];
    private Integer[] placements    =new Integer[5];

    public Integer[] getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(Integer[] courseIds) {
        this.courseIds = courseIds;
    }

    public Double[] getEndPercents() {
        return endPercents;
    }

    public void setEndPercents(Double[] endPercents) {
        this.endPercents = endPercents;
    }
    
    public Integer[] getLiveKTs() {
        return liveKTs;
    }

    public void setLiveKTs(Integer[] liveKTs) {
        this.liveKTs = liveKTs;
    }

    public Integer[] getPassingYears() {
        return passingYears;
    }

    public void setPassingYears(Integer[] passingYears) {
        this.passingYears = passingYears;
    }


    public Double[] getStartPercents() {
        return startPercents;
    }

    public void setStartPercents(Double[] startPercents) {
        this.startPercents = startPercents;
    }

    public Integer[] getStreamIds() {
        return streamIds;
    }

    public void setStreamIds(Integer[] streamIds) {
        this.streamIds = streamIds;
    }

    public Integer[] getPlacements() {
        return placements;
    }

    public void setPlacements(Integer[] placements) {
        this.placements = placements;
    } 
}
