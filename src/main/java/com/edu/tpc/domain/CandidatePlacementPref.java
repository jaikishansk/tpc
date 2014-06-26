package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
 * Date: 7/10/12
 */

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column; 
import javax.persistence.JoinColumn; 
import javax.persistence.ManyToOne; 

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="CandidatePlacementPref")
public class CandidatePlacementPref implements java.io.Serializable {
     @Id
    private CandidatePlacementPrefPK primaryKey; 
    @Column(name="orgId")
    private int orgId; 
    
     public CandidatePlacementPrefPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(CandidatePlacementPrefPK primaryKey) {
        this.primaryKey = primaryKey;
    } 
     
    @ManyToOne
    @JoinColumn(name="Cuid",updatable = false, insertable = false)
    private Candidate candidate             ;

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }  

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    } 
    
}
