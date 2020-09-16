package com.tcb.vru_service.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * @Author: WangLei
 * @Description: GIS坐标点VO
 * @Date: Create in 2020/09/02 09:08
 * @Modify by WangLei
 */
public class PointVO {

    private String pointId;
    private String pointCode;
    private String pointName;
    private Double mapX;
    private Double mapY;
    private Integer alarmCount;
    private Integer warnCount;
    private TreeMap<String, LinkedHashMap<String, Double>> monitorVOMap;
    private List<PointAlarmCountVO> alarmCountVOList;

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getPointCode() {
        return pointCode;
    }

    public void setPointCode(String pointCode) {
        this.pointCode = pointCode;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
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

    public Integer getAlarmCount() {
        return alarmCount;
    }

    public void setAlarmCount(Integer alarmCount) {
        this.alarmCount = alarmCount;
    }

    public Integer getWarnCount() {
        return warnCount;
    }

    public void setWarnCount(Integer warnCount) {
        this.warnCount = warnCount;
    }

    public TreeMap<String, LinkedHashMap<String, Double>> getMonitorVOMap() {
        return monitorVOMap;
    }

    public void setMonitorVOMap(TreeMap<String, LinkedHashMap<String, Double>> monitorVOMap) {
        this.monitorVOMap = monitorVOMap;
    }

    public List<PointAlarmCountVO> getAlarmCountVOList() {
        return alarmCountVOList;
    }

    public void setAlarmCountVOList(List<PointAlarmCountVO> alarmCountVOList) {
        this.alarmCountVOList = alarmCountVOList;
    }

}
