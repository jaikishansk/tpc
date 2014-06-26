package com.edu.tpc.domain;
/**
 *
 * @author Jaikishan
 */
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.FetchType;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name="CandidateContact") 
public class CandidateContact implements java.io.Serializable {
    @Id 
    @Column(name="Cuid")
    private int candidateUID;
    @Column(name="orgId")
    private int orgId;
    @Column private String primaryEmail ;
    @Column private String secondaryEmail ;
    @Column private String primaryCellNo ;
    @Column private String secondaryCellNo ;
    @Column private String primaryLandLineNo;
    @Column private String secondaryLandLineNo;

    @OneToOne(optional=false,fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @PrimaryKeyJoinColumn
    private Candidate candidate;

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public int getCandidateUID() {
        return candidateUID;
    }

    public void setCandidateUID(int candidateUID) {
        this.candidateUID = candidateUID;
    }   

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    } 
    
    public String getPrimaryCellNo() {
        return primaryCellNo;
    }

    public void setPrimaryCellNo(String primaryCellNo) {
        this.primaryCellNo = primaryCellNo;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryCellNo() {
        return secondaryCellNo;
    }

    public void setSecondaryCellNo(String secondaryCellNo) {
        this.secondaryCellNo = secondaryCellNo;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getPrimaryLandLineNo() {
        return primaryLandLineNo;
    }

    public void setPrimaryLandLineNo(String primaryLandLineNo) {
        this.primaryLandLineNo = primaryLandLineNo;
    }

    public String getSecondaryLandLineNo() {
        return secondaryLandLineNo;
    }

    public void setSecondaryLandLineNo(String secondaryLandLineNo) {
        this.secondaryLandLineNo = secondaryLandLineNo;
    }
}
