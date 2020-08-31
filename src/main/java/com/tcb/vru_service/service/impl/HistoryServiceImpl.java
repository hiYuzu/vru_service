package com.tcb.vru_service.service.impl;

import com.tcb.vru_service.dao.DeviceDao;
import com.tcb.vru_service.dao.InstitutionDao;
import com.tcb.vru_service.dao.ThingDao;
import com.tcb.vru_service.pojo.BaseDeviceDO;
import com.tcb.vru_service.pojo.BaseInstitutionDO;
import com.tcb.vru_service.pojo.BaseThingDO;
import com.tcb.vru_service.response.ResultVO;
import com.tcb.vru_service.service.IHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: vru_service
 * @description: 历史service
 * @author: laoXue
 * @create: 2020-08-31 13:58
 **/
@Service
public class HistoryServiceImpl implements IHistoryService {
    @Resource
    private InstitutionDao institutionDao;
    @Resource
    private DeviceDao deviceDao;
    @Resource
    private ThingDao thingDao;

    @Override
    public Object queryHead(String institutionId, String deviceId) {
//        初始化
        if (StringUtils.isEmpty(institutionId) && StringUtils.isEmpty(deviceId)) {
            List<BaseInstitutionDO> listInstitution = institutionDao.listInstitution(null);
            if (listInstitution.size() > 0) {
                List<Option<Long>> institutionData =
                        listInstitution.stream().map(institution -> new Option<>(institution.getInstitutionId(), institution.getInstitutionName())).collect(Collectors.toList());
                List<BaseDeviceDO> deviceList = getDeviceData(institutionData.get(0).value);
                List<Option<Integer>> deviceData = new ArrayList<>();
                List<Option<String>> thingData = new ArrayList<>();
                if (deviceList.size() > 0) {
                    deviceData = deviceList.stream().map(device -> new Option<>(device.getDeviceId(), device.getDeviceName())).collect(Collectors.toList());
                    List<BaseThingDO> thingList = getThingData(deviceData.get(0).value);
                    if (thingList.size() > 0) {
                        thingData = thingList.stream().map(thing -> new Option<>(thing.getThingCode(), thing.getThingName())).collect(Collectors.toList());
                    }
                }
                QueryResult<Option> result = new QueryResult(institutionData, deviceData, thingData);
                return result;
            } else {
                return new ResultVO<>(null);
            }
        }
        return null;
    }

    private List<BaseDeviceDO> getDeviceData(Long institutionId) {
        BaseDeviceDO device = new BaseDeviceDO();
        device.setInstitutionId(institutionId);
        return deviceDao.listFiled(device);
    }

    private List<BaseThingDO> getThingData(Integer deviceId) {
        return thingDao.getThingByDeviceId(deviceId);
    }

    private class QueryResult<T> {
        private List<T> institutionData;
        private List<T> deviceData;
        private List<T> thingData;

        public QueryResult(List<T> institutionData, List<T> deviceData, List<T> thingData) {
            this.institutionData = institutionData;
            this.deviceData = deviceData;
            this.thingData = thingData;
        }

        public List<T> getInstitutionData() {
            return institutionData;
        }

        public void setInstitutionData(List<T> institutionData) {
            this.institutionData = institutionData;
        }

        public List<T> getDeviceData() {
            return deviceData;
        }

        public void setDeviceData(List<T> deviceData) {
            this.deviceData = deviceData;
        }

        public List<T> getThingData() {
            return thingData;
        }

        public void setThingData(List<T> thingData) {
            this.thingData = thingData;
        }
    }

    private class Option<T> {
        private String label;
        private T value;

        public Option(T value, String label) {
            this.label = label;
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
}
