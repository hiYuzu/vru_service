package com.tcb.vru_service.service.impl;

import com.tcb.vru_service.dao.*;
import com.tcb.vru_service.model.*;
import com.tcb.vru_service.pojo.*;
import com.tcb.vru_service.service.IMapService;
import com.tcb.vru_service.service.IRoleService;
import com.tcb.vru_service.util.AlarmCodeEnum;
import com.tcb.vru_service.util.CommonFunction;
import com.tcb.vru_service.util.DateUtil;
import com.tcb.vru_service.util.DefaultParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Author: WangLei
 * @Description: GIS地图服务接口实现类
 * @Date: Create in 2020/09/02 09:30
 * @Modify by WangLei
 */
@Service("mapService")
public class MapServiceImpl implements IMapService {

    @Resource
    private InstitutionDao institutionDao;

    @Resource
    private IRoleService roleService;

    @Resource
    private DeviceDao deviceDao;

    @Resource
    private ThingDao thingDao;

    @Resource
    private StorageDao storageDao;

    @Resource
    private AlarmDao alarmDao;

    @Resource
    private DictionaryDao dictionaryDao;

    @Override
    public List<PointVO> getMapPoint(String userCode) {
        List<PointVO> pointVOList = new ArrayList<>();
        if (!StringUtils.isEmpty(userCode)) {
            List<BaseDeviceDO> baseDeviceDOList = roleService.getRoleDeviceList(userCode);
            List<Long> institutionIdList = CommonFunction.getInstitutionIdList(baseDeviceDOList);
            if (institutionIdList != null && institutionIdList.size() > 0) {
                int count = institutionDao.countInstitution(null, institutionIdList);
                if (count > 0) {
                    List<BaseInstitutionDO> institutionDOList = institutionDao.listInstitution(null, institutionIdList);
                    if (institutionDOList != null && institutionDOList.size() > 0) {
                        for (BaseInstitutionDO temp : institutionDOList) {
                            PointVO pointVO = new PointVO();
                            pointVO.setPointId(String.valueOf(temp.getInstitutionId()));
                            pointVO.setPointCode(temp.getInstitutionCode());
                            pointVO.setPointName(temp.getInstitutionName());
                            pointVO.setMapX(temp.getMapX());
                            pointVO.setMapY(temp.getMapY());
                            //实时数据查询
                            TreeMap<String, LinkedHashMap<String, Double>> monitorVOMap = new TreeMap<>();
                            List<Integer> deviceIdList = CommonFunction.getDeviceIdList(baseDeviceDOList, 1, temp.getInstitutionId());
                            List<String> deviceCodeList = deviceDao.selectDeviceCodeById(deviceIdList);
                            List<DataStorageDO> dataStorageDOList = storageDao.listRecentValue(deviceCodeList, null, 2011);
                            if (dataStorageDOList != null && dataStorageDOList.size() > 0) {
                                for (DataStorageDO storageTemp : dataStorageDOList) {
                                    if (storageTemp != null) {
                                        String frequentTime = DateUtil.TimestampToString(storageTemp.getBeginTime(), DateUtil.DATA_TIME_SECOND);
                                        String thingName = thingDao.selectThingNameByCode(storageTemp.getThingCode());
                                        Double thingValue = storageTemp.getThingAvg();
                                        if (monitorVOMap.containsKey(frequentTime)) {
                                            if (monitorVOMap.get(frequentTime).containsKey(thingName)) {
                                                continue;
                                            } else {
                                                monitorVOMap.get(frequentTime).put(thingName, thingValue);
                                            }
                                        } else {
                                            if (monitorVOMap.size() == 0) {
                                                LinkedHashMap<String, Double> hashMapList = new LinkedHashMap<>();
                                                hashMapList.put(thingName, thingValue);
                                                monitorVOMap.put(frequentTime, hashMapList);
                                            }
                                        }
                                    }
                                }
                            }
                            pointVO.setMonitorVOMap(monitorVOMap);
                            //报警查询（24小时内小时数据）
                            List<PointAlarmCountVO> alarmCountVOList = new ArrayList<>();
                            Timestamp beginTimestamp = DateUtil.GetSystemDateTime(DefaultParameter.MILLISECOND_ONE_HOUR * 24);
                            Timestamp endTimestamp = DateUtil.GetSystemDateTime(0);
                            String beginTime = DateUtil.TimestampToString(beginTimestamp, DateUtil.DATA_TIME_SECOND);
                            String endTime = DateUtil.TimestampToString(endTimestamp, DateUtil.DATA_TIME_SECOND);
                            String institutionCode = institutionDao.selectInstitutionCodeById(temp.getInstitutionId());
                            int allWarnCount = 0;
                            int allAlarmCount = 0;
                            //进出口流量比
                            PointAlarmCountVO alarmCountLLB = new PointAlarmCountVO();
                            int warnCount = alarmDao.getWithinAlarmCount(institutionCode, beginTime, endTime, 1, AlarmCodeEnum.LLB.toString());
                            int alarmCount = alarmDao.getWithinAlarmCount(institutionCode, beginTime, endTime, 2, AlarmCodeEnum.LLB.toString());
                            alarmCountLLB.setAlarmCode(AlarmCodeEnum.LLB.toString());
                            alarmCountLLB.setAlarmName("油气处理装置进出口流量比");
                            alarmCountLLB.setWarnCount(warnCount);
                            alarmCountLLB.setAlarmCount(alarmCount);
                            alarmCountVOList.add(alarmCountLLB);
                            allWarnCount += warnCount;
                            allAlarmCount += alarmCount;
                            //油气收集系统压力
                            PointAlarmCountVO alarmCountYL = new PointAlarmCountVO();
                            warnCount = alarmDao.getWithinAlarmCount(institutionCode, beginTime, endTime, 1, AlarmCodeEnum.YL.toString());
                            alarmCount = alarmDao.getWithinAlarmCount(institutionCode, beginTime, endTime, 2, AlarmCodeEnum.YL.toString());
                            alarmCountYL.setAlarmCode(AlarmCodeEnum.YL.toString());
                            alarmCountYL.setAlarmName("油气收集系统压力");
                            alarmCountYL.setWarnCount(warnCount);
                            alarmCountYL.setAlarmCount(alarmCount);
                            alarmCountVOList.add(alarmCountYL);
                            allWarnCount += warnCount;
                            allAlarmCount += alarmCount;
                            //油气处理装置出口浓度
                            PointAlarmCountVO alarmCountND = new PointAlarmCountVO();
                            warnCount = alarmDao.getWithinAlarmCount(institutionCode, beginTime, endTime, 1, AlarmCodeEnum.ND.toString());
                            alarmCount = alarmDao.getWithinAlarmCount(institutionCode, beginTime, endTime, 2, AlarmCodeEnum.ND.toString());
                            alarmCountND.setAlarmCode(AlarmCodeEnum.ND.toString());
                            alarmCountND.setAlarmName("油气处理装置出口浓度");
                            alarmCountND.setWarnCount(warnCount);
                            alarmCountND.setAlarmCount(alarmCount);
                            alarmCountVOList.add(alarmCountND);
                            allWarnCount += warnCount;
                            allAlarmCount += alarmCount;
                            //发油气液比
                            PointAlarmCountVO alarmCountQYB = new PointAlarmCountVO();
                            warnCount = alarmDao.getWithinAlarmCount(institutionCode, beginTime, endTime, 1, AlarmCodeEnum.QYB.toString());
                            alarmCount = alarmDao.getWithinAlarmCount(institutionCode, beginTime, endTime, 2, AlarmCodeEnum.QYB.toString());
                            alarmCountQYB.setAlarmCode(AlarmCodeEnum.QYB.toString());
                            alarmCountQYB.setAlarmName("发油气液比");
                            alarmCountQYB.setWarnCount(warnCount);
                            alarmCountQYB.setAlarmCount(alarmCount);
                            alarmCountVOList.add(alarmCountQYB);
                            allWarnCount += warnCount;
                            allAlarmCount += alarmCount;
                            //设置值
                            pointVO.setAlarmCountVOList(alarmCountVOList);
                            pointVO.setAlarmCount(allAlarmCount);
                            pointVO.setWarnCount(allWarnCount);
                            pointVOList.add(pointVO);
                        }
                    }
                }
            }
        }
        return pointVOList;
    }

