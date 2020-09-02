package com.tcb.vru_service.service.impl;

import com.tcb.vru_service.dao.InstitutionDao;
import com.tcb.vru_service.model.PointVO;
import com.tcb.vru_service.pojo.BaseInstitutionDO;
import com.tcb.vru_service.service.IMapService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<PointVO> getMapPoint(String userCode) {
        List<PointVO> pointVOList = new ArrayList<>();
        if (!StringUtils.isEmpty(userCode)) {
            //TODO 查询权限
        } else {
            //全部查询
            int count = institutionDao.countInstitution(null);
            if (count > 0) {
                List<BaseInstitutionDO> institutionDOList = institutionDao.listInstitution(null);
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
                    }
                }
            }
        }
        return pointVOList;
    }
}
