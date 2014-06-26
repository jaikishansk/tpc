package com.edu.tpc.service;
/**
 *
 * @author Jaikishan
 * Date: 22/10/11
 */


import java.util.*;

import com.edu.tpc.domain.company.Company;


public interface CompanyService {
    public void registerCompany(Company company);
    public void editCompany(Company company);
    public Company getCompanyByName(String companyName, int orgId);
    public String getCompanyName(int companyId);
    public Map<Integer,String> getCompanies(int orgId);
    public Company getCompany(int companyId); 
    public void deleteCompany(String companyName, int orgId);
}
