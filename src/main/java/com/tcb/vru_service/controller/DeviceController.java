package com.tcb.vru_service.controller;
import com.tcb.vru_service.response.ResultVO;
import com.tcb.vru_service.service.IDeviceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
}
