package com.tcb.vru_service.controller;

import com.tcb.vru_service.response.ResultVO;
import com.tcb.vru_service.service.IHistoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: vru_service
 * @description: 历史
 * @author: laoXue
 * @create: 2020-08-31 13:53
 **/
@RestController

public class HistoryController {
    @Resource
    private IHistoryService historyService;

    @PostMapping(value = "historyHeadInit")
    public ResultVO historyHeadInit() {
        Object result = historyService.historyHeadInit();
        return new ResultVO(result);
    }

    @PostMapping(value = "historyQuery")
    public ResultVO historyQuery(String[] deviceIds, String[] thingCodes, Integer dataType, String[] time) {
        Object result = historyService.historyQuery(deviceIds, thingCodes, dataType, time);
        return new ResultVO(result);
    }
}