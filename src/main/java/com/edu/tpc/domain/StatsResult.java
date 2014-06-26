/*
 * Date: 6/3/12
 * @author Jaikishan
*/

package com.edu.tpc.domain;
 
public class StatsResult
{
    private String companyName ;
    private int totalEligible ;
    private int totalAppeared ;
    private int totalSelected ;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getTotalAppeared() {
        return totalAppeared;
    }

    public void setTotalAppeared(int totalAppeared) {
        this.totalAppeared = totalAppeared;
    }

    public int getTotalEligible() {
        return totalEligible;
    }

    public void setTotalEligible(int totalEligible) {
        this.totalEligible = totalEligible;
    }

    public int getTotalSelected() {
        return totalSelected;
    }

    public void setTotalSelected(int totalSelected) {
        this.totalSelected = totalSelected;
    }
}
