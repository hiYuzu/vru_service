package com.tcb.vru_service.controller;

import com.tcb.vru_service.model.PointDataVO;
import com.tcb.vru_service.model.PointVO;
import com.tcb.vru_service.response.ResultVO;
import com.tcb.vru_service.service.IMapService;
import com.tcb.vru_service.util.CommonFunction;
import com.tcb.vru_service.util.DateUtil;
import com.tcb.vru_service.util.DefaultParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class MapController {

    @Resource
    private IMapService mapService;

    /**
     * 获取坐标点信息
     *
     * @param request
     * @return
     */
    @PostMapping(value = "getMapPoint")
    public ResultVO<List<PointVO>> getMapPoint(HttpServletRequest request) {
        String userCode = CommonFunction.getLoginUserCode(request);
        List<PointVO> pointVOList = mapService.getMapPoint(userCode);
        return new ResultVO(pointVOList);
    }

    /**
     * 获取监控数据（污染物数据，预警报警数据，发油信息）
     *
     * @param institutionId
     * @return
     */
    @PostMapping(value = "getInstitutionData")
    public ResultVO<PointDataVO> getInstitutionData(
            String institutionId, @RequestParam(required = false) String beginTime,
            @RequestParam(required = false) String endTime, HttpServletRequest request) {
        ResultVO<PointDataVO> resultVO;
        if (!StringUtils.isEmpty(institutionId)) {
            Timestamp beginTimestamp;
            Timestamp endTimestamp;
            if (!StringUtils.isEmpty(beginTime)) {
                beginTimestamp = DateUtil.StringToTimestamp(beginTime, DateUtil.DATA_TIME_SECOND);
            } else {
                beginTimestamp = DateUtil.GetSystemDateTime(DefaultParameter.MILLISECOND_ONE_HOUR * 24);
            }
            if (!StringUtils.isEmpty(endTime)) {
                endTimestamp = DateUtil.StringToTimestamp(endTime, DateUtil.DATA_TIME_SECOND);
            } else {
                endTimestamp = DateUtil.GetSystemDateTime(0);
            }
            String userCode = CommonFunction.getLoginUserCode(request);
            PointDataVO pointDataVO = mapService.getPointData(Long.valueOf(institutionId), userCode, beginTimestamp, endTimestamp);
            if (pointDataVO != null) {
                resultVO = new ResultVO(pointDataVO);
            } else {
                resultVO = new ResultVO(false, "获取监测信息失败！");
            }
        } else {
            resultVO = new ResultVO(false, "查询参数不能为空！");
        }
        return resultVO;
    }


}
