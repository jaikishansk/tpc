/*
 *  Date: 14/5/12
 *
 */

package com.edu.tpc.dao;

/**
 *
 * @author Jaikishan
 */
import java.util.*;

import com.edu.tpc.domain.EligibilityReport;
import com.edu.tpc.domain.PECriteria;

public interface DashboardDAO
{
    public List<EligibilityReport> getEligibleCandidates(PECriteria pec);
}
