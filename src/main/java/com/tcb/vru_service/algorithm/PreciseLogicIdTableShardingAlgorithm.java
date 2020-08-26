package com.tcb.vru_service.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

public class PreciseLogicIdTableShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {

//    @Override
//    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
//        //对于库的分片collection存放的是所有的库的列表
//        //配置的分片的sharding-column对应的值
//        Integer idValue = String.valueOf(preciseShardingValue.getValue());
//        if (idValue == null) {
//            throw new UnsupportedOperationException("preciseShardingValue is null");
//        }
//        //按DATA_ID路由
//        for (String each : collection) {
//            String valueString = StringUtils.substring(idValue, idValue.length() - 4, idValue.length()); //获取后四位
//            String value = String.format("%03d", (Integer.valueOf(valueString) % 1000));
//            if (each.endsWith(value)) {
//                //这里返回回去的就是最终需要查询的表名
//                return each;
//            }
//        }
//        return null;
//    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        //对于库的分片collection存放的是所有的库的列表
        //配置的分片的sharding-column对应的值
        Integer idValue = Integer.valueOf(preciseShardingValue.getValue());
        if (idValue == null) {
            throw new UnsupportedOperationException("preciseShardingValue is null");
        }
        //按DATA_ID路由
        for (String each : collection) {
            String value = "data_storage_"+idValue;
            if (each.equals(value)) {
                //这里返回回去的就是最终需要查询的表名
                return each;
            }
        }
        return null;
    }
}
