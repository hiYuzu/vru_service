package com.tcb.vru_service.algorithm;

import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.ArrayList;
import java.util.Collection;

public class PreciseDataTableShardingAlgorithm implements PreciseShardingAlgorithm<String>, RangeShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        //对于库的分片collection存放的是所有的库的列表
        //配置的分片的sharding-column对应的值
        String timeValue = preciseShardingValue.getValue();
        if (StringUtils.isBlank(timeValue)) {
            throw new UnsupportedOperationException("preciseShardingValue is null");
        }
        //按月路由
        for (String each : collection) {
            String value = StringUtils.substring(timeValue, 0, 4)+StringUtils.substring(timeValue, 5, 7); //获取到年月日期
            if (each.endsWith(value)) {
                //这里返回回去的就是最终需要查询的表名
                return each;
            }
        }
        return null;
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<String> rangeShardingValue) {
        Collection<String> availableCollection = new ArrayList<>();
        Range valueRange = rangeShardingValue.getValueRange();
        for (String target : collection) {
            Integer shardYearValue = Integer.parseInt(target.substring(target.lastIndexOf("_") + 1, target.lastIndexOf("_") + 5));
            Integer shardMonthValue = Integer.parseInt(target.substring(target.lastIndexOf("_") + 5, target.lastIndexOf("_") + 7));
            if (valueRange.hasLowerBound()) {
                String lowerStr = valueRange.lowerEndpoint().toString();
                Integer startYear = Integer.parseInt(lowerStr.substring(0, 4));
                Integer startMonth = Integer.parseInt(lowerStr.substring(5, 7));
                if (startYear - shardYearValue > 0) {
                    continue;
                }
                if (startMonth - shardMonthValue > 0) {
                    continue;
                }
            }
            if (valueRange.hasUpperBound()) {
                String upperStr = valueRange.upperEndpoint().toString();
                Integer endYear = Integer.parseInt(upperStr.substring(0, 4));
                Integer endMonth = Integer.parseInt(upperStr.substring(5, 7));
                if (endYear - shardYearValue < 0) {
                    continue;
                }
                if (endMonth - shardMonthValue < 0) {
                    continue;
                }
            }
            availableCollection.add(target);
        }
        return availableCollection;
    }
}
