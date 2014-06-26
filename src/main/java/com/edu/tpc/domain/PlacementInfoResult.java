/*
 * Date: 28/2/12
 */

package com.edu.tpc.domain;

/**
 *
 * author Jaikishan
 */

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat; 
import javax.persistence.Temporal;
import javax.persistence.TemporalType; 

public class PlacementInfoResult
{
    private int companyId;
    @Temporal(TemporalType.DATE)
    private Date dtdateOfPlacement;
    @Temporal(TemporalType.DATE) 
    private Date dtdateOfJoining;
    private Integer bondPeriod;
    private Double salaryOffered;
    private String dateOfPlacement;
    private String dateOfJoining;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public PlacementInfoResult(int companyId, Date dateOfPlacement,
                                Date dateOfJoining, Integer bondPeriod,
                                Double salaryOffered) 
    {
        this.companyId = companyId;
        this.dtdateOfPlacement = dateOfPlacement;
        this.dtdateOfJoining = dateOfJoining;
        this.bondPeriod = bondPeriod;
        this.salaryOffered = salaryOffered;
        this.dateOfPlacement=dateFormat.format(dtdateOfPlacement);
        this.dateOfJoining=dateFormat.format(dtdateOfJoining); 
    }

    public Integer getBondPeriod() {
        return bondPeriod;
    }

    public void setBondPeriod(int bondPeriod) {
        this.bondPeriod = bondPeriod;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getDateOfPlacement() {
        return dateOfPlacement;
    }

    public void setDateOfPlacement(String dateOfPlacement) {
        this.dateOfPlacement = dateOfPlacement;
    }

    public Double getSalaryOffered() {
        return salaryOffered;
    }

    public void setSalaryOffered(Double salaryOffered) {
        this.salaryOffered = salaryOffered;
    } 
}
