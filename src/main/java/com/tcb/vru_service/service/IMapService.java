package com.tcb.vru_service.service;

import com.tcb.vru_service.model.PointVO;

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
     * @param userCode
     * @return
     */
    List<PointVO> getMapPoint(String userCode);

}
