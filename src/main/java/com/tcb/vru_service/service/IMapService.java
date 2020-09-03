package com.tcb.vru_service.service;

import com.tcb.vru_service.model.PointDataVO;
import com.tcb.vru_service.model.PointVO;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: WangLei
 * @Description: GIS地图服务接口
 * @Date: Create in 2020/09/02 09:08
 * @Modify by WangLei
 */
public interface IMapService {

    /**
     * 获取GIS坐标点信息
     *
     * @param userCode
     * @return
     */
    List<PointVO> getMapPoint(String userCode);

    /**
     * 获取监测机构(发油库)设备监测数据
     *
     * @param institutionId
     * @param userCode
     * @param beginTime
     * @param endTime
     * @return
     */
    PointDataVO getPointData(Long institutionId, String userCode,
                                   Timestamp beginTime,Timestamp endTime);

}
