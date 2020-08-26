package com.tcb.vru_service.pojo;

import com.tcb.vru_service.base.BaseDO;

import java.sql.Timestamp;

/**
 * 报警设置DO
 */
public class AlarmSetDO extends BaseDO {

    private Integer alarmSetId;
    private String deviceCode;
    private String thingCode;
    private String alarmCode;
    private Integer dataType;
    private Double minValue;
    private Double maxValue;
    private Boolean smsFlag;
    private Boolean enableFlag;
    private Integer planId;
    private Timestamp beginTime;
    private Timestamp endTime;
    private Integer levelNo;
    private Integer dataFlag;

    public Integer getAlarmSetId() {
        return alarmSetId;
    }

    public void setAlarmSetId(Integer alarmSetId) {
        this.alarmSetId = alarmSetId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getThingCode() {
        return thingCode;
    }

    public void setThingCode(String thingCode) {
        this.thingCode = thingCode;
    }

    public String getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Boolean getSmsFlag() {
        return smsFlag;
    }

    public void setSmsFlag(Boolean smsFlag) {
        this.smsFlag = smsFlag;
    }

    public Boolean getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Boolean enableFlag) {
        this.enableFlag = enableFlag;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
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

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public Integer getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(Integer dataFlag) {
        this.dataFlag = dataFlag;
    }
}
