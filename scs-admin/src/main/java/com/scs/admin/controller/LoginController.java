package com.scs.admin.controller;

import com.scs.common.core.domain.AccessToken;
import com.scs.common.core.domain.JsonResult;
import com.scs.common.core.domain.entity.UserInfo;
import com.scs.common.core.domain.request.UserRequest;
import com.scs.common.core.domain.vo.UserVO;
import com.scs.common.exception.BizBaseException;
import com.scs.common.utils.StringUtils;
import com.scs.system.service.ILoginService;
import com.scs.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(value = "登录认证")
@RequestMapping("/authentication")
public class LoginController {

    private final ILoginService loginService;
    private final IUserService sysUserService;

    public LoginController(ILoginService loginService, IUserService sysUserService) {
        this.loginService = loginService;
        this.sysUserService = sysUserService;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/getCode")
    @ApiOperation(value = "获取手机验证码")
    public JsonResult getCode(@RequestParam("phone") String phone) {
        if (StringUtils.isEmpty(phone)) {
            throw new BizBaseException("手机号码不能为空");
        }

        loginService.getCode(phone);
        return JsonResult.success();
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public JsonResult<Boolean> register(@RequestBody @Validated UserVO userVO) {
        /** 增加判重逻辑 */
        if (isRegister(userVO.getPhone()))
            throw new BizBaseException("用户已存在，可直接登录");

        /**
         * 判断验证码是否有效
         */

        String pwd = userVO.getPassword();
        userVO.setPassword(passwordEncoder.encode(pwd));
        boolean result = loginService.saveUserDetail(userVO);
        return JsonResult.success(result);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public JsonResult<AccessToken> login(@RequestBody UserRequest request) {
        if(StringUtils.isEmpty(request.getPhone())) {
            throw new BizBaseException("手机号码不能为空");
        }

        if(StringUtils.isEmpty(request.getCode())) {
            throw new BizBaseException("验证码不能为空");
        }

        if (!isRegister(request.getPhone()))
            throw new BizBaseException("用户不存在，请先注册");

        return JsonResult.success(loginService.login(request));
    }

    @GetMapping("/logout")
    @ApiOperation(value = "用户注销")
    public JsonResult logout() {
        return JsonResult.success(loginService.logout());
    }

    @ApiOperation(value = "根据手机号码验证账号是否已经被注册")
    public Boolean isRegister(String phone) {
        UserInfo user = sysUserService.getOne(phone);
        return null != user;
    }

}
