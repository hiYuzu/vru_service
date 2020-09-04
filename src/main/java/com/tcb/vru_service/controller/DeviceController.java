package com.tcb.vru_service.controller;
import com.tcb.vru_service.response.ResultVO;
import com.tcb.vru_service.service.IDeviceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author hiYuzu
 * @version V1.0
 * @date 2020/8/31 11:53
 */
@RestController
public class DeviceController {
    @Resource
    private IDeviceService deviceService;

    @PostMapping("/getAuthorityDeviceHead")
    public ResultVO<List> getAuthorityDeviceHead(String institutionId, HttpServletRequest request) {
        String userCode = String.valueOf(request.getAttribute("subject"));
        return new ResultVO<>(deviceService.getAuthorityDeviceHead(institutionId, userCode));
    }


    @PostMapping("/onOffLineStatistic")
    public ResultVO<Map<String, Integer>> onOffLineStatistic(HttpServletRequest request) {
        String userCode = String.valueOf(request.getAttribute("subject"));
        List<Map> devices = deviceService.getAuthorityDeviceHead(null, userCode);
        return new ResultVO<>(deviceService.onOffLineStatistic(devices));
    }
}
