package com.tcb.vru_service.service.impl;

import com.tcb.vru_service.dao.DeviceDao;
import com.tcb.vru_service.dao.InstitutionDao;
import com.tcb.vru_service.dao.StorageDao;
import com.tcb.vru_service.dao.ThingDao;
import com.tcb.vru_service.model.PointDataAlarmVO;
import com.tcb.vru_service.model.DataStorageOilVO;
import com.tcb.vru_service.model.PointDataVO;
import com.tcb.vru_service.model.PointVO;
import com.tcb.vru_service.pojo.BaseDeviceDO;
import com.tcb.vru_service.pojo.BaseInstitutionDO;
import com.tcb.vru_service.pojo.DataStorageDO;
import com.tcb.vru_service.pojo.DataStorageOilDO;
import com.tcb.vru_service.service.IMapService;
import com.tcb.vru_service.service.IRoleService;
import com.tcb.vru_service.util.CommonFunction;
import com.tcb.vru_service.util.DateUtil;
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
                            //TODO 报警查询
                            pointVO.setAlarmCount(1);
                            pointVO.setWarnCount(0);
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
        );
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
                }
            }
        }
        pointDataVO.setMonitorData(treeMonitorMap);
        //2.报警信息监测数据
        List<PointDataAlarmVO> alarmVOList = new ArrayList<>();
        //TODO 需要整合报警模块代码后进行数据复制
        pointDataVO.setAlarmVOList(alarmVOList);
        //3.发油监测数据
        List<DataStorageOilVO> dataStorageOilVOList = new ArrayList<>();
        List<DataStorageOilDO> storageOilDOList = storageDao.listStorageOil(null, institutionId, beginTime, endTime);
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
