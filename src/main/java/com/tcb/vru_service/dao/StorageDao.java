package com.tcb.vru_service.dao;

import com.tcb.vru_service.pojo.DataStorageDO;
import com.tcb.vru_service.pojo.DataStorageOilDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * 数据DAO
 */
@Mapper
public interface StorageDao {

    /**
     * 查询数据个数
     *
     * @param listDeviceId
     * @param listThingCode
     * @param dataType
     * @param listThingFlag
     * @param beginTime
     * @param endTime
     * @return
     */
    int countStorage(@Param("listDeviceId") List<Integer> listDeviceId,
                     @Param("listThingCode") List<String> listThingCode,
                     @Param("dataType") Integer dataType,
                     @Param("listThingFlag") List<String> listThingFlag,
                     @Param("beginTime") Timestamp beginTime,
                     @Param("endTime") Timestamp endTime);

    /**
     * 查询数据
     *
     * @param listDeviceId
     * @param listThingCode
     * @param dataType
     * @param listThingFlag
     * @param beginTime
     * @param endTime
     * @param rowIndex
     * @param rowCount
     * @return
     */
    List<DataStorageDO> listStorage(@Param("listDeviceId") List<Integer> listDeviceId,
                                    @Param("listThingCode") List<String> listThingCode,
                                    @Param("dataType") Integer dataType,
                                    @Param("listThingFlag") List<String> listThingFlag,
                                    @Param("beginTime") Timestamp beginTime,
                                    @Param("endTime") Timestamp endTime,
                                    @Param("rowIndex") Integer rowIndex,
                                    @Param("rowCount") Integer rowCount);

    /**
     * 更新数据
     *
     * @param dataId
     * @param dataType
     * @param beginTime
     * @param thingValue
     * @return
     */
    int updateStorage(@Param("dataId") Long dataId,
                      @Param("dataType") Integer dataType,
                      @Param("beginTime") Timestamp beginTime,
                      @Param("thingValue") Double thingValue);

    /**
     * 查询发油信息
     *
     * @param dataStorageOilDO
     * @param listDeviceId
     * @param beginTime
     * @param endTime
     * @return
     */
    List<DataStorageOilDO> listStorageOil(@Param("dataStorageOilDO") DataStorageOilDO dataStorageOilDO,
                                          @Param("listDeviceId") List<Integer> listDeviceId,
                                          @Param("beginTime") Timestamp beginTime,
                                          @Param("endTime") Timestamp endTime);

    /**
     * 查询最近更新数据
     *
     * @param listDeviceCode
     * @param listThingCode
     * @param dataType
     * @return
     */
    List<DataStorageDO> listRecentValue(@Param("listDeviceCode") List<String> listDeviceCode,
                                        @Param("listThingCode") List<String> listThingCode,
                                        @Param("dataType") Integer dataType);

}
