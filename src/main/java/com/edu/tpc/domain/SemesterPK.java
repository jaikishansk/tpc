package com.edu.tpc.domain;
/**
 *
 * @author Jaikishan
 */
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SemesterPK implements java.io.Serializable
{

    @Column private int courseId;
    @Column private String candidateId;
    @Column private int semesterId ;
    @Column private String qualRollNo;

    public SemesterPK() {} 
    public SemesterPK(String candidateId, String qualRollNo, int courseId, int semesterId)
    {
        this.candidateId=candidateId;
        this.qualRollNo=qualRollNo;
        this.courseId=courseId;
        this.semesterId=semesterId;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) 
    { 
        this.semesterId = semesterId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getQualRollNo() {
        return qualRollNo;
    }

    public void setQualRollNo(String qualRollNo) {
        this.qualRollNo = qualRollNo;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof SemesterPK))
            return false; 
        if (candidateId == null)
            return false; 
        SemesterPK other = (SemesterPK) object;
        return  candidateId.equals(other.getCandidateId())
                &&
                qualRollNo.equals(other.getQualRollNo())
                &&
                courseId==other.getCourseId()
                &&
                semesterId==other.getSemesterId();
    }
    @Override
    public int hashCode()
    {
        int hash=5 ;

        hash=31*hash+courseId;
        hash=31*hash+semesterId;
        hash=31*hash+candidateId.hashCode();
        hash=31*hash+qualRollNo.hashCode();
        return hash;
    }


    @Override
    public String toString() {
        return "SemesterPK{" + "courseId=" + courseId + "candidateId=" +
                candidateId + "semesterId=" + semesterId + '}';
    }

}
