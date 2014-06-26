package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
 * Modified on: 25/10/11 
 */

import java.util.*;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;  
import javax.persistence.TemporalType;

import org.apache.commons.collections.list.*;
import org.apache.commons.collections.functors.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty; 

@Entity
@Table(name="Candidate") 
public class Candidate extends AuditInfo implements java.io.Serializable {
    @Id
    @NotEmpty
    @Column (name="Cuid")
    private int candidateUID;
    @Column private String candidateId;
    @Column private int candidateType;
    @Column private int orgId;
    @Column private String firstName;
    @Column private String fathersName;
    @Column private String mothersName;
    @Column private String lastName;
    @Column private int gender;
    @Temporal(TemporalType.DATE) 
    @Column private Date dateOfBirth;
    @Column (name="CourseId")
    private int course;
    @Column (name="StreamId")
    private int stream;
    @Column(name="PassingYear")
    private int passingYear;
    
    //qualification and contact details
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="candidate")
    @OrderBy("course,stream")
    private List<Qualification> quals ; 

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="candidate", optional=true)
    private CandidateContact contact     ;
  
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="candidate", optional=true)
    private CandidateAddress address     ; 
    
    @OneToMany( fetch = FetchType.LAZY, mappedBy="candidate") 
    @OrderBy("primaryKey.candidateUID,primaryKey.placementPref")
    @Cascade({ org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    private List<CandidatePlacementPref> placementPrefs ;  
     
    public Candidate() { 
        // this is how one to many relationship is implemented
        quals = LazyList.decorate(new ArrayList<Qualification>(),
                                           new InstantiateFactory(Qualification.class));
         placementPrefs = LazyList.decorate(new ArrayList<CandidatePlacementPref>(),
                                            new InstantiateFactory(CandidatePlacementPref.class));
        
        contact = new CandidateContact();
        address = new CandidateAddress(); 
     }

    public int getCandidateUID() {
        return candidateUID;
    }

    public void setCandidateUID(int candidateUID) {
        this.candidateUID = candidateUID;
    }
 
    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public int getCandidateType() {
        return candidateType;
    }

    public void setCandidateType(int candidateType) {
        this.candidateType = candidateType;
    }
    
    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    } 
    
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) { 
        this.dateOfBirth = dateOfBirth; 
    }
    
    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getStream() {
        return stream;
    }

    public void setStream(int stream) {
        this.stream = stream;
    }

    public int getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(int passingYear) {
        this.passingYear = passingYear;
    } 
    
    public List<Qualification> getQuals() {
        return quals;
    }

    public void setQuals(List<Qualification> quals) {
        this.quals = quals;
    } 
     
    public CandidateContact getContact() {
        return contact;
    }
    
    public void setContact(CandidateContact contact) {
        this.contact = contact;
    }
     
    public CandidateAddress getAddress() {
        return address;
    }
    
    public void setAddress(CandidateAddress address) {
        this.address = address;
    }

    public List<CandidatePlacementPref> getPlacementPrefs() {
        return placementPrefs;
    }

    public void setPlacementPref(List<CandidatePlacementPref> placementPrefs) {
        this.placementPrefs = placementPrefs;
    }  
}
