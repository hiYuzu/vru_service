package com.tcb.vru_service.util;

/**
 * <p>
 * [功能描述]：系统默认参数值
 * </p>
 * <p>
 * Copyright (c) 2020-2030 TCB Corporation
 * </p>
 *
 * @author 王垒
 * @version 1.0, 2020年04月22日上午13:18:28
 * @since vru_service 1.0.0
 *
 */
public class DefaultParameter {

    private DefaultParameter() {
    }

    /**
     * 主键ID
     */
    public static long WORK_ID;

    /**
     * 数据区ID
     */
    public static long DATA_CENTER_ID;

    /**
     * 主数据库保存数据天数
     */
    public static final int RECENT_DAYS = 30;

}
