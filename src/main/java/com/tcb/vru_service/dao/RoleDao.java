package com.tcb.vru_service.dao;

import com.tcb.vru_service.pojo.SystemRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色DAO
 */
@Mapper
public interface RoleDao {

    /**
     * 查询角色个数
     *
     * @param roleDO
     * @return
     */
    int countRole(@Param("roleDO") SystemRoleDO roleDO);

    /**
     * 查询角色数据
     *
     * @param roleDO
     * @return
     */
    List<SystemRoleDO> listRole(@Param("roleDO") SystemRoleDO roleDO);

    /**
     * 插入角色数据
     *
     * @param roleDO
     * @return
     */
    int insertRole(@Param("roleDO") SystemRoleDO roleDO);

    /**
     * 更新角色数据
     *
     * @param roleDO
     * @return
     */
    int updateRole(@Param("roleDO") SystemRoleDO roleDO);

    /**
     * 删除角色数据
     *
     * @param listRoleId
     * @return
     */
    int deleteListRole(@Param("listRoleId") List<Integer> listRoleId);

}
