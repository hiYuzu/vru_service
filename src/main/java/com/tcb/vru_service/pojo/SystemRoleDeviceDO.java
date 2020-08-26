package com.tcb.vru_service.pojo;

import com.tcb.vru_service.base.BaseDO;

/**
 * 角色设备DO
 */
public class SystemRoleDeviceDO extends BaseDO {

    private String roleCode;
    private String deviceCode;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }
}
