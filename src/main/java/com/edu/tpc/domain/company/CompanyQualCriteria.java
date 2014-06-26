package com.edu.tpc.domain.company;

/**
 *
 * @author Jaikishan
 * Date: 22/10/11
 */ 

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table; 
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;  
 
@Entity
@Table(name="CompanyQualCriteria")
public class CompanyQualCriteria implements java.io.Serializable {
    @Id
    private CompanyQCPK primaryKey;   
    @Column private Double percentage;
    @Column private Double liveKT;
    @Column private Double pastKT;
    @Column private String kTSubjects ; 
     
    @ManyToOne
    @JoinColumn(name="companyId",updatable = false, insertable = false)
    private Company company;

    public CompanyQCPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(CompanyQCPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getLiveKT() {
        return liveKT;
    }

    public void setLiveKT(Double liveKT) {
        this.liveKT = liveKT;
    }

    public Double getPastKT() {
        return pastKT;
    }

    public void setPastKT(Double pastKT) {
        this.pastKT = pastKT;
    }

    public String getkTSubjects() {
        return kTSubjects;
    }

    public void setkTSubjects(String kTSubjects) {
        this.kTSubjects = kTSubjects;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    } 
    
}
