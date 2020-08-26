package com.tcb.vru_service.pojo;

import com.tcb.vru_service.base.BaseDO;

/**
 * 资源DO
 */
public class SystemResourceDO extends BaseDO {

    private Integer resourceId;
    private String resourceCode;
    private String resourceName;
    private String resourceTitle;
    private Integer resourceParentId;
    private Boolean resourceType;
    private String resourceProp;
    private Integer resourceSort;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceTitle() {
        return resourceTitle;
    }

    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

    public Integer getResourceParentId() {
        return resourceParentId;
    }

    public void setResourceParentId(Integer resourceParentId) {
        this.resourceParentId = resourceParentId;
    }

    public Boolean getResourceType() {
        return resourceType;
    }

    public void setResourceType(Boolean resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceProp() {
        return resourceProp;
    }

    public void setResourceProp(String resourceProp) {
        this.resourceProp = resourceProp;
    }

    public Integer getResourceSort() {
        return resourceSort;
    }

    public void setResourceSort(Integer resourceSort) {
        this.resourceSort = resourceSort;
    }
}
