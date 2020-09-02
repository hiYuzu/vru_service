package com.tcb.vru_service.pojo;

import com.tcb.vru_service.base.BaseDO;

/**
 * 角色设备DO
 */
public class SystemRoleDeviceDO extends BaseDO {

    private Integer roleDeviceId;
    private SystemRoleDO role;
    private BaseDeviceDO device;

    public Integer getRoleDeviceId() {
        return roleDeviceId;
    }

    public void setRoleDeviceId(Integer roleDeviceId) {
        this.roleDeviceId = roleDeviceId;
    }

    public SystemRoleDO getRole() {
        return role;
    }

    public void setRole(SystemRoleDO role) {
        this.role = role;
    }

    public BaseDeviceDO getDevice() {
        return device;
    }

    public void setDevice(BaseDeviceDO device) {
        this.device = device;
    }

}
