package com.tcb.vru_service.dao;

import com.tcb.vru_service.pojo.AlarmSetDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 报警设置DAO
 */
@Mapper
public interface AlarmSetDao {

    /**
     * 查询报警设置个数
     *
     * @param alarmSetDO
     * @return
     */
    int countAlarmSet(@Param("alarmSetDO") AlarmSetDO alarmSetDO);

    /**
     * 查询报警设置数据
     *
     * @param alarmSetDO
     * @return
     */
    List<AlarmSetDO> listAlarmSet(@Param("alarmSetDO") AlarmSetDO alarmSetDO);

    /**
     * 插入报警设置数据
     *
     * @param alarmSetDO
     * @return
     */
    int insertAlarmSet(@Param("alarmSetDO") AlarmSetDO alarmSetDO);

    /**
     * 更新报警设置数据
     *
     * @param alarmSetDO
     * @return
     */
    int updateAlarmSet(@Param("alarmSetDO") AlarmSetDO alarmSetDO);

    /**
     * 删除报警设置数据
     *
     * @param listAlarmSetId
     * @return
     */
    int deleteListAlarmSet(@Param("listAlarmSetId") List<Integer> listAlarmSetId);

}
