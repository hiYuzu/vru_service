package com.tcb.vru_service.pojo;

import com.tcb.vru_service.base.BaseDO;

/**
 * 项目DO
 */
public class SystemProjectDO extends BaseDO {

    private Integer projectId;
    private String projectCode;
    private String projectName;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
