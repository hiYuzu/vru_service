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

    @PostMapping(value = "queryHead")
    public ResultVO queryHead(String institutionId,
                              String deviceId) {
        Object result = historyService.queryHead(institutionId, deviceId);
        return new ResultVO(result);
    }
}