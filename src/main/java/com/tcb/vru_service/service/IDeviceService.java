package com.tcb.vru_service.service;

import java.util.List;
import java.util.Map;

/**
 * @author hiYuzu
 * @version V1.0
 * @date 2020/8/31 9:35
 */
public interface IDeviceService {
    /**
     * 获取权限设备基本信息（code+name）
     * @param institutionId
     * @param userCode
     * @return
     */
    List<Map> getAuthorityDeviceHead(String institutionId, String userCode);

}
