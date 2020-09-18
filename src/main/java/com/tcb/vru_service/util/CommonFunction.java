package com.tcb.vru_service.util;

import com.tcb.vru_service.pojo.BaseDeviceDO;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WangLei
 * @Description: 系统共用方法
 * @Date: Create in 2020/09/02 10:09
 * @Modify by WangLei
 */
public class CommonFunction {

    /**
     * 获取登录用户编码信息
     *
     * @param request
     * @return
     */
    public static String getLoginUserCode(HttpServletRequest request) {
        Object subject = request.getAttribute("subject");
        if (subject == null || !(subject instanceof String)) {
            return null;
        }
        String userCode = (String) subject;
        if (StringUtils.isEmpty(userCode)) {
            return null;
        } else {
            return userCode;
        }
    }

    /**
     * 筛选设备ID
     *
     * @param baseDeviceDOList
     * @return
     */
    public static List<Integer> getDeviceIdList(List<BaseDeviceDO> baseDeviceDOList, Integer fieldId, Long institutionId) {
        List<Integer> deviceIdList = new ArrayList<>();
        if (baseDeviceDOList != null && baseDeviceDOList.size() > 0) {
            for (BaseDeviceDO baseDeviceDO : baseDeviceDOList) {
                if (baseDeviceDO != null && baseDeviceDO.getDeviceId() != null) {
                    if (!deviceIdList.contains(baseDeviceDO.getDeviceId())) {
                        if (fieldId != null && baseDeviceDO.getFieldId().intValue() != fieldId) {
                            continue;
                        } else {
                            if (institutionId != null && baseDeviceDO.getInstitutionId().longValue() != institutionId) {
                                continue;
                            } else {
                                deviceIdList.add(baseDeviceDO.getDeviceId());
                            }
                        }
                    }
                }
            }
        }
        return deviceIdList;
    }

    /**
     * 筛选监测就够ID
     *
     * @param baseDeviceDOList
     * @return
     */
    public static List<Long> getInstitutionIdList(List<BaseDeviceDO> baseDeviceDOList) {
        List<Long> institutionIdList = new ArrayList<>();
        if (baseDeviceDOList != null && baseDeviceDOList.size() > 0) {
            for (BaseDeviceDO baseDeviceDO : baseDeviceDOList) {
                if (baseDeviceDO != null && baseDeviceDO.getInstitutionId() != null) {
                    if (!institutionIdList.contains(baseDeviceDO.getInstitutionId())) {
                        institutionIdList.add(baseDeviceDO.getInstitutionId());
                    }
                }
            }
        }
        return institutionIdList;
    }

}
