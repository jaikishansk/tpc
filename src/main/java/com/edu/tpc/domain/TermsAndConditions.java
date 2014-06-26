package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
 * Date: 21/10/11
 */ 
public class TermsAndConditions
{
    private double payPackage;
    private int bondPeriod;
    private double sscPercentage;
    private double hscPercentage;
    private double qualPercentage;
    private int liveKTs;
    private int pastKts;

    public int getBondPeriod() {
        return bondPeriod;
    }

    public void setBondPeriod(int bondPeriod) {
        this.bondPeriod = bondPeriod;
    }

    public double getHscPercentage() {
        return hscPercentage;
    }

    public void setHscPercentage(double hscPercentage) {
        this.hscPercentage = hscPercentage;
    }

    public int getLiveKTs() {
        return liveKTs;
    }

    public void setLiveKTs(int liveKTs) {
        this.liveKTs = liveKTs;
    }

    public int getPastKts() {
        return pastKts;
    }

    public void setPastKts(int pastKts) {
        this.pastKts = pastKts;
    }

    public double getPayPackage() {
        return payPackage;
    }

    public void setPayPackage(double payPackage) {
        this.payPackage = payPackage;
    }

    public double getQualPercentage() {
        return qualPercentage;
    }

    public void setQualPercentage(double qualPercentage) {
        this.qualPercentage = qualPercentage;
    }

    public double getSscPercentage() {
        return sscPercentage;
    }

    public void setSscPercentage(double sscPercentage) {
        this.sscPercentage = sscPercentage;
    }
}