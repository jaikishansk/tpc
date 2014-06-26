package com.edu.tpc.domain; 
/**
 *
 * @author Jaikishan
 * 10/8/11
 * Modified: 2/11/11
 */

import java.util.*;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.Max;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="User")
public class User implements java.io.Serializable
{
    @Id
    @NotEmpty
    @Max(30)
    private String userId ;
    @NotEmpty
    @Max(30)
    @Column private String password ;
    @Column private int orgId;
    @Column private int userType;
    @Column private boolean isActive ;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column private Date lastLogin;

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    } 

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    } 
}
