package com.tcb.vru_service.pojo;

/**
 * 设备使用情况DO
 */
public class BaseDeviceUseDO {

    private Integer deviceId;
    private Boolean deviceUse;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Boolean getDeviceUse() {
        return deviceUse;
    }

    public void setDeviceUse(Boolean deviceUse) {
        this.deviceUse = deviceUse;
    }
}
