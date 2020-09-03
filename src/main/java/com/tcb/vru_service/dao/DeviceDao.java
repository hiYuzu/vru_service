package com.tcb.vru_service.dao;

import com.tcb.vru_service.pojo.BaseDeviceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 监测设备DAO
 */
@Mapper
public interface DeviceDao {

    /**
     * 查询监测设备个数
     *
     * @param deviceDO
     * @return
     */
    int countDevice(@Param("deviceDO") BaseDeviceDO deviceDO);

    /**
     * 查询监测设备数据
     *
     * @param deviceDO
     * @return
     */
    List<BaseDeviceDO> listDevice(@Param("deviceDO") BaseDeviceDO deviceDO);

    /**
     * 插入监测设备数据
     *
     * @param deviceDO
     * @return
     */
    int insertDevice(@Param("deviceDO") BaseDeviceDO deviceDO);

    /**
     * 更新监测设备数据
     *
     * @param deviceDO
     * @return
     */
    int updateDevice(@Param("deviceDO") BaseDeviceDO deviceDO);

    /**
     * 删除监测设备数据
     *
     * @param listDeviceId
     * @return
     */
    int deleteListDevice(@Param("listDeviceId") List<Integer> listDeviceId);


}
