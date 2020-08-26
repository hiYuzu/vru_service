package com.tcb.vru_service.base;

import java.sql.Timestamp;

/**
 * DO基类
 */
public class BaseDO {

    /**
     * 操作者
     */
    private Integer operateUser;

    /**
     * 操作时间
     */
    private Timestamp operateTime;

    /**
     * 查询个数
     */
    private Integer rowCount;
    /**
     * 开始查询位置
     */
    private Integer rowIndex;

    public Integer getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(Integer operateUser) {
        this.operateUser = operateUser;
    }

    public Timestamp getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Timestamp operateTime) {
        this.operateTime = operateTime;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }
}
