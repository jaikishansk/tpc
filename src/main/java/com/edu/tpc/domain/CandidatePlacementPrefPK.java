package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
 * 7/10/12
 */

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CandidatePlacementPrefPK  implements java.io.Serializable {
    @Column (name="Cuid")
    private int candidateUID;
    @Column private Integer placementPref; 
      
    public CandidatePlacementPrefPK(){}
    public CandidatePlacementPrefPK(int candidateUID, Integer placementPref) { 
        this.candidateUID=candidateUID;
        this.placementPref=placementPref;
    }

    @Override
    public boolean equals(Object object) { 
        if (!(object instanceof CandidatePlacementPrefPK)) {
            return false;
        } 
        if(object == null) {
            return false;
        }
        CandidatePlacementPrefPK other = (CandidatePlacementPrefPK) object;
        return  candidateUID==other.getCandidateUID()
                    &&
                    placementPref.equals(other.getPlacementPref());
                
    }
    @Override
    public int hashCode() { 
        int hash=5 ;
        if(placementPref!=null) {
            hash=31*hash+placementPref.hashCode(); 
        }
        hash=31*hash+candidateUID;
        return hash;
    }
    
    
    public int getCandidateUID() {
        return candidateUID;
    }

    public void setCandidateUID(int candidateUID) {
        this.candidateUID = candidateUID;
    }

    public Integer getPlacementPref() {
        return placementPref;
    }

    public void setPlacementPref(Integer placementPref) {
        this.placementPref = placementPref;
    } 
}
