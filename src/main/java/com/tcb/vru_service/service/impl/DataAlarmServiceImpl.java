package com.tcb.vru_service.service.impl;

import com.tcb.vru_service.dao.AlarmDao;
import com.tcb.vru_service.model.DataAlarmVO;
import com.tcb.vru_service.pojo.DataAlarmDO;
import com.tcb.vru_service.service.IDataAlarmService;
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
    public Map<String, List> alarmRank(String levelNo, int timeRange, ArrayList<String> deviceCodes) {
        String endTime = DateUtil.GetSystemDateTime(DateUtil.DATA_TIME_SECOND);
        String beginTime = DateUtil.TimestampToString(DateUtil.GetSystemDateTime((long) timeRange * 24 * 60 * 60 * 1000), DateUtil.DATA_TIME_SECOND);
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
}
