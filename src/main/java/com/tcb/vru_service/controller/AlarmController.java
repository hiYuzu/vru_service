package com.tcb.vru_service.controller;

import com.tcb.vru_service.model.DataAlarmVO;
import com.tcb.vru_service.pojo.DataAlarmDO;
import com.tcb.vru_service.response.ResultVO;
import com.tcb.vru_service.service.IDataAlarmService;
import com.tcb.vru_service.service.IInstitutionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author hiYuzu
 * @version V1.0
 * @date 2020/8/31 11:06
 */
@RestController
public class AlarmController {
    @Resource
    private IDataAlarmService dataAlarmService;
    @Resource
    private IInstitutionService institutionService;

    @PostMapping("/getAlarmShowDataCount")
    public ResultVO<Integer> getAlarmShowDataCount(DataAlarmDO dataAlarmDO) {
        return new ResultVO<>(dataAlarmService.getAlarmDataCount(dataAlarmDO));
    }
    /**
     * 查询报警信息
     * @param dataAlarmDO
     * @param rowIndex
     * @param rowCount
     * @return
     */
    @PostMapping("/getAlarmShowData")
    public ResultVO<List<DataAlarmVO>> getAlarmShowData(DataAlarmDO dataAlarmDO, String rowIndex, String rowCount) {
        return new ResultVO<>(dataAlarmService.getAlarmShowData(dataAlarmDO, rowIndex, rowCount));
    }

    @PostMapping("/getInstitutionHead")
    public ResultVO<List> getInstitutionHead() {
        return new ResultVO<>(institutionService.getInstitutionHead());
    }
}
