package com.tcb.vru_service.pojo;

import com.tcb.vru_service.base.BaseDO;

import java.sql.Timestamp;

/**
 * 设备DO
 */
public class BaseDeviceDO extends BaseDO {

    private Integer deviceId;
    private String deviceCode;
    private String deviceMn;
    private String deviceName;
    private Integer fieldId;
    private Integer manufacturerId;
    private Long institutionId;
    private String deviceStatus;
    private String deviceIp;
    private Integer devicePort;
    private String devicePwd;
    private Double mapX;
    private Double mapY;
    private Double gpsX;
    private Double gpsY;
    private Integer superviseOrganization;
    private String buildUnit;
    private Integer deviceManager;
    private String deviceAddress;
    private String monitorArea;
    private Boolean makeMinute;
    private Boolean makeHour;
    private Boolean makeDay;
    private String replyFlag;
    private Boolean replyForce;
    private Boolean makeFlow;
    private Double pipeArea;
    private Integer hourCount;
    private String gasPath;
    private Timestamp switchTime;
    private String systemVersion;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceMn() {
        return deviceMn;
    }

    public void setDeviceMn(String deviceMn) {
        this.deviceMn = deviceMn;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Long getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Long institutionId) {
        this.institutionId = institutionId;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public Integer getDevicePort() {
        return devicePort;
    }

    public void setDevicePort(Integer devicePort) {
        this.devicePort = devicePort;
    }

    public String getDevicePwd() {
        return devicePwd;
    }

    public void setDevicePwd(String devicePwd) {
        this.devicePwd = devicePwd;
    }

    public Double getMapX() {
        return mapX;
    }

    public void setMapX(Double mapX) {
        this.mapX = mapX;
    }

    public Double getMapY() {
        return mapY;
    }

    public void setMapY(Double mapY) {
        this.mapY = mapY;
    }

    public Double getGpsX() {
        return gpsX;
    }

    public void setGpsX(Double gpsX) {
        this.gpsX = gpsX;
    }

    public Double getGpsY() {
        return gpsY;
    }

    public void setGpsY(Double gpsY) {
        this.gpsY = gpsY;
    }

    public Integer getSuperviseOrganization() {
        return superviseOrganization;
    }

    public void setSuperviseOrganization(Integer superviseOrganization) {
        this.superviseOrganization = superviseOrganization;
    }

    public String getBuildUnit() {
        return buildUnit;
    }

    public void setBuildUnit(String buildUnit) {
        this.buildUnit = buildUnit;
    }

    public Integer getDeviceManager() {
        return deviceManager;
    }

    public void setDeviceManager(Integer deviceManager) {
        this.deviceManager = deviceManager;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public String getMonitorArea() {
        return monitorArea;
    }

    public void setMonitorArea(String monitorArea) {
        this.monitorArea = monitorArea;
    }

    public Boolean getMakeMinute() {
        return makeMinute;
    }

    public void setMakeMinute(Boolean makeMinute) {
        this.makeMinute = makeMinute;
    }

    public Boolean getMakeHour() {
        return makeHour;
    }

    public void setMakeHour(Boolean makeHour) {
        this.makeHour = makeHour;
    }

    public Boolean getMakeDay() {
        return makeDay;
    }

    public void setMakeDay(Boolean makeDay) {
        this.makeDay = makeDay;
    }

    public String getReplyFlag() {
        return replyFlag;
    }

    public void setReplyFlag(String replyFlag) {
        this.replyFlag = replyFlag;
    }

    public Boolean getReplyForce() {
        return replyForce;
    }

    public void setReplyForce(Boolean replyForce) {
        this.replyForce = replyForce;
    }

    public Boolean getMakeFlow() {
        return makeFlow;
    }

    public void setMakeFlow(Boolean makeFlow) {
        this.makeFlow = makeFlow;
    }

    public Double getPipeArea() {
        return pipeArea;
    }

    public void setPipeArea(Double pipeArea) {
        this.pipeArea = pipeArea;
    }

    public Integer getHourCount() {
        return hourCount;
    }

    public void setHourCount(Integer hourCount) {
        this.hourCount = hourCount;
    }

    public String getGasPath() {
        return gasPath;
    }

    public void setGasPath(String gasPath) {
        this.gasPath = gasPath;
    }

    public Timestamp getSwitchTime() {
        return switchTime;
    }

    public void setSwitchTime(Timestamp switchTime) {
        this.switchTime = switchTime;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }
}
