package com.tcb.vru_service.service.impl;

import com.tcb.vru_service.dao.RoleDeviceDao;
import com.tcb.vru_service.dao.RoleResourceDao;
import com.tcb.vru_service.dao.RoleUserDao;
import com.tcb.vru_service.pojo.BaseDeviceDO;
import com.tcb.vru_service.pojo.SystemResourceDO;
import com.tcb.vru_service.pojo.SystemRoleDeviceDO;
import com.tcb.vru_service.pojo.SystemRoleResourceDO;
import com.tcb.vru_service.service.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: WangLei
 * @Description: 角色资源接口实现类
 * @Date: Create in 2020/07/13 09:17
 * @Modify by WangLei
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleResourceDao roleResourceDao;

    @Resource
    private RoleDeviceDao roleDeviceDao;

    @Resource
    private RoleUserDao roleUserDao;

    @Override
    public Map<String, List<String>> getRoleResourceMap(String userCode) {
        Map<String, List<String>> roleResourceMap = new HashMap<>();
        List<String> roleCodeList = roleUserDao.selectRoleCodeByUserCode(userCode);
        List<SystemRoleResourceDO> roleResourceDOList = roleResourceDao.selectResourceByRoleCode(roleCodeList);
        if (roleResourceDOList != null && roleResourceDOList.size() > 0) {
            for (SystemRoleResourceDO temp : roleResourceDOList) {
                SystemResourceDO resourceDO = temp.getResource();
                if (resourceDO != null) {
                    String resourceCode = resourceDO.getResourceCode();
                    String resourceProp = resourceDO.getResourceProp();
                    if (!StringUtils.isEmpty(resourceCode)) {
                        if (roleResourceMap.containsKey(resourceCode)) {
                            roleResourceMap.get(resourceCode).add(resourceProp);
                        } else {
                            List<String> propList = new ArrayList<>();
                            propList.add(resourceProp);
                            roleResourceMap.put(resourceCode, propList);
                        }
                    }
                }
            }
        }
        return roleResourceMap;
    }

    @Override
    public List<BaseDeviceDO> getRoleDeviceList(String userCode) {
        List<BaseDeviceDO> baseDeviceDOList = new ArrayList<>();
        List<String> roleCodeList = roleUserDao.selectRoleCodeByUserCode(userCode);
        List<SystemRoleDeviceDO> systemRoleDeviceDOList = roleDeviceDao.selectDeviceByRoleCode(roleCodeList);
        if (systemRoleDeviceDOList != null && systemRoleDeviceDOList.size() > 0) {
            for (SystemRoleDeviceDO temp : systemRoleDeviceDOList) {
                if (temp.getDevice() != null) {
                    baseDeviceDOList.add(temp.getDevice());
                }
            }
        }
        return baseDeviceDOList;
    }
}
