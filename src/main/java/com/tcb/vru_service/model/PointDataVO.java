package com.tcb.vru_service.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * @Author: WangLei
 * @Description: 坐标点数据VO
 * @Date: Create in 2020/09/03 09:25
 * @Modify by WangLei
 */
public class PointDataVO {

    private TreeMap<String, LinkedHashMap<String, Double>> monitorData;
    private List<DataStorageOilVO> oilVOList;
    private List<PointDataAlarmVO> alarmVOList;

    public TreeMap<String, LinkedHashMap<String, Double>> getMonitorData() {
        return monitorData;
    }

    public void setMonitorData(TreeMap<String, LinkedHashMap<String, Double>> monitorData) {
        this.monitorData = monitorData;
    }

    public List<DataStorageOilVO> getOilVOList() {
        return oilVOList;
    }

    public void setOilVOList(List<DataStorageOilVO> oilVOList) {
        this.oilVOList = oilVOList;
    }

    public List<PointDataAlarmVO> getAlarmVOList() {
        return alarmVOList;
    }

    public void setAlarmVOList(List<PointDataAlarmVO> alarmVOList) {
        this.alarmVOList = alarmVOList;
    }

}
