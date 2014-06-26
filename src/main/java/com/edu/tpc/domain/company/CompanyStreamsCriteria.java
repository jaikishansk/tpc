package com.edu.tpc.domain.company;

import javax.persistence.Id;
import javax.persistence.Entity; 
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Jaikishan
 * 10th Feb 2013
 */

@Entity
@Table(name="CompanyStreamCriteria")
public class CompanyStreamsCriteria implements java.io.Serializable {
    @Id
    private CompanySCPK primaryKey;  
    
    @ManyToOne
    @JoinColumn(name="companyId",updatable = false, insertable = false)
    private Company company;

    public CompanySCPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(CompanySCPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    } 
}
