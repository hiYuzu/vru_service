package com.tcb.vru_service.pojo;

/**
 * 设备监测因子DO
 */
public class BaseDeviceThingDO {

    private Long deviceId;
    private Integer thingId;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getThingId() {
        return thingId;
    }

    public void setThingId(Integer thingId) {
        this.thingId = thingId;
    }
}
