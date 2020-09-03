package com.tcb.vru_service.pojo;

import java.sql.Timestamp;

/**
 * 发油数据DO
 */
public class DataStorageOilDO {

    private Long dataId;
    private Integer deviceId;
    private Timestamp dataFysj;
    private String dataYplx;
    private String dataYply;
    private String dataYpqc;
    private Double dataFytj;
    private Double dataHqtj;
    private Double dataFyqyb;
    private Double dataSjxtyl;
    private Timestamp updateTime;

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

    public Timestamp getDataFysj() {
        return dataFysj;
    }

    public void setDataFysj(Timestamp dataFysj) {
        this.dataFysj = dataFysj;
    }

    public String getDataYplx() {
        return dataYplx;
    }

    public void setDataYplx(String dataYplx) {
        this.dataYplx = dataYplx;
    }

    public String getDataYply() {
        return dataYply;
    }

    public void setDataYply(String dataYply) {
        this.dataYply = dataYply;
    }

    public String getDataYpqc() {
        return dataYpqc;
    }

    public void setDataYpqc(String dataYpqc) {
        this.dataYpqc = dataYpqc;
    }

    public Double getDataFytj() {
        return dataFytj;
    }

    public void setDataFytj(Double dataFytj) {
        this.dataFytj = dataFytj;
    }

    public Double getDataHqtj() {
        return dataHqtj;
    }

    public void setDataHqtj(Double dataHqtj) {
        this.dataHqtj = dataHqtj;
    }

    public Double getDataFyqyb() {
        return dataFyqyb;
    }

    public void setDataFyqyb(Double dataFyqyb) {
        this.dataFyqyb = dataFyqyb;
    }

    public Double getDataSjxtyl() {
        return dataSjxtyl;
    }

    public void setDataSjxtyl(Double dataSjxtyl) {
        this.dataSjxtyl = dataSjxtyl;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

}
