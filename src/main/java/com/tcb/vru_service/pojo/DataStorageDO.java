package com.tcb.vru_service.pojo;

import com.tcb.vru_service.base.BaseDO;

import java.sql.Timestamp;

/**
 * 数据DO
 */
public class DataStorageDO extends BaseDO {

    private Long dataId;
    private Integer deviceId;
    private String thingCode;
    private Double thingAvg;
    private Double thingMax;
    private Double thingMin;
    private Double thingZsAvg;
    private Double thingZsMax;
    private Double thingZsMin;
    private Double thingCou;
    private Double thingZsCou;
    private String thingFlag;
    private Integer dataType;
    private Timestamp updateTime;
    private Timestamp beginTime;
    private Timestamp endTime;

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getThingCode() {
        return thingCode;
    }

    public void setThingCode(String thingCode) {
        this.thingCode = thingCode;
    }

    public Double getThingAvg() {
        return thingAvg;
    }

    public void setThingAvg(Double thingAvg) {
        this.thingAvg = thingAvg;
    }

    public Double getThingMax() {
        return thingMax;
    }

    public void setThingMax(Double thingMax) {
        this.thingMax = thingMax;
    }

    public Double getThingMin() {
        return thingMin;
    }

    public void setThingMin(Double thingMin) {
        this.thingMin = thingMin;
    }

    public Double getThingZsAvg() {
        return thingZsAvg;
    }

    public void setThingZsAvg(Double thingZsAvg) {
        this.thingZsAvg = thingZsAvg;
    }

    public Double getThingZsMax() {
        return thingZsMax;
    }

    public void setThingZsMax(Double thingZsMax) {
        this.thingZsMax = thingZsMax;
    }

    public Double getThingZsMin() {
        return thingZsMin;
    }

    public void setThingZsMin(Double thingZsMin) {
        this.thingZsMin = thingZsMin;
    }

    public Double getThingCou() {
        return thingCou;
    }

    public void setThingCou(Double thingCou) {
        this.thingCou = thingCou;
    }

    public Double getThingZsCou() {
        return thingZsCou;
    }

    public void setThingZsCou(Double thingZsCou) {
        this.thingZsCou = thingZsCou;
    }

    public String getThingFlag() {
        return thingFlag;
    }

    public void setThingFlag(String thingFlag) {
        this.thingFlag = thingFlag;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

}
