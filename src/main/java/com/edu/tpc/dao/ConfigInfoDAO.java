/*
 * Date: 6/7/12
 * Author: Jaikishan
 */

package com.edu.tpc.dao;

public interface ConfigInfoDAO { 
    public int findMaxPlacementOffers(int orgId);
    public String getOrganizationName(int orgId);
}
