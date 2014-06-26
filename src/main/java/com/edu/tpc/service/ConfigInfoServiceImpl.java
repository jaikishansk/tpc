/*
 * Date: 6/7/12
 * Author: Jaikishan
 */

package com.edu.tpc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.tpc.dao.ConfigInfoDAO;

@Service("configInfoService")
public class ConfigInfoServiceImpl implements ConfigInfoService { 
    private ConfigInfoDAO configInfoDAO; 
    
    public ConfigInfoDAO getConfigInfoDAO() {
        return configInfoDAO;
    }

    @Autowired
    public void setConfigInfoDAO(ConfigInfoDAO configInfoDAO) {
        this.configInfoDAO = configInfoDAO;
    }

    @Override
    public int getMaxPlacementOffers(int orgId) { 
        return configInfoDAO.findMaxPlacementOffers(orgId);
    }
    @Override
     public String getOrganizationName(int orgId){
            return configInfoDAO.getOrganizationName(orgId);
     }
}