    @Override
    public PointDataVO getPointData(Long institutionId, String userCode, Timestamp beginTime, Timestamp endTime) {
        PointDataVO pointDataVO = new PointDataVO();
        List<BaseDeviceDO> baseDeviceDOList = deviceDao.selectDeviceByInstitution(institutionId, userCode);
        //1.污染物质监测数据
        TreeMap<String, LinkedHashMap<String, Double>> treeMonitorMap = new TreeMap<>(
                (Comparator<Object>) (o1, o2) -> {
                    if (o1 == null || o2 == null) {
                        return 0;
                    }
                    return o2.toString().compareTo(o1.toString());
                }
        );//列表
        List<String> listName = new ArrayList<>();
        List<Integer> deviceIdList = CommonFunction.getDeviceIdList(baseDeviceDOList, 1, institutionId);
        if (deviceIdList != null && deviceIdList.size() > 0) {
            List<DataStorageDO> dataStorageDOList = storageDao.listStorage(deviceIdList, null, 2061, null, beginTime, endTime, null, null);
            if (dataStorageDOList != null && dataStorageDOList.size() > 0) {
                for (DataStorageDO temp : dataStorageDOList) {
                    String frequentTime = DateUtil.TimestampToString(temp.getBeginTime(), DateUtil.DATA_TIME_SECOND);
                    String thingName = thingDao.selectThingNameByCode(temp.getThingCode());
                    Double thingValue = temp.getThingAvg();
                    if (treeMonitorMap.containsKey(frequentTime)) {
                        if (treeMonitorMap.get(frequentTime).containsKey(thingName)) {
                            continue;
                        } else {
                            treeMonitorMap.get(frequentTime).put(thingName, thingValue);
                        }
                    } else {
                        LinkedHashMap<String, Double> hashMapList = new LinkedHashMap<>();
                        hashMapList.put(thingName, thingValue);
                        treeMonitorMap.put(frequentTime, hashMapList);
                    }
                    if (!listName.contains(thingName)) {
                        listName.add(thingName);
                    }
                }
                pointDataVO.setMonitorVOMap(treeMonitorMap);
                LinkedHashMap<String, List<String>> monitorVOChart = new LinkedHashMap<>();//图表
                // 排序处理
                TreeMap<String, List<DataStorageDO>> treeMonitor = new TreeMap<>();
                for (DataStorageDO temp : dataStorageDOList) {
                    String frequentTime = DateUtil.TimestampToString(temp.getBeginTime(), DateUtil.DATA_TIME_SECOND);
                    if (treeMonitor.containsKey(frequentTime)) {
                        treeMonitor.get(frequentTime).add(temp);
                    } else {
                        List<DataStorageDO> listTemp = new ArrayList<>();
                        listTemp.add(temp);
                        treeMonitor.put(frequentTime, listTemp);
                    }
                }
                for (Map.Entry<String, List<DataStorageDO>> tempTreeMonitor : treeMonitor.entrySet()) {
                    String frequentTime = tempTreeMonitor.getKey();
                    // 时间
                    if (monitorVOChart.containsKey(DefaultParameter.CHART_TIME)) {
                        monitorVOChart.get(DefaultParameter.CHART_TIME).add(frequentTime);
                    } else {
                        List<String> listTime = new ArrayList<>();
                        listTime.add(frequentTime);
                        monitorVOChart.put(DefaultParameter.CHART_TIME, listTime);
                    }
                    // 监测因子
                    for (String temp : listName) {
                        String thingName;
                        String monitorValue = null;
                        for (DataStorageDO tempMonitor : tempTreeMonitor.getValue()) {
                            thingName = thingDao.selectThingNameByCode(tempMonitor.getThingCode());
                            if (temp.equals(thingName)) {
                                monitorValue = String.valueOf(tempMonitor.getThingAvg());
                                break;
                            }
                        }
                        if (monitorVOChart.containsKey(temp)) {
                            monitorVOChart.get(temp).add(monitorValue);

                        } else {
                            List<String> listThing = new ArrayList<String>();
                            listThing.add(monitorValue);
                            monitorVOChart.put(temp, listThing);
                        }
                    }
                }
                if (!monitorVOChart.containsKey(DefaultParameter.CHART_THING)) {
                    monitorVOChart.put(DefaultParameter.CHART_THING, listName);
                }
                pointDataVO.setMonitorVOChart(monitorVOChart);
            }
        }
        //2.报警信息监测数据
        List<PointDataAlarmVO> alarmVOList = new ArrayList<>();//列表
        List<Long> institutionIdList = new ArrayList<>();
        institutionIdList.add(institutionId);
        List<String> institutionCodeList = institutionDao.listInstitutionCodeById(institutionIdList);
        List<String> deviceCodeList = deviceDao.selectDeviceCodeById(deviceIdList);
        List<DataAlarmDO> dataAlarmDOList = alarmDao.listAlarm(institutionCodeList, deviceCodeList, null, beginTime, endTime, null);
        if (dataAlarmDOList != null && dataAlarmDOList.size() > 0) {
            for (DataAlarmDO temp : dataAlarmDOList) {
                PointDataAlarmVO pointDataAlarmVO = new PointDataAlarmVO();
                String alarmId = String.valueOf(temp.getAlarmId());
                String alarmCode = temp.getAlarmCode();
                String alarmName = dictionaryDao.selectDictionaryNameByCode(alarmCode, "alarm_code");
                String alarmInfo = temp.getAlarmInfo();
                String levelNo = String.valueOf(temp.getLevelNo());
                String deviceCode = temp.getDeviceCode();
                String deviceName = deviceDao.selectDeviceNameByCode(deviceCode);
                String alarmTime = DateUtil.TimestampToString(temp.getAlarmTime(), DateUtil.DATA_TIME_SECOND);
                pointDataAlarmVO.setAlarmId(alarmId);
                pointDataAlarmVO.setAlarmCode(alarmCode);
                pointDataAlarmVO.setAlarmName(alarmName);
                pointDataAlarmVO.setAlarmInfo(alarmInfo);
                pointDataAlarmVO.setLevelNo(levelNo);
                pointDataAlarmVO.setDeviceCode(deviceCode);
                pointDataAlarmVO.setDeviceName(deviceName);
                pointDataAlarmVO.setAlarmTime(alarmTime);
                if (!StringUtils.isEmpty(pointDataAlarmVO.getAlarmId())) {
                    alarmVOList.add(pointDataAlarmVO);
                }
            }
        }
        LinkedHashMap<String, Integer> alarmVOChart = new LinkedHashMap<>();//图表
        int warnCount = 0;
        int alarmCount = 0;
        for (PointDataAlarmVO temp : alarmVOList) {
            String levelNo = temp.getLevelNo();
            if (!StringUtils.isEmpty(levelNo)) {
                int levelNoInt = Integer.valueOf(levelNo);
                if (levelNoInt == 1) {
                    warnCount++;
                } else if (levelNoInt == 2) {
                    alarmCount++;
                } else {
                    continue;
                }
            }
        }
        if (!alarmVOChart.containsKey(DefaultParameter.CHART_WARN)) {
            alarmVOChart.put(DefaultParameter.CHART_WARN, warnCount);
        }
        if (!alarmVOChart.containsKey(DefaultParameter.CHART_ALARM)) {
            alarmVOChart.put(DefaultParameter.CHART_ALARM, alarmCount);
        }
        pointDataVO.setAlarmVOChart(alarmVOChart);
        pointDataVO.setAlarmVOList(alarmVOList);
        //3.发油监测数据
        List<DataStorageOilVO> dataStorageOilVOList = new ArrayList<>();
        List<DataStorageOilDO> storageOilDOList = storageDao.listStorageOil(null, deviceIdList, beginTime, endTime);
        if (storageOilDOList != null && storageOilDOList.size() > 0) {
            for (DataStorageOilDO temp : storageOilDOList) {
                DataStorageOilVO dataStorageOilVO = new DataStorageOilVO();
                dataStorageOilVO.setDataId(String.valueOf(temp.getDataId()));
                dataStorageOilVO.setDeviceId(String.valueOf(temp.getDeviceId()));
                dataStorageOilVO.setDataFysj(DateUtil.TimestampToString(temp.getDataFysj(), DateUtil.DATA_TIME_SECOND));
                dataStorageOilVO.setDataYplx(temp.getDataYplx());
                dataStorageOilVO.setDataYply(temp.getDataYply());
                dataStorageOilVO.setDataYpqc(temp.getDataYpqc());
                dataStorageOilVO.setDataFytj(String.valueOf(temp.getDataFytj()));
                dataStorageOilVO.setDataHqtj(String.valueOf(temp.getDataHqtj()));
                dataStorageOilVO.setDataFyqyb(String.valueOf(temp.getDataFyqyb()));
                dataStorageOilVO.setDataSjxtyl(String.valueOf(temp.getDataSjxtyl()));
                dataStorageOilVO.setUpdateTime(DateUtil.TimestampToString(temp.getUpdateTime(), DateUtil.DATA_TIME_SECOND));
                dataStorageOilVOList.add(dataStorageOilVO);
            }
        }
        pointDataVO.setOilVOList(dataStorageOilVOList);
        return pointDataVO;
    }
}
