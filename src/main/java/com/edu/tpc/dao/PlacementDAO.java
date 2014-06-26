package com.edu.tpc.dao;
/**
 *
 * @author Jaikishan
 * Date: 19/10/11
 */

import java.util.*;

import com.edu.tpc.domain.CRResult;
import com.edu.tpc.domain.PlacementInfo;
import com.edu.tpc.domain.PlacementInfoResult;

public interface PlacementDAO {
    public void savePlacementInfo(PlacementInfo pi);
    public void saveEditedPI(int candidateUID,ArrayList<PlacementInfo> pi);
    public int deletePI(int candidateUID);
    public List<PlacementInfo> findPlacements(int candidateUID);
    public List<PlacementInfoResult>findPIForEdit(String candidateId);
    public List getCampusCompanies(int passingYear);
    public List<CRResult> findConsolidatedPInfo(int orgId, int passingYear);
    public int findNumOfPlacements(int candidateUID);
}
