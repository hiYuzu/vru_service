package com.tcb.vru_service.dao;

import com.tcb.vru_service.pojo.BaseAreaDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 区域DAO
 */
@Mapper
public interface AreaDao {

    /**
     * 查询区域个数
     *
     * @param areaDO
     * @return
     */
    int countArea(@Param("areaDO") BaseAreaDO areaDO);

    /**
     * 查询区域数据
     *
     * @param areaDO
     * @return
     */
    List<BaseAreaDO> listArea(@Param("areaDO") BaseAreaDO areaDO);

    /**
     * 插入区域数据
     *
     * @param areaDO
     * @return
     */
    int insertArea(@Param("areaDO") BaseAreaDO areaDO);

    /**
     * 更新区域数据
     *
     * @param areaDO
     * @return
     */
    int updateArea(@Param("areaDO") BaseAreaDO areaDO);

    /**
     * 删除区域数据
     *
     * @param listAreaId
     * @return
     */
    int deleteListArea(@Param("listAreaId") List<Integer> listAreaId);

}
