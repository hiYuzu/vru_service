package com.tcb.vru_service.dao;

import com.tcb.vru_service.pojo.BaseInstitutionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 监测机构DAO
 */
@Mapper
public interface InstitutionDao {

    /**
     * 查询监测机构个数
     *
     * @param institutionDO
     * @return
     */
    int countInstitution(@Param("institutionDO") BaseInstitutionDO institutionDO);

    /**
     * 查询监测机构数据
     *
     * @param institutionDO
     * @return
     */
    List<BaseInstitutionDO> listInstitution(@Param("institutionDO") BaseInstitutionDO institutionDO);

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
    int deleteListInstitution(@Param("listInstitutionId") List<Integer> listInstitutionId);

}
