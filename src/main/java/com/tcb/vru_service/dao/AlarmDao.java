package com.tcb.vru_service.dao;

import com.tcb.vru_service.model.DataAlarmVO;
import com.tcb.vru_service.pojo.DataAlarmDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author hiYuzu
 * @version V1.0
 * @date 2020/7/16 14:59
 */
@Mapper
public interface AlarmDao {
    /**
     * 查询报警个数
     * @param dataAlarmDO
     * @return
     */
    Integer getAlarmDataCount(@Param("dataAlarmDO") DataAlarmDO dataAlarmDO);


    /**
     * 获取报警信息
     * @param dataAlarmDO
     * @return
     */
    /*
    List<DataAlarmDO> getAlarmData(@Param("dataAlarmDO") DataAlarmDO dataAlarmDO);
    */

    /**
     * 获取仅用于展示的报警信息
     * @param dataAlarmDO
     * @param rowIndex
     * @param rowCount
     * @return
     */
    List<DataAlarmVO> getAlarmShowData(@Param("dataAlarmDO") DataAlarmDO dataAlarmDO, @Param("rowIndex") String rowIndex, @Param("rowCount") String rowCount);

    /**
     * 获取报警类型字典
     * @return
     */
    List<Map> getAlarmDictionary();
}
