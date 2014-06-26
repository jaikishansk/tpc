/*
 * Date: 14/5/12
 */

package com.edu.tpc.service;

/**
 *
 * @author Jaikishan
 */
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired; 

import com.edu.tpc.common.ERExcelView;
import com.edu.tpc.dao.DashboardDAO;
import com.edu.tpc.domain.EligibilityReport;
import com.edu.tpc.domain.PECriteria;

@Service("dashBoardService")
public class DashboardServiceImpl implements DashboardService {
    private DashboardDAO dashBoardDAO; 
    private ERExcelView eRExcelView;
    
    public DashboardDAO getDashBoardDAO() {
        return dashBoardDAO;
    } 
    @Autowired
    public void setDashBoardDAO(DashboardDAO dashBoardDAO) {
        this.dashBoardDAO = dashBoardDAO;
    }

    public ERExcelView geteRExcelView() {
        return eRExcelView;
    }

    @Autowired
    public void seteRExcelView(ERExcelView eRExcelView) {
        this.eRExcelView = eRExcelView;
    } 

    @Override
    public List<EligibilityReport> getEligibleCandidates(PECriteria pec) {
        return dashBoardDAO.getEligibleCandidates(pec);
    } 
}

