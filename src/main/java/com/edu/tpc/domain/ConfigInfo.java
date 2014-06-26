package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
 * Start date: 17/10/11
 */
 
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="ConfigInfo")
public class ConfigInfo extends AuditInfo implements java.io.Serializable { 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int orgId;
    // organization config info
    @Column private String orgName ;
    @Column private String addressLine1;
    @Column private String addressLine2;
    @Column private String city;
    @Column private String stateLocated;
    @Column private String pin;

    // search results info
    @Column private int searchRecPerPage;
    @Column private int reportRecPerPage;
    @Column private int exportRecPerPage;

    // placement config
    @Column private int maxPlacementOffers;

    // notification mail id
    @Column private int notificationMailId;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getExportRecPerPage() {
        return exportRecPerPage;
    }

    public void setExportRecPerPage(int exportRecPerPage) {
        this.exportRecPerPage = exportRecPerPage;
    }

    public int getReportRecPerPage() {
        return reportRecPerPage;
    }

    public void setReportRecPerPage(int reportRecPerPage) {
        this.reportRecPerPage = reportRecPerPage;
    }

    public int getSearchRecPerPage() {
        return searchRecPerPage;
    }

    public void setSearchRecPerPage(int searchRecPerPage) {
        this.searchRecPerPage = searchRecPerPage;
    }

    public String getStateLocated() {
        return stateLocated;
    }

    public void setStateLocated(String stateLocated) {
        this.stateLocated = stateLocated;
    }

    public int getMaxPlacementOffers() {
        return maxPlacementOffers;
    }

    public void setMaxPlacementOffers(int maxPlacementOffers) {
        this.maxPlacementOffers = maxPlacementOffers;
    }

    public int getNotificationMailId() {
        return notificationMailId;
    }

    public void setNotificationMailId(int notificationMailId) {
        this.notificationMailId = notificationMailId;
    } 
}
