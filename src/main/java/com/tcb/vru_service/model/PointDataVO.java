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

    private TreeMap<String, LinkedHashMap<String, Double>> monitorVOMap;
    private List<DataStorageOilVO> oilVOList;
    private List<PointDataAlarmVO> alarmVOList;

    public TreeMap<String, LinkedHashMap<String, Double>> getMonitorVOMap() {
        return monitorVOMap;
    }

    public void setMonitorVOMap(TreeMap<String, LinkedHashMap<String, Double>> monitorVOMap) {
        this.monitorVOMap = monitorVOMap;
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
