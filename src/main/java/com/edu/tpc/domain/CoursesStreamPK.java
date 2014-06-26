package com.edu.tpc.domain;
/**
 *
 * @author Jaikishan
 * Date: 19/10/11
 */
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CoursesStreamPK implements java.io.Serializable
{
    @Column private int courseId;
    @Column private int streamId;
    @Column private int orgId;

    public CoursesStreamPK(){}
    public CoursesStreamPK(int courseId, int streamId,int orgId)
    {
        this.courseId=courseId;
        this.streamId=streamId;
        this.orgId=orgId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getStreamId() {
        return streamId;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof CoursesStreamPK))
            return false;

        CoursesStreamPK other = (CoursesStreamPK) object;
        return  courseId==(other.getCourseId())
                &&
                streamId==other.getStreamId()
                &&
                orgId==other.getOrgId();
    }
    @Override
    public int hashCode()
    {
        int hash=5 ;

        hash=31*hash+courseId;
        hash=31*hash+streamId;
        hash=31*hash+orgId;
        return hash;
    }
}
