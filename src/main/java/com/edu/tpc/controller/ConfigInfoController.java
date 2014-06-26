//TODO: When an organization is added, insert entry in sequence table for getting next candidateid

package com.edu.tpc.controller;
/**
 *
 * @author Jaykishan
 * Start date: 17/10/11
 */

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.tpc.domain.ConfigInfo;
import com.edu.tpc.service.ConfigInfoService;

@Controller
public class ConfigInfoController extends BaseController { 
    private ConfigInfoService configInfoService;

    @Autowired
    public ConfigInfoService getConfigInfoService() {
        return configInfoService;
    }

    public void setConfigInfoService(ConfigInfoService configInfoService) {
        this.configInfoService = configInfoService;
    }
    
    @RequestMapping(value="configure.html", method=RequestMethod.GET)
    public String showConfigForm(ModelMap model) { 
        ConfigInfo cInfo = new ConfigInfo();
        // Set default values for search 
        cInfo.setMaxPlacementOffers(2);
        cInfo.setSearchRecPerPage(5);
        cInfo.setExportRecPerPage(10);
        cInfo.setReportRecPerPage(10);
        model.put("configInfo", cInfo);
        return "config/ConfigInfo";
    }
    public int getMaxPlacementOffers(int orgId) { 
        return configInfoService.getMaxPlacementOffers(orgId);
    }
}
