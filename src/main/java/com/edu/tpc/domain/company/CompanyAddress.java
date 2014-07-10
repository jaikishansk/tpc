package com.edu.tpc.domain.company;
/**
 *
 * @author Jaikishan
 * Date: 22/10/11
 */

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;  
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToOne; 
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.Parameter;

import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator; 

import com.edu.tpc.domain.BaseAddress;

import org.hibernate.annotations.Cascade;


@Entity
@Table(name="CompanyAddress") 
public class CompanyAddress extends BaseAddress implements java.io.Serializable {
    
	@Id
	@Column(name="Id")
    private Integer Id;
	
	@Column(name="CompanyId")
    private Integer CompanyId; 
    @Column private int orgId; 
    
    @OneToOne(optional=false,fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private Company company;

   
   public void setCompanyId(Integer companyId) {
        this.CompanyId = companyId;
    }
    
    public int getCompanyId() {
        return CompanyId;
    }


    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

	public int getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	@Override
	public String toString() {
		return "CompanyAddress [Id=" + Id + ", CompanyId=" + CompanyId
				+ ", orgId=" + orgId + "]";
	} 
}
