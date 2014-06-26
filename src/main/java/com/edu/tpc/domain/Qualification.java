package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
*/
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OrderBy;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne; 
import javax.persistence.JoinColumn;

import java.util.*; 
import org.apache.commons.collections.list.*;
import org.apache.commons.collections.functors.*;

@Entity
@Table(name="CandidateQualification")
public class Qualification implements java.io.Serializable { 
    @Id
    private QualificationPK primaryKey; 
    @Column (name="CourseId")
    private int course;
    @Column (name="StreamId")
    private int stream;
    @Column (name="University")
    private String university;
    @Column private Integer maxMarks;
    @Column private Integer obtdMarks;
    @Column private Double percentMarks;
    @Column private Integer passingYear;
    @Column private Integer liveKT;
    @Column private Integer pastKT;
    @Column private String ktSubjects;
    @Column(name="orgId")
    private int orgId;
    
    @ManyToOne
    @JoinColumn(name="Cuid",updatable = false, insertable = false)
    private Candidate candidate             ;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="qualification")
    @OrderBy("primaryKey.courseId,primaryKey.semesterId")
    private List<Semester> semesters ;

    public Qualification() { 
        semesters = LazyList.decorate(new ArrayList<Semester>(),new InstantiateFactory(Semester.class));
    }

    public QualificationPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(QualificationPK primaryKey) {
        this.primaryKey = primaryKey;
    } 

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    } 
    
    public String getKtSubjects() {
        return ktSubjects;
    }

    public void setKtSubjects(String ktSubjects) {
        this.ktSubjects = ktSubjects;
    }

    public Integer getLiveKT() {
        return liveKT;
    }

    public void setLiveKT(Integer liveKT) {
        this.liveKT = liveKT;
    }

    public Integer getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(Integer maxMarks) {
        this.maxMarks = maxMarks;
    }

    public Integer getObtdMarks() {
        return obtdMarks;
    }

    public void setObtdMarks(Integer obtdMarks) {
        this.obtdMarks = obtdMarks;
    }

    public Integer getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(Integer passingYear) {
        this.passingYear = passingYear;
    }

    public Integer getPastKT() {
        return pastKT;
    }

    public void setPastKT(Integer pastKT) {
        this.pastKT = pastKT;
    }

    public Double getPercentMarks() {
        return percentMarks;
    }

    public void setPercentMarks(Double percentMarks) {
        this.percentMarks = percentMarks;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Integer getStream() {
        return stream;
    }

    public void setStream(Integer stream) {
        this.stream = stream;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    } 

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    } 
}
