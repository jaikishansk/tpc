package com.edu.tpc.domain.company;
 
/**
 *
 * @author Jaikishan
 */

import java.util.List;
import java.util.Date;
import java.util.ArrayList; 

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.TemporalType;

import org.apache.commons.collections.list.LazyList;
import org.apache.commons.collections.functors.InstantiateFactory;

import com.edu.tpc.domain.AuditInfo;

import javax.persistence.JoinColumn;

import org.hibernate.annotations.Cascade;


@Entity
@Table(name="company")
public class Company extends AuditInfo  implements java.io.Serializable { 
    // general info
    @Id 
    private int companyId;
    @Column private String companyName; 
    @Column private String companyType;
    
    // streams
    @OneToMany( fetch = FetchType.LAZY, mappedBy="company") 
    @Cascade({ org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    private List<CompanyStreamsCriteria> companyStreams;
    
    // offer
    @Column private Double payPackage;
    @Column private Integer bondPeriod; 
    @Column private Integer yearOfPlacement; 
    @Column private int orgId; 
   
    // address
    @OneToOne(fetch = FetchType.LAZY, mappedBy="company" , optional=false)
    @Cascade({ org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    private CompanyAddress address;
    
    // contact
    @OneToMany( fetch = FetchType.LAZY, mappedBy="company")
    @Cascade({ org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    private List<CompanyContact> contacts;
    
    // eligibility
    @OneToMany(fetch = FetchType.LAZY, mappedBy="company")
    @Cascade({ org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    private List<CompanyQualCriteria> companyQC; 
    
    public Company() {
        // this is how one to many relationship is implemented
        companyStreams=LazyList.decorate(new ArrayList<CompanyStreamsCriteria>(),
                                           new InstantiateFactory(CompanyStreamsCriteria.class));
        contacts = LazyList.decorate(new ArrayList<CompanyContact>(),
                                           new InstantiateFactory(CompanyContact.class));
        address=new CompanyAddress();
        companyQC= LazyList.decorate(new ArrayList<CompanyQualCriteria>(),
                                           new InstantiateFactory(CompanyQualCriteria.class)); 
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public List<CompanyStreamsCriteria> getCompanyStreams() {
        return companyStreams;
    }

    public void setCompanyStreams(List<CompanyStreamsCriteria> companyStreams) {
        this.companyStreams = companyStreams;
    }

    public Double getPayPackage() {
        return payPackage;
    }

    public void setPayPackage(Double payPackage) {
        this.payPackage = payPackage;
    }

    public Integer getBondPeriod() {
        return bondPeriod;
    }

    public void setBondPeriod(Integer bondPeriod) {
        this.bondPeriod = bondPeriod;
    }

    public Integer getYearOfPlacement() {
        return yearOfPlacement;
    }

    public void setYearOfPlacement(Integer yearOfPlacement) {
        this.yearOfPlacement = yearOfPlacement;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public CompanyAddress getAddress() {
        return address;
    }

    public void setAddress(CompanyAddress address) {
        this.address = address;
    }

    public List<CompanyContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<CompanyContact> contacts) {
        this.contacts = contacts;
    }

    public List<CompanyQualCriteria> getCompanyQC() {
        return companyQC;
    }

    public void setCompanyQC(List<CompanyQualCriteria> companyQC) {
        this.companyQC = companyQC;
    } 
}
