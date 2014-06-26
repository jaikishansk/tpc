/*
 * Date: 27/7/12
 * Author: Jaikishan
 */

package com.edu.tpc.domain;

import java.util.*;

import javax.persistence.Column; 
import javax.persistence.Temporal;
import javax.persistence.TemporalType; 
import javax.persistence.MappedSuperclass;
 
@MappedSuperclass
public class AuditInfo {
    //Audit Info
    @Temporal(TemporalType.DATE)
    @Column private Date createdOn ;
    @Column private String createdBy ;
    @Temporal(TemporalType.DATE)
    @Column private Date modifiedOn ;
    @Column private String modifiedBy ;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
