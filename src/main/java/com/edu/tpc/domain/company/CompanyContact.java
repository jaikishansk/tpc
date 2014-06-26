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
@Table(name="CompanyContact")
public class CompanyContact implements java.io.Serializable { 
    @Column private int companyId;
    @Column private int orgId;
    @Column private String name;
    @Column private String cellNumber;
    @Column private String landLine;
    @Id
    @Column private String emailId;

    @ManyToOne
    @JoinColumn(name="companyId",updatable = false, insertable = false)
    private Company company;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getLandLine() {
        return landLine;
    }

    public void setLandLine(String landLine) {
        this.landLine = landLine;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    } 
}
