package com.tcb.vru_service.service.impl;

import com.tcb.vru_service.dao.AlarmDao;
import com.tcb.vru_service.model.DataAlarmVO;
import com.tcb.vru_service.pojo.DataAlarmDO;
import com.tcb.vru_service.response.ResultVO;
import com.tcb.vru_service.service.IDataAlarmService;
import com.tcb.vru_service.util.AlarmCodeEnum;
import com.tcb.vru_service.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author hiYuzu
 * @version V1.0
 * @date 2020/8/31 13:36
 */
@Service("dataAlarmService")
public class DataAlarmServiceImpl implements IDataAlarmService {

    @Resource
    private AlarmDao alarmDao;

    @Override
    public Integer getAlarmDataCount(DataAlarmDO dataAlarmDO) {
        return alarmDao.getAlarmDataCount(dataAlarmDO);
    }

    @Override
    public List<DataAlarmVO> getAlarmShowData(DataAlarmDO dataAlarmDO, String rowIndex, String rowCount) {
        return alarmDao.getAlarmShowData(dataAlarmDO, rowIndex, rowCount);
    }

    @Override
    public List<Map> getAlarmDictionary() {
        return alarmDao.getAlarmDictionary();
    }

    @Override
    public Map<String, List> alarmRank(String levelNo, String beginTime, String endTime, ArrayList<String> deviceCodes) {
        List<Map<String, Object>> list = alarmDao.alarmRank(levelNo, beginTime, endTime, deviceCodes);
        ArrayList<String> xAxisData = new ArrayList<>();
        ArrayList<Integer> seriesData = new ArrayList<>();
        for (Map<String, Object> map : list) {
            if (map.containsKey("institutionName")) {
                xAxisData.add((String) map.get("institutionName"));
            } else {
                xAxisData.add("未命名油库");
            }
            seriesData.add((Integer) map.get("alarmTimes"));
        }
        Map<String, List> result = new HashMap<>(3);
        result.put("xAxisData", xAxisData);
        result.put("seriesData", seriesData);
        return result;
    }

    @Override
    public Map<String, Integer> getAlarmPercent(String levelNo, String beginTime, String endTime, ArrayList<String> deviceCodes) {
        Map<String, Integer> result = new HashMap<>(5);
        result.put(AlarmCodeEnum.QYB.toString(), alarmDao.getAlarmCodeCount(AlarmCodeEnum.QYB.toString(), levelNo, beginTime, endTime, deviceCodes));
        result.put(AlarmCodeEnum.YL.toString(), alarmDao.getAlarmCodeCount(AlarmCodeEnum.YL.toString(), levelNo, beginTime, endTime, deviceCodes));
        result.put(AlarmCodeEnum.ND.toString(), alarmDao.getAlarmCodeCount(AlarmCodeEnum.ND.toString(), levelNo, beginTime, endTime, deviceCodes));
        result.put(AlarmCodeEnum.LLB.toString(), alarmDao.getAlarmCodeCount(AlarmCodeEnum.LLB.toString(), levelNo, beginTime, endTime, deviceCodes));
        result.put(AlarmCodeEnum.O.toString(), alarmDao.getAlarmCodeCount(AlarmCodeEnum.O.toString(), levelNo, beginTime, endTime, deviceCodes));
        return result;
    }

