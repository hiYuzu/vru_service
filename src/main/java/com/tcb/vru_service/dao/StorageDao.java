package com.tcb.vru_service.dao;

import com.tcb.vru_service.pojo.DataStorageDO;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * 数据DAO
 */
public interface StorageDao {

    /**
     * 查询数据个数
     *
     * @param listDataId
     * @param listThingCode
     * @param dataType
     * @param listThingFlag
     * @param beginTime
     * @param endTime
     * @return
     */
    List<DataStorageDO> countStorage(@Param("listDataId") List<Integer> listDataId,
                                     @Param("listThingCode") List<String> listThingCode,
                                     @Param("dataType") Integer dataType,
                                     @Param("listThingFlag") List<String> listThingFlag,
                                     @Param("beginTime") Timestamp beginTime,
                                     @Param("endTime") Timestamp endTime);

    /**
     * 查询数据
     *
     * @param listDataId
     * @param listThingCode
     * @param dataType
     * @param listThingFlag
     * @param beginTime
     * @param endTime
     * @param rowIndex
     * @param rowCount
     * @return
     */
    List<DataStorageDO> listStorage(@Param("listDataId") List<Integer> listDataId,
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
    int updateStorage(@Param("dataId") Integer dataId,
                      @Param("dataType") Integer dataType,
                      @Param("beginTime") Timestamp beginTime,
                      @Param("thingValue") Double thingValue);

}
