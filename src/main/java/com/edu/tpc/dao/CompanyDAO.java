package com.edu.tpc.dao;
/**
 *
 * @author Jaikishan
 * Date: 22/10/11
 */

import java.util.*;

import com.edu.tpc.domain.company.Company;

public interface CompanyDAO { 
    public void save(Company company);
    public void update(Company company);
    public int getNextCompanyID(int orgId);
    public Company findCompanyByName(String name, int orgId);
    public String getCompanyName(int companyId);
    public Map<Integer,String> findAllByOrgId(int orgId);
    public Company getCompany(int companyId);
    public void deleteCompany(String companyName, int orgId);
}
