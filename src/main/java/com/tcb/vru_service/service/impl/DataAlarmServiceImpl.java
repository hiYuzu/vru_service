package com.tcb.vru_service.service.impl;

import com.tcb.vru_service.dao.AlarmDao;
import com.tcb.vru_service.model.DataAlarmVO;
import com.tcb.vru_service.pojo.DataAlarmDO;
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
}
