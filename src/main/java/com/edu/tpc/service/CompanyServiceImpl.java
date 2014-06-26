package com.edu.tpc.service;
/**
 *
 * @author Jaikishan
 * Date: 22/10/11
 */

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import com.edu.tpc.dao.CompanyDAO;
import com.edu.tpc.domain.company.Company;
import com.edu.tpc.domain.company.CompanyAddress;
import com.edu.tpc.domain.company.CompanyContact;
import com.edu.tpc.domain.company.CompanyQualCriteria;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService { 
    
    private CompanyDAO companyDAO;

    public CompanyDAO getCompanyDAO() {
        return companyDAO;
    }

    @Autowired
    public void setCompanyDAO(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

     private void initializeCompanyAuditInfo(Company company) { 
        Calendar cal = Calendar.getInstance();
        company.setCreatedOn(cal.getTime()); 
        company.setModifiedOn(cal.getTime());
    }
    @Override
    public void registerCompany(Company company) { 
        System.out.println("registering company..."); 
        int orgId=company.getOrgId();
        int companyId=companyDAO.getNextCompanyID(orgId);
        company.setCompanyId(companyId);
        
        // set address ids
        CompanyAddress address=company.getAddress();
        address.setCompany(company);
        address.setOrgId(orgId);
        address.setCompanyId(companyId);
        
        // set contact ids
        List<CompanyContact> contacts= new ArrayList<CompanyContact>();
        for(CompanyContact contact:contacts) {
            contact.setOrgId(orgId);
            contact.setCompany(company);
            contact.setCompanyId(companyId);
        } 
        
        // set qual ids
        List<CompanyQualCriteria> companyQCs=company.getCompanyQC();
        for(CompanyQualCriteria companyQC:companyQCs) {
            companyQC.getPrimaryKey().setCompanyId(companyId);
            companyQC.getPrimaryKey().setOrgId(orgId);
        }
        initializeCompanyAuditInfo(company);
        companyDAO.save(company);
    }
    
    @Override
    public void editCompany(Company company) { 
        companyDAO.update(company);
    }

    @Override
    public String getCompanyName(int companyId) { 
        return companyDAO.getCompanyName(companyId);
    }
    
    @Override
    public Map<Integer,String> getCompanies(int orgId) { 
        return companyDAO.findAllByOrgId(orgId);
    }

    @Override
    public Company getCompany(int companyId){ 
        return companyDAO.getCompany(companyId);
    }
    
    @Override
    public Company getCompanyByName(String companyName, int orgId) {
        return companyDAO.findCompanyByName(companyName, orgId);
    }
    
    @Override
    public void deleteCompany(String companyName, int orgId) {
        companyDAO.deleteCompany(companyName, orgId);
    }
}
