package com.tcb.vru_service.dao;

import com.tcb.vru_service.pojo.SystemUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户DAO
 */
@Mapper
public interface UserDao {

    /**
     * 查询用户个数
     *
     * @param userDO
     * @return
     */
    int countUser(@Param("userDO") SystemUserDO userDO);

    /**
     * 查询用户数据
     *
     * @param userDO
     * @return
     */
    List<SystemUserDO> listUser(@Param("userDO") SystemUserDO userDO);

    /**
     * 插入用户数据
     *
     * @param userDO
     * @return
     */
    int insertUser(@Param("userDO") SystemUserDO userDO);

    /**
     * 更新用户数据
     *
     * @param userDO
     * @return
     */
    int updateUser(@Param("userDO") SystemUserDO userDO);

    /**
     * 删除用户数据
     *
     * @param listUserId
     * @return
     */
    int deleteListUser(@Param("listUserId") List<Integer> listUserId);

    /**
     * 查询账号和密码是否存在
     *
     * @param userCode
     * @param userPassword
     * @return
     */
    int selectByNameAndPassword(@Param("userCode") String userCode, @Param("userPassword") String userPassword);
}
