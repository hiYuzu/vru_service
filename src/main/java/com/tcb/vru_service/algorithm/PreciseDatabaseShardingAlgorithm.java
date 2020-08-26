package com.tcb.vru_service.algorithm;

import com.google.common.collect.Range;
import com.tcb.vru_service.util.DateUtil;
import com.tcb.vru_service.util.DefaultParameter;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.ArrayList;
import java.util.Collection;

public class PreciseDatabaseShardingAlgorithm implements PreciseShardingAlgorithm<String>, RangeShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        //对于库的分片collection存放的是所有的库的列表，这里代表dataSource_1~dataSource_2
        //配置的分片的sharding-column对应的值
        String timeValue = preciseShardingValue.getValue();
        if (StringUtils.isBlank(timeValue)) {
            throw new UnsupportedOperationException("preciseShardingValue is null");
        }
        //按年路由
        for (String target : collection) {
            String value;
            if (!DateUtil.isRecentlyData(DateUtil.StringToTimestampSecond(timeValue), DefaultParameter.RECENT_DAYS)) {
                value = "ds3";
            } else {
                value = "ds2";
            }
            if (target.endsWith(value)) {
                //这里返回回去的就是最终需要查询的库名
                return target;
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<String> rangeShardingValue) {
        Collection<String> availableCollection = new ArrayList<>();
        Range valueRange = rangeShardingValue.getValueRange();
        String valueLow = "";
        String valueUpper = "";
        if (valueRange.hasLowerBound()) {
            String lowerStr = valueRange.lowerEndpoint().toString();
            if (!DateUtil.isRecentlyData(DateUtil.StringToTimestampSecond(lowerStr), DefaultParameter.RECENT_DAYS)) {
                valueLow = "ds3";
            } else {
                valueLow = "ds2";
            }
        }
        if (valueRange.hasUpperBound()) {
            String upperStr = valueRange.upperEndpoint().toString();
            if (!DateUtil.isRecentlyData(DateUtil.StringToTimestampSecond(upperStr), DefaultParameter.RECENT_DAYS)) {
                valueUpper = "ds3";
            } else {
                valueUpper = "ds2";
            }
        }
        for (String target : collection) {
            if (target.equals(valueLow) || target.equals(valueUpper)) {
                availableCollection.add(target);
            }
        }
        return availableCollection;
    }
}
