/*
 *  Date: 6/3/12
 *  @author Jaikishan
 */

package com.edu.tpc.domain;

public class CRResult
{
    private int courseId ;
    private int streamId ;
    private long count ;

    public CRResult() {} 
    public CRResult(int courseId, int streamId, long count)
    {
        this.courseId=courseId;
        this.streamId=streamId;
        this.count=count;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStreamId() {
        return streamId;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
    } 
}
