package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
 * 30/6/12
 */

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class QualificationPK implements java.io.Serializable { 
    @Column (name="QualRollNo")
    private String qualRollNo   ; 
    @Column (name="Cuid")
    private int candidateUID;
    
    public QualificationPK() {}
    public QualificationPK(int candidateUID, String qualRollNo) { 
        this.candidateUID=candidateUID;
        this.qualRollNo=qualRollNo;
    }

    public int getCandidateUID() {
        return candidateUID;
    }

    public void setCandidateUID(int candidateUID) {
        this.candidateUID = candidateUID;
    }

    public String getQualRollNo() {
        return qualRollNo;
    }

    public void setQualRollNo(String qualRollNo) {
        this.qualRollNo = qualRollNo;
    }
 
    @Override
    public boolean equals(Object object) { 
        if (!(object instanceof QualificationPK)) {
            return false;
        } 
        QualificationPK other = (QualificationPK) object;
        return  candidateUID==other.getCandidateUID()
                    &&
                    qualRollNo.equals(other.getQualRollNo());
    }
    @Override
    public int hashCode() { 
        int hash=5 ;
        hash=31*hash+candidateUID; 
        hash=31*hash+qualRollNo.hashCode();
        return hash;
    }

//    @Override
//    public String toString() {
//        return "QualificationPK{CandidateId=" + candidateId + "QualId=" +
//                qualRollNo + "courseId=" + course + "stream="+stream+"univ="+university+"}";
//    } 
}
    
    