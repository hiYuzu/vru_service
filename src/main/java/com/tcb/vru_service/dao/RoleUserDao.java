package com.tcb.vru_service.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: WangLei
 * @Description: 角色用户DAO
 * @Date: Create in 2020/07/13 09:52
 * @Modify by WangLei
 */
@Mapper
public interface RoleUserDao {

    /**
     * 查询用户角色编码
     *
     * @param userCode
     * @return
     */
    List<String> selectRoleCodeByUserCode(@Param("userCode") String userCode);

}
