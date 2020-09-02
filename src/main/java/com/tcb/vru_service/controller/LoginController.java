package com.tcb.vru_service.controller;

import com.laoxue.token.TokenProcess;
import com.tcb.vru_service.response.ResultVO;
import com.tcb.vru_service.service.IRoleService;
import com.tcb.vru_service.service.IUserService;
import com.tcb.vru_service.util.ShaUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    private IUserService userService;

    @Resource
    private IRoleService roleService;

    /**
     * 登录
     * @param userCode
     * @param userPassword
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/vruApi/login")
    public ResultVO login(@RequestParam(value = "userCode") String userCode,
                          @RequestParam(value = "userPassword") String userPassword,
                          @RequestParam(value = "validCode") String validCode,
                          HttpServletRequest request) throws Exception {
        Object object = request.getSession(true).getAttribute("_validCode");
        if (object == null) {
            return new ResultVO(false, "login error !");
        }
        String yzm = object.toString().toLowerCase();
        if (!yzm.equals(validCode.toLowerCase())) {
            return new ResultVO(false, "验证信息错误，请刷新登陆页后尝试!");
        }
        if (StringUtils.isEmpty(userCode) || StringUtils.isEmpty(userPassword)) {
            return new ResultVO(false, "用户名或密码不能为空!");
        }
        boolean status = userService.login(userCode, ShaUtil.shaEncode(userPassword));
        if (status) {
            String token = tokenProcess.generateToken(userCode);
            return new ResultVO(token);
        }
        return new ResultVO(false, "登录失败!");
    }

    /**
     * 获取用户资源信息
     * @param request
     * @return
     */
    @PostMapping(value = "/getUserInfo")
    public ResultVO<Map<String, List<String>>> getUserInfo(HttpServletRequest request) {
        Object subject = request.getAttribute("subject");
        if (subject == null || !(subject instanceof String)) {
            return new ResultVO(false, "do not get userName !");
        }
        String userCode = (String) subject;
        if (StringUtils.isEmpty(userCode)) {
            return new ResultVO(false, "userCode is empty!");
        }
        Map<String, List<String>> route = roleService.getRoleResourceMap(String.valueOf(subject));
        return new ResultVO(route);
    }

    @PostMapping(value = "logout")
    public ResultVO logout() {
        return new ResultVO("OK");
    }

    @RequestMapping("/vruApi/getValidCode")
    public String getValidCode(HttpServletRequest request) {
        String code = ShaUtil.shaEncode(String.valueOf(Math.random()));
        request.getSession(true).setAttribute("_validCode", code);
        return code;
    }

}
