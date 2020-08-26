package com.tcb.vru_service.dao;

import com.tcb.vru_service.pojo.SystemRoleResourceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: WangLei
 * @Description: 角色资源DAO
 * @Date: Create in 2020/07/10 15:22
 * @Modify by WangLei
 */
@Mapper
public interface RoleResourceDao {

    /**
     * 通过roleCode查询资源数据
     * @param roleCodeList
     * @return
     */
    List<SystemRoleResourceDO> selectResourceByRoleCode(@Param("roleCodeList")List<String> roleCodeList);

}
