package com.edu.tpc.domain;
/**
 *
 * @author Jaikishan
 * StartDate: 15/10/11
 */
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Placement")
public class PlacementInfo extends AuditInfo implements java.io.Serializable { 
    @Id
    private PlacementInfoPK primaryKey ;  
    @Temporal(TemporalType.DATE)
    @Column private Date dateOfPlacement;
    @Temporal(TemporalType.DATE)
    @Column private Date dateOfJoining;  
    @Column private Integer bondPeriod;
    @Column private Double salaryOffered; 

    public PlacementInfo(){}

    public PlacementInfoPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PlacementInfoPK primaryKey) {
        this.primaryKey = primaryKey;
    } 

    public Integer getBondPeriod() {
        return bondPeriod;
    }

    public void setBondPeriod(Integer bondPeriod) {
        this.bondPeriod = bondPeriod;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Date getDateOfPlacement() {
        return dateOfPlacement;
    }

    public void setDateOfPlacement(Date dateOfPlacement) {
        this.dateOfPlacement = dateOfPlacement;
    }

    public Double getSalaryOffered() {
        return salaryOffered;
    }

    public void setSalaryOffered(Double salaryOffered) {
        this.salaryOffered = salaryOffered;
    }  
 
}
