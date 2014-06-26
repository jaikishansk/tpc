package com.edu.tpc.service;
/**
 *
 * @author Jaikishan
 * Date: 19/10/11
 */

import java.util.*;

import com.edu.tpc.dao.PlacementDAO;
import com.edu.tpc.domain.CRResult;
import com.edu.tpc.domain.PlacementInfo;
import com.edu.tpc.domain.PlacementInfoResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("placementService")
public class PlacementServiceImpl implements PlacementService { 
    private PlacementDAO placementDAO;

    public PlacementDAO getPlacementDAO() {
        return placementDAO;
    }

    @Autowired
    public void setPlacementDAO(PlacementDAO placementDAO) {
        this.placementDAO = placementDAO;
    }
    @Override
    public void savePlacementInfo(PlacementInfo pi)
    {
        placementDAO.savePlacementInfo(pi);
    }
    @Override
    public void saveEditedPI(int candidateUID, ArrayList<PlacementInfo> pi) { 
         placementDAO.saveEditedPI(candidateUID,pi);
    }
    @Override
    public int deletePI(int candidateUID)
    {
        return placementDAO.deletePI(candidateUID);
    }
    @Override
    public List<PlacementInfo> findPlacements(int candidateUID) { 
        return placementDAO.findPlacements(candidateUID);
    }
    @Override
    public List<PlacementInfoResult>findPIForEdit(String candidateId)
    {
        return placementDAO.findPIForEdit(candidateId);
    }
    @Override
    public List getCampusCompanies(int passingYear)
    {
        return placementDAO.getCampusCompanies(passingYear);
    }
    @Override
    public List<CRResult> findConsolidatedPInfo(int orgId, int passingYear) { 
        return placementDAO.findConsolidatedPInfo(orgId,passingYear);
    }
    @Override
    public int numOfPlacements(int candidateUID) { 
        return placementDAO.findNumOfPlacements(candidateUID);
    }
}
