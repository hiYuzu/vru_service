package com.tcb.vru_service.dao;

import com.tcb.vru_service.pojo.BaseOrganizationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 组织DAO
 */
@Mapper
public interface OrganizationDao {

    /**
     * 查询组织个数
     *
     * @param organizationDO
     * @return
     */
    int countOrganization(@Param("organizationDO") BaseOrganizationDO organizationDO);

    /**
     * 查询组织数据
     *
     * @param organizationDO
     * @return
     */
    List<BaseOrganizationDO> listOrganization(@Param("organizationDO") BaseOrganizationDO organizationDO);

    /**
     * 插入组织数据
     *
     * @param organizationDO
     * @return
     */
    int insertOrganization(@Param("organizationDO") BaseOrganizationDO organizationDO);

    /**
     * 更新组织数据
     *
     * @param organizationDO
     * @return
     */
    int updateOrganization(@Param("organizationDO") BaseOrganizationDO organizationDO);

    /**
     * 删除组织数据
     *
     * @param listOrganizationId
     * @return
     */
    int deleteListOrganization(@Param("listOrganizationId") List<Integer> listOrganizationId);

}
