package com.edu.tpc.dao; 

import com.edu.tpc.domain.CandidateNotificationsLog;
import com.edu.tpc.domain.NCCriteria;

/**
 *
 * @author Jaikishan
 * 21/10/12
 */
public interface NotificationDAO { 
    public void logNotification(CandidateNotificationsLog log);
}
