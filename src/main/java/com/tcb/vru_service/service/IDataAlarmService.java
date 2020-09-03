package com.tcb.vru_service.service;

import com.tcb.vru_service.model.DataAlarmVO;
import com.tcb.vru_service.pojo.DataAlarmDO;

import java.util.List;
import java.util.Map;

/**
 * @author hiYuzu
 * @version V1.0
 * @date 2020/8/31 12:07
 */
public interface IDataAlarmService {
    /**
     * 查询报警个数
     * @param dataAlarmDO
     * @return
     */
    Integer getAlarmDataCount(DataAlarmDO dataAlarmDO);

    /**
     * 获取表data_alarm报警信息
     * @param dataAlarmDO
     * @return
     */
    /*
    List<DataAlarmDO> getAlarmData(DataAlarmDO dataAlarmDO);
    */

    /**
     * 获取仅用于展示的报警信息
     * @param dataAlarmDO
     * @param rowIndex
     * @param rowCount
     * @return
     */
    List<DataAlarmVO> getAlarmShowData(DataAlarmDO dataAlarmDO, String rowIndex, String rowCount);

    /**
     * 获取报警类型字典
     * @return
     */
    List<Map> getAlarmDictionary();
}
