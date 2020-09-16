package com.tcb.vru_service.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统字典表DAO
 */
@Mapper
public interface DictionaryDao {

    /**
     * 获取字典表Name
     * @param dictionaryCode
     * @param dictionaryType
     * @return
     */
    String selectDictionaryNameByCode(@Param("dictionaryCode") String dictionaryCode,
                                      @Param("dictionaryType") String dictionaryType);

}
