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
            if (map.containsKey("deviceName")) {
                xAxisData.add((String) map.get("deviceName"));
            } else {
                xAxisData.add((String) map.get("deviceCode"));
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
        result.put(AlarmCodeEnum.GLR.toString(), alarmDao.getAlarmCodeCount(AlarmCodeEnum.GLR.toString(), levelNo, beginTime, endTime, deviceCodes));
        result.put(AlarmCodeEnum.PRE.toString(), alarmDao.getAlarmCodeCount(AlarmCodeEnum.PRE.toString(), levelNo, beginTime, endTime, deviceCodes));
        result.put(AlarmCodeEnum.NMHC.toString(), alarmDao.getAlarmCodeCount(AlarmCodeEnum.NMHC.toString(), levelNo, beginTime, endTime, deviceCodes));
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

        List<Map> glrList = alarmDao.getAlarmStatistic(levelNo, deviceCode, beginTime, endTime, AlarmCodeEnum.GLR.toString());
        ArrayList<Integer> glrData = new ArrayList<>();
        List<Map> preList = alarmDao.getAlarmStatistic(levelNo, deviceCode, beginTime, endTime, AlarmCodeEnum.PRE.toString());
        ArrayList<Integer> preData = new ArrayList<>();
        List<Map> nmhcList = alarmDao.getAlarmStatistic(levelNo, deviceCode, beginTime, endTime, AlarmCodeEnum.NMHC.toString());
        ArrayList<Integer> nmhcData = new ArrayList<>();

        int glrIndex = 0;
        int preIndex = 0;
        int nmhcIndex = 0;
        for (Map temp : allResult) {
            String alarmTime = DateUtil.TimestampToString(DateUtil.StringToTimestampSecond(temp.get("alarmTime").toString()), DateUtil.DATA_DAY);

            if (alarmTime != null && glrList.size() > 0 && alarmTime.equals(DateUtil.TimestampToString(DateUtil.StringToTimestampSecond(glrList.get(glrIndex).get("alarmTime").toString()), DateUtil.DATA_DAY))) {
                glrData.add((Integer)(glrList.get(glrIndex).get("alarmCount")));
                glrIndex++;
            }
            else {
                glrData.add(0);
            }

            if (alarmTime != null && preList.size() > 0 && alarmTime.equals(DateUtil.TimestampToString(DateUtil.StringToTimestampSecond(preList.get(preIndex).get("alarmTime").toString()), DateUtil.DATA_DAY))) {
                preData.add((Integer)(preList.get(preIndex).get("alarmCount")));
                preIndex++;
            }
            else {
                preData.add(0);
            }

            if (alarmTime != null && nmhcList.size() > 0 && alarmTime.equals(DateUtil.TimestampToString(DateUtil.StringToTimestampSecond(nmhcList.get(nmhcIndex).get("alarmTime").toString()), DateUtil.DATA_DAY))) {
                nmhcData.add((Integer)(nmhcList.get(nmhcIndex).get("alarmCount")));
                nmhcIndex++;
            }
            else {
                nmhcData.add(0);
            }
        }

        List<Map<String, Object>> seriesData = new ArrayList<>();
        Map<String, Object> glrMap = new HashMap<>(5);
        Map<String, Object> preMap = new HashMap<>(5);
        Map<String, Object> nmhcMap = new HashMap<>(5);
        glrMap.put("name", "气液比");
        glrMap.put("type", "bar");
        glrMap.put("data", glrData);
        preMap.put("name", "压力");
        preMap.put("type", "bar");
        preMap.put("data", preData);
        nmhcMap.put("name", "NMHC浓度");
        nmhcMap.put("type", "bar");
        nmhcMap.put("data", nmhcData);
        seriesData.add(glrMap);
        seriesData.add(preMap);
        seriesData.add(nmhcMap);

        Map<String, List> result = new HashMap<>(3);
        result.put("xAxisData", xAxisData);
        result.put("seriesData", seriesData);
        return result;
    }
}
