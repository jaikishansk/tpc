/*
 * Date: 14/5/12
 *
 */

package com.edu.tpc.service;

/**
 *
 * @author Jaikishan
 */

import java.util.*;

import com.edu.tpc.domain.EligibilityReport;
import com.edu.tpc.domain.PECriteria;

public interface DashboardService {
    public List<EligibilityReport> getEligibleCandidates(PECriteria pec); 
}
