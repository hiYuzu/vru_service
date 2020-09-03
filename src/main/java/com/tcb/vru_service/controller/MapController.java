package com.tcb.vru_service.controller;

import com.tcb.vru_service.model.PointVO;
import com.tcb.vru_service.response.ResultVO;
import com.tcb.vru_service.service.IMapService;
import com.tcb.vru_service.util.CommonFunction;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MapController {

    @Resource
    private IMapService mapService;

    /**
     * 获取坐标点信息
     * @param request
     * @return
     */
    @PostMapping(value = "getMapPoint")
    public ResultVO<List<PointVO>> getMapPoint(HttpServletRequest request){
        String userCode = CommonFunction.getLoginUserCode(request);
        List<PointVO> pointVOList = mapService.getMapPoint(userCode);
        return new ResultVO(pointVOList);
    }

    /**
     * 获取监控数据（污染物数据，预警报警数据，发油信息）
     * @param institutionId
     * @return
     */
    @PostMapping(value = "getInstitutionData")
    public ResultVO getInstitutionData(String institutionId){
        if(!StringUtils.isEmpty(institutionId)){
            //TODO 污染物数据，预警报警数据，发油信息总计三种监控数据

        }
        return null;
    }


}
