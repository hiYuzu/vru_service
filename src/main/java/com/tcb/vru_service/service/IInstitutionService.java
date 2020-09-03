package com.tcb.vru_service.service;

import java.util.List;
import java.util.Map;

/**
 * @author hiYuzu
 * @version V1.0
 * @date 2020/8/31 11:39
 */
public interface IInstitutionService {
    /**
     * 获取发油库
     * @param userCode
     * @return
     */
    List<Map> getInstitutionHead();
}
