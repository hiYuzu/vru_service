package com.tcb.vru_service.controller;

import com.laoxue.token.TokenProcess;
import com.tcb.vru_service.response.ResultVO;
import com.tcb.vru_service.service.IRoleResourceService;
import com.tcb.vru_service.service.IUserService;
import com.tcb.vru_service.util.ShaUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: WangLei
 * @Description: 登录接口
 * @Date: Create in 2020/07/10 10:08
 * @Modify by WangLei
 */
@RestController
public class LoginController {

    @Resource
    private TokenProcess tokenProcess;

    @Resource
    private IUserService IUserService;

    @Resource
    private IRoleResourceService roleResourceService;

    /**
     * 登录
     * @param userCode
     * @param userPassword
     * @return
     * @throws Exception
     */
    @PostMapping(value = "login")
    public ResultVO login(@RequestParam(value = "userName") String userCode,
                          @RequestParam(value = "password") String userPassword) throws Exception {
        if (StringUtils.isEmpty(userCode) || StringUtils.isEmpty(userPassword)) {
            return new ResultVO(false, "login param error !");
        }
        boolean status = IUserService.login(userCode, ShaUtil.shaEncode(userPassword));
        if (status) {
            String token = tokenProcess.generateToken(userCode);
            return new ResultVO(token);
        }
        return new ResultVO(false, "login error !");
    }

    /**
     * 获取用户资源信息
     * @param request
     * @return
     */
    @PostMapping(value = "getUserInfo")
    public ResultVO<Map<String, List<String>>> getUserInfo(HttpServletRequest request) {
        Object subject = request.getAttribute("subject");
        if (subject == null || !(subject instanceof String)) {
            return new ResultVO(false, "do not get userName !");
        }
        String userCode = (String) subject;
        if (StringUtils.isEmpty(userCode)) {
            return new ResultVO(false, "userCode is empty!");
        }
        Map<String, List<String>> route =roleResourceService.getRoleResourceMap(String.valueOf(subject));
        return new ResultVO(route);
    }

    @PostMapping(value = "logout")
    public ResultVO logout() {
        return new ResultVO("OK");
    }
}
