package com.tcb.vru_service.pojo;

import com.tcb.vru_service.base.BaseDO;

import java.sql.Timestamp;

/**
 * 用户DO
 */
public class SystemUserDO extends BaseDO {

    private Integer userId;
    private String userCode;
    private String userName;
    private String userPassword;
    private String userPhone;
    private String userEmail;
    private Integer organizationId;
    private Boolean userStop;
    private Timestamp loginTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Boolean getUserStop() {
        return userStop;
    }

    public void setUserStop(Boolean userStop) {
        this.userStop = userStop;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }
}
