package com.tcb.vru_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParameterConfig {

    @Value("${vru.param.redis.flag}")
    private int flag;
    @Value("${vru.param.redis.password}")
    private String password;
    @Value("${vru.param.util.workerId}")
    private long workId;
    @Value("${vru.param.util.dataCenterId}")
    private long dateCenterId;

    public int getFlag() {
        return flag;
    }

    public String getPassword() {
        return password;
    }

    public long getWorkId() {
        return workId;
    }

    public void setWorkId(long workId) {
        this.workId = workId;
    }

    public long getDateCenterId() {
        return dateCenterId;
    }

    public void setDateCenterId(long dateCenterId) {
        this.dateCenterId = dateCenterId;
    }
}
