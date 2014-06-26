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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.FetchType;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name="CandidateAddress") 
public class CandidateAddress extends BaseAddress implements java.io.Serializable {
    @Id
    @Column(name="Cuid")
    private int candidateUID;
    @Column(name="orgId")
    private int orgId; 
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

    @Override
    public int hashCode() { 
        int hash=7;
        hash=31*hash+(getAddressLine1()==null?0:getAddressLine1().hashCode());
        hash=31*hash+(getAddressLine2()==null?0:getAddressLine2().hashCode());
        hash=31*hash+(getCity()==null?0:getCity().hashCode());
        hash=31*hash+(getResidentState()==null?0:getResidentState().hashCode());
        hash=31*hash+(getPin()==null?0:getPin().hashCode()); 
        return hash;
    }
    @Override
    public boolean equals( Object obj ) { 
        if(this == obj)
            return true;
        if((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        // object must be Test at this point
        CandidateAddress test = (CandidateAddress)obj;
        return ((getAddressLine1()!=null && getAddressLine1().equals(test.getAddressLine1()))
                &&
                (getAddressLine2()!=null && getAddressLine2().equals(test.getAddressLine2()))
                &&
                (getCity()!=null && getCity().equals(test.getCity()))
                &&
                (getResidentState()!=null && getResidentState().equals(test.getResidentState()))
                &&
                (getPin()!=null && getPin().equals(test.getPin()))
                ); 
    }
}
