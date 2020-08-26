package com.tcb.vru_service.controller;

import com.tcb.vru_service.service.ITestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {

    @Resource
    private ITestService testService;

    @RequestMapping("/test/device")
    public List<Map> testDevice() {
        try {
            List<Map> map = testService.getDeviceData();
            return map;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @RequestMapping("/test/alarm")
    public List<Map> testAlarm(
            @RequestParam(required = false) String beginTime,
            @RequestParam(required = false) String endTime) {
        try {
            if(StringUtils.isEmpty(beginTime)){
                beginTime = "2020-04-01";
            }
            if(StringUtils.isEmpty(endTime)){
                endTime = "2020-06-30";
            }
            List<Map> map = testService.getAlarmData(beginTime,endTime);
            return map;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @RequestMapping("/test/data")
    public List<Map> testData(
            @RequestParam(required = false) String beginTime,
            @RequestParam(required = false) String endTime) {
        try {
            if(StringUtils.isEmpty(beginTime)){
                beginTime = "2020-04-01";
            }
            if(StringUtils.isEmpty(endTime)){
                endTime = "2020-06-15";
            }
            List<Integer> idList = new ArrayList<>();
            idList.add(12);
            idList.add(13);
            List<Map> map = testService.getData(idList,beginTime,endTime);
            return map;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
