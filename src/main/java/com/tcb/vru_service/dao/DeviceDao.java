package com.tcb.vru_service.dao;

import com.tcb.vru_service.pojo.BaseDeviceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 监测设备DAO
 */
@Mapper
public interface DeviceDao {


    /**
     * 获取权限设备基本信息（code+name）
     *
     * @param institutionId
     * @param userCode
     * @return
     */
    List<Map> getAuthorityDeviceHead(@Param("institutionId") String institutionId, @Param("userCode") String userCode);

    /**
     * 统计设备状态
     *
     * @param deviceCodes
     * @return
     */
    List<Map> onOffLineStatistic(@Param("deviceCodes") List<String> deviceCodes);

    /**
     * 查询监测机构下权限设备
     *
     * @param institutionId
     * @param userCode
     * @return
     */
    List<BaseDeviceDO> selectDeviceByInstitution(@Param("institutionId") Long institutionId,
                                                 @Param("userCode") String userCode);

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

    /**
     * 通过DeviceId获取DeviceCode
     *
     * @param listDeviceId
     * @return
     */
    List<String> selectDeviceCodeById(@Param("listDeviceId") List<Integer> listDeviceId);


}
