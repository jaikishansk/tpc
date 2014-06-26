package com.edu.tpc.service;

import com.edu.tpc.domain.NCCriteria;

/**
 *
 * @author Jaikishan
 * 21/10/12
 */
public interface NotificationService {
    public void notifyCandidates(int orgId,NCCriteria ncCriteria) throws Exception;
    public int findNCPotentialMatch(final NCCriteria ncCriteria,final int orgId);
}
