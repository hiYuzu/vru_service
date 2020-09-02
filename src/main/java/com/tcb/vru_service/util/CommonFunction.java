package com.tcb.vru_service.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: WangLei
 * @Description: 系统共用方法
 * @Date: Create in 2020/09/02 10:09
 * @Modify by WangLei
 */
public class CommonFunction {

    /**
     * 获取登录用户编码信息
     * @param request
     * @return
     */
    public static String getLoginUserCode(HttpServletRequest request){
        Object subject = request.getAttribute("subject");
        if (subject == null || !(subject instanceof String)) {
            return null;
        }
        String userCode = (String) subject;
        if (StringUtils.isEmpty(userCode)) {
            return null;
        }else{
            return userCode;
        }
    }

}
