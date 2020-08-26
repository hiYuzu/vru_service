package com.tcb.vru_service.init;

import com.tcb.vru_service.config.ParameterConfig;
import com.tcb.vru_service.util.DefaultParameter;
import com.tcb.vru_service.util.RedisUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Order(2)
public class InitApplication implements CommandLineRunner {

    /**
     * 参数文件
     */
    @Resource
    private ParameterConfig parameterConfig;

    @Override
    public void run(String... args) throws Exception {
        //设置数据ID参数
        DefaultParameter.WORK_ID = parameterConfig.getWorkId();
        DefaultParameter.DATA_CENTER_ID = parameterConfig.getDateCenterId();
        //赋值内存数据库访问密码
        RedisUtil.PWD = parameterConfig.getPassword();
    }
}