    @Override
    public Map getAlarmStatistic(String levelNo, String deviceCode, String beginTime, String endTime) {

        List<Map> allResult = alarmDao.getAlarmStatistic(levelNo, deviceCode, beginTime, endTime, null);

        ArrayList<String> xAxisData = new ArrayList<>();
        for (Map temp : allResult) {
            xAxisData.add(DateUtil.TimestampToString(DateUtil.StringToTimestampSecond(temp.get("alarmTime").toString()), "MM/dd"));
        }

        List<Map> qybList = alarmDao.getAlarmStatistic(levelNo, deviceCode, beginTime, endTime, AlarmCodeEnum.QYB.toString());
        ArrayList<Integer> qybData = new ArrayList<>();
        List<Map> ylList = alarmDao.getAlarmStatistic(levelNo, deviceCode, beginTime, endTime, AlarmCodeEnum.YL.toString());
        ArrayList<Integer> ylData = new ArrayList<>();
        List<Map> ndList = alarmDao.getAlarmStatistic(levelNo, deviceCode, beginTime, endTime, AlarmCodeEnum.ND.toString());
        ArrayList<Integer> ndData = new ArrayList<>();
        List<Map> llbList = alarmDao.getAlarmStatistic(levelNo, deviceCode, beginTime, endTime, AlarmCodeEnum.LLB.toString());
        ArrayList<Integer> llbData = new ArrayList<>();

        int qybIndex = 0;
        int ylIndex = 0;
        int ndIndex = 0;
        int llbIndex = 0;
        for (Map temp : allResult) {
            String alarmTime = DateUtil.TimestampToString(DateUtil.StringToTimestampSecond(temp.get("alarmTime").toString()), DateUtil.DATA_DAY);

            if (alarmTime != null && qybList.size() > qybIndex && alarmTime.equals(DateUtil.TimestampToString(DateUtil.StringToTimestampSecond(qybList.get(qybIndex).get("alarmTime").toString()), DateUtil.DATA_DAY))) {
                qybData.add((Integer)(qybList.get(qybIndex).get("alarmCount")));
                qybIndex++;
            }
            else {
                qybData.add(0);
            }

            if (alarmTime != null && ylList.size() > ylIndex && alarmTime.equals(DateUtil.TimestampToString(DateUtil.StringToTimestampSecond(ylList.get(ylIndex).get("alarmTime").toString()), DateUtil.DATA_DAY))) {
                ylData.add((Integer)(ylList.get(ylIndex).get("alarmCount")));
                ylIndex++;
            }
            else {
                ylData.add(0);
            }

            if (alarmTime != null && ndList.size() > ndIndex && alarmTime.equals(DateUtil.TimestampToString(DateUtil.StringToTimestampSecond(ndList.get(ndIndex).get("alarmTime").toString()), DateUtil.DATA_DAY))) {
                ndData.add((Integer)(ndList.get(ndIndex).get("alarmCount")));
                ndIndex++;
            }
            else {
                ndData.add(0);
            }

            if (alarmTime != null && llbList.size() > llbIndex && alarmTime.equals(DateUtil.TimestampToString(DateUtil.StringToTimestampSecond(llbList.get(llbIndex).get("alarmTime").toString()), DateUtil.DATA_DAY))) {
                llbData.add((Integer)(llbList.get(llbIndex).get("alarmCount")));
                llbIndex++;
            }
            else {
                llbData.add(0);
            }
        }

        List<Map<String, Object>> seriesData = new ArrayList<>();
        Map<String, Object> qybMap = new HashMap<>(5);
        Map<String, Object> ylMap = new HashMap<>(5);
        Map<String, Object> ndMap = new HashMap<>(5);
        Map<String, Object> llbMap = new HashMap<>(5);
        qybMap.put("name", "气液比");
        qybMap.put("type", "bar");
        qybMap.put("data", qybData);
        ylMap.put("name", "压力值");
        ylMap.put("type", "bar");
        ylMap.put("data", ylData);
        ndMap.put("name", "浓度值");
        ndMap.put("type", "bar");
        ndMap.put("data", ndData);
        llbMap.put("name", "流量比");
        llbMap.put("type", "bar");
        llbMap.put("data", llbData);
        seriesData.add(qybMap);
        seriesData.add(ylMap);
        seriesData.add(ndMap);
        seriesData.add(llbMap);

        Map<String, List> result = new HashMap<>(3);
        result.put("xAxisData", xAxisData);
        result.put("seriesData", seriesData);
        return result;
    }
}
