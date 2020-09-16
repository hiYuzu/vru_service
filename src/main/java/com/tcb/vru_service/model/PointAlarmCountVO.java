package com.tcb.vru_service.model;

/**
 * @Author: WangLei
 * @Description: GIS坐标点报警信息VO
 * @Date: Create in 2020/09/15 09:27
 * @Modify by WangLei
 */
public class PointAlarmCountVO {

    private String alarmCode;
    private String alarmName;
    private Integer warnCount;
    private Integer alarmCount;
    private String rate;

    public String getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public Integer getWarnCount() {
        return warnCount;
    }

    public void setWarnCount(Integer warnCount) {
        this.warnCount = warnCount;
    }

    public Integer getAlarmCount() {
        return alarmCount;
    }

    public void setAlarmCount(Integer alarmCount) {
        this.alarmCount = alarmCount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

}
