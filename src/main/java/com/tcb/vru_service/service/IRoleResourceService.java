package com.tcb.vru_service.service;

import java.util.List;
import java.util.Map;

/**
 * @Author: WangLei
 * @Description: 角色资源接口
 * @Date: Create in 2020/07/13 09:17
 * @Modify by WangLei
 */
public interface IRoleResourceService {

    /**
     * 获取角色资源数据（通用用户名）
     * @param userCode
     * @return
     */
    Map<String, List<String>> getRoleResourceMap(String userCode);

}
