package com.tcb.vru_service.dao;

import com.tcb.vru_service.pojo.BaseManufacturerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 生产厂商DAO
 */
@Mapper
public interface ManufacturerDao {

    /**
     * 查询生产厂商个数
     *
     * @param manufacturerDO
     * @return
     */
    int countManufacturer(@Param("manufacturerDO") BaseManufacturerDO manufacturerDO);

    /**
     * 查询生产厂商数据
     *
     * @param manufacturerDO
     * @return
     */
    List<BaseManufacturerDO> listManufacturer(@Param("manufacturerDO") BaseManufacturerDO manufacturerDO);

    /**
     * 插入生产厂商数据
     *
     * @param manufacturerDO
     * @return
     */
    int insertManufacturer(@Param("manufacturerDO") BaseManufacturerDO manufacturerDO);

    /**
     * 更新生产厂商数据
     *
     * @param manufacturerDO
     * @return
     */
    int updateManufacturer(@Param("manufacturerDO") BaseManufacturerDO manufacturerDO);

    /**
     * 删除生产厂商数据
     *
     * @param listManufacturerId
     * @return
     */
    int deleteListManufacturer(@Param("listManufacturerId") List<Integer> listManufacturerId);

}
