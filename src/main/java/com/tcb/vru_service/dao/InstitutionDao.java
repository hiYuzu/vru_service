package com.tcb.vru_service.dao;

import com.tcb.vru_service.pojo.BaseInstitutionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 监测机构DAO
 */
@Mapper
public interface InstitutionDao {

    /**
     * 获取发油库
     *
     * @param userCode
     * @return
     */
    List<Map> getInstitutionHead();

    /**
     * 查询监测机构个数
     *
     * @param institutionDO
     * @param institutionIdList
     * @return
     */
    int countInstitution(
            @Param("institutionDO") BaseInstitutionDO institutionDO,
            @Param("institutionIdList") List<Long> institutionIdList);

    /**
     * 查询监测机构数据
     *
     * @param institutionDO
     * @param institutionIdList
     * @return
     */
    List<BaseInstitutionDO> listInstitution(
            @Param("institutionDO") BaseInstitutionDO institutionDO,
            @Param("institutionIdList") List<Long> institutionIdList);

    /**
     * 插入监测机构数据
     *
     * @param institutionDO
     * @return
     */
    int insertInstitution(@Param("institutionDO") BaseInstitutionDO institutionDO);

    /**
     * 更新监测机构数据
     *
     * @param institutionDO
     * @return
     */
    int updateInstitution(@Param("institutionDO") BaseInstitutionDO institutionDO);

    /**
     * 删除监测机构数据
     *
     * @param listInstitutionId
     * @return
     */
    int deleteListInstitution(@Param("listInstitutionId") List<Long> listInstitutionId);

    /**
     * 通过机构Id获取机构Code
     *
     * @param listInstitutionId
     * @return
     */
    List<String> selectInstitutionCodeById(@Param("listInstitutionId") List<Long> listInstitutionId);

}
