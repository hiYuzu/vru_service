package com.tcb.vru_service.pojo;

import com.tcb.vru_service.base.BaseDO;

/**
 * 组织DO
 */
public class BaseOrganizationDO extends BaseDO {

    private Integer organizationId;
    private String organizationName;
    private Integer parentOrganizationId;
    private String organizationType;
    private String organizationAddress;
    private String organizationPath;
    private String organizationTelephone;
    private String organizationFax;
    private String organizationContacter;

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getParentOrganizationId() {
        return parentOrganizationId;
    }

    public void setParentOrganizationId(Integer parentOrganizationId) {
        this.parentOrganizationId = parentOrganizationId;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public String getOrganizationPath() {
        return organizationPath;
    }

    public void setOrganizationPath(String organizationPath) {
        this.organizationPath = organizationPath;
    }

    public String getOrganizationTelephone() {
        return organizationTelephone;
    }

    public void setOrganizationTelephone(String organizationTelephone) {
        this.organizationTelephone = organizationTelephone;
    }

    public String getOrganizationFax() {
        return organizationFax;
    }

    public void setOrganizationFax(String organizationFax) {
        this.organizationFax = organizationFax;
    }

    public String getOrganizationContacter() {
        return organizationContacter;
    }

    public void setOrganizationContacter(String organizationContacter) {
        this.organizationContacter = organizationContacter;
    }
}
