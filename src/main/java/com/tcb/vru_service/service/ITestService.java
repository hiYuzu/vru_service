package com.tcb.vru_service.service;

import java.util.List;
import java.util.Map;

public interface ITestService {

    List<Map> getDeviceData();

    List<Map> getAlarmData(String beginTime,String endTime);

    List<Map> getData(List<Integer> idList,String beginTime,String endTime);

}
