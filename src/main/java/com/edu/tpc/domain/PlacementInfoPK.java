package com.edu.tpc.domain;
/**
 *
 * @author Jaikishan
 * Start date: 15/10/11
 */
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PlacementInfoPK implements java.io.Serializable { 
    @Column private int orgId ;
    @Column (name="Cuid")
    private int candidateUID;
    @Column private int companyId;

    public PlacementInfoPK(){}
    public PlacementInfoPK(int orgId, int candidateUID, int companyId) { 
        this.orgId=orgId;
        this.candidateUID=candidateUID;
        this.companyId=companyId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    } 

    public int getCandidateUID() {
        return candidateUID;
    }

    public void setCandidateUID(int candidateUID) {
        this.candidateUID = candidateUID;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object object) { 
        if (!(object instanceof PlacementInfoPK))
            return false; 
        PlacementInfoPK other = (PlacementInfoPK) object;
        return  candidateUID==other.getCandidateUID()
                &&
                companyId==other.getCompanyId()
                &&
                orgId==other.getOrgId();
    }
    @Override
    public int hashCode() { 
        int hash=5 ;

        hash=31*hash+companyId;
        hash=31*hash+orgId;
        hash=31*hash+candidateUID;
        return hash;
    }
}
