package com.tcb.vru_service.pojo;

import com.tcb.vru_service.base.BaseDO;

/**
 * 系统字典DO
 */
public class UtilDictionaryDO extends BaseDO {

    private Integer dictionaryId;
    private String dictionaryCode;
    private String dictionaryName;
    private String dictionaryType;

    public Integer getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getDictionaryCode() {
        return dictionaryCode;
    }

    public void setDictionaryCode(String dictionaryCode) {
        this.dictionaryCode = dictionaryCode;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public String getDictionaryType() {
        return dictionaryType;
    }

    public void setDictionaryType(String dictionaryType) {
        this.dictionaryType = dictionaryType;
    }
}
