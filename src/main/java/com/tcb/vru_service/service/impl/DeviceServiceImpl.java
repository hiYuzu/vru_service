package com.tcb.vru_service.service.impl;

import com.tcb.vru_service.dao.DeviceDao;
import com.tcb.vru_service.service.IDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hiYuzu
 * @version V1.0
 * @date 2020/8/31 9:35
 */
@Service("deviceService")
public class DeviceServiceImpl implements IDeviceService {
    @Resource
    private DeviceDao deviceDao;

    @Override
    public List<Map> getAuthorityDeviceHead(String institutionId, String userCode) {
        return deviceDao.getAuthorityDeviceHead(institutionId, userCode);
    }
}
