package com.tcb.vru_service.service.impl;

import com.tcb.vru_service.dao.*;
import com.tcb.vru_service.model.*;
import com.tcb.vru_service.pojo.*;
import com.tcb.vru_service.service.IMapService;
import com.tcb.vru_service.service.IRoleService;
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
                            List<Integer> deviceIdList = CommonFunction.getDeviceIdList(baseDeviceDOList, 1);
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
                            String beginTime = DateUtil.TimestampToString(beginTimestamp, DateUtil.DATA_HOUR) + ":00:00";
                            String endTime = DateUtil.TimestampToString(endTimestamp, DateUtil.DATA_HOUR) + ":59:59";
                            List<String> institutionCodeList = institutionDao.selectInstitutionCodeById(institutionIdList);
                            int allWarnCount = 0;
                            int allAlarmCount = 0;
                            //气液比
                            PointAlarmCountVO alarmCountGLR = new PointAlarmCountVO();
                            int warnCount = alarmDao.getWithinAlarmCount(institutionCodeList, beginTime, endTime, 1, "GLR");
                            int alarmCount = alarmDao.getWithinAlarmCount(institutionCodeList, beginTime, endTime, 2, "GLR");
                            alarmCountGLR.setAlarmCode("GLR");
                            alarmCountGLR.setAlarmName("气液比");
                            alarmCountGLR.setWarnCount(warnCount);
                            alarmCountGLR.setAlarmCount(alarmCount);
                            alarmCountVOList.add(alarmCountGLR);
                            allWarnCount += warnCount;
                            allAlarmCount += alarmCount;
                            //压力
                            PointAlarmCountVO alarmCountPRE = new PointAlarmCountVO();
                            warnCount = alarmDao.getWithinAlarmCount(institutionCodeList, beginTime, endTime, 1, "PRE");
                            alarmCount = alarmDao.getWithinAlarmCount(institutionCodeList, beginTime, endTime, 2, "PRE");
                            alarmCountPRE.setAlarmCode("PRE");
                            alarmCountPRE.setAlarmName("压力");
                            alarmCountPRE.setWarnCount(warnCount);
                            alarmCountPRE.setAlarmCount(alarmCount);
                            alarmCountVOList.add(alarmCountPRE);
                            allWarnCount += warnCount;
                            allAlarmCount += alarmCount;
                            //NMHC浓度
                            PointAlarmCountVO alarmCountNMHC = new PointAlarmCountVO();
                            warnCount = alarmDao.getWithinAlarmCount(institutionCodeList, beginTime, endTime, 1, "NMHC");
                            alarmCount = alarmDao.getWithinAlarmCount(institutionCodeList, beginTime, endTime, 2, "NMHC");
                            alarmCountNMHC.setAlarmCode("NMHC");
                            alarmCountNMHC.setAlarmName("NMHC浓度");
                            alarmCountNMHC.setWarnCount(warnCount);
                            alarmCountNMHC.setAlarmCount(alarmCount);
                            alarmCountVOList.add(alarmCountNMHC);
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
        List<Integer> deviceIdList = CommonFunction.getDeviceIdList(baseDeviceDOList, 1);
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
        //TODO 需要整合报警模块代码后进行数据复制
        for (int i = 0; i < 20; i++) {
            PointDataAlarmVO pointDataAlarmVO = new PointDataAlarmVO();
            if (i % 2 == 0) {
                pointDataAlarmVO.setAlarmId("11111111213123");
                pointDataAlarmVO.setAlarmCode("GLR");
                pointDataAlarmVO.setAlarmName("气液比");
                pointDataAlarmVO.setAlarmInfo("气液比小于0.9预警");
                pointDataAlarmVO.setLevelNo("1");
                pointDataAlarmVO.setDeviceCode("testdevice1");
                pointDataAlarmVO.setDeviceName("油气回收设备");
            } else if (i % 3 == 0) {
                pointDataAlarmVO.setAlarmId("222222222334454");
                pointDataAlarmVO.setAlarmCode("NMHC");
                pointDataAlarmVO.setAlarmName("NMHC浓度");
                pointDataAlarmVO.setAlarmInfo("NMHC浓度报警");
                pointDataAlarmVO.setLevelNo("2");
                pointDataAlarmVO.setDeviceCode("testdevice1");
                pointDataAlarmVO.setDeviceName("油气回收设备");
            } else if (i % 5 == 0) {
                pointDataAlarmVO.setAlarmId("4444444444444344");
                pointDataAlarmVO.setAlarmCode("PRE");
                pointDataAlarmVO.setAlarmName("压力");
                pointDataAlarmVO.setAlarmInfo("压力预警");
                pointDataAlarmVO.setLevelNo("1");
                pointDataAlarmVO.setDeviceCode("testdevice1");
                pointDataAlarmVO.setDeviceName("油气回收设备");
            } else if (i % 7 == 0) {
                pointDataAlarmVO.setAlarmId("666676767676");
                pointDataAlarmVO.setAlarmCode("GLR");
                pointDataAlarmVO.setAlarmName("气液比");
                pointDataAlarmVO.setAlarmInfo("气液比小于0.9报警警");
                pointDataAlarmVO.setLevelNo("2");
                pointDataAlarmVO.setDeviceCode("testdevice1");
                pointDataAlarmVO.setDeviceName("油气回收设备");
            } else if (i % 9 == 0) {
                pointDataAlarmVO.setAlarmId("888888888888567");
                pointDataAlarmVO.setAlarmCode("PRE");
                pointDataAlarmVO.setAlarmName("压力");
                pointDataAlarmVO.setAlarmInfo("压力报警");
                pointDataAlarmVO.setLevelNo("2");
                pointDataAlarmVO.setDeviceCode("testdevice1");
                pointDataAlarmVO.setDeviceName("油气回收设备");
            }
            if (!StringUtils.isEmpty(pointDataAlarmVO.getAlarmId())) {
                alarmVOList.add(pointDataAlarmVO);
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
                alarmVOChart.put(DefaultParameter.CHART_WARN, alarmCount);
            }
            if (!alarmVOChart.containsKey(DefaultParameter.CHART_ALARM)) {
                alarmVOChart.put(DefaultParameter.CHART_ALARM, warnCount);
            }
            pointDataVO.setAlarmVOChart(alarmVOChart);
        }
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
