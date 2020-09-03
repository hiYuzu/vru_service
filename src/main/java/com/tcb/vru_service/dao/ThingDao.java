package com.tcb.vru_service.dao;

import com.tcb.vru_service.pojo.BaseDeviceThingDO;
import com.tcb.vru_service.pojo.BaseThingDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 监测因子DAO
 */
@Mapper
public interface ThingDao {

    /**
     * 根据监测物质编码查询名称
     * @param thingCode
     * @return
     */
    String selectThingNameByCode(@Param("thingCode") String thingCode);

    /**
     * 查询监测因子个数
     *
     * @param thingDO
     * @return
     */
    int countThing(@Param("thingDO") BaseThingDO thingDO);

    /**
     * 查询监测因子数据
     *
     * @param thingDO
     * @return
     */
    List<BaseThingDO> listThing(@Param("thingDO") BaseThingDO thingDO);

    List<BaseThingDO> getThingByDeviceId(@Param("deviceId") Integer deviceId);

    List<BaseDeviceThingDO> getDeviceThingData();

    /**
     * 插入监测因子数据
     *
     * @param thingDO
     * @return
     */
    int insertThing(@Param("thingDO") BaseThingDO thingDO);

    /**
     * 更新监测因子数据
     *
     * @param thingDO
     * @return
     */
    int updateThing(@Param("thingDO") BaseThingDO thingDO);

    /**
     * 删除监测因子数据
     *
     * @param listThingId
     * @return
     */
    int deleteListThing(@Param("listThingId") List<Integer> listThingId);

}
