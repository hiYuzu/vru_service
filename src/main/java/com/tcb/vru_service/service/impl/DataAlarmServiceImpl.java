package com.tcb.vru_service.service.impl;

import com.tcb.vru_service.dao.AlarmDao;
import com.tcb.vru_service.model.DataAlarmVO;
import com.tcb.vru_service.pojo.DataAlarmDO;
import com.tcb.vru_service.service.IDataAlarmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    /*
    @Override
    public List<DataAlarmDO> getAlarmData(DataAlarmDO dataAlarmDO) {
        return alarmDao.getAlarmData(dataAlarmDO);
    }
    */

    @Override
    public List<DataAlarmVO> getAlarmShowData(DataAlarmDO dataAlarmDO, String rowIndex, String rowCount) {
        return alarmDao.getAlarmShowData(dataAlarmDO, rowIndex, rowCount);
    }

    @Override
    public List<Map> getAlarmDictionary() {
        return alarmDao.getAlarmDictionary();
    }
}
