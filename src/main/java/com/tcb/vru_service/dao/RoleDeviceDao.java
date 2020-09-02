package com.tcb.vru_service.dao;

import com.tcb.vru_service.pojo.SystemRoleDeviceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色设备DAO
 */
@Mapper
public interface RoleDeviceDao {

    /**
     * 通过roleCode查询设备数据
     *
     * @param roleCodeList
     * @return
     */
    List<SystemRoleDeviceDO> selectDeviceByRoleCode(@Param("roleCodeList") List<String> roleCodeList);

}
