package com.tcb.vru_service.service.impl;

import com.tcb.vru_service.dao.DeviceDao;
import com.tcb.vru_service.service.IDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Override
    public Map<String, Integer> onOffLineStatistic(List<Map> devices) {
        if (devices.isEmpty()) {
            return new HashMap<>(0);
        }
        List<String> deviceCodes = new ArrayList();
        for (Map device : devices) {
            deviceCodes.add((String) device.get("value"));
        }
        List<Map> mapList = deviceDao.onOffLineStatistic(deviceCodes);
        Map<String, Integer> result = new HashMap<>(6);
        result.put("N", 0);
        result.put("D", 0);
        result.put("O", 0);
        int resultCount = 0;
        for (Map map : mapList) {
            int count = (int) map.get("statusCount");
            result.put((String) map.get("deviceStatus"), count);
            resultCount += count;
        }
        result.put("others", deviceCodes.size() - resultCount);
        return result;
    }
}
