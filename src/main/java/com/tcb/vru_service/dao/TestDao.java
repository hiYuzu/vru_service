package com.tcb.vru_service.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestDao {

    List<Map> getData(
            @Param("idList") List<Integer> idList,
            @Param("beginTime") String beginTime,
            @Param("endTime") String endTime);

    List<Map> getTestData();

    List<Map> getAlarmData(
            @Param("beginTime") String beginTime,
            @Param("endTime") String endTime);

}
