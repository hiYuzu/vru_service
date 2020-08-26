package com.tcb.vru_service.pojo;

import com.tcb.vru_service.base.BaseDO;

/**
 * @Author: WangLei
 * @Description: 角色资源DO
 * @Date: Create in 2020/07/10 15:22
 * @Modify by WangLei
 */
public class SystemRoleResourceDO extends BaseDO {

    private Integer roleResourceId;
    private SystemRoleDO role;
    private SystemResourceDO resource;

    public Integer getRoleResourceId() {
        return roleResourceId;
    }

    public void setRoleResourceId(Integer roleResourceId) {
        this.roleResourceId = roleResourceId;
    }

    public SystemRoleDO getRole() {
        return role;
    }

    public void setRole(SystemRoleDO role) {
        this.role = role;
    }

    public SystemResourceDO getResource() {
        return resource;
    }

    public void setResource(SystemResourceDO resource) {
        this.resource = resource;
    }

}
