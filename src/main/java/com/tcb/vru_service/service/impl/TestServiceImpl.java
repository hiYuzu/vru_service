package com.tcb.vru_service.service.impl;

import com.tcb.vru_service.dao.TestDao;
import com.tcb.vru_service.service.ITestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("testService")
public class TestServiceImpl implements ITestService {

    @Resource
    private TestDao testDao;


    @Override
    public List<Map> getData(List<Integer> idList, String beginTime, String endTime) {
        return testDao.getData(idList,beginTime,endTime);
    }

    @Override
    public List<Map> getDeviceData() {
        return testDao.getTestData();
    }

    @Override
    public List<Map> getAlarmData(String beginTime,String endTime) {
        return testDao.getAlarmData(beginTime,endTime);
    }
}
