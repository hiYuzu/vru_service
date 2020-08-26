package com.tcb.vru_service.pojo;

import com.tcb.vru_service.base.BaseDO;

/**
 * 因子DO
 */
public class BaseThingDO extends BaseDO {

    private Integer thingId;
    private String thingCode;
    private String thingName;
    private String thingUnit;
    private Integer thingOrder;
    private Integer fieldId;

    public Integer getThingId() {
        return thingId;
    }

    public void setThingId(Integer thingId) {
        this.thingId = thingId;
    }

    public String getThingCode() {
        return thingCode;
    }

    public void setThingCode(String thingCode) {
        this.thingCode = thingCode;
    }

    public String getThingName() {
        return thingName;
    }

    public void setThingName(String thingName) {
        this.thingName = thingName;
    }

    public String getThingUnit() {
        return thingUnit;
    }

    public void setThingUnit(String thingUnit) {
        this.thingUnit = thingUnit;
    }

    public Integer getThingOrder() {
        return thingOrder;
    }

    public void setThingOrder(Integer thingOrder) {
        this.thingOrder = thingOrder;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }
}
