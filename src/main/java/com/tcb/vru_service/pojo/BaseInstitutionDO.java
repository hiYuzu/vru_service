package com.tcb.vru_service.pojo;

import com.tcb.vru_service.base.BaseDO;

/**
 * 机构DO
 */
public class BaseInstitutionDO extends BaseDO {

    private Integer institutionId;
    private String institutionName;
    private String institutionAddress;
    private Integer areaId;
    private String institutionPermit;
    private String institutionPermitUrl;
    private String institutionContact;
    private String institutionType;

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getInstitutionAddress() {
        return institutionAddress;
    }

    public void setInstitutionAddress(String institutionAddress) {
        this.institutionAddress = institutionAddress;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getInstitutionPermit() {
        return institutionPermit;
    }

    public void setInstitutionPermit(String institutionPermit) {
        this.institutionPermit = institutionPermit;
    }

    public String getInstitutionPermitUrl() {
        return institutionPermitUrl;
    }

    public void setInstitutionPermitUrl(String institutionPermitUrl) {
        this.institutionPermitUrl = institutionPermitUrl;
    }

    public String getInstitutionContact() {
        return institutionContact;
    }

    public void setInstitutionContact(String institutionContact) {
        this.institutionContact = institutionContact;
    }

    public String getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }
}
