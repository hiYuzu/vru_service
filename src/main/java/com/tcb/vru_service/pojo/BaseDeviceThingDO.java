package com.tcb.vru_service.pojo;

/**
 * 设备监测因子DO
 */
public class BaseDeviceThingDO {

    private Integer deviceId;
    private Integer thingId;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getThingId() {
        return thingId;
    }

    public void setThingId(Integer thingId) {
        this.thingId = thingId;
    }
}
