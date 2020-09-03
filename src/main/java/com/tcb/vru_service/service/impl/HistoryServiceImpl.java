package com.tcb.vru_service.service.impl;

import com.tcb.vru_service.dao.DeviceDao;
import com.tcb.vru_service.dao.InstitutionDao;
import com.tcb.vru_service.dao.StorageDao;
import com.tcb.vru_service.dao.ThingDao;
import com.tcb.vru_service.pojo.*;
import com.tcb.vru_service.service.IHistoryService;
import com.tcb.vru_service.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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
    @Resource
    private StorageDao storageDao;

    @Override
    public Object historyHeadInit() {
        List<Option<Long>> institutionData = new ArrayList<>();
        List<DeviceThing> deviceThingData = new ArrayList<>();
        List<Option<Integer>> deviceData = new ArrayList<>();
        List<Option<String>> thingData = new ArrayList<>();

        List<BaseInstitutionDO> listInstitution = institutionDao.listInstitution(null,null);
        if (listInstitution.size() > 0) {
            institutionData =
                    listInstitution.stream().map(institution -> new Option<>(institution.getInstitutionId(), institution.getInstitutionName(), null)).collect(Collectors.toList());
            List<BaseDeviceDO> deviceList = getDeviceData();
            if (deviceList.size() > 0) {
                deviceData = deviceList.stream().map(device -> new Option<>(device.getDeviceId(), device.getDeviceName(), device.getInstitutionId())).collect(Collectors.toList());
                List<BaseThingDO> thingList = getThingData();
                if (thingList.size() > 0) {
                    thingData = thingList.stream().map(thing -> new Option<>(thing.getThingCode(), thing.getThingName(), thing.getThingId().longValue())).collect(Collectors.toList());
                }
                List<BaseDeviceThingDO> deviceThingDOList = getDeviceThingData();
                if (deviceThingDOList.size() > 0) {
                    deviceThingData = deviceThingDOList.stream().map(deviceThing -> new DeviceThing(deviceThing.getDeviceId(), deviceThing.getThingId())).collect(Collectors.toList());
                }
            }
        }
        QueryResult<Option> result = new QueryResult(institutionData, deviceData, thingData, deviceThingData);
        return result;
    }

    @Override
    public Object historyQuery(String[] deviceIds, String[] thingCodes, Integer dataType, String[] time) {
        Timestamp beginTimestamp = null;
        Timestamp endTimestamp = null;
        String beginTime = time[0];
        String endTime = time[1];
        if (!StringUtils.isEmpty(beginTime)) {
            beginTimestamp = DateUtil.StringToTimestampSecond(beginTime);
        }
        if (!StringUtils.isEmpty(endTime)) {
            endTimestamp = DateUtil.StringToTimestampSecond(endTime);
        }
        List<Long> listDataId = new ArrayList<>();
        for (int i = 0; i < deviceIds.length; i++) {
            listDataId.add(Long.parseLong(deviceIds[i]));
        }
        List<String> listThingCode = new ArrayList<>();
        listThingCode.addAll(Arrays.asList(thingCodes));
//        List<DataStorageDO> dataStorageDOS = storageDao.listStorage(listDataId, listThingCode, dataType, null, beginTimestamp, endTimestamp, null, null);
        List<DataStorageDO> dataStorageDOS = new ArrayList<>();
        DataStorageDO dataStorageDO = new DataStorageDO();
        dataStorageDO.setDeviceId(1L);
        dataStorageDO.setThingCode("CKND");
        dataStorageDO.setDataType(2011);
        dataStorageDO.setThingAvg(100.1);
        dataStorageDOS.add(dataStorageDO);
        dataStorageDO.setBeginTime(new Timestamp(System.currentTimeMillis()));
        List<StorageData> storageData = dataStorageDOS.stream().map(storage -> new StorageData(storage.getDeviceId(), storage.getThingCode(), storage.getThingAvg(), storage.getDataType(), DateUtil.TimestampToString(storage.getBeginTime(), "yyyy-MM-dd HH:mm:ss"))).collect(Collectors.toList());
        return storageData;
    }

    private List<BaseDeviceDO> getDeviceData() {
        return deviceDao.listDevice(null);
    }

    private List<BaseThingDO> getThingData() {
        return thingDao.listThing(null);
    }

    private List<BaseDeviceThingDO> getDeviceThingData() {
        return thingDao.getDeviceThingData();
    }

    private class QueryResult<T> {
        private List<T> institutionData;
        private List<T> deviceData;
        private List<T> thingData;
        private List<DeviceThing> deviceThingData;

        public QueryResult(List<T> institutionData, List<T> deviceData, List<T> thingData, List<DeviceThing> deviceThingData) {
            this.institutionData = institutionData;
            this.deviceData = deviceData;
            this.thingData = thingData;
            this.deviceThingData = deviceThingData;
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

        public List<DeviceThing> getDeviceThingData() {
            return deviceThingData;
        }

        public void setDeviceThingData(List<DeviceThing> deviceThingData) {
            this.deviceThingData = deviceThingData;
        }
    }

    private class Option<T> {
        private String label;
        private T value;
        private Long id;

        public Option(T value, String label, Long id) {
            this.label = label;
            this.value = value;
            this.id = id;
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

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    private class DeviceThing {
        private Long deviceId;
        private Integer thingId;

        public DeviceThing(Long deviceId, Integer thingId) {
            this.deviceId = deviceId;
            this.thingId = thingId;
        }

        public Long getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(Long deviceId) {
            this.deviceId = deviceId;
        }

        public Integer getThingId() {
            return thingId;
        }

        public void setThingId(Integer thingId) {
            this.thingId = thingId;
        }
    }

    private class StorageData {
        private Long deviceId;
        private String thingCode;
        private Double thingAvg;
        private String beginTime;
        private Integer dataType;

        public StorageData(Long deviceId, String thingCode, Double thingAvg, Integer dataType, String beginTime) {
            this.deviceId = deviceId;
            this.thingCode = thingCode;
            this.thingAvg = thingAvg;
            this.dataType = dataType;
            this.beginTime = beginTime;
        }

        public Long getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(Long deviceId) {
            this.deviceId = deviceId;
        }

        public String getThingCode() {
            return thingCode;
        }

        public void setThingCode(String thingCode) {
            this.thingCode = thingCode;
        }

        public Double getThingAvg() {
            return thingAvg;
        }

        public void setThingAvg(Double thingAvg) {
            this.thingAvg = thingAvg;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public Integer getDataType() {
            return dataType;
        }

        public void setDataType(Integer dataType) {
            this.dataType = dataType;
        }
    }
}
