package com.edu.tpc.domain;
/**
 *
 * @author Jaikishan
 * Date: 12/10/11
 */

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseAddress implements java.io.Serializable
{
    @Column private String addressLine1 ;
    @Column private String addressLine2 ;
    @Column private String city ;
    @Column private String residentState ;
    @Column private String pin ;

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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getResidentState() {
        return residentState;
    }

    public void setResidentState(String residentState) {
        this.residentState = residentState;
    }
}
