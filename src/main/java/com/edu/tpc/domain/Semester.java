package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
 * 10th Feb 2013
 */

import javax.persistence.Id;  
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns; 
import javax.persistence.ManyToOne;

@Entity
@Table(name="CandidateSemester")
public class Semester implements java.io.Serializable {  
    @Id
    private SemesterPK primaryKey ;  
    @Column (name="Cuid")
    private int candidateUID;
    @Column(name="orgId")
    private int orgId;
    @Column private Integer maxMarks ;
    @Column private Integer marksObtd ;
    @Column private Double percentMarks ;

    public Semester() {}
    public Semester(SemesterPK pk){ 
        this.primaryKey=pk;
    }
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="Cuid",updatable = false, insertable = false),
        @JoinColumn(name="qualRollNo",updatable = false, insertable = false)
    })
    private Qualification qualification;
    
    public SemesterPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(SemesterPK primaryKey) {
        this.primaryKey = primaryKey;
    } 

    public Qualification getQual() {
        return qualification;
    }
  
    public void setQual(Qualification qual) {
        this.qualification = qual;
    }

    public int getCandidateUID() {
        return candidateUID;
    }

    public void setCandidateUID(int candidateUID) {
        this.candidateUID = candidateUID;
    } 
    
    public Integer getMarksObtd() {
        return marksObtd;
    }

    public void setMarksObtd(Integer marksObtd) {
        this.marksObtd = marksObtd;
    }

    public Integer getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(Integer maxMarks) {
        this.maxMarks = maxMarks;
    }

    public Double getPercentMarks() {
        return percentMarks;
    }

    public void setPercentMarks(Double percentMarks) {
        this.percentMarks = percentMarks;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    } 
    
    @Override
    public String toString() {
        return "Semester{" + "primaryKey=" + primaryKey + "maxMarks=" + maxMarks +
               "marksObtd=" + marksObtd + "percentMarks=" + percentMarks +
               "qualification=" + qualification + "orgId="+ orgId+ '}';
    }

}
