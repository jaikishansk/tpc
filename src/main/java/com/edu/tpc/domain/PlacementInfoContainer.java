/*
 * Date: 7/7/12
 * Author: Jaikishan
 */

package com.edu.tpc.domain;

import java.util.*;

public class PlacementInfoContainer
{
    private ArrayList<PlacementInfo> placements;

    public PlacementInfoContainer(){} 
    public PlacementInfoContainer(ArrayList<PlacementInfo> placements)
    {
        this.placements=placements;
    }
    public ArrayList<PlacementInfo> getPlacements() {
        return placements;
    }

    public void setPlacements(ArrayList<PlacementInfo> placements) {
        this.placements = placements;
    }
}
